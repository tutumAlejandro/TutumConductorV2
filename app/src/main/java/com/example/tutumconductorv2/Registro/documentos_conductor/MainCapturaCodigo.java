package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.example.tutumconductorv2.Registro.menus_rol.MainSnvDocuemtos;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

public class MainCapturaCodigo extends AppCompatActivity {

    private TextInputLayout codigo_vehiculo;
    private ImageView btn_regreso_codigo;

    private String rol;
    private String code2="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_codigo);

        rol = getIntent().getStringExtra("rol");

        btn_regreso_codigo = findViewById(R.id.img_retroceso_codigo);
        codigo_vehiculo = findViewById(R.id.InputCodigo);

        btn_regreso_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_snv_documentos = new Intent(MainCapturaCodigo.this, MainSnvDocuemtos.class);
                cadenas_documentos.check_codigo3=false;
                startActivity(main_snv_documentos);
                finish();
            }
        });
    }

    private boolean check_codigo(String code)
    {
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
       else{
            codigo_vehiculo.setErrorEnabled(false);
            return true;
        }
    }

    public void btn_guardar_codigo(View v)
    {
        code2 = codigo_vehiculo.getEditText().getText().toString().trim();
        if(!check_codigo(code2)){
            return;
        }else{
            realizarPost();
            Intent main_snv_documentos = new Intent(MainCapturaCodigo.this,MainSnvDocuemtos.class);
            cadenas_documentos.check_codigo3=true;
            cadenas_documentos.Codigo=code2;
            startActivity(main_snv_documentos);
            finish();
        }
    }
    public void realizarPost() {
        String url = "https://tutumapps.com/api/driver/uploadVehicleCode";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", cadenas_registro.telefono);
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
}