package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_chat_reporte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_reporte);
    }
    public void btnRegresarProblema1(View V){
        Intent intentIni = new Intent(activity_chat_reporte.this, ReportarProblema.class);
        startActivity(intentIni);
    }
}