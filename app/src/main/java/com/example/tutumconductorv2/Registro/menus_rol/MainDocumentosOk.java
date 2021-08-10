package com.example.tutumconductorv2.Registro.menus_rol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainDocumentosOk extends AppCompatActivity {

    private String url_timeline="https://www.tutumapps.com/api/driver/registryTimelineStatus";

    private String url2= "https://tutumapps.com/plataforma/models";

    private SwipeRefreshLayout refresh;
    private TextView txt_rch_hd,txt_rch_bd,txt_aprb_hd,txt_aprb_bd;
    private TextView txt_cita_hd,txt_cita_bd1, txt_cita_bd2,txt_cita_bd3;
    private ImageView btn_cita;
    private ImageView btn_rechazo,btn_aprobado;

    private String rol="";
    private String id ="";
    private String password="";
    private String email="";
    private String api_token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_documentos_ok);
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        rol= preferences.getString("rol","");
        password = preferences.getString("password","");
        email = preferences.getString("email","");
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putInt("State",8);
        obj_editor.commit();


        txt_aprb_hd = findViewById(R.id.txt_aprobado_documentos_head);
        txt_aprb_bd = findViewById(R.id.txt_aprobado_documentos_body);
        txt_rch_hd = findViewById(R.id.txt_rechazado_documentos_head);
        txt_rch_bd = findViewById(R.id.txt_rechazado_documentos_body);
        btn_rechazo = findViewById(R.id.check_rechazado_documentos);
        btn_aprobado = findViewById(R.id.check_aprobado_documentos);

        txt_cita_hd = findViewById(R.id.txt_cita_documentos_head);
        txt_cita_bd1 = findViewById(R.id.txt_cita_documentos_body_1);
        txt_cita_bd2 = findViewById(R.id.txt_cita_documentos_body_2);
        txt_cita_bd3 = findViewById(R.id.txt_cita_documentos_body_3);
        btn_cita = findViewById(R.id.check_cita_documentos);

        refresh = findViewById(R.id.refresh_screen);

        mostrar_revision();
        getDriverData();

        realizarPost(url_timeline);
        //update_registry();

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                realizarPost(url_timeline);

            }
        });
        btn_rechazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rol.matches("Socio")){
                    Intent main_socio_documentos = new Intent(MainDocumentosOk.this, MainSocioDocumentos.class);
                    startActivity(main_socio_documentos);
                    finish();
                }else if(rol.matches("Conductor")){
                    Intent main_conductor_documentos = new Intent(MainDocumentosOk.this, MainConductorDocumentos.class);
                    startActivity(main_conductor_documentos);
                    finish();
                }else{
                    Intent main_snv_documentos = new Intent(MainDocumentosOk.this, MainSnvDocuemtos.class);
                    startActivity(main_snv_documentos);
                    finish();
                }
            }
        });

        //Mostrar la ubicacion de las oficinas de Tutum
        txt_cita_bd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Jardines de Versalles 238");
                //Jardines de Versalles 238, Versalles 1ra Secc, 20285 Aguascalientes, Ags.
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });


    }
    private void realizarPost(String url) {
        refresh.setRefreshing(true);
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        String tel = preferences.getString("phone","");
        try {
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("phone",tel);
                final String requestBody = jsonObject.toString(); // Con esta cadena podemos ver que mandamos dentro del JSON
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Si hay una pinche respuesta, esta biene en forma de un Json Object
                        Log.d("TEST!!!!!","Respuesta del servidor"+response);
                        //Log.d("TEST!!!!!","hasta aqui funciona");
                        try {
                            //recapitulando no sabemos que putas manda Fracktal/
                            JSONObject exito = response.getJSONObject("data");
                            String status1=exito.getString("status");
                            switch (status1)
                            {
                                case "5": {mostrar_revision();}break;
                                case "7": {
                                            mostrar_rechazo();
                                            if(rol.matches("Snv")) {realizarPostSnv();}
                                            else realizarPost2();
                                          }break;
                                case "8": {mostrar_aceptado();}break;
                                case "9": {mostrar_cita1(); }break;
                                case "10": {
                                            SharedPreferences preferences1 = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor obj_editor = preferences1.edit();
                                            obj_editor.putInt("State",10);
                                            obj_editor.commit();
                                            getDriverData();
                                            update_registry();
                                            //Intent main = new Intent(MainDocumentosOk.this, MainPopUpUbicacion.class);
                                            //startActivity(main);
                                            //finish();
                                           }break;// asignar state a 10
                            }
                            id = exito.getString("registry_id");
                            Log.d("TEST!!!!!","Exito Status: "+status1);
                            Log.d("TEST!!!!!","Exito id: "+id);
                            JSONArray exito2 = exito.getJSONArray("timeline");
                            for(int i=0; i<exito2.length();i++)
                            {
                                JSONObject exito3 = exito2.getJSONObject(i);
                                String cadena1 = exito3.getString("status");
                                String cadena2 = exito3.getString("date");
                                String cadena3 = exito3.getString("name");
                                String cadena4 = exito3.getString("description");
                                String cadena5 = exito3.getString("success");
                                if(cadena1.matches("9")){
                                    String cita = exito3.getString("appointment");
                                    txt_cita_bd2.setText(cita);
                                    txt_cita_bd2.setVisibility(View.VISIBLE);
                                    txt_cita_bd3.setVisibility(View.VISIBLE);
                                    Log.d("TEST!!!!!","Exito Cita: "+cita);
                                }
                                Log.d("TEST!!!!!","Exito Status: "+cadena1);
                                Log.d("TEST!!!!!","Exito Status: "+cadena2);
                                Log.d("TEST!!!!!","Exito Status: "+cadena3);
                                Log.d("TEST!!!!!","Exito Status: "+cadena4);
                                Log.d("TEST!!!!!","Exito Status: "+cadena5);
                                Log.d("TEST!!!!!","Exito Status: "+cadena5);
                                Log.d("TEST!!!!!","Sigue vivo :)");

                            }
                        } catch (JSONException e) {
                            Log.d("TEST!!!!!","Aqui Murio :(");
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                //
                requestQueue.add(request);// Se añada a la cola el archivo JSON para ser enviado
        }catch (Exception e){
            Log.d("Error JSON","hubo un error fatal");
        }
        refresh.setRefreshing(false);
    }

    private void mostrar_revision()
    {
        txt_aprb_hd.setVisibility(View.GONE);
        txt_aprb_bd.setVisibility(View.GONE);
        btn_aprobado.setVisibility(View.GONE);
        txt_rch_hd.setVisibility(View.GONE);
        txt_rch_bd.setVisibility(View.GONE);
        btn_rechazo.setVisibility(View.GONE);
    }

    private void mostrar_rechazo()
    {
        txt_aprb_hd.setVisibility(View.GONE);
        txt_aprb_bd.setVisibility(View.GONE);
        btn_aprobado.setVisibility(View.GONE);
        txt_rch_hd.setVisibility(View.VISIBLE);
        txt_rch_bd.setVisibility(View.VISIBLE);
        btn_rechazo.setVisibility(View.VISIBLE);

    }
    private void mostrar_aceptado()
    {
        txt_aprb_hd.setVisibility(View.VISIBLE);
        txt_aprb_bd.setVisibility(View.VISIBLE);
        btn_aprobado.setVisibility(View.VISIBLE);
        txt_rch_hd.setVisibility(View.GONE);
        txt_rch_bd.setVisibility(View.GONE);
        btn_rechazo.setVisibility(View.GONE);
    }
    private void mostrar_cita1(){
        mostrar_aceptado();
        txt_cita_hd.setVisibility(View.VISIBLE);
        txt_cita_bd1.setVisibility(View.VISIBLE);
        btn_cita .setVisibility(View.VISIBLE);
    }
    public void realizarPost2() {
        String url = "https://www.tutumapps.com/api/driver/documentsStatus";
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        String tel=preferences.getString("phone","");
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone",tel);
            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag",">>>>>>>>>>>>Respuesta: "+response);
                    try {
                        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
                          SharedPreferences.Editor obj_edit = preferences.edit();
                          JSONObject exito = response.getJSONObject("data");


                          //Obtener los datos de los terminos y condiciones
                          JSONObject terms_array = exito.getJSONObject("terms");
                          String name1 = terms_array.getString("name");
                          String title1 = terms_array.getString("title");
                          String descripcion1 = terms_array.getString("description");
                          String status1 = terms_array.getString("status");
                          String error1 = terms_array.getString("error");
                          String type1 = terms_array.getString("type");
                          Log.d("My Tag",">>>>>>>>>>>>Terminos: "+name1);
                          Log.d("My Tag",">>>>>>>>>>>>Terminos: "+title1);
                          Log.d("My Tag",">>>>>>>>>>>>Terminos: "+descripcion1);
                          Log.d("My Tag",">>>>>>>>>>>>Terminos: "+status1);
                          Log.d("My Tag",">>>>>>>>>>>>Terminos: "+error1);
                          if(status1.matches("2")) {
                              Log.d("Error terms","Hay errores en los terminos");
                              if(rol.matches("Socio")){
                                  obj_edit.putString("terminos1","2");
                                  obj_edit.putString("error1",error1);
                                  obj_edit.putInt("State",7);
                                  obj_edit.commit();
                              }else if(rol.matches("Socio")){
                                  obj_edit.putString("terminos2","2");
                                  obj_edit.putString("error1",error1);
                                  obj_edit.putInt("State",7);
                                  obj_edit.commit();
                              }else{
                                  obj_edit.putString("terminos3","2");
                                  obj_edit.putString("error1",error1);
                                  obj_edit.putInt("State",7);
                                  obj_edit.commit();
                              }

                             }else {
                              Log.d("Error terms","No hay errores en los terminos");
                          }
                          Log.d("My Tag",">>>>>>>>>>>>Terminos: "+type1);

                        //Obtener los datos de la INE
                        JSONObject ine_array = exito.getJSONObject("identification");
                        String name2 = ine_array.getString("name");
                        String title2 = ine_array.getString("title");
                        String descripcion2 = ine_array.getString("description");
                        String status2 = ine_array.getString("status");
                        String error2 = ine_array.getString("error");
                        if(status2.matches("2")){
                            Log.d("Error INE","Nay errores en la INE");
                            if(rol.matches("Socio")){
                                obj_edit.putString("error2",error2);
                                obj_edit.putString("ine1","2");
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else if(rol.matches("Conductor")){
                                obj_edit.putString("error2",error2);
                                obj_edit.putString("ine2","2");
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else{
                                obj_edit.putString("error2",error2);
                                obj_edit.putString("ine3","2");
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }

                        }else{
                            Log.d("Error INE","Nay errores en la INE");
                        }
                        String type2 = ine_array.getString("type");
                        Log.d("My Tag",">>>>>>>>>>>>INE: "+name2);
                        Log.d("My Tag",">>>>>>>>>>>>INE: "+title2);
                        Log.d("My Tag",">>>>>>>>>>>>INE: "+descripcion2);
                        Log.d("My Tag",">>>>>>>>>>>>INE: "+status2);
                        Log.d("My Tag",">>>>>>>>>>>>INE: "+error2);
                        Log.d("My Tag",">>>>>>>>>>>>INE: "+type2);

                        //Obtener los datos de la licencia
                        JSONObject licence_array = exito.getJSONObject("license");
                        String name3 = licence_array.getString("name");
                        String title3 = licence_array.getString("title");
                        String descripcion3 = licence_array.getString("description");
                        String status3 = licence_array.getString("status");
                        String error3 = licence_array.getString("error");
                        if(status3.matches("2")){
                            Log.d("Error Licencia","Hay errores en la Licencia");
                            if(rol.matches("Socio")){
                                obj_edit.putString("error3",error3);
                                obj_edit.putString("licencia1","2");
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else if (rol.matches("Conductor")){
                                obj_edit.putString("error3",error3);
                                obj_edit.putString("licencia2","2");
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else{
                                obj_edit.putString("licencia3","2");
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }

                        }else {
                            Log.d("Error Licencia","No hay errores en la Licencia");
                        }
                        String type3 = licence_array.getString("type");
                        Log.d("My Tag",">>>>>>>>>>>>Licencia: "+name3);
                        Log.d("My Tag",">>>>>>>>>>>>Licencia: "+title3);
                        Log.d("My Tag",">>>>>>>>>>>>Licencia: "+descripcion3);
                        Log.d("My Tag",">>>>>>>>>>>>Licencia: "+status3);
                        Log.d("My Tag",">>>>>>>>>>>>Licencia: "+error3);
                        Log.d("My Tag",">>>>>>>>>>>>Licencia: "+type3);

                        //Obtener los datos de las caracteristicas del vehiculo
                        JSONObject car_array = exito.getJSONObject("vehicle_details");
                        String name4 = car_array.getString("name");
                        String title4 = car_array.getString("title");
                        String descripcion4 = car_array.getString("description");
                        String status4 = car_array.getString("status");
                        String error4 = car_array.getString("error");
                        if(status4.matches("2")){
                            Log.d("Error Caracteristicas","Hay errores en las caracteristicas");
                            if(rol.matches("Socio")){
                                obj_edit.putString("caracteristicas1","2");
                                obj_edit.putString("error4",error4);
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else if(rol.matches("Conductor")){
                                obj_edit.putString("caracteristicas2","2");
                                obj_edit.putString("error4",error4);
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else {
                                     return;
                            }

                        }else{
                            Log.d("Error Caracteristicas","Nay errores en la Caracteristicas");
                        }
                        String type4 = car_array.getString("type");
                        Log.d("My Tag",">>>>>>>>>>>>Caracteristicas: "+name4);
                        Log.d("My Tag",">>>>>>>>>>>>Caracteristicas: "+title4);
                        Log.d("My Tag",">>>>>>>>>>>>Caracteristicas: "+descripcion4);
                        Log.d("My Tag",">>>>>>>>>>>>Caracteristicas: "+status4);
                        Log.d("My Tag",">>>>>>>>>>>>Caracteristicas: "+error4);
                        Log.d("My Tag",">>>>>>>>>>>>Caracteristicas: "+type4);

                        //Obtener los datos de las tarjeta
                        JSONObject card_array = exito.getJSONObject("circulation_card");
                        String name5 = card_array.getString("name");
                        String title5 = card_array.getString("title");
                        String descripcion5 = card_array.getString("description");
                        String status5 = card_array.getString("status");
                        String error5 = card_array.getString("error");
                        String type5 = card_array.getString("type");
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeta: "+name5);
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeta: "+title5);
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeta: "+descripcion5);
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeta: "+status5);
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeta: "+error5);
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeta: "+type5);
                        Log.d("My Tag","RESPUESTA DEL SERVIDOR: "+ status5);


                        if(status5.matches("2"))
                        {
                            Log.d("Error Tarjeta","Hay errores en la tarjeta");
                            if(rol.matches("Socio")){
                                obj_edit.putString("tarjeta1","2");
                                obj_edit.putString("error5",error5);
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else if(rol.matches("Conductor")){
                                obj_edit.putString("tarjeta2","2");
                                obj_edit.putString("error5",error5);
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else { return;

                            }

                        }else {
                            Log.d("Error Tarjeta","No hay errores en la tarjeta");
                        }

                        //Obtener los datos de las poliza
                        JSONObject insurance_array = exito.getJSONObject("insurance");
                        String name6 = insurance_array.getString("name");
                        String title6 = insurance_array.getString("title");
                        String descripcion6 = insurance_array.getString("description");
                        String status6 = insurance_array.getString("status");
                        String error6 = insurance_array.getString("error");

                        String type6 = insurance_array.getString("type");
                        Log.d("My Tag",">>>>>>>>>>>>Poliza: "+name6);
                        Log.d("My Tag",">>>>>>>>>>>>Poliza: "+title6);
                        Log.d("My Tag",">>>>>>>>>>>>Poliza: "+descripcion6);
                        Log.d("My Tag",">>>>>>>>>>>>Poliza: "+status6);
                        Log.d("My Tag",">>>>>>>>>>>>Poliza: "+error6);
                        Log.d("My Tag",">>>>>>>>>>>>Poliza: "+type6);
                        if(status6.matches("2"))
                        {
                            Log.d("My Tag","<<<<<<<<<<<<   Cambio de estado >>>>>>>>>>>>");
                            if(rol.matches("Socio")){
                                obj_edit.putString("poliza1","2");
                                obj_edit.putString("error6",error6);
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else if(rol.matches("Conductor")){
                                obj_edit.putString("poliza2","2");
                                obj_edit.putString("error6",error6);
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else{ return;

                            }

                        }else {
                            Log.d("My Tag","<<<<<<<<<<<<   Cambio de estado22 >>>>>>>>>>>>");
                        }

                        //Obtener los datos de las tarjeton
                        JSONObject control_array = exito.getJSONObject("control_card");
                        String name7 = control_array.getString("name");
                        String title7 = control_array.getString("title");
                        String descripcion7 = control_array.getString("description");
                        String status7 = control_array.getString("status");
                        String error7 = control_array.getString("error");
                        if(status7.matches("2")){
                            if(rol.matches("Socio")){
                                obj_edit.putString("tarjeton1","2");
                                obj_edit.putString("error7",error7);
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else if(rol.matches("Conductor")){
                                obj_edit.putString("tarjeton2","2");
                                obj_edit.putString("error7",error7);
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }else{
                                obj_edit.putString("tarjeton3","2");
                                obj_edit.putString("error7",error7);
                                obj_edit.putInt("State",7);
                                obj_edit.commit();
                            }

                        }
                        String type7 = control_array.getString("type");
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeton: "+name7);
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeton: "+title7);
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeton: "+descripcion7);
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeton: "+status7);
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeton: "+error7);
                        Log.d("My Tag",">>>>>>>>>>>>Tarjeton: "+type7);


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

    public void realizarPostSnv() {
        String url = "https://www.tutumapps.com/api/driver/documentsStatus";
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        String tel=preferences.getString("phone","");
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone",tel);
            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        SharedPreferences preferencias = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
                        SharedPreferences.Editor obj_editor = preferencias.edit();
                        JSONObject exito1 = response.getJSONObject("data");
                        JSONObject terms_array = exito1.getJSONObject("terms");
                        String name1 = terms_array.getString("name");
                        String title1 = terms_array.getString("title");
                        String description1 = terms_array.getString("description");
                        String status1 = terms_array.getString("status");
                        String error1 = terms_array.getString("error");
                        String type1 = terms_array.getString("type");
                        Log.d("Respuesta Terminos",">>>>>Nombre: "+name1);
                        Log.d("Respuesta Terminos",">>>>>Titulo: "+title1);
                        Log.d("Respuesta Terminos",">>>>>Descripcion: "+description1);
                        Log.d("Respuesta Terminos",">>>>>Status: "+status1);
                        Log.d("Respuesta Terminos",">>>>>Error: "+error1);
                        Log.d("Respuesta Terminos",">>>>>Type: "+type1);
                        if(status1.matches("2")){
                            obj_editor.putString("error1",error1);
                            obj_editor.putString("terminos3","2");
                            obj_editor.putInt("State",7);
                            obj_editor.commit();
                        }

                        JSONObject ine_array = exito1.getJSONObject("identification");
                        String name2 = ine_array.getString("name");
                        String title2 = ine_array.getString("title");
                        String description2 = ine_array.getString("description");
                        String status2 = ine_array.getString("status");
                        String error2 = ine_array.getString("error");
                        String type2 = ine_array.getString("type");
                        Log.d("Respuesta INE",">>>>>Nombre: "+name2);
                        Log.d("Respuesta INE",">>>>>Titulo: "+title2);
                        Log.d("Respuesta INE",">>>>>Descripcion: "+description2);
                        Log.d("Respuesta INE",">>>>>Status: "+status2);
                        Log.d("Respuesta INE",">>>>>Error: "+error2);
                        Log.d("Respuesta INE",">>>>>Type: "+type2);
                        if(status2.matches("2")){
                            obj_editor.putString("error2",error2);
                            obj_editor.putString("ine3","2");
                            obj_editor.putInt("State",7);
                            obj_editor.commit();
                        }

                        JSONObject lic_array = exito1.getJSONObject("identification");
                        String name3 = lic_array.getString("name");
                        String title3 = lic_array.getString("title");
                        String description3 = lic_array.getString("description");
                        String status3 = lic_array.getString("status");
                        String error3 = lic_array.getString("error");
                        String type3 = lic_array.getString("type");
                        Log.d("Respuesta Licencia",">>>>>Nombre: "+name3);
                        Log.d("Respuesta Licencia",">>>>>Titulo: "+title3);
                        Log.d("Respuesta Licencia",">>>>>Descripcion: "+description3);
                        Log.d("Respuesta Licencia",">>>>>Status: "+status3);
                        Log.d("Respuesta Licencia",">>>>>Error: "+error3);
                        Log.d("Respuesta Licencia",">>>>>Type: "+type3);
                        if(status3.matches("2")){
                            obj_editor.putString("error3",error3);
                            obj_editor.putString("licencia3","2");
                            obj_editor.putInt("State",7);
                            obj_editor.commit();
                        }

                        JSONObject code_array = exito1.getJSONObject("vehicle_code");
                        String name4 = code_array.getString("name");
                        String title4 = code_array.getString("title");
                        String description4 = code_array.getString("description");
                        String status4 = code_array.getString("status");
                        String error4 = code_array.getString("error");
                        String type4 = code_array.getString("type");
                        Log.d("Respuesta Code",">>>>>Nombre: "+name4);
                        Log.d("Respuesta Code",">>>>>Titulo: "+title4);
                        Log.d("Respuesta Code",">>>>>Descripcion: "+description4);
                        Log.d("Respuesta Code",">>>>>Status: "+status4);
                        Log.d("Respuesta Code",">>>>>Error: "+error4);
                        Log.d("Respuesta Code",">>>>>Type: "+type4);
                        if(status4.matches("2")){
                            obj_editor.putString("error4",error4);
                            obj_editor.putInt("State",7);
                            obj_editor.putString("codigo3","2");
                            obj_editor.commit();
                        }

                        JSONObject tarjeton_array = exito1.getJSONObject("control_card");
                        String name5 = tarjeton_array.getString("name");
                        String title5 = tarjeton_array.getString("title");
                        String description5 = tarjeton_array.getString("description");
                        String status5 = tarjeton_array.getString("status");
                        String error5 = tarjeton_array.getString("error");
                        String type5 = tarjeton_array.getString("type");
                        Log.d("Respuesta Tarjeton",">>>>>Nombre: "+name5);
                        Log.d("Respuesta Tarjeton",">>>>>Titulo: "+title5);
                        Log.d("Respuesta Tarjeton",">>>>>Descripcion: "+description5);
                        Log.d("Respuesta Tarjeton",">>>>>Status: "+status5);
                        Log.d("Respuesta Tarjeton",">>>>>Error: "+error5);
                        Log.d("Respuesta Tarjeton",">>>>>Type: "+type5);
                        if(status5.matches("2")){
                            obj_editor.putString("error5",error5);
                            obj_editor.putInt("State",7);
                            obj_editor.putString("tarjeton3","2");
                            obj_editor.commit();
                        }

                    }catch (Exception e){
                            Log.e("Error TimeLine Doc","Hubo un error en el servidor, por favor intentalo mas tarde");
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

    private void getDriverData(){
        String url = "https://www.tutumapps.com/api/driver/getDriverData";
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        String phone = preferences.getString("phone","");

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final org.json.JSONObject jsonObject = new org.json.JSONObject();

        try {
              jsonObject.put("phone",phone);
              final String requestBody = jsonObject.toString();
              JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                  @Override
                  public void onResponse(JSONObject response) {
                      Log.e("TAG", "Success! :)" + " " + response);
                      try {
                          JSONObject data = response.getJSONObject("data");
                          api_token = data.getString("api_token");
                          Log.e("Login","Api_Token: "+api_token);
                          JSONObject driver = data.getJSONObject("driver");
                          String driver_id = driver.getString("driver_id");
                          String user_id = driver.getString("user_id");
                          String name = driver.getString("name");
                          String alias = driver.getString("alias");
                          String driver_img = driver.getString("driver_img");
                          String email = driver.getString("email");
                          Log.e("Login","driver id: "+driver_id);
                          Log.e("Login","user id: "+user_id);
                          Log.e("Login","name: "+name);
                          Log.e("Login","alias: "+alias);
                          Log.e("Login","driver_img: "+alias);
                          Log.e("Login","drive_img: "+driver_img);
                          Log.e("Login", "email: "+email);
                          JSONObject vehicle = data.getJSONObject("vehicle");
                          String vehicle_id = vehicle.getString("vehicle_id");
                          String title = vehicle.getString("vehicle_title");
                          String year = vehicle.getString("vehicle_year");
                          String status = vehicle.getString("vehicle_status");
                          String color = vehicle.getString("vehicle_color");
                          String plate = vehicle.getString("vehicle_plate");
                          String vehicle_img = vehicle.getString("vehicle_img");

                          Log.e("Login","vehicle_id: "+vehicle_id);
                          Log.e("Login","title: "+title);
                          Log.e("Login","year: "+year);
                          Log.e("Login","status: "+status);
                          Log.e("Login","color: "+color);
                          Log.e("Login","plate: "+plate);
                          Log.e("Login","vehicle_img: "+vehicle_img);


                      } catch (JSONException e) {
                          e.printStackTrace();
                      }

                  }
              }, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {
                      Log.d("Respuesta Servidor", "Error: ");
                  }
              });
            requestQueue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private void update_registry(){
        String url = "https://tutumapps.com/api/driver/updateProfile";
        Log.e("TEST","API: "+api_token);
        //Log.e("TEST","EMAIL: "+email);
        Log.e("TEST","PASSWORD: "+password);
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("email",email);
            jsonObject.put("password", password);
            jsonObject.put("api_token",api_token);


            final String requestBody = jsonObject.toString();


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                        Log.e("Respuesta Registro","Exito!!!: "+response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("My Tag","Error"+error);
                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    return params;}
            };

            requestQueue.add(request);

        }catch (JSONException e){
            e.printStackTrace();
        }

    }

}