package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_termina_subir_documento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termina_subir_documento);
    }

    public void btnUnidad5(View V){
        Intent intentIni = new Intent(activity_termina_subir_documento.this, activity_unidades.class);
        startActivity(intentIni);
    }
}