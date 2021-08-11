package com.example.tutumconductorv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
import com.example.tutumconductorv2.adapters.CardViewDatos;
import com.example.tutumconductorv2.adapters.HistoryBookingDriverAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class activity_historial_viajes extends AppCompatActivity {


    private String api_token;
    private RecyclerView card_history_booking;
    private HistoryBookingDriverAdapter heads_adapter;
    List<CardViewDatos> heads_list;
    private int[] id_viaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_viajes);
        card_history_booking = findViewById(R.id.recyclerViewHistoryBooking);
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
        api_token = preferences.getString("api_token","");
        heads_list = new ArrayList<>();
        HistorialViaje(api_token);

        card_history_booking.setLayoutManager(new LinearLayoutManager(this));


    }

    public void btnRegresar3(View V){
        Intent intentIni = new Intent(activity_historial_viajes.this, Inicio.class);
        startActivity(intentIni);
    }

    private void HistorialViaje(String api_token){
        String url = "https://www.tutumapps.com/api/driver/journeyHistory";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            jsonObject.put("type","historial");
            jsonObject.put("api_token",api_token);
            final String requestBody = jsonObject.toString();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.e("Historial de Viajes" +
                                "","Rerspuesta!!!: "+response);
                        //test_check.setText(response.getString("success"));
                        if(response.getString("success").matches("true"))
                        {
                            JSONArray data = response.getJSONArray("data");
                            //Log.e("Numero de reportes","No: "+data.length());
                            id_viaje = new int[data.length()];
                            for(int i=0;i<data.length();i++){
                                JSONObject report_body = data.getJSONObject(i);
                                String title =  report_body.getString("title");
                                id_viaje[i] = report_body.getInt("id");
                                Log.e("id_repor","id: "+report_body.getInt("id"));
                                heads_list.add(new CardViewDatos(title));

                            }
                            card_history_booking.setAdapter(heads_adapter);
                        }else
                        {
                            Log.e("Respuesta Historial","ERROR!!!: "+response);

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
















   /* RecyclerView recyclerView;
    private RecyclerView mReciclerView;
    //private HistoryBookingDriverAdapter mAdapter;
    private AuthProvider mAuthProvider;
    private FirebaseAuth mAuth ;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_viajes);

        SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
       *//* SharedPreferences.Editor obj_editor = preferences.edit();

        obj_editor.commit();*//*
        obtenerDatos(preferences.getString("api_token",""));


        mReciclerView = findViewById(R.id.recyclerViewHistoryBooking);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mReciclerView.setLayoutManager(linearLayoutManager);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        recyclerView = findViewById(R.id.recyclerViewHistoryBooking);
        ArrayList HistoryBooking = new ArrayList();
        String api_token = new String();
        //obtenerDatos(api_token);
        HistoryBookingDriverAdapter adaptadorRecycler = new HistoryBookingDriverAdapter();
        recyclerView.setAdapter(adaptadorRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    public void btnRegresar3(View V){
        Intent intentIni = new Intent(activity_historial_viajes.this, Inicio.class);
        startActivity(intentIni);
    }

    private String api_token;
    private ArrayList<HistoryBooking> lista = new ArrayList<HistoryBooking>();
    private void obtenerDatos(String api_token) {
        String url = "https://www.tutumapps.com/api/driver/journeyHistory";

        JsonObjectRequest peticionJSON = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final JSONObject jsonObject = new JSONObject();
                    JSONArray arrayJSON = response.getJSONArray("results");
                    //arrayJSON.put(Integer.parseInt("api_token"),api_token);
                    //jsonObject.put("api_token",api_token);
                    final String requestBody = jsonObject.toString();
                    Log.v("test",arrayJSON.toString());

                    for (int i=0;i<arrayJSON.length();i++){
                        jsonObject.put("api_token",api_token);
                        JSONObject resultado = arrayJSON.getJSONObject(i);
                        String titulo = (String) resultado.get("origen");
                       // String imagen = "https://image.tmdb.org/t/p/w500/"+(String) resultado.get("poster_path");
                        //int valoracion = (int) resultado.get("vote_average");
                        //double r = (double)((Integer)((Object)4));
                        HistoryBooking p = new HistoryBooking(titulo);
                        lista.add(p);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                return params;}
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(peticionJSON);
    }*/






 /*   @Override
    protected void onStart() {
        super.onStart();
       mAuthProvider = new AuthProvider();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("HistoryBooking")
                .orderByChild("driver_id")
                .equalTo(mAuthProvider.driver_id());
        FirebaseRecyclerOptions<HistoryBooking> options = new FirebaseRecyclerOptions.Builder<HistoryBooking>()
                .setQuery(query, HistoryBooking.class)
                .build();
        mAdapter = new HistoryBookingDriverAdapter(options, activity_historial_viajes.this);

        mReciclerView.setAdapter(mAdapter);
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }*/


    @Override
    public void onBackPressed(){

    }
}
