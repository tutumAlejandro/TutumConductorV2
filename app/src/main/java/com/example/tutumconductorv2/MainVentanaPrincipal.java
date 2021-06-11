package com.example.tutumconductorv2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tutumconductorv2.Registro.datos_personales.MainContinuarRegistro;
import com.example.tutumconductorv2.Registro.datos_personales.MainRegistrate;
import com.example.tutumconductorv2.Registro.menus_rol.MainDocumentosOk;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

public class MainVentanaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ventana_principal);

    }
    public void btn_registrate(View v)
    {
        Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainContinuarRegistro.class);
        startActivity(main_registrate);

    }
    public void btn_inicia_sesion(View v)
    {
        Intent main_inicio_sesion = new Intent(MainVentanaPrincipal.this,Main_IniciaSesion.class);
        startActivity(main_inicio_sesion);
    }
}