package com.example.tutumconductorv2.Registro.datos_personales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;

public class MainRegistroTelefono extends AppCompatActivity {

    private TextInputLayout telefono;
    private String url_registro="https://www.tutumapps.com/api/driver/registryDriver";
    private String url_timeline="https://www.tutumapps.com/api/driver/registryTimelineStatus";
    private String name,phone,email,tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_telefono);
        telefono = findViewById(R.id.InputTelefono);

        SharedPreferences preferences_user = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        int state= preferences_user.getInt("State",0);
        Log.d("Revisar Estado","Estado actual"+state);
        name = preferences_user.getString("name","");
        email = preferences_user.getString("email","");

    }

    private boolean check_telefono(String num_telefono) {
        if (num_telefono.isEmpty()) {
            telefono.setErrorEnabled(true);
            telefono.setError("Campo Requerido");
            telefono.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        } else if (num_telefono.length() < 10) {
            telefono.setErrorEnabled(true);
            telefono.setError("Formato Invalido");
            telefono.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        } else {
            telefono.setErrorEnabled(false);
            return true;
        }
    }

    public void main_otp(View v) {

        phone=telefono.getEditText().getText().toString().trim();
        SharedPreferences preferences_user = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences_user.edit();
        obj_editor.putString("phone",phone);
        obj_editor.putInt("State",2);
        obj_editor.commit();

       if(!check_telefono(phone)){
            return;
       }else {
               if(!cadenas_registro.edit_phone){
                   editPhoneNumber();
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

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getString("success").matches("true"))
                        {
                            Log.d("Respuesta Registro","Exito!!!: "+response);
                            Intent main_popup = new Intent(MainRegistroTelefono.this, MainPopUpRegistro.class);
                            startActivity(main_popup);
                            finish();
                        }else
                        {
                            Log.e("Respuesta Registro","Error!!!: "+response);
                            Intent main_popup_fail = new Intent(MainRegistroTelefono.this, MainPopUpRegistroFail.class);
                            startActivity(main_popup_fail);
                            finish();
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
                            Intent main_popup = new Intent(MainRegistroTelefono.this, MainPopUpRegistro.class);
                            startActivity(main_popup);
                        }else
                        {
                            cadenas_registro.msg_fail= response.getString("msg");
                            Intent main_popup_fail = new Intent(MainRegistroTelefono.this, MainPopUpRegistroFail.class);
                            startActivity(main_popup_fail);
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
}
