package com.example.tutumconductorv2.Actividad_Principal.Inicio_Sesion.Reportes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tutumconductorv2.R;

public class activity_chat_reporte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_reporte);
    }
    public void btnRegresarProblema1(View V){
        Intent intentIni = new Intent(activity_chat_reporte.this, ReportarProblema.class);
        startActivity(intentIni);
        for(int i =0; i<10;i++){

        }
    }
}