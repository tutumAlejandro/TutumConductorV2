package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainCapturaPoliza extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_regreso_poliza;
    private ImageButton btn_poliza;
    private EditText vigenciaPoliza;
    private int year, month, day;
    private String rol;

    private boolean check_poliza= false;

    int SELEC_IMAGEN = 200;
    int codigoBoton = 0;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    int factor = 32;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_poliza);

        rol = getIntent().getStringExtra("rol");

        vigenciaPoliza = findViewById(R.id.VigenciaPoliza);
        btn_regreso_poliza = findViewById(R.id.img_retroceso_poliza);
        btn_poliza = findViewById(R.id.btn_img_poliza);

        vigenciaPoliza.setOnClickListener(this);

        btn_regreso_poliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaPoliza.this, MainSocioDocumentos.class);
                    cadenas_documentos.check_poliza1=false;
                    startActivity(main_socio_documentos);
                    finish();
                } else {
                    Intent main_conductor_documentos = new Intent(MainCapturaPoliza.this, MainConductorDocumentos.class);
                    cadenas_documentos.check_poliza2=false;
                    startActivity(main_conductor_documentos);
                    finish();
                }
            }
        });

        btn_poliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto(v,"poliza");
                check_poliza = true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        final Calendar calendario = Calendar.getInstance();
        year = calendario.get(Calendar.YEAR);
        month = calendario.get(Calendar.MONTH);
        day = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                vigenciaPoliza.setText(dayOfMonth + "/" + (month +1) + "/" + year);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    private boolean check_vigencia_poliza(String vigencia)
    {
        if (vigencia.isEmpty())
        {
            return false;
        }
        else{
            return true;
        }
    }

    public void guarda_poliza(View v)
    {
        String vig = vigenciaPoliza.getText().toString().trim();
        if(!check_vigencia_poliza(vig) | !check_poliza)
        {
            return;
        }else {
            if(rol.matches("Socio"))
            {
                Intent main_socio_documentos = new Intent(MainCapturaPoliza.this, MainSocioDocumentos.class);
                cadenas_documentos.vigPoliza=vig;
                cadenas_documentos.check_poliza1=true;
                startActivity(main_socio_documentos);
                finish();
            }else if(rol.matches("Conductor"))
            {
                Intent main_conductor_documentos = new Intent(MainCapturaPoliza.this, MainConductorDocumentos.class);
                cadenas_documentos.vigPoliza=vig;
                cadenas_documentos.check_poliza2=true;
                startActivity(main_conductor_documentos);
                finish();
            }
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile(String nombreFoto) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = nombreFoto + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpeg", storageDir);

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    public void tomarFoto(View view, String nomFoto) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(nomFoto);
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                galleryAddPic();

            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void setPic(ImageView boton) {
        // Get the dimensions of the View
        int targetW = boton.getWidth();
        int targetH = boton.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/(targetW*64), photoH/(targetH*64));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        boton.setImageBitmap(bitmap);
    }

    public void seleccionarImagen() {
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, SELEC_IMAGEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            MediaScannerConnection.scanFile(MainCapturaPoliza.this, new String[]{mCurrentPhotoPath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) {

                }
            });

            int targetW = btn_poliza.getWidth();
            int targetH = btn_poliza.getHeight();

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;

            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW/(targetW*factor), photoH/(targetH*factor));

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            btn_poliza.setImageBitmap(bitmap);
        }
    }
}