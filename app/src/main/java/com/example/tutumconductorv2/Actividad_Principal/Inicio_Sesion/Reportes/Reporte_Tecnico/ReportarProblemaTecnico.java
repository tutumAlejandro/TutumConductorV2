package com.example.tutumconductorv2.Actividad_Principal.Inicio_Sesion.Reportes.Reporte_Tecnico;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.Actividad_Principal.Inicio_Sesion.Reportes.ChatReportes;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.activity_ayuda;
import com.example.tutumconductorv2.adapters.CardViewDatos;
import com.example.tutumconductorv2.adapters.CardView_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportarProblemaTecnico extends AppCompatActivity {

    private String api_token;
    private RecyclerView recycler_reports;
    private CardView_Adapter heads_adapter;
    List<CardViewDatos> heads_list;
    private int[] id_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_problema_tecnico);
        recycler_reports = findViewById(R.id.recycler_problem_tech);
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token","");
        heads_list = new ArrayList<>();
        getReportHistorial(api_token);

        recycler_reports.setLayoutManager(new LinearLayoutManager(this));
        heads_adapter = new CardView_Adapter(heads_list);

        heads_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"seleccion:"+
                        heads_list.get(recycler_reports.getChildAdapterPosition(v)).getHeader(),Toast.LENGTH_SHORT).show();
                        SharedPreferences data_report = getSharedPreferences("Datos_Usuario_Login",Context.MODE_PRIVATE);
                        SharedPreferences.Editor obj_editor = data_report.edit();
                        obj_editor.putString("head_report",heads_list.get(recycler_reports.getChildAdapterPosition(v)).getHeader());
                        obj_editor.putInt("id_report",id_report[recycler_reports.getChildAdapterPosition(v)]);
                        obj_editor.commit();
                        Intent chat_report = new Intent(ReportarProblemaTecnico.this, ChatReportes.class);
                        startActivity(chat_report);

            }
        });





    }

    public void btnRegresarayuda2(View V){
        Intent intentIni = new Intent(ReportarProblemaTecnico.this, activity_ayuda.class);
        startActivity(intentIni);
    }
    public void btnNuevoTecnico(View V){
        Intent intentIni = new Intent(ReportarProblemaTecnico.this, activity_problema_tecnico.class);
        startActivity(intentIni);
    }

    private void getReportHistorial(String api_token){
        String url = "https://www.tutumapps.com/api/driver/reports";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("email",email);
            jsonObject.put("type","tecnico");
            jsonObject.put("api_token",api_token);
            final String requestBody = jsonObject.toString();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.e("Respuesta Reportes" +
                                "","Rerspuesta!!!: "+response);
                        //test_check.setText(response.getString("success"));
                        if(response.getString("success").matches("true"))
                        {
                            JSONArray data = response.getJSONArray("data");
                            Log.e("Numero de reportes","No: "+data.length());
                            id_report = new int[data.length()];
                            for(int i=0;i<data.length();i++){
                                JSONObject report_body = data.getJSONObject(i);
                                String title =  report_body.getString("title");
                                id_report[i] = report_body.getInt("id");
                                Log.e("id_repor","id: "+report_body.getInt("id"));
                                heads_list.add(new CardViewDatos(title));

                            }
                            recycler_reports.setAdapter(heads_adapter);
                        }else
                        {
                            Log.e("Respuesta Registro","ERROR!!!: "+response);

                        }
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