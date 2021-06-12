package com.example.tutumconductorv2;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.Registro.datos_personales.MainContinuarRegistro;
import com.example.tutumconductorv2.Registro.datos_personales.MainRegistrate;
import com.example.tutumconductorv2.Registro.menus_rol.MainDocumentosOk;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainVentanaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ventana_principal);

    }
    public void btn_registrate(View v)
    {

        realizarPost("https://www.tutumapps.com/api/driver/registryTimelineStatus");

    }
    public void btn_inicia_sesion(View v)
    {
        Intent main_inicio_sesion = new Intent(MainVentanaPrincipal.this,Main_IniciaSesion.class);
        startActivity(main_inicio_sesion);
    }
    public void realizarPost(String url) {
        String tel="";
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        tel=preferences.getString("phone","");
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);


            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!!!! "+response);
                    try {
                        if(response.has("msg")){
                            Log.d("ERROR!!!!!", "No hay un registro con ese numero");
                            Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainRegistrate.class);
                            startActivity(main_registrate);
                        }else {
                            Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainContinuarRegistro.class);
                            startActivity(main_registrate);

                            JSONObject ranita = response.getJSONObject("data");
                            String status = ranita.getString("status");
                            JSONArray respuesta = ranita.getJSONArray("timeline");
                            JSONObject respuesta1 = respuesta.getJSONObject(0);
                            String status1 = respuesta1.getString("status");
                            String date = respuesta1.getString("date");
                            String name = respuesta1.getString("name");
                            String descripcion = respuesta1.getString("descripcion");
                            String success = respuesta1.getString("success");
                            Log.d("TEST REGISTRO", "valor estatus " + status1);
                            Log.d("TEST REGISTRO", "valor date " + date);
                            Log.d("TEST REGISTRO", "valor descripcion " + descripcion);
                            Log.d("TEST REGISTRO", "valor estatus " + name);
                            Log.d("TEST REGISTRO", "valor estatus " + success);
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