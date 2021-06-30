package com.example.tutumconductorv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class CambioContrasena extends AppCompatActivity {

    private Button btn_back_pass, btn_acept_pass, btn_cancel_pass;
    private TextInputLayout input_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contrasena);

        btn_back_pass = findViewById(R.id.cambioPass_backbutton);
        btn_acept_pass = findViewById(R.id.cambioPass_aceptarBtn);
        btn_cancel_pass = findViewById(R.id.cambioPass_cancelBtn);

        input_password = findViewById(R.id.input_nuevo_pass);
        btn_back_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_perfil = new Intent(CambioContrasena.this, activity_perfil.class);
                startActivity(main_perfil);
                finish();
            }
        });
        btn_cancel_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_perfil = new Intent(CambioContrasena.this, activity_perfil.class);
                startActivity(main_perfil);
                finish();
            }
        });
        btn_acept_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check_pass(input_password.getEditText().getText().toString().trim())){

                }else{
                    SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor obj_editor = preferences.edit();
                    obj_editor.putString("email",input_password.getEditText().getText().toString().trim());
                    obj_editor.commit();
                    editPassProfile(preferences.getString("api_token",""), input_password.getEditText().getText().toString().trim());

                }
            }
        });

    }
    private boolean check_pass(String contraseña){
        if(contraseña.isEmpty())
        {
            input_password.setErrorEnabled(true);
            input_password.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            input_password.setError("Campo Requerido");
            return false;
        }else if(contraseña.length() < 8)
        {
            input_password.setErrorEnabled(true);
            input_password.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            input_password.setError("Este campo acepta un minimo de 8 caracteres");
            return false;
        }
        else
        {
            input_password.setErrorEnabled(false);
            return true;
        }
    }
    private void editPassProfile(String api_token,String pass){
        String url = "https://www.tutumapps.com/api/driver/updateProfile";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("email",email);
            jsonObject.put("password", pass);
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
                                    Intent main_inicio = new Intent(CambioContrasena.this, Inicio.class);
                                    startActivity(main_inicio);
                                    finish();
                                }
                            },1000);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(CambioContrasena.this,R.style.DialogTheme);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#088d30'> <b> Contraseña </b> </font>"));
                            registro_exitoso.setMessage(Html.fromHtml("<font color='#088d30'> <b>Contraseña actualizado correctamente</b> </font>"));
                            registro_exitoso.show();
                        }else
                        {
                            Log.e("Respuesta Registro","ERROR!!!: "+response);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            },1000);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(CambioContrasena.this);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#FF0000'> <b>Contraseña </b></font>"));
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


}