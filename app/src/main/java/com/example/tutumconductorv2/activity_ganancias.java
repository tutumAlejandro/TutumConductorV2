package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class activity_ganancias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganancias);
    }

    public void btnRegresar2(View V){
        Intent intentIni = new Intent(activity_ganancias.this, Inicio.class);
        startActivity(intentIni);
    }

    @Override
    public void onBackPressed(){

    }
}