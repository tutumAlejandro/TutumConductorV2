package com.example.tutumconductorv2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.Registro.datos_personales.MainOTP;
import com.example.tutumconductorv2.Registro.datos_personales.MainRegistrate;
import com.example.tutumconductorv2.Registro.datos_personales.MainRegistroTelefono;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainDocumentosOk;
import com.example.tutumconductorv2.Registro.menus_rol.MainRolConductor;
import com.example.tutumconductorv2.Registro.menus_rol.MainSnvDocuemtos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

import org.json.JSONObject;

public class MainVentanaPrincipal extends AppCompatActivity {

    private int estado_previo;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ventana_principal);

    }
    public void btn_registrate(View v)
    {
        SharedPreferences preferencias_ventanaPrincipal = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        estado_previo = preferencias_ventanaPrincipal.getInt("State",0);
        if(estado_previo == 0){
            Log.d("Main Ventana Principal","Mo hay ningun registro sin terminar");
            Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainRegistrate.class);
            startActivity(main_registrate);
            finish();
        }else{ // En caso de que no haya un registro incomplemto, hacer una solicitud al servidor para saber si existe un
            // un registro incompleto con el servidor

            // se tiene que hacer un POST al servidor para saber el time
            Log.d("Main Ventana Principal","Hay un registro sin terminar en el telefono");
            phone = preferencias_ventanaPrincipal.getString("phone","");
            if(phone.isEmpty()){
                Log.d("Main Ventana Principal", "No hay ningun numero de telefono registrado");
            }else{
                Log.d("Main Venatan Principal","Hay un numero de telefono registrado en el telefono");
                POST_timeline();
            }


        }
    }
    public void btn_inicia_sesion(View v)
    {
        Intent main_inicio_sesion = new Intent(MainVentanaPrincipal.this,Main_IniciaSesion.class);
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
                    //Si hay respuesta por parte del servidor, esta debe de ser analizada
                    Log.d("Main Ventana Principal","Respuesta del servidor: " + response);
                    //Debido a que podemos recibir dos tipos de respuestas usaremos un if
                    if(response.has("msg")){
                        // Si existe un encabezado que se llame msg, entonces no hay un registro con el numero de telefono guardado
                        Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainRegistrate.class);
                        startActivity(main_registrate);
                        Log.d("Main Ventana Principal","No se encontro el registro");
                    }else{
                        //Si no existe el encabezado "msg", quiere decir que hay un registro con ese numero
                        Log.d("Main Ventana Principal", "Registro encontrado");
                        String msg2= "";

                        final AlertDialog.Builder alert_continue = new AlertDialog.Builder(MainVentanaPrincipal.this);
                        //alert_continue.setView(R.layout.dialog_continue);

                        switch (estado_previo){
                            case 1: {msg2 = "Etapa: Registro del Telefono";}break;
                            case 2: {msg2 = "Etapa: Validación del OTP";}break;
                            case 3: {msg2 = "Etapa: Selección del tipo de usuario";}break;
                            case 4: {msg2 = "Etapa: Documentos documentos Socio";}break;
                            case 5: {msg2 = "Etapa: Documentos Conductor";}break;
                            case 6: {msg2 = "Etapa: Documentos Conductor Sin Vehiculo";}break;
                            case 8: {msg2 = "Etapa: Documentos Entregados";}break;
                        }


                        alert_continue.setPositiveButton(R.string.txt_btn_pop_up1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (estado_previo){
                                    case 1: {Intent main_reg_tel = new Intent(MainVentanaPrincipal.this, MainRegistroTelefono.class);
                                        startActivity(main_reg_tel); finish();}break;
                                    case 2: {Intent main_otp = new Intent(MainVentanaPrincipal.this, MainOTP.class);
                                        startActivity(main_otp); finish();}break;
                                    case 3: {Intent main_rol = new Intent(MainVentanaPrincipal.this, MainRolConductor.class);
                                        startActivity(main_rol); finish();}break;
                                    case 4: {Intent main_socio = new Intent(MainVentanaPrincipal.this, MainSocioDocumentos.class);
                                        startActivity(main_socio);finish();}break;
                                    case 5: {Intent main_conductor = new Intent(MainVentanaPrincipal.this, MainConductorDocumentos.class);
                                        startActivity(main_conductor);finish();}break;
                                    case 6: {Intent main_snv = new Intent(MainVentanaPrincipal.this, MainSnvDocuemtos.class);
                                        startActivity(main_snv); finish();}break;
                                    case 7: {Intent main_error = new Intent(MainVentanaPrincipal.this, MainDocumentosOk.class);
                                             startActivity(main_error);}break;
                                    case 8: {Intent main_ok = new Intent(MainVentanaPrincipal.this, MainDocumentosOk.class);
                                        startActivity(main_ok);finish();}break;
                                }
                            }
                        });
                        alert_continue.setNegativeButton(R.string.txt_btn_pop_up2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                HardReset();
                                Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainRegistrate.class);
                                startActivity(main_registrate);
                            }
                        });
                        //alert_continue.setMessage(msg2);
                        alert_continue.setTitle(Html.fromHtml("<font color='#E4B621'> <b>Registro Conductor</b></font>"));
                        alert_continue.setIcon(R.drawable.logo_1024);
                        alert_continue.setMessage(Html.fromHtml("<p><b>Parece que tienes un registro sin completar.</b></p>"+"<b>¿Quieres continuar con tu registro</b>"+"\n \n"+msg2));
                        alert_continue.show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Si no hay respuesta del servidor mostrar un error ya mediante el LOg y un AlertDialog
                    Log.e("Main Ventana Principal", "<<<<<<<<<No hay respuesta del servidor>>>>>>>>>>");
                }
            });
            requestQueue.add(request_timeline);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void HardReset(){
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