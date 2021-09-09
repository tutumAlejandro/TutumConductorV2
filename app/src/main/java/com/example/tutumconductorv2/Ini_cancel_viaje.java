package com.example.tutumconductorv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ini_cancel_viaje extends AppCompatActivity {

    private TextView tiempo, distancia, precio,origen,destino,nombre_pasajero;
    String cad1= "256",cad2="45.50",cad3="20:50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_ini_cancel_viaje);

       // Adecuar la vista para que la solicitud de viaje se muestre como ventana flotante
        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);
        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;
        getWindow().setLayout((int)(ancho*0.80), (int)(alto*0.8));
        //----------------------------------------------------------------------------------
        SharedPreferences preferences = getSharedPreferences("Datos_Viaje", Context.MODE_PRIVATE);
        // Asociacion de los xml con la parte del java
        nombre_pasajero = findViewById(R.id.name_passenger_request);
        origen = findViewById(R.id.request_origin);
        destino = findViewById(R.id.request_detiny);
        precio = findViewById(R.id.price_request);
        tiempo = findViewById(R.id.time_request);
        distancia = findViewById(R.id.distance_request);
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

    }


    public void Regresar(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(Ini_cancel_viaje.this);
        alerta.setMessage("Desea cancelar el viaje?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish(); //funcion en la que se debe regresar a la pantalla del viaje
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel(); //se mantiene en la misma pantalla para poder iniciar el viaje
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Confirmacion");
        titulo.show();
    }
    public void Iniciar(){
        Toast.makeText(this, "Viaje iniciado",Toast.LENGTH_SHORT).show(); //mensaje para indicar que el viaje inicio
        finish();
    }
}