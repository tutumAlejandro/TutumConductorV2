package com.example.tutumconductorv2.Registro.documentos_conductor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSnvDocuemtos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainCapturaIne extends AppCompatActivity {

    private ImageView btn_retroceso_ine;
    private String rol;
    //private ImageButton ine_reverso;
    private ImageButton ine_reverso,ine_frontal;
    private boolean check_ine_reverso=false;
    private boolean check_ine_frontal=false;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    int SELEC_IMAGEN = 200;
    int codigoBoton = 0;
    int factor = 32;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_ine);
        if (ContextCompat.checkSelfPermission(MainCapturaIne.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainCapturaIne.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainCapturaIne.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        rol = getIntent().getStringExtra("rol");

        ine_frontal = findViewById(R.id.btn_frontal_ine);
        ine_reverso = findViewById(R.id.btn_reverso_ine);

        btn_retroceso_ine = findViewById(R.id.img_retroceso_ine);

        btn_retroceso_ine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rol.matches("Socio")){
                    Intent main_socio_documentos = new Intent(MainCapturaIne.this, MainSocioDocumentos.class);
                    cadenas_documentos.check_ine1=false;
                    startActivity(main_socio_documentos);
                    finish();
                }else if(rol.matches("Conductor")){
                    Intent main_conductor_documentos = new Intent(MainCapturaIne.this, MainConductorDocumentos.class);
                    cadenas_documentos.check_ine2=false;
                    startActivity(main_conductor_documentos);
                    finish();
                }else{
                    Intent main_snv_documentos = new Intent(MainCapturaIne.this, MainSnvDocuemtos.class);
                    cadenas_documentos.check_ine3=false;
                    startActivity(main_snv_documentos);
                    finish();
                }
            }
        });

        ine_frontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codigoBoton=1;
                tomarFoto(v,"ine_frontal");
                //setPic(ine_frontal);
                check_ine_frontal=true;
            }
        });
        ine_reverso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codigoBoton=2;
                tomarFoto(v,"ine_reverso");
                //setPic(ine_frontal);
                check_ine_reverso=true;
            }
        });

    }



    public void guardar_ine (View V)
    {
        if(check_ine_frontal && check_ine_reverso){
            if(rol.matches("Socio")){
                Intent main_socio_documentos = new Intent(MainCapturaIne.this, MainSocioDocumentos.class);
                cadenas_documentos.check_ine1=true;
                startActivity(main_socio_documentos);
                finish();
            }else if(rol.matches("Conductor")){
                Intent main_conductor_documentos = new Intent(MainCapturaIne.this, MainConductorDocumentos.class);
                cadenas_documentos.check_ine2=true;
                startActivity(main_conductor_documentos);
                finish();
            }else
            {
                Intent main_snv_documentos = new Intent(MainCapturaIne.this, MainSnvDocuemtos.class);
                cadenas_documentos.check_ine3=true;
                startActivity(main_snv_documentos);
                finish();
            }
        }else{
            return;
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


    public void tomarFoto(View view,String nomFoto) {
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
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider", photoFile);
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
        int targetW = ine_frontal.getWidth();
        int targetH = ine_frontal.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        boton.setImageBitmap(bitmap);
    }

    public void seleccionarImagen(){
        Intent galeria = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria,SELEC_IMAGEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            MediaScannerConnection.scanFile(MainCapturaIne.this, new String[]{mCurrentPhotoPath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) {

                }
            });

            //Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
            // Get the dimensions of the View
            int targetW = ine_frontal.getWidth();
            int targetH = ine_frontal.getHeight();

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
            if (codigoBoton == 1) {
                ine_frontal.setImageBitmap(bitmap);
                ine_frontal.setBackgroundColor(0x00000000);
            } else {
                ine_reverso.setImageBitmap(bitmap);
                ine_frontal.setBackgroundColor(0x00000000);
            }
        }
    }
}