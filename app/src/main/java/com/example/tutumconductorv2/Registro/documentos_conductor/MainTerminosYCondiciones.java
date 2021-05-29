package com.example.tutumconductorv2.Registro.documentos_conductor;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

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
        rol = getIntent().getStringExtra("rol");



        btn_regreso_term = findViewById(R.id.img_retroceso_terminos_condiciones);
        _politica = findViewById(R.id.link_politica);
        _terminos = findViewById(R.id.txt_terminos_y_condiciones);

        btn_regreso_term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rol.matches("Socio")){
                    Intent main_socio_documentos = new Intent(MainTerminosYCondiciones.this, MainSocioDocumentos.class);
                    cadenas_documentos.check_terminos1 = false;
                    startActivity(main_socio_documentos);
                    finish();
                }else if(rol.matches("Conductor")){
                    Intent main_conductor_documentos = new Intent(MainTerminosYCondiciones.this, MainConductorDocumentos.class);
                    cadenas_documentos.check_terminos2 = false;
                    startActivity(main_conductor_documentos);
                    finish();
                }else{
                    Intent main_snv_documentos = new Intent(MainTerminosYCondiciones.this, MainSnvDocuemtos.class);
                    cadenas_documentos.check_terminos3 = false;
                    startActivity(main_snv_documentos);
                    finish();
                }
            }
        });

        _politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_politica = new Intent(MainTerminosYCondiciones.this, MainTextoPoliticaPrivacidad.class);
                main_politica.putExtra("rol",rol);
                startActivity(main_politica);
            }
        });
        _terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos = new Intent(MainTerminosYCondiciones.this, MainTextoPoliticaPrivacidad.class);
                main_terminos.putExtra("rol",rol);
                startActivity(main_terminos);
                finish();
            }
        });

    }
    public void btn_guardar_terminos(View v)
    {

        if(rol.matches("Socio")){
            realizarPost();
            Intent main_socio_documentos = new Intent(MainTerminosYCondiciones.this, MainSocioDocumentos.class);
            cadenas_documentos.check_terminos1 = true;
            startActivity(main_socio_documentos);
            finish();
        }else if(rol.matches("Conductor")){
            realizarPost();
            Intent main_conductor_documentos = new Intent(MainTerminosYCondiciones.this, MainConductorDocumentos.class);
            cadenas_documentos.check_terminos2 = true;
            startActivity(main_conductor_documentos);
            finish();
        }else{
            realizarPost();
            Intent main_snv_documentos = new Intent(MainTerminosYCondiciones.this, MainSnvDocuemtos.class);
            cadenas_documentos.check_terminos3 = true;
            startActivity(main_snv_documentos);
            finish();
        }
    }
    public void realizarPost() {
        String url = "https://tutumapps.com/api/driver/updateRegistryFields";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", cadenas_registro.telefono);
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