package com.example.tutumconductorv2.Registro.menus_rol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainTerminosYCondiciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;

public class MainDocumentosOk extends AppCompatActivity {

    private String url_timeline="https://www.tutumapps.com/api/driver/registryTimelineStatus";

    private SwipeRefreshLayout refresh;
    private TextView txt_rch_hd,txt_rch_bd,txt_aprb_hd,txt_aprb_bd;
    private TextView txt_cita_hd,txt_cita_bd1, txt_cita_bd2,txt_cita_bd3;
    private ImageView btn_cita;
    private ImageView btn_rechazo,btn_aprobado;

    private String rol="";
    private String status ="";
    private String id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_documentos_ok);


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

        txt_aprb_hd.setVisibility(View.GONE);
        txt_aprb_bd.setVisibility(View.GONE);
        btn_aprobado.setVisibility(View.GONE);
        txt_rch_hd.setVisibility(View.GONE);
        txt_rch_bd.setVisibility(View.GONE);
        btn_rechazo.setVisibility(View.GONE);


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


    }
    private void realizarPost(String url) {
        refresh.setRefreshing(true);
        try {
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("phone","4495846875");
                final String requestBody = jsonObject.toString(); // Con esta cadena podemos ver que mandamos dentro del JSON
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Si hay una pinche respuesta, esta biene en forma de un Json Object
                        JSONObject jsonObject1 = response;
                        Log.d("TEST!!!!!","Respuesta del servidor"+response);
                        //Log.d("TEST!!!!!","hasta aqui funciona");
                        try {
                            //recapitulando no sabemos que putas manda Fracktal/
                            JSONObject exito = jsonObject1.getJSONObject("data");
                            status = exito.getString("status");
                            switch (status)
                            {
                                case "7": {mostrar_rechazo();}break;
                                case "8": {mostrar_aceptado();}break;
                                case "9": {mostrar_cita1();}break;
                                case "10": {}break;
                            }
                            id = exito.getString("registry_id");
                            Log.d("TEST!!!!!","Exito Status: "+status);
                            Log.d("TEST!!!!!","Exito id: "+id);
                            JSONArray exito2 = exito.getJSONArray("timeline");
                            for(int i=0; i<exito2.length();i++)
                            {
                                JSONObject exito3 = exito2.getJSONObject(i);
                                String cadena1 = exito3.getString("status");
                                String cadena2 = exito3.getString("date");
                                String cadena3 = exito3.getString("name");
                                String cadena4 = exito3.getString("descripcion");
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

}