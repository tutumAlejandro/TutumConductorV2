package com.example.tutumconductorv2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tutumconductorv2.Registro.datos_personales.MainRegistrate;

public class MainVentanaPrincipal extends AppCompatActivity {

    private static String TempDataRegister = "TempData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ventana_principal);

    }
    public void btn_registrate(View v)
    {
        Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainRegistrate.class);
        startActivity(main_registrate);
        finish();
    }
    public void btn_inicia_sesion(View v)
    {
        Intent main_inicio_sesion = new Intent(MainVentanaPrincipal.this,Main_IniciaSesion.class);
        startActivity(main_inicio_sesion);
    }
}