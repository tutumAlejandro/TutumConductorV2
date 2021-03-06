 package com.example.tutumconductorv2.RegistroConductor.DatosPersonales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hbb20.CountryCodePicker;

import org.json.JSONException;
import org.json.JSONObject;

public class MainRegistroTelefono extends AppCompatActivity {

    private TextInputLayout telefono;
    private String url_registro="https://www.tutumapps.com/api/driver/registryDriver";
   // private String url_timeline="https://www.tutumapps.com/api/passenger/newJourney";
    private String name,phone,email,token,password;
    CountryCodePicker ccp;
    boolean ccp1;
    boolean ok=false;

    private boolean isSucess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_telefono);
        telefono = findViewById(R.id.InputTelefono);
        ccp = findViewById(R.id.ccp);

        SharedPreferences preferences_user = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        int state= preferences_user.getInt("State",0);
        name = preferences_user.getString("name","");
        email = preferences_user.getString("email","");
        password = preferences_user.getString("password","");
        get_FCM();

    }

    private boolean check_telefono(String num_telefono) {
        if (num_telefono.isEmpty()) {
            telefono.setErrorEnabled(true);
            telefono.setError("Campo Requerido");
            telefono.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        } else if (num_telefono.length() <=9 ) {
            telefono.setErrorEnabled(true);
            telefono.setError("Formato Invalido");
            telefono.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        }else if (num_telefono.length() >= 11){
            telefono.setErrorEnabled(true);
            telefono.setError("Formato Invalido");
            telefono.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        }
        else {
            telefono.setErrorEnabled(false);
            return true;
        }
    }

    public void main_otp(View v) {

        phone=telefono.getEditText().getText().toString().trim();
        Log.e("Telefono","telefono:" + phone);
        ccp1=ccp.getCcpDialogShowNameCode();
        ccp1 = ccp.getCcpDialogShowFlag();
        SharedPreferences preferences_user = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences_user.edit();
        obj_editor.putString("phone",phone);
        obj_editor.putInt("State",3);
        obj_editor.commit();

        if(!check_telefono(phone)){
            return;
        }else {
            if(preferences_user.getBoolean("edit_phone",false)){
                editPhoneNumber();
                Intent main_otp = new Intent(MainRegistroTelefono.this, MainOTP.class);
                startActivity(main_otp);
                finish();
            }
            else {
                realizarPost(url_registro);


            }
        }
    }

    public void realizarPost(String url)  {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("email",email);
            jsonObject.put("phone",phone);
            jsonObject.put("name",name);
            jsonObject.put("password",password);
            jsonObject.put("status",3);
            jsonObject.put("fcm_token",token);

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getString("success").matches("true"))
                        {
                            Log.d("Respuesta Registro","Exito!!!: "+response);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(MainRegistroTelefono.this);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#088d30'> <b> Registro Telefono </b> </font>"));
                            registro_exitoso.setMessage(Html.fromHtml("<font color='#088d30'> <b>N??mero de tel??fono y correo electr??nico agregados correctamente</b> </font>"));
                            registro_exitoso.show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder pagina_loading = new AlertDialog.Builder(MainRegistroTelefono.this);
                                    pagina_loading.setTitle(Html.fromHtml("<font color='#FF0000'> <b>Registro Telefono </b></font>"));
                                    pagina_loading.setMessage(Html.fromHtml("<font color='#FF0000'> <b>Lo sentimos, este n??mero de tel??fono y/o correo electr??nico ya est?? siendo utilizado, intenta con otro</b> </font>"));
                                    pagina_loading.show();
                                    SharedPreferences preferences_user = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor obj_editor = preferences_user.edit();
                                    obj_editor.putString("phone",phone);
                                    obj_editor.putInt("State",2);
                                    obj_editor.commit();

                                    Intent main_otp = new Intent(MainRegistroTelefono.this, MainOTP.class);
                                    startActivity(main_otp);
                                    solicitarOTP();
                                    finish();

                                }
                            },1000);

                            //final AlertDialog.Builder dialog = new

                        }else
                        {
                            Log.d("Respuesta Registro","ERROR!!!: "+response);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            },1000);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(MainRegistroTelefono.this);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#FF0000'> <b>Registro Telefono </b></font>"));
                            registro_exitoso.setMessage(Html.fromHtml("<font color='#FF0000'> <b>Lo sentimos, este n??mero de tel??fono y/o correo electr??nico ya est?? siendo utilizado, intenta con otro</b> </font>"));
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
            });
            requestQueue.add(request);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
    public void editPhoneNumber(){

        String url = "https://www.tutumapps.com/api/driver/updateRegistryPhone";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            jsonObject.put("email",email);
            jsonObject.put("phone",telefono);

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //test_check.setText(response.getString("success"));
                        if(response.getString("success").matches("true"))
                        {
                            Log.d("Respuesta Registro","Exito!!!: "+response);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder cargando_resultado = new AlertDialog.Builder(MainRegistroTelefono.this);
                                    cargando_resultado .setTitle(Html.fromHtml("<font color='#FF0000'> <b>Aplicaci??n en curso </b></font>"));
                                    cargando_resultado .setMessage(Html.fromHtml("<font color='#FF0000'> <b>Espere un momento </b> </font>"));
                                    cargando_resultado .show();
                                    SharedPreferences preferences_user = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor obj_editor = preferences_user.edit();
                                    obj_editor.putString("phone",phone);
                                    obj_editor.putInt("State",3);
                                    obj_editor.commit();

                                    Intent main_otp = new Intent(MainRegistroTelefono.this, MainOTP.class);
                                    startActivity(main_otp);
                                    solicitarOTP();
                                    finish();
                                }
                            },1000);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(MainRegistroTelefono.this);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#088d30'> <b> Registro Telefono </b> </font>"));
                            registro_exitoso.setMessage(Html.fromHtml("<font color='#088d30'> <b>N??mero de tel??fono y correo electr??nico agregados correctamente</b> </font>"));
                            registro_exitoso.show();
                        }else
                        {
                            Log.d("Respuesta Registro","ERROR!!!: "+response);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            },1000);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(MainRegistroTelefono.this);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#FF0000'> <b>Registro Telefono </b></font>"));
                            registro_exitoso.setMessage(Html.fromHtml("<font color='#FF0000'> <b>Lo sentimos, este n??mero de tel??fono y/o correo electr??nico ya est?? siendo utilizado, intenta con otro</b> </font>"));
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
            });
            requestQueue.add(request);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }



    private void solicitarOTP(){

        String url = "https://www.tutumapps.com/api/driver/otp";

        try{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JSONObject jsonObject = new JSONObject();

            jsonObject.put("phone", phone);

            final String requestBody = jsonObject.toString();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("TAG", "Success! :D" + " " + response);
                    try {
                        isSucess = response.getBoolean("success");
                        if(isSucess){
                            Toast.makeText(MainRegistroTelefono.this, "OTP", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{
                            Toast.makeText(MainRegistroTelefono.this, "Hubo un error con el codigo OTP", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("TAG", "Error: " + error);
                }
            });
            requestQueue.add(jsonObjectRequest);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void get_FCM(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful())
                {
                    Log.e("FCM","No se pudo obtener el token FCM");
                    return;
                }
                token = task.getResult();
                Log.e("Token FCM","token generado en el registro: "+token);
            }
        });
    }
    public AlertDialog dialogResposePhone(String response, boolean succes){
        //Creamos un alertDialog y su constructor (builder)
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Obtenemos el layoutInflater
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.response_phone_registry, null);
        //TextView = view.findViewById(R.id);


        builder.setView(view);
        alertDialog = builder.create();
      return alertDialog;
    }

}