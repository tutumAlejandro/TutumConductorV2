package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ProblemaViaje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema__viaje);
    }

    public void btnRegresarayuda(View V){
        Intent intentIni = new Intent(ProblemaViaje.this, activity_ayuda.class);
        startActivity(intentIni);
    }



}