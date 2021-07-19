package com.example.tutumconductorv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.Registro.datos_personales.MainOTP;
import com.example.tutumconductorv2.Registro.datos_personales.MainRegistroTelefono;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaIne;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaPoliza;
import com.example.tutumconductorv2.adapters.CardViewMensajes;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RespuestaReporteUsuario extends AppCompatActivity {

    private TextInputLayout input_descripcion;
    private Button responder_button,back_button_response;
    private ImageView captura_respuesta;


    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PICK_IMAGE=100;
    int quality_image=40;
    private String image_code="";


    private String descripcion="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta_reporte_usuario);

        input_descripcion = findViewById(R.id.input_body_response);
        responder_button = findViewById(R.id.btn_aceptar_reporte_tecnico);
        back_button_response = findViewById(R.id.editReporte_backbutton2);
        captura_respuesta = findViewById(R.id.img_error_tecnico);


        back_button_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_otp = new Intent(RespuestaReporteUsuario.this, ChatReportes.class);
                startActivity(main_otp);
                finish();
            }
        });

        captura_respuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder opcion = new android.app.AlertDialog.Builder(RespuestaReporteUsuario.this);
                opcion.setPositiveButton("Camara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tomarFoto(v,"Captura_Repuesta");
                    }
                });
                opcion.setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cargarImagen();
                    }
                });
                android.app.AlertDialog dialog = opcion.create();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.BOTTOM;
                dialog.show();
            }
        });
        responder_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
                if(check_descripcion(input_descripcion.getEditText().getText().toString())){
                    descripcion=input_descripcion.getEditText().getText().toString();
                    getSendResponse(preferences.getString("api_token",""),preferences.getInt("id_report",0),descripcion,image_code);
                }else {
                    return;
                }
            }
        });
    }

    private boolean check_descripcion(String desc){
        if(desc.isEmpty()){
            input_descripcion.setErrorEnabled(true);
            input_descripcion.setError("Campo Requerido");
            return false;
        }
        else {
            input_descripcion.setErrorEnabled(false);
            return  true;
        }
    }
    private void getSendResponse(String api_token,int id_report,String response,String captura){
        String url = "https://www.tutumapps.com/api/driver/responseReport";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            Log.e("respuesta carajo",response);

            //jsonObject.put("email",email);
            jsonObject.put("report_id",id_report);
            jsonObject.put("response",response);
            jsonObject.put("captura",captura);
            jsonObject.put("api_token",api_token);
            final String requestBody = jsonObject.toString();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Char Reporte","RESPUESTA: "+response);
                    try {

                        AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(RespuestaReporteUsuario.this);
                        registro_exitoso.setTitle(Html.fromHtml("<font color='#088d30'> <b> Respuesta Reporte </b> </font>"));
                        registro_exitoso.setMessage(Html.fromHtml("<font color='#088d30'> <b>"+response.getString("msg")+"</b> </font>"));
                        registro_exitoso.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent main_otp = new Intent(RespuestaReporteUsuario.this, ChatReportes.class);
                                startActivity(main_otp);
                                finish();

                            }
                        },1000);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("My Tag","Error"+error);
                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    return params;}
            };

            requestQueue.add(request);
        }catch (JSONException e){
            e.printStackTrace();
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
        if(resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE ){
            MediaScannerConnection.scanFile(RespuestaReporteUsuario.this, new String[]{mCurrentPhotoPath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) { }
            });

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            final int heigth = bmOptions.outHeight;
            final int width = bmOptions.outWidth;
            int targetW = captura_respuesta.getWidth();
            int targetH = captura_respuesta.getHeight();
            int scaleFactor = 1;
            if (heigth > targetH || width > targetW) {
                int halfHeigth = heigth/2;
                int halfWidth = width / 2;
                while ((halfHeigth/scaleFactor)>=targetH && (halfWidth/scaleFactor)>= targetW){
                    scaleFactor *=2;
                }
            }
            //Decodificar  el archivo de la imagen dentro del tamaño del Bitmap para llenar la vista
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap,targetW,targetH,false);
            captura_respuesta.setImageBitmap(bitmap2);
            captura_respuesta.setBackgroundColor(0x00000000);
            ByteArrayOutputStream array = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
            byte[] imageByte = array.toByteArray();
            image_code = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);
        }
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE ){
            Uri path = data.getData();

            int targetW = captura_respuesta.getWidth();
            int targetH = captura_respuesta.getHeight();

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;

            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            int scaleFactor = Math.min((photoW/targetW),(photoH/targetH));

            //Decodificar  el archivo de la imagen dentro del tamaño del Bitmap para llenar la vista
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;
            Bitmap bitmap = null;
            try {

                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),path);

            } catch (IOException e) {
                e.printStackTrace();
            }

            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap,targetW,targetH,false);
            captura_respuesta.setImageBitmap(bitmap2);
            captura_respuesta.setBackgroundColor(0x00000000);
            ByteArrayOutputStream array = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
            byte[] imageByte = array.toByteArray();
            image_code = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);
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
    public void cargarImagen(){
        Intent galeria = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galeria.setType("image/*");
        startActivityForResult(galeria.createChooser(galeria,"Seleccione la aplicación"),PICK_IMAGE);
    }
}