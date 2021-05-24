package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
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

public class MainCapturaTarjetaCirculacion extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_regreso_tarjeta_circulacion;
    private ImageButton btn_tarjeta;
    private EditText vigencia_tarjeta;
    private String rol;
    private int day,month,year;

    private String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private boolean check_tarjeta = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_tarjeta_circulacion);
        btn_regreso_tarjeta_circulacion = findViewById(R.id.img_retroceso_tarjeta);
        vigencia_tarjeta = findViewById(R.id.VigenciaTarjeta);

        btn_tarjeta = findViewById(R.id.btn_img_tarjeta);

        vigencia_tarjeta.setOnClickListener(this);

        rol = getIntent().getStringExtra("rol");


        btn_regreso_tarjeta_circulacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainSocioDocumentos.class);
                    cadenas_documentos.check_tarjeta1=false;
                    startActivity(main_socio_documentos);
                    finish();
                } else {
                    Intent main_conductor_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainConductorDocumentos.class);
                    cadenas_documentos.check_tarjeta2=false;
                    startActivity(main_conductor_documentos);
                    finish();
                }
            }
        });

        btn_tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto(v,"tarjeta_circulacion");
                check_tarjeta = true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        final Calendar calendario = Calendar.getInstance();
        year = calendario.get(Calendar.YEAR);
        month = calendario.get(Calendar.MONTH);
        day = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                vigencia_tarjeta.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    private boolean check_vigencia_tarjeta(String vigencia)
    {
        if (vigencia.isEmpty())
        {
            return false;
        }
        else{
            return true;
        }
    }

    public void guarda_tarjeta(View v)
    {
        String vig = vigencia_tarjeta.getText().toString().trim();
        if(!check_vigencia_tarjeta(vig) | !check_tarjeta)
        {
            return;
        }else {
            if(rol.matches("Socio"))
            {
                Intent main_socio_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainSocioDocumentos.class);
                cadenas_documentos.vigTarjeta=vig;
                cadenas_documentos.check_tarjeta1=true;
                startActivity(main_socio_documentos);
                finish();
            }else
            {
                Intent main_conductor_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainConductorDocumentos.class);
                cadenas_documentos.vigTarjeta=vig;
                cadenas_documentos.check_tarjeta2=true;
                startActivity(main_conductor_documentos);
                finish();
            }
        }
    }
    private File createImageFile(String nombreFoto) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = nombreFoto + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

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
            }
        }
    }
}