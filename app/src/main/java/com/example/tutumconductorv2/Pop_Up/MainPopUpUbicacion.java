package com.example.tutumconductorv2.Pop_Up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.tutumconductorv2.Inicio;
import com.example.tutumconductorv2.R;

public class MainPopUpUbicacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pop_up_ubicacion);

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho*0.80), (int)(alto*0.8));
    }

    public void aceptar_ubicacion(View view){
        Intent main_inicio = new Intent(MainPopUpUbicacion.this, Inicio.class);
        startActivity(main_inicio);
        finish();
    }

    public void rechazar_ubicacion(View view)
    {
        finish();
    }
}