package com.example.tutumconductorv2.Registro.datos_personales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.tutumconductorv2.R;

public class MainPopUpRegistroFail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pop_up_registro_fail);
        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho * 0.8),(int)(alto * 0.3));
    }
}