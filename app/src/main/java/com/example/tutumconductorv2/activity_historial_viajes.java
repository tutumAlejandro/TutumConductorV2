package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class activity_historial_viajes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_viajes);
    }

    public void btnRegresar3(View V){
        Intent intentIni = new Intent(activity_historial_viajes.this, Inicio.class);
        startActivity(intentIni);
    }
}