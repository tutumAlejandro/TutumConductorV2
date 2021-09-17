package com.example.tutumconductorv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
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


public class CambioTelefono extends AppCompatActivity {

    private TextInputLayout n_telefono;
    private Button btn_regreso_edTelefono, btn_aceptar, btn_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_telefono);

        n_telefono = findViewById(R.id.input_nuevo_telefono);
        btn_regreso_edTelefono = findViewById(R.id.cambioTel_backbutton);
        btn_aceptar = findViewById(R.id.cambioTel_aceptarBtn);
        btn_cancelar = findViewById(R.id.cambioTel_cancelBtn);


        btn_regreso_edTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regreso_esTelefono = new Intent(CambioTelefono.this,Inicio.class);
                startActivity(regreso_esTelefono);
                finish();
            }
        });

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check_phone(n_telefono.getEditText().getText().toString())){

                }else{
                    SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor obj_editor = preferences.edit();
                    obj_editor.putString("phone",n_telefono.getEditText().getText().toString().trim());
                    obj_editor.commit();
                    editPhoneNumber(preferences.getString("api_token",""), preferences.getString("phone",""));
                }
            }
        });

    }

    private boolean check_phone(String new_phone){
        if(new_phone.isEmpty()){
            n_telefono.setErrorEnabled(true);
            n_telefono.setError("Campo Requerido");
            return false;
        }else if(new_phone.length()<= 9 | new_phone.length() >= 11 ){
            n_telefono.setErrorEnabled(true);
            n_telefono.setError("Formato Invalido");
            return false;
        }else {
            n_telefono.setErrorEnabled(false);
            return true;
        }
    }

    public void editPhoneNumber(String api_token, String phone){
        String url = "https://www.tutumapps.com/api/driver/updateProfile";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("email",email);
            jsonObject.put("phone",phone);
            jsonObject.put("api_token",api_token);


            final String requestBody = jsonObject.toString();


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //test_check.setText(response.getString("success"));
                        AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(CambioTelefono.this);
                        registro_exitoso.setTitle(Html.fromHtml("<font color='#088d30'> <b> Registro Telefono </b> </font>"));
                        registro_exitoso.setMessage(Html.fromHtml("<font color='#088d30'> <b>Número de teléfono actualizado correctamente</b> </font>"));
                        registro_exitoso.show();
                        if(response.getString("success").matches("true"))
                        {
                            Log.e("Respuesta Registro","Exito!!!: "+response);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent main_inicio = new Intent(CambioTelefono.this, Inicio.class);
                                    startActivity(main_inicio);
                                    finish();
                                }
                            },1000);
                            registro_exitoso.setCancelable(true);

                        }else
                        {
                            Log.e("Respuesta Registro","ERROR!!!: "+response);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            },1000);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#FF0000'> <b>Registro Telefono </b></font>"));
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
                public Map<String, String> getHeaders() throws AuthFailureError{
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