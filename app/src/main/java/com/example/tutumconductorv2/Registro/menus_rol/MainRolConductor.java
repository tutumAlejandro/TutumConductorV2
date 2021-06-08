package com.example.tutumconductorv2.Registro.menus_rol;

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
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;

import org.json.JSONObject;

public class MainRolConductor extends AppCompatActivity {

    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rol_conductor);
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        tel=preferences.getString("phone","");
        Log.d("Telefono","Telefono: "+tel);

    }
    public void main_doctos_socio(View v) {
        realizarPostSocio();
        Intent main_doc_socio = new Intent(MainRolConductor.this, MainSocioDocumentos.class);
        cadenas_documentos.check_terminos1=false;
        cadenas_documentos.check_ine1=false;
        cadenas_documentos.check_licencia1=false;
        cadenas_documentos.check_caracteristicas1=false;
        cadenas_documentos.check_tarjeta1=false;
        cadenas_documentos.check_poliza1=false;
        cadenas_documentos.check_tarjeton1=false;
        startActivity(main_doc_socio);
        finish();
    }
    public void main_doctos_conductor(View v)
    {
        realizarPostConductor();
        Intent main_conductor_doctos = new Intent(MainRolConductor.this, MainConductorDocumentos.class);
        cadenas_documentos.check_terminos2=false;
        cadenas_documentos.check_ine2=false;
        cadenas_documentos.check_licencia2=false;
        cadenas_documentos.check_caracteristicas2=false;
        cadenas_documentos.check_tarjeta2=false;
        cadenas_documentos.check_poliza2=false;
        cadenas_documentos.check_tarjeton2=false;
        startActivity(main_conductor_doctos);
        finish();
    }
    public void main_doctos_snv(View v)
    {
        realizarPostSnv();
        Intent main_doc_snv = new Intent(MainRolConductor.this, MainSnvDocuemtos.class);
        cadenas_documentos.check_terminos3=false;
        cadenas_documentos.check_ine3=false;
        cadenas_documentos.check_licencia3=false;
        cadenas_documentos.check_codigo3=false;
        cadenas_documentos.check_tarjeton3=false;
        startActivity(main_doc_snv);
        finish();

    }


    public void realizarPostSocio() {
        String url = "https://tutumapps.com/api/driver/updateRegistryFields";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);
            jsonObject.put("status","0");
            jsonObject.put("type","1");
            jsonObject.put("only",0);
            jsonObject.put("confirmation_phone",'1');
            jsonObject.put("terms_confirmation","0");

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
    public void realizarPostConductor() {
        String url = "https://tutumapps.com/api/driver/updateRegistryFields";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);
            jsonObject.put("status","0");
            jsonObject.put("type","1");
            jsonObject.put("only",1);
            jsonObject.put("confirmation_phone",'1');
            jsonObject.put("terms_confirmation","0");

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
    public void realizarPostSnv() {
        String url = "https://tutumapps.com/api/driver/updateRegistryFields";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);
            jsonObject.put("status","0");
            jsonObject.put("type","0");
            jsonObject.put("only",1);
            jsonObject.put("confirmation_phone",'1');
            jsonObject.put("terms_confirmation","0");

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
    public void post_confirm(){
        String url = "https://tutumapps.com/api/driver/updateRegistryFields";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);
            jsonObject.put("status","0");
            jsonObject.put("type","0");
            jsonObject.put("only",1);
            jsonObject.put("confirmation_phone",'1');
            jsonObject.put("terms_confirmation","0");

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