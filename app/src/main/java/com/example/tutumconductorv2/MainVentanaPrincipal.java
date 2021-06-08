package com.example.tutumconductorv2;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.tutumconductorv2.Registro.datos_personales.MainContinuarRegistro;
import com.example.tutumconductorv2.Registro.datos_personales.MainOTP;
import com.example.tutumconductorv2.Registro.datos_personales.MainRegistrate;
import com.example.tutumconductorv2.Registro.datos_personales.MainRegistroTelefono;
import com.example.tutumconductorv2.Registro.menus_rol.MainRolConductor;

public class MainVentanaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ventana_principal);
        SharedPreferences preferences_user = getSharedPreferences("Datos_usuario", Context.MODE_PRIVATE);

    }
    public void btn_registrate(View v)
    {
        SharedPreferences preferences_user = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        int state = preferences_user.getInt("State",0);
        if (state == 0)
        {
            Intent main_registrate = new Intent(MainVentanaPrincipal.this, MainRegistrate.class);
            startActivity(main_registrate);
        }else{
            Intent main_continuar_registro = new Intent(MainVentanaPrincipal.this, MainContinuarRegistro.class);
            startActivity(main_continuar_registro);
        }



    }
    public void btn_inicia_sesion(View v)
    {
        Intent main_inicio_sesion = new Intent(MainVentanaPrincipal.this,Main_IniciaSesion.class);
        startActivity(main_inicio_sesion);
    }
}