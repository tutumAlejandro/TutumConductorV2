package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ReportarProblemaTecnico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_problema_tecnico);
    }

    public void btnRegresarayuda2(View V){
        Intent intentIni = new Intent(ReportarProblemaTecnico.this, activity_ayuda.class);
        startActivity(intentIni);
    }

}