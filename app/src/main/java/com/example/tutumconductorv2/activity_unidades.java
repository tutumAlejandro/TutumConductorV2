package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class activity_unidades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidades);
    }


    public void addUnidad(View view)
    {
        Intent nuevaUnidad = new Intent(activity_unidades.this, MainCapturaCaracteristicas .class);
        startActivity(nuevaUnidad);
        //finish();
    }

    public void terminarSubir(View view)
    {
        Intent terminardoctos = new Intent(activity_unidades.this, MainCapturaCaracteristicas .class);
        startActivity(terminardoctos);
        //finish();
    }

}