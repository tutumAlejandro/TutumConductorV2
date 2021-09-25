package com.example.tutumconductorv2.RegistroConductor.CapturaDocumentos;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
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
import com.example.tutumconductorv2.RegistroConductor.SeleccionRol.MainConductorDocumentos;
import com.example.tutumconductorv2.RegistroConductor.SeleccionRol.MainSnvDocuemtos;
import com.example.tutumconductorv2.RegistroConductor.SeleccionRol.MainSocioDocumentos;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainCapturaLicencia extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_regreso_licencia;
    private EditText vigenciaLicencia;
    private ImageView licencia_frente, licencia_reverso;

    private int year, month, day;
    private String rol;
    private String vigencia=" ";
    private boolean check_licencia_frente = false;
    private boolean check_licencia_reverso = false;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PICK_IMAGE=100;

    int codigoBoton = 0;
    int quality_image=30;

    private RequestQueue queue;

    private String image_code1="";
    private String image_code2="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_licencia);

        vigenciaLicencia = findViewById(R.id.VigenciaLicencia);
        btn_regreso_licencia = findViewById(R.id.img_retroceso_licencia);
        licencia_frente = findViewById(R.id.btn_frontal_licencia);
        licencia_reverso = findViewById(R.id.btn_reverso_licencia);

        SharedPreferences preferencias_licencia = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        rol = preferencias_licencia.getString("rol","");


        if (ContextCompat.checkSelfPermission(MainCapturaLicencia.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainCapturaLicencia.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainCapturaLicencia.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        vigenciaLicencia.setOnClickListener(this);
        btn_regreso_licencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferencias_licencia = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
                SharedPreferences.Editor obj_editor = preferencias_licencia.edit();
                int state = preferencias_licencia.getInt("State",0);
                if(state == 7)
                {
                    if (rol.matches("Socio")) {
                        Intent main_socio_documentos = new Intent(MainCapturaLicencia.this, MainSocioDocumentos.class);
                        obj_editor.putString("licencia1","2");
                        obj_editor.commit();
                        startActivity(main_socio_documentos);
                        finish();
                    } else if (rol.matches("Conductor")) {
                        Intent main_conductor_documentos = new Intent(MainCapturaLicencia.this, MainConductorDocumentos.class);
                        obj_editor.putString("licencia2","2");
                        obj_editor.commit();
                        startActivity(main_conductor_documentos);
                        finish();
                    } else {
                        Intent main_snv_documentos = new Intent(MainCapturaLicencia.this, MainSnvDocuemtos.class);
                        obj_editor.putString("licencia3","2");
                        obj_editor.commit();
                        startActivity(main_snv_documentos);
                        finish();
                    }
                }else{
                    if (rol.matches("Socio")) {
                        Intent main_socio_documentos = new Intent(MainCapturaLicencia.this, MainSocioDocumentos.class);
                        obj_editor.putString("licencia1","0");
                        obj_editor.commit();
                        startActivity(main_socio_documentos);
                        finish();
                    } else if (rol.matches("Conductor")) {
                        Intent main_conductor_documentos = new Intent(MainCapturaLicencia.this, MainConductorDocumentos.class);
                        obj_editor.putString("licencia2","0");
                        obj_editor.commit();
                        startActivity(main_conductor_documentos);
                        finish();
                    } else {
                        Intent main_snv_documentos = new Intent(MainCapturaLicencia.this, MainSnvDocuemtos.class);
                        obj_editor.putString("licencia3","0");
                        obj_editor.commit();
                        startActivity(main_snv_documentos);
                        finish();
                    }
                }

            }
        });

        licencia_frente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder opcion = new AlertDialog.Builder(MainCapturaLicencia.this);
                opcion.setPositiveButton("Camara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        codigoBoton = 1;
                        check_licencia_frente = true;
                        tomarFoto(v,"licencia_frente");
                    }
                });
                opcion.setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      cargarImagen();
                      codigoBoton = 1;
                      check_licencia_frente = true;
                    }
                });
                AlertDialog dialog = opcion.create();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.BOTTOM;
                dialog.show();
            }
        });

        licencia_reverso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder opcion = new AlertDialog.Builder(MainCapturaLicencia.this);
                opcion.setPositiveButton("Camara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        codigoBoton = 2;
                        check_licencia_reverso = true;
                        tomarFoto(v,"licencia_reverso");
                    }
                });
                opcion.setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        codigoBoton = 2;
                        check_licencia_reverso = true;
                        cargarImagen();
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

    @Override
    public void onClick(View v) {
        final Calendar calendario = Calendar.getInstance();
        year = calendario.get(Calendar.YEAR);
        month = calendario.get(Calendar.MONTH);
        day = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                vigenciaLicencia.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                vigencia= year+"-"+(month+1)+"-"+dayOfMonth;
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private boolean check_vigencia_licencia(String vigencia) {
        if (vigencia.isEmpty()) {
            vigenciaLicencia.setTextColor(ColorStateList.valueOf(Color.RED));
            vigenciaLicencia.setError("Campo Requerido");
            return false;
        } else {
            return true;
        }
    }


    public void guarda_licencia(View v) {
        SharedPreferences preferencias_licencia = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias_licencia.edit();
        String vig = vigenciaLicencia.getText().toString().trim();
        if (!check_vigencia_licencia(vig) | !check_licencia_frente | !check_licencia_reverso) {
            androidx.appcompat.app.AlertDialog.Builder registro_exitoso = new androidx.appcompat.app.AlertDialog.Builder(MainCapturaLicencia.this);
            registro_exitoso.setTitle(Html.fromHtml("<font color='#FF0404'> <b> licencia de conducir</b> </font>"));
            registro_exitoso.setMessage(Html.fromHtml("<font color='#FF0404'> <b>  Favor de subir de manera correcta su licencia vigente. </b> </font>"));
            registro_exitoso.show();
            return;
        } else {
            if (rol.matches("Socio")) {
                realizarPost();
                Intent main_socio_documentos = new Intent(MainCapturaLicencia.this, MainSocioDocumentos.class);
                obj_editor.putString("licencia1","1");
                obj_editor.commit();
                startActivity(main_socio_documentos);
                finish();
            } else if (rol.matches("Conductor")) {
                realizarPost();
                Intent main_conductor_documentos = new Intent(MainCapturaLicencia.this, MainConductorDocumentos.class);
                obj_editor.putString("licencia2","1");
                obj_editor.commit();
                startActivity(main_conductor_documentos);
                finish();
            } else {
                realizarPost();
                Intent main_snv_documentos = new Intent(MainCapturaLicencia.this, MainSnvDocuemtos.class);
                obj_editor.putString("licencia3","1");
                obj_editor.commit();
                startActivity(main_snv_documentos);
                finish();
            }
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
            MediaScannerConnection.scanFile(MainCapturaLicencia.this, new String[]{mCurrentPhotoPath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) { }
            });
            // Se debe de redimensionar la imagen  antes de cargarla en los ImageButton(Se usa ImageButton para no consumir tantos recursos)
            int targetW = licencia_frente.getWidth();
            int targetH = licencia_frente.getHeight();

            //Obtener las dimensiones del Bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;

            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            //Determinar el factor de escalamiento de la imagenes bitmap
            int scaleFactor = Math.min((photoW/targetW),(photoH/targetH));

            //Decodificar  el archivo de la imagen dentro del tamaño del Bitmap para llenar la vista
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap,targetW,targetH,false);
            if(codigoBoton==1){
                licencia_frente.setImageBitmap(bitmap2);
                licencia_frente.setBackgroundColor(0x00000000);
                ByteArrayOutputStream array = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
                byte[] imageByte = array.toByteArray();
                image_code1 = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);
            }else{
                licencia_reverso.setImageBitmap(bitmap2);
                licencia_reverso.setBackgroundColor(0x00000000);
                ByteArrayOutputStream array = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
                byte[] imageByte = array.toByteArray();
                image_code2 = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);
            }
        }
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            Uri path = data.getData();

            int targetW = licencia_frente.getWidth();
            int targetH = licencia_frente.getHeight();

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
            if(codigoBoton==1){
                licencia_frente.setImageBitmap(bitmap2);
                licencia_frente.setBackgroundColor(0x00000000);
                ByteArrayOutputStream array = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
                byte[] imageByte = array.toByteArray();
                image_code1 = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);
            }else{
                licencia_reverso.setImageBitmap(bitmap2);
                licencia_reverso.setBackgroundColor(0x00000000);
                ByteArrayOutputStream array = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
                byte[] imageByte = array.toByteArray();
                image_code2 = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);
            }
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
        String url = "https://tutumapps.com/api/driver/uploadRegistriLicense";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);
            jsonObject.put("img_front",image_code1);
            jsonObject.put("img_back",image_code2);
            jsonObject.put("driver_license_expiry",vigencia);

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

    public void cargarImagen(){
        Intent galeria = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galeria.setType("image/*");
        startActivityForResult(galeria.createChooser(galeria,"Seleccione la aplicacion"),PICK_IMAGE);
    }


}