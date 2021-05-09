package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_detalle_unidad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_unidad);
    }

    public void btnRegresar4(View V){
        Intent intentIni = new Intent(activity_detalle_unidad.this, activity_unidades.class);
        startActivity(intentIni);
    }
}