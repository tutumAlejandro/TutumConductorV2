package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class caracteristicas_nueva_unidad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caracteristicas_nueva_unidad);
    }

    public void btnUnidad3(View V){
        Intent intentIni = new Intent(caracteristicas_nueva_unidad.this, activity_unidades.class);
        startActivity(intentIni);
    }
}