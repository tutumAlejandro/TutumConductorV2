package com.example.tutumconductorv2.Registro.documentos_conductor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

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
    private ImageButton ine_reverso, ine_frontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_ine);
        if (ContextCompat.checkSelfPermission(MainCapturaIne.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainCapturaIne.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainCapturaIne.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        rol = getIntent().getStringExtra("rol");

        ine_frontal = findViewById(R.id.btn_frontal_ine);

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
    }


    public void guardar_ine (View V)
    {
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
    }

    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Backup_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;
    public void tomarFoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
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
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ine_frontal.setImageBitmap(imageBitmap);
        }
    }


}