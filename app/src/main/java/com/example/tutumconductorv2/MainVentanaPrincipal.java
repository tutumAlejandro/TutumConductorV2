package com.example.tutumconductorv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.Actividad_Principal.Inicio_Sesion.Main_IniciaSesion;
import com.example.tutumconductorv2.Pop_Up.PopUpContinuarRegistro;
import com.example.tutumconductorv2.Registro.datos_personales.MainPopUpData;
import com.example.tutumconductorv2.Registro.datos_personales.MainRegistrate;
import com.example.tutumconductorv2.Registro.menus_rol.MainDocumentosOk;

import org.json.JSONException;
import org.json.JSONObject;

public class MainVentanaPrincipal extends AppCompatActivity {

    private int estado_previo;
    private String phone;
    //private String state="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ventana_principal);
        //POST_timeline();
            Sesion_login();
    }

    private void Sesion_login(){
        SharedPreferences sharedPreferences = getSharedPreferences("isLogged", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("isLogged", "YES");
        editor.apply();
    }

    public void btn_registrate(View v)
    {

        SharedPreferences preferencias_ventanaPrincipal = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        estado_previo = preferencias_ventanaPrincipal.getInt("State",0);
        if(estado_previo == 0){ // falta agregar state == 10
            Log.d("Main Ventana Principal","Mo hay ningun registro sin terminar");
            Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainPopUpData.class);
            startActivity(main_registrate);
        }else{
            Log.d("Main Ventana Principal","Hay un registro sin terminar en el telefono");
            phone = preferencias_ventanaPrincipal.getString("phone","");
            if(phone.isEmpty()){
                HARD_RESET();
                Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainPopUpData.class);
                startActivity(main_registrate);
                Log.d("Main Ventana Principal", "No hay ningun numero de telefono registrado");
            }else{
                Log.d("Main Ventana Principal","Hay un numero de telefono registrado en el telefono");
                POST_timeline();
            }


        }
    }
    public void btn_inicia_sesion(View v)
    {
        Intent main_inicio_sesion = new Intent(MainVentanaPrincipal.this, Main_IniciaSesion.class);
        startActivity(main_inicio_sesion);
    }
    private void POST_timeline(){
        String url = "https://www.tutumapps.com/api/driver/registryTimelineStatus";

        try{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone",phone);
            final String requestBody = jsonObject.toString();
            Log.d("Main Ventana Principal","Peticion hecha al servidor");
            JsonObjectRequest request_timeline = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Main Ventana Principal","Respuesta del servidor: " + response);
                    if(response.has("msg")){
                        HARD_RESET();
                        // Si existe un encabezado que se llame msg, entonces no hay un registro con el numero de telefono guardado
                        Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainRegistrate.class);
                        startActivity(main_registrate);
                        Log.d("Main Ventana Principal","No se encontro el registro y se borran los datos de la BD Shared Preferences");
                    }else{
                        try {
                            JSONObject obj1 = response.getJSONObject("data");
                            String state = obj1.getString("status");
                            Log.e("Main Ventana Principal", "valor status: "+state);
                            if(state.matches("5") || state.matches("6") || state.matches("7") || state.matches("8") || state.matches("9") || state.matches("10")){
                                Intent main_timeline = new Intent(MainVentanaPrincipal.this, MainDocumentosOk.class);
                                startActivity(main_timeline);
                            }else{
                                Intent main_continua_registro = new Intent(MainVentanaPrincipal.this, PopUpContinuarRegistro.class);
                                startActivity(main_continua_registro);
                                Log.d("Main Ventana Principal", "Registro Terminado Inicia Sesión");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Si no hay respuesta del servidor mostrar un error ya mediante el LOg y un AlertDialog
                    Toast.makeText(MainVentanaPrincipal.this,"Hubo un problema en el servidor, intentalo mas tarde",Toast.LENGTH_SHORT).show();
                    Log.e("Main Ventana Principal", "<<<<<<<<<No hay respuesta del servidor>>>>>>>>>>");
                    HARD_RESET();
                    // Si existe un encabezado que se llame msg, entonces no hay un registro con el numero de telefono guardado
                    Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainRegistrate.class);
                    startActivity(main_registrate);
                }
            });
            requestQueue.add(request_timeline);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void HARD_RESET(){

        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString("name","");
        obj_editor.putString("phone","");
        obj_editor.putString("password","");
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