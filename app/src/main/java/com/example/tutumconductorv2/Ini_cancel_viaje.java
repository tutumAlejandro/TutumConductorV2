package com.example.tutumconductorv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Ini_cancel_viaje extends AppCompatActivity {

    private TextView tiempo, distancia, precio,origen,destino,nombre_pasajero;
    String cad1= "256",cad2="45.50",cad3="20:50";
    private Button btn_acpt, btn_cancel;
    private String journey_id;
    private String api_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_ini_cancel_viaje);

       // Adecuar la vista para que la solicitud de viaje se muestre como ventana flotante
        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);
        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;
        getWindow().setLayout((int)(ancho*0.90), (int)(alto*0.8));
        //----------------------------------------------------------------------------------
        SharedPreferences preferences = getSharedPreferences("Datos_Viaje", Context.MODE_PRIVATE);
        // Asociacion de los xml con la parte del java
        nombre_pasajero = findViewById(R.id.name_passenger_request);
        origen = findViewById(R.id.request_origin);
        destino = findViewById(R.id.request_detiny);
        precio = findViewById(R.id.price_request);
        tiempo = findViewById(R.id.time_request);
        distancia = findViewById(R.id.distance_request);

        btn_acpt = findViewById(R.id.btn_confirm_request);
        btn_cancel = findViewById(R.id.btn_cancel_request);
        /**Valores que nos interesan de la base de datos Shared Preferences
         * "pick_address"
         * "drop_address"
         * "duration"
         * "distance"
         * "origin_lat"
         * "origin_lng"
         * "destiny_lat"
         * "destiny_lng"
         * "fare"
         * "passenger_name"
         * "passenger_score"
         * "passenger_img"
         * "journey_id"
         * "payment_method"
         * "has_pending_payment"
         * "cancellation_price"
         */
        nombre_pasajero.setText(preferences.getString("passenger_name",""));
        origen.setText(preferences.getString("pick_address",""));
        destino.setText(preferences.getString("drop_address",""));
        precio.setText("$"+preferences.getString("fare",""));
        tiempo.setText(preferences.getString("duration","")+"min");
        distancia.setText(preferences.getString("distance","")+"km");
        journey_id = preferences.getString("journey_id","");

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_acpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
                Log.e("api token","api token: "+preferences.getString("api_token",""));
                Log.e("Journey_id","Valor Journey_id: "+journey_id);
                accepTravel(preferences.getString("api_token",""),Integer.parseInt(journey_id));
                finish();
            }
        });

    }

    public void accepTravel(String api_token, int journey){
        String url = "https://www.tutumapps.com/api/driver/acceptJourney";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("email",email);
            jsonObject.put("journey_id",journey);
            jsonObject.put("api_token",api_token);


            final String requestBody = jsonObject.toString();


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("respuesta viaje", "respuesta: "+response);

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