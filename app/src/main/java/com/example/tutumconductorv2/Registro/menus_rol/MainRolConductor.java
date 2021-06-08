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
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString("rol","Socio");
        obj_editor.putBoolean("terminos1",false);
        obj_editor.putBoolean("ine1",false);
        obj_editor.putBoolean("licencia1",false);
        obj_editor.putBoolean("caracteristicas1",false);
        obj_editor.putBoolean("tarjeta1",false);
        obj_editor.putBoolean("poliza1",false);
        obj_editor.putBoolean("tarjeton1",false);
        obj_editor.putInt("State",4);
        obj_editor.commit();
        Intent main_doc_socio = new Intent(MainRolConductor.this, MainSocioDocumentos.class);
        startActivity(main_doc_socio);
        finish();
    }
    public void main_doctos_conductor(View v)
    {
        realizarPostConductor();
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString("rol","Conductor");
        obj_editor.putBoolean("terminos2",false);
        obj_editor.putBoolean("ine2",false);
        obj_editor.putBoolean("licencia2",false);
        obj_editor.putBoolean("caracteristicas2",false);
        obj_editor.putBoolean("tarjeta2",false);
        obj_editor.putBoolean("poliza2",false);
        obj_editor.putBoolean("tarjeton2",false);
        obj_editor.putInt("State",5);
        obj_editor.commit();
        Intent main_conductor_doctos = new Intent(MainRolConductor.this, MainConductorDocumentos.class);
        startActivity(main_conductor_doctos);
        finish();
    }
    public void main_doctos_snv(View v)
    {
        realizarPostSnv();
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString("rol","Snv");
        obj_editor.putBoolean("terminos3",false);
        obj_editor.putBoolean("ine3",false);
        obj_editor.putBoolean("licencia3",false);
        obj_editor.putBoolean("codigo3",false);
        obj_editor.putBoolean("tarjeton3",false);
        obj_editor.putInt("State",6);
        obj_editor.commit();
        Intent main_doc_snv = new Intent(MainRolConductor.this, MainSnvDocuemtos.class);
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