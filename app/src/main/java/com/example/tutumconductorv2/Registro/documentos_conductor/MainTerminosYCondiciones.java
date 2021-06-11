package com.example.tutumconductorv2.Registro.documentos_conductor;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSnvDocuemtos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

import org.json.JSONObject;

public class MainTerminosYCondiciones extends AppCompatActivity {

    private ImageView btn_regreso_term;
    private TextView _politica;
    private TextView _terminos;

    // Cadenas para revisar
    private String rol;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_terminos_y_condiciones);
        SharedPreferences preferencias_terminos = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        rol = preferencias_terminos.getString("rol","");
        btn_regreso_term = findViewById(R.id.img_retroceso_terminos_condiciones);
        _politica = findViewById(R.id.link_politica);
        _terminos = findViewById(R.id.txt_terminos_y_condiciones);

        btn_regreso_term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferencias_terminos = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
                SharedPreferences.Editor obj_editor = preferencias_terminos.edit();
                if(rol.matches("Socio")){
                    Intent main_socio_documentos = new Intent(MainTerminosYCondiciones.this, MainSocioDocumentos.class);
                    obj_editor.putString("terminos1","0");
                    obj_editor.commit();
                    startActivity(main_socio_documentos);
                    finish();
                }else if(rol.matches("Conductor")){
                    Intent main_conductor_documentos = new Intent(MainTerminosYCondiciones.this, MainConductorDocumentos.class);
                    obj_editor.putString("terminos2","0");
                    obj_editor.commit();
                    startActivity(main_conductor_documentos);
                    finish();
                }else{
                    Intent main_snv_documentos = new Intent(MainTerminosYCondiciones.this, MainSnvDocuemtos.class);
                    obj_editor.putString("terminos3","0");
                    obj_editor.commit();
                    startActivity(main_snv_documentos);
                    finish();
                }
            }
        });

        _politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_politica = new Intent(MainTerminosYCondiciones.this, MainTextoPoliticaPrivacidad.class);
                startActivity(main_politica);
            }
        });
        _terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos = new Intent(MainTerminosYCondiciones.this, MainTextoPoliticaPrivacidad.class);
                startActivity(main_terminos);
                finish();
            }
        });

    }
    public void btn_guardar_terminos(View v)
    {
        SharedPreferences preferencias_terminos = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias_terminos.edit();
        if(rol.matches("Socio")){
            realizarPost();
            Intent main_socio_documentos = new Intent(MainTerminosYCondiciones.this, MainSocioDocumentos.class);
            obj_editor.putString("terminos1","1");
            obj_editor.commit();
            startActivity(main_socio_documentos);
            finish();
        }else if(rol.matches("Conductor")){
            realizarPost();
            Intent main_conductor_documentos = new Intent(MainTerminosYCondiciones.this, MainConductorDocumentos.class);
            obj_editor.putString("terminos2","1");
            obj_editor.commit();
            startActivity(main_conductor_documentos);
            finish();
        }else{
            realizarPost();
            Intent main_snv_documentos = new Intent(MainTerminosYCondiciones.this, MainSnvDocuemtos.class);
            obj_editor.putString("terminos3","1");
            obj_editor.commit();
            startActivity(main_snv_documentos);
            finish();
        }
    }
    public void realizarPost() {
        String tel="";
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        tel=preferences.getString("phone","");
        String url = "https://tutumapps.com/api/driver/updateRegistryFields";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);
            jsonObject.put("status","0");
            jsonObject.put("terms_confirmation","1");

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
}