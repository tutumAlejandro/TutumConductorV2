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
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

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

public class CambioCorreo extends AppCompatActivity {

    private Button btn_back_email,btn_cancel_email,btn_acept_email;
    private TextInputLayout input_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_correo);

        btn_back_email = findViewById(R.id.cambioEmail_backbutton);
        btn_cancel_email = findViewById(R.id.cambioEmail_cancelBtn);
        btn_acept_email = findViewById(R.id.cambioEmail_aceptarBtn);

        input_email = findViewById(R.id.input_nuevo_email);

        btn_back_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regreso_perfil = new Intent(CambioCorreo.this,activity_perfil.class);
                startActivity(regreso_perfil);
                finish();
            }
        });

        btn_cancel_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regreso_perfil = new Intent(CambioCorreo.this,activity_perfil.class);
                startActivity(regreso_perfil);
                finish();
            }
        });

        btn_acept_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check_email(input_email.getEditText().getText().toString())){

                }else{
                    SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor obj_editor = preferences.edit();
                    obj_editor.putString("email",input_email.getEditText().getText().toString().trim());
                    obj_editor.commit();
                    editEmailProfile(preferences.getString("api_token",""), input_email.getEditText().getText().toString().trim());
                }
            }
        });

    }

    private boolean check_email(String correo){
        if(correo.isEmpty())
        {
            input_email.setErrorEnabled(true);
            input_email.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            input_email.setError("Campor Requerido");
            return false;
        }else if(!(Patterns.EMAIL_ADDRESS.matcher(correo).matches()))
        {
            input_email.setErrorEnabled(true);
            input_email.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            input_email.setError("Formato Invalido");
            return false;
        }else
        {
            input_email.setErrorEnabled(false);
            return true;
        }
    }

    private void editEmailProfile(String api_token, String email){
        String url = "https://www.tutumapps.com/api/driver/updateProfile";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("email",email);
            jsonObject.put("email", email);
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
                                    Intent main_inicio = new Intent(CambioCorreo.this, Inicio.class);
                                    startActivity(main_inicio);
                                    finish();
                                }
                            },1000);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(CambioCorreo.this,R.style.DialogTheme);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#088d30'> <b> Correo Electrónico </b> </font>"));
                            registro_exitoso.setMessage(Html.fromHtml("<font color='#088d30'> <b>Correo Electrónico actualizado correctamente</b> </font>"));
                            registro_exitoso.show();
                        }else
                        {
                            Log.e("Respuesta Registro","ERROR!!!: "+response);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            },1000);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(CambioCorreo.this);
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

}