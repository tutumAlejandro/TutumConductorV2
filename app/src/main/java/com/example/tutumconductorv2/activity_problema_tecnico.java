package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_problema_tecnico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema_tecnico);
    }

    public void btnRegresarTecnico(View V){
        Intent intentIni = new Intent(activity_problema_tecnico.this, ReportarProblemaTecnico.class);
        startActivity(intentIni);
    }
}