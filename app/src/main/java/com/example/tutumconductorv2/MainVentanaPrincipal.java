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
import com.example.tutumconductorv2.RegistroConductor.DatosPersonales.MainRegistrate;

import org.json.JSONException;
import org.json.JSONObject;

public class MainVentanaPrincipal extends AppCompatActivity {

    // Decalracion de variables globales
    private int state;
    private String phone;
    private String url_timeline="https://www.tutumapps.com/api/driver/registryTimelineStatus";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ventana_principal);
        // abrir la base de datos SharedPreferences y sacar las variables Status y phone
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        state = preferences.getInt("State",0);
        phone = preferences.getString("phone","");
        Log.e("Ventana Principal","Status guardado: "+state);
        Log.e("Ventana Principal","phone guardado: "+ phone);
        Sesion_login();
    }

//////////////////////prueba de ventanas

       /* public void btnprueba(View view) {
        Intent prueba = new Intent(MainVentanaPrincipal.this, Ini_cancel_viaje.class);
        startActivity(prueba);
        }*/

    //////////////
    private void Sesion_login(){
        SharedPreferences sharedPreferences = getSharedPreferences("isLogged", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("isLogged", "YES");
        editor.apply();
    }


    public void btnRegistro(View view){
        //Si el campo "phone" en la base de datos SharedPreferences esta vacio quiere decir que no hay un registro activo
        if(phone.isEmpty()){
            startActivity(new Intent(MainVentanaPrincipal.this, MainRegistrate.class));
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }else{
            // Si el status de la solicitud se encuentra en 0 quiere decir que nop hay ningun dato asociado con ese telefono
            // o si el status es igual a 10 significa que ya hay un registro completo con ese numero de telefono
            if(state == 0 || state == 1 || state == 10){
                HARD_RESET(); // Se reinician los valores de la base de datos "Datos_Usuario  a sus valore por Default
                startActivity(new Intent(MainVentanaPrincipal.this, MainRegistrate.class)); //Iniciar la actividada para registrarse
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }else{
                Log.e("Ventana Principal", "Hay un registro sin concluir, mandando una peticion Json con el numero de telefono: "+ phone);
                POST_timeline();
            }
        }


    }
    public void btn_inicia_sesion(View v)
    {
        startActivity(new Intent(MainVentanaPrincipal.this, Main_IniciaSesion.class));
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    private void POST_timeline(){
        try{
            //Creacion de un peticion para la comunicacion a traves de internet
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone",phone);
            final String requestBody = jsonObject.toString();
            Log.e("Main Ventana Principal","Peticion hecha al servidor");
            JsonObjectRequest request_timeline = new JsonObjectRequest(Request.Method.POST, url_timeline, jsonObject, new Response.Listener<JSONObject>() {


                @Override
                public void onResponse(JSONObject response) {
                    //Mostrar la respuesta del servidor
                    Log.e("Main Ventana Principal","Respuesta del servidor: " + response);

                    //Si en la respuesta contiene un mensaje(msg) quiere decir que no hay un registro con esos datos en nuestra base de datos
                    if(response.has("msg")){
                        Log.d("Main Ventana Principal","No se encontro el registro y se borran los datos de la BD Shared Preferences");
                        HARD_RESET(); // Se reinician los valores de la base de datos "Datos_Usuario  a sus valore por Default
                        startActivity(new Intent(MainVentanaPrincipal.this, MainRegistrate.class)); //Iniciar la actividada para registrarse
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    }else{
                        // Si no contiene un mensaje la respuesta del servidor tiene el status donde se quedo el proceso de registro de un nuevo conductor
                        try {
                            JSONObject obj1 = response.getJSONObject("data");
                            String state = obj1.getString("status");
                            Log.e("Main Ventana Principal", "valor status: "+state);
                            // si el estatus es 0 quiere decir que en el servidor no existe un registro con el numero de telefono guardado en el SharedPreferences
                            //Si el estatus es 10 quiere decir que el prospecto fue aceptado como conductor
                            if(state.matches("0") || state.matches("10") ){
                                HARD_RESET(); // Se reinician los valores de la base de datos "Datos_Usuario  a sus valore por Default
                                startActivity(new Intent(MainVentanaPrincipal.this, MainRegistrate.class)); //Iniciar la actividada para registrarse
                                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                            }else{
                                //
                                startActivity(new Intent(MainVentanaPrincipal.this, PopUpContinuarRegistro.class)); //Iniciar la actividada para registrarse
                                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                                Log.d("Main Ventana Principal", "Registro sin concluir, cargando los datos del usuario");
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
                    // Si existe un encabezado que se llame msg, entonces no hay un registro con el numero de telefono guardado
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