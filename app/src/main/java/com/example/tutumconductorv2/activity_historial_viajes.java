package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.adapters.HistoryBookingDriverAdapter;
import com.example.tutumconductorv2.models.HistoryBooking;
import com.example.tutumconductorv2.providers.AuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class activity_historial_viajes extends AppCompatActivity {
    RecyclerView recyclerView;
    private RecyclerView mReciclerView;
    //private HistoryBookingDriverAdapter mAdapter;
    private AuthProvider mAuthProvider;
    private FirebaseAuth mAuth ;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_booking_driver);


        mReciclerView = findViewById(R.id.recyclerViewHistoryBooking);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mReciclerView.setLayoutManager(linearLayoutManager);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        recyclerView = findViewById(R.id.recyclerViewHistoryBooking);
        ArrayList HistoryBooking = new ArrayList();
        String api_token = new String();
        obtenerDatos(HistoryBooking, api_token);
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

    private ArrayList<String> lista = new ArrayList<>();
    private void obtenerDatos(final ArrayList lista, String api_token) {
        String url = "https://www.tutumapps.com/api/driver/journeyHistory";

        JsonObjectRequest peticionJSON = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final org.json.JSONObject jsonObject = new org.json.JSONObject();
                    JSONArray arrayJSON = response.getJSONArray("results");
                    arrayJSON.put(Integer.parseInt("api_token"),api_token);
                    Log.v("test",arrayJSON.toString());

                    for (int i=0;i<arrayJSON.length();i++){

                        JSONObject resultado = arrayJSON.getJSONObject(i);
                        String titulo = (String) resultado.get("title");
                        String imagen = "https://image.tmdb.org/t/p/w500/"+(String) resultado.get("poster_path");
                        int valoracion = (int) resultado.get("vote_average");
                        //double r = (double)((Integer)((Object)4));
                        HistoryBooking p = new HistoryBooking(titulo,imagen,valoracion);
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
    }






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
