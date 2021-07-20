package com.example.tutumconductorv2.Actividad_Principal.Inicio_Sesion.Reportes.Reporte_Tecnico;

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
import com.example.tutumconductorv2.Inicio;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.ReportarProblemaTecnico;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class activity_problema_tecnico extends AppCompatActivity {

    private TextInputLayout title_report,body_report;
    private Button btn_acept,btn_back_report;
    private ImageView img_error;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private boolean check_image= false;

    private String image_code1;
    private int quality_image=30;
    static final int PICK_IMAGE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema_tecnico);

        title_report = findViewById(R.id.input_title_report);
        body_report = findViewById(R.id.input_body_report);
        btn_acept = findViewById(R.id.btn_aceptar_reporte_tecnico);
        btn_back_report = findViewById(R.id.editReporte_backbutton);
        img_error = findViewById(R.id.img_error_tecnico);

        btn_acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check_title(title_report.getEditText().getText().toString()) | !check_body(body_report.getEditText().getText().toString())){
                   return;
                }else {
                    SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
                    sendReportTechical(title_report.getEditText().getText().toString(), body_report.getEditText().getText().toString(),preferences.getString("api_token",""));
                }
            }
        });
        btn_back_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_reportes = new Intent(activity_problema_tecnico.this, ReportarProblemaTecnico.class);
                startActivity(main_reportes);
                finish();
            }
        });
        img_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder opcion = new AlertDialog.Builder(activity_problema_tecnico.this);
                opcion.setPositiveButton("Camara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                              tomarFoto(v,"img_error");
                              check_image = true;
                    }

                });

                opcion.setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cargarImagen();
                        check_image= true;
                    }
                });
                AlertDialog dialog = opcion.create();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.BOTTOM;
                dialog.show();
            }
        });

    }
    private boolean check_title(String title){
        if(title.isEmpty()){
            title_report.setErrorEnabled(true);
            title_report.setError("Campo Requerido");
            return false;
        }else {
            title_report.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_body(String body){
        if(body.isEmpty()){
            body_report.setErrorEnabled(true);
            body_report.setError("Campo Requerido");
            return false;
        }else {
            body_report.setErrorEnabled(false);
            return true;
        }
    }

    private void sendReportTechical(String title, String body,String api_token){
        String url = "https://www.tutumapps.com/api/driver/newReport";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("email",email);
            jsonObject.put("title", title);
            jsonObject.put("type","tecnico");
            jsonObject.put("description",body);
            jsonObject.put("captura",image_code1);
            jsonObject.put("api_token",api_token);
            final String requestBody = jsonObject.toString();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //test_check.setText(response.getString("success"));
                        if(response.getString("success").matches("true"))
                        {
                            Log.e("Respuesta Registro","Exito!!!: "+response);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent main_inicio = new Intent(activity_problema_tecnico.this, Inicio.class);
                                    startActivity(main_inicio);
                                    finish();
                                }
                            },1000);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(activity_problema_tecnico.this,R.style.DialogTheme);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#088d30'> <b> Reporte Tecnico </b> </font>"));
                            registro_exitoso.setMessage(Html.fromHtml("<font color='#088d30'> <b>Hemos Recibido tu reporte, dentro de poco nos responderemos tu reporte</b> </font>"));
                            registro_exitoso.show();
                        }else
                        {
                            Log.e("Respuesta Registro","ERROR!!!: "+response);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            },1000);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(activity_problema_tecnico.this);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#FF0000'> <b>Correo Electrónico </b></font>"));
                            registro_exitoso.setMessage(Html.fromHtml("<font color='#FF0000'> <b>"+response.getString("msg")+"</b> </font>"));
                            registro_exitoso.show();
                        }
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
            MediaScannerConnection.scanFile(activity_problema_tecnico.this, new String[]{mCurrentPhotoPath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) { }
            });
            // Se debe de redimensionar la imagen  antes de cargarla en los ImageButton(Se usa ImageButton para no consumir tantos recursos)
            int targetW=img_error.getWidth();
            int targetH = img_error.getHeight();
            //Obtener las dimensiones del Bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;

            //Determinar el factor de escalamiento de la imagenes bitmap
            int scaleFactor = 2;

            //Decodificar  el archivo de la imagen dentro del tamaño del Bitmap para llenar la vista
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap,targetW,targetH,false);
            img_error.setImageBitmap(bitmap2);
            img_error.setBackgroundColor(0x00000000);
            ByteArrayOutputStream array = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
            byte[] imageByte = array.toByteArray();
            image_code1 = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);

        }else if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            Uri path = data.getData();
            int targetW=img_error.getWidth();
            int targetH = img_error.getHeight();

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;


            //Determinar el factor de escalamiento de la imagenes bitmap
            int scaleFactor =2;

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
            img_error.setImageBitmap(bitmap2);
            img_error.setBackgroundColor(0x00000000);
            ByteArrayOutputStream array = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
            byte[] imageByte = array.toByteArray();
            image_code1 = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);

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
        startActivityForResult(galeria.createChooser(galeria,"Seleccione la aplicacion"),PICK_IMAGE);
    }

}