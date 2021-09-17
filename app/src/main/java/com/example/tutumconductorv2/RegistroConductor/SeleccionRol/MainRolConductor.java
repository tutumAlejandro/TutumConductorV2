package com.example.tutumconductorv2.RegistroConductor.SeleccionRol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;

import org.json.JSONObject;

public class MainRolConductor extends AppCompatActivity {

    private String tel;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rol_conductor);
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        tel=preferences.getString("phone","");
        email=preferences.getString("email","");
        password = preferences.getString("password","");

       // post_update();
    }
    public void main_doctos_socio(View v) {
        realizarPostSocio();
        HardReset();
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString("rol","Socio");
        obj_editor.putInt("State",4);
        obj_editor.commit();
        Intent main_doc_socio = new Intent(MainRolConductor.this, MainSocioDocumentos.class);
        startActivity(main_doc_socio);
        finish();
    }
    public void main_doctos_conductor(View v)
    {
        realizarPostConductor();
        HardReset();
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString("rol","Conductor");
        obj_editor.putInt("State",5);
        obj_editor.commit();
        Intent main_conductor_doctos = new Intent(MainRolConductor.this, MainConductorDocumentos.class);
        startActivity(main_conductor_doctos);
        finish();
    }
    public void main_doctos_snv(View v)
    {
        realizarPostSnv();
        HardReset();
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString("rol","Snv");
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
                    Log.d("Seleccion de Rol","Socio Administrador "+response);
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
                    Log.d("Seleccion de Rol","Conductor"+response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("Seleccion de Rol","Error en el servidor"+error);
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
                    Log.d("Seleccion de Rol","Conductor sin Vehiculo "+response);
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
    public void post_update(){
        String url = "https://tutumapps.com/api/driver/updateProfile";
         SharedPreferences preferences = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
         String email2 = preferences.getString("email","");
         String phone2 = preferences.getString("phone","");
         String password2 = preferences.getString("password","");
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("email",email2);
            jsonObject.put("phone", phone2);
            jsonObject.put("password",password2);

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Registro Actualizado "+response);
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
    public void HardReset(){
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();

        obj_editor.putString("terminos1","0");
        obj_editor.putString("ine1","0");
        obj_editor.putString("licencia1","0");
        obj_editor.putString("caracteristicas1","0");
        obj_editor.putString("tarjeta1","0");
        obj_editor.putString("poliza1","0");
        obj_editor.putString("tarjeton1","0");

        obj_editor.putString("terminos2","0");
        obj_editor.putString("ine2","0");
        obj_editor.putString("licencia2","0");
        obj_editor.putString("caracteristicas2","0");
        obj_editor.putString("tarjeta2","0");
        obj_editor.putString("poliza2","0");
        obj_editor.putString("tarjeton2","0");

        obj_editor.putString("terminos3","0");
        obj_editor.putString("ine3","0");
        obj_editor.putString("licencia3","0");
        obj_editor.putString("codigo3","0");
        obj_editor.putString("tarjeton3","0");

        obj_editor.putString("error1","");
        obj_editor.putString("error2","");
        obj_editor.putString("error3","");
        obj_editor.putString("error4","");
        obj_editor.putString("error5","");
        obj_editor.putString("error6","");
        obj_editor.putString("error7","");
        obj_editor.commit();

    }
}