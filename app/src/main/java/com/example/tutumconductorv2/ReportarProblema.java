package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
}