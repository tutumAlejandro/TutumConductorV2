package com.example.tutumconductorv2.Registro.documentos_conductor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSnvDocuemtos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class MainCapturaIne extends AppCompatActivity {

    private ImageView btn_retroceso_ine;
    private String rol;
    //private ImageButton ine_reverso;
    private ImageView ine_reverso,ine_frontal;
    //private ImageButton ine_frontal;
    private boolean check_ine_reverso=false;
    private boolean check_ine_frontal=false;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    int codigoBoton = 0;
    int factor = 1;
    int quality_image=30;

    private RequestQueue queue;

    private String image_code1="";
    private String image_code2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_ine);
        if (ContextCompat.checkSelfPermission(MainCapturaIne.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainCapturaIne.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainCapturaIne.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }
        queue = Volley.newRequestQueue(this);

        // Bandera para saber el rol de la solicitud
        rol = getIntent().getStringExtra("rol");

        //Asociacion de la parte grafica con la parte logica
        ine_frontal = findViewById(R.id.btn_frontal_ine);
        ine_reverso = findViewById(R.id.btn_reverso_ine);
        btn_retroceso_ine = findViewById(R.id.img_retroceso_ine);

        //Declaración de las funciones al hacer clic en la imagen de retroceso y las imagenes frontal y trasera de la INE/IFE
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
                tomarFoto(v,"ine_frente");
                check_ine_frontal=true;
            }
        });
        ine_reverso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codigoBoton=2;
                tomarFoto(v,"ine_frente");
                check_ine_reverso=true;
            }
        });

    }



    public void guardar_ine (View V)
    {
        if(check_ine_frontal && check_ine_reverso){
            if(rol.matches("Socio")){
                realizarPost();
                Intent main_socio_documentos = new Intent(MainCapturaIne.this, MainSocioDocumentos.class);
                cadenas_documentos.check_ine1=true;
                startActivity(main_socio_documentos);
                finish();
            }else if(rol.matches("Conductor")){
                realizarPost();
                Intent main_conductor_documentos = new Intent(MainCapturaIne.this, MainConductorDocumentos.class);
                cadenas_documentos.check_ine2=true;
                startActivity(main_conductor_documentos);
                finish();
            }else {
                realizarPost();
                Intent main_snv_documentos = new Intent(MainCapturaIne.this, MainSnvDocuemtos.class);
                cadenas_documentos.check_ine3=true;
                startActivity(main_snv_documentos);
                finish();
            }
        }else{
            return;
        }
    }

    /** Pasos para crear abrir la camara del telefono, capturar una imagen y mostrar su previsualización
     *
     * 1.- Primero debemos añadir los permisos de uso de la camara y almacenamiento externo de la aplicación
     *     ver en el AndroidManifest.xml el apartado 2
     * 2.- Segundo se debe añadir los paths(rutas de almacenamiento de las imagenes) ver apartado 3 y crear
     *     el archivo file_paths.xml (puede tener el nombre que quieras pero debe ser igual al que se
     *     definio en el manifest apartado 3)
     */


    String mCurrentPhotoPath; //path donde se va a almacenar las imagenes
    private File createImageFile(String nombreFoto) throws IOException {
        // Funcion para crear un archivo del tipo imagen y asignación de un nombre anti-colisiones(evita que tengan el mismo nombre repetido)
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = nombreFoto + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // En la funcion onActivityResult es donde se activa la camara y se almacena la imagen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Primero se pregunta se el resultado fue correcto y si hay una solicitud de captura de imagen
        if(resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE){
            MediaScannerConnection.scanFile(MainCapturaIne.this, new String[]{mCurrentPhotoPath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) { }
            });
        }
        // Se debe de redimensionar la imagen  antes de cargarla en los ImageButton(Se usa ImageButton para no consumir tantos recursos)
        int targetW = ine_frontal.getWidth();
        int targetH = ine_frontal.getHeight();

        //Obtener las dimensiones del Bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        //Determinar el factor de escalamiento de la imagenes bitmap
        int scaleFactor = Math.min((photoW/(targetW*factor)),(photoH/(targetH*factor)));

        //Decodificar  el archivo de la imagen dentro del tamaño del Bitmap para llenar la vista
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        if(codigoBoton==1){
            ine_frontal.setImageBitmap(bitmap);
            ine_frontal.setBackgroundColor(0x00000000);
            ByteArrayOutputStream array = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
            byte[] imageByte = array.toByteArray();
            image_code1 = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);
        }else{
            ine_reverso.setImageBitmap(bitmap);
            ine_reverso.setBackgroundColor(0x00000000);
            ByteArrayOutputStream array = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
            byte[] imageByte = array.toByteArray();
            image_code2 = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);
        }

    }
    public void tomarFoto(View view,String nomFoto){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createImageFile(nomFoto);
            }catch (IOException e){

            }
            if(photoFile != null){
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePictureIntent,REQUEST_TAKE_PHOTO);
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                File f = new File(mCurrentPhotoPath);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);
            }
        }
    }

    public void realizarPost() {
        String tel="";
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        tel=preferences.getString("phone","");
        String url = "https://tutumapps.com/api/driver/uploadRegistryINE";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);
            jsonObject.put("img_front",image_code1);
            jsonObject.put("img_back",image_code2);
            Log.d("valor imagen ","Valor"+image_code1);
            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!!!! "+response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("My Tag","Error"+error);
                }
            });
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}