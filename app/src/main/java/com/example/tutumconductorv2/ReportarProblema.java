package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ReportarProblema extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_problema);
    }

    public void btnRegresarayuda1(View V){
        Intent intentIni = new Intent(ReportarProblema.this, activity_ayuda.class);
        startActivity(intentIni);
    }

    public void btnNuevoReporte(View V){
        Intent intentIni = new Intent(ReportarProblema.this, ProblemaViaje.class);
        startActivity(intentIni);
    }
    public void btnHistorial(View V){
        Intent intentIni = new Intent(ReportarProblema.this, activity_chat_reporte.class);
        startActivity(intentIni);
    }
}