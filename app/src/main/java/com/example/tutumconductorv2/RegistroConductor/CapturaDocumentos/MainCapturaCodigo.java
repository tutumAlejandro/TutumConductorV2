package com.example.tutumconductorv2.RegistroConductor.CapturaDocumentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.RegistroConductor.SeleccionRol.MainSnvDocuemtos;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class MainCapturaCodigo extends AppCompatActivity {

    private TextInputLayout codigo_vehiculo;
    private ImageView btn_regreso_codigo;

    private String code2="";
    private String error_server="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_codigo);

        btn_regreso_codigo = findViewById(R.id.img_retroceso_codigo);
        codigo_vehiculo = findViewById(R.id.InputCodigo);

        btn_regreso_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferencias_codigo = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
                SharedPreferences.Editor obj_editor = preferencias_codigo.edit();

                Intent main_snv_documentos = new Intent(MainCapturaCodigo.this, MainSnvDocuemtos.class);
                obj_editor.putString("codigo3","0");
                obj_editor.commit();
                startActivity(main_snv_documentos);
                finish();
            }
        });
    }

    private boolean check_codigo(String code)
    {
        CheckCode();
        if(code.isEmpty())
        {
            codigo_vehiculo.setErrorEnabled(true);
            codigo_vehiculo.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            codigo_vehiculo.setError("Campo Requerido");
            return false;
        }else if(code.length() < 5)
        {
            codigo_vehiculo.setErrorEnabled(true);
            codigo_vehiculo.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            codigo_vehiculo.setError("Formato Invalido");
            return false;
        }
        else if(!error_server.isEmpty())
        {
            codigo_vehiculo.setErrorEnabled(true);
            codigo_vehiculo.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            codigo_vehiculo.setError(error_server);
            return false;
        }
       else{
            codigo_vehiculo.setErrorEnabled(false);
            return true;
        }
    }

    public void btn_guardar_codigo(View v)
    {
        SharedPreferences preferencias_codigo = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias_codigo.edit();

        code2 = codigo_vehiculo.getEditText().getText().toString().trim();
        if(!check_codigo(code2)){
            return;
        }else{
            realizarPost();
            Intent main_snv_documentos = new Intent(MainCapturaCodigo.this,MainSnvDocuemtos.class);
            obj_editor.putString("codigo3","1");
            obj_editor.commit();
            startActivity(main_snv_documentos);
            finish();
        }
    }
    public void realizarPost() {
        String tel="";
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        tel=preferences.getString("phone","");
        String url = "https://tutumapps.com/api/driver/uploadVehicleCode";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);
            jsonObject.put("vehicle_code",code2);

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

    public void CheckCode() {
        String tel="";
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        tel=preferences.getString("phone","");
        String url = "https://tutumapps.com/api/driver/uploadVehicleCode";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);
            jsonObject.put("vehicle_code",code2);

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!!!! "+response);
                    try {
                        boolean success = response.getBoolean("success");
                        if(!success){
                            error_server= response.getString("msg");
                        }
                        else {
                            error_server="";
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}