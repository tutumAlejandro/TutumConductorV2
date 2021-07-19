package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.adapters.CardViewDatos;
import com.example.tutumconductorv2.adapters.CardViewMensajes;
import com.example.tutumconductorv2.adapters.CardViewMensajes_Adapter;
import com.example.tutumconductorv2.adapters.CardViewMensajes_Adapter2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatReportes extends AppCompatActivity {

    private String api_token;
    private RecyclerView recyclerChat_tecnico;
    private CardViewMensajes_Adapter msg_adapter;
    private CardViewMensajes_Adapter2 msg_adapter_usr;
    List<CardViewMensajes> msg_platform;
    private Button btn_responder_reporte,btn_back_chat;
    //private int[] id_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_reportes);
        recyclerChat_tecnico = findViewById(R.id.recicler_chat_tecnico);
        btn_responder_reporte = findViewById(R.id.btn_response_report);
        btn_back_chat = findViewById(R.id.editReporte_backbutton_chat);

        SharedPreferences data = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
        getChatReport(data.getString("api_token",""),data.getInt("id_report",0));

        msg_platform = new ArrayList<>();
        recyclerChat_tecnico.setLayoutManager(new LinearLayoutManager(this));
        msg_adapter = new CardViewMensajes_Adapter(msg_platform);
        /////////////////////////////////////////////
        msg_adapter_usr = new CardViewMensajes_Adapter2(msg_platform);

        btn_responder_reporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent respuesta = new Intent(ChatReportes.this, RespuestaReporteUsuario.class);
                startActivity(respuesta);
                finish();
            }
        });
        btn_back_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent respuesta = new Intent(ChatReportes.this, ReportarProblemaTecnico.class);
                startActivity(respuesta);
                finish();
            }
        });

    }

    private void getChatReport(String api_token,int id_report){
        String url = "https://www.tutumapps.com/api/driver/responsesReport";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("email",email);
            jsonObject.put("report_id",id_report);
            jsonObject.put("api_token",api_token);
            final String requestBody = jsonObject.toString();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Char Reporte","RESPUESTA: "+response);
                    try {
                        JSONArray data = response.getJSONArray("data");
                        Log.e("tamaño de la respuesta","tamaño: "+data.length());
                        for(int i=0;i<data.length();i++){
                            JSONObject msg = data.getJSONObject(i);
                            String test_msg = msg.getString("response");
                            String url_imagen = msg.getString("captura");
                            String type_usr;
                            if(msg.getString("type_user").matches("Administrador") || msg.getString("type_user").matches("Supervisor")){
                                type_usr="TUTUM APP";
                                url_imagen = url_imagen.replace("https:\\/\\/www.tutumapps.com\\/media\\/reports\\","https://www.tutumapps.com/media/reports/");
                                msg_platform.add(new CardViewMensajes(test_msg,type_usr,url_imagen));
                            }else{
                                type_usr="Usuario";
                                url_imagen = url_imagen.replace("https:\\/\\/www.tutumapps.com\\/media\\/reports\\","https://www.tutumapps.com/media/reports/");
                                msg_platform.add(new CardViewMensajes(test_msg,type_usr,url_imagen));
                            }

                        }
                        recyclerChat_tecnico.setAdapter(msg_adapter);
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