package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class activity_perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
    }
  public void actCorreo(View V){
        Intent intentIni = new Intent(activity_perfil.this, CambioCorreo.class);
        startActivity(intentIni);
    }

    public void actContrasena(View V){
        Intent intentIni = new Intent(activity_perfil.this, CambioContrasena.class);
        startActivity(intentIni);
    }

    public void acTelefono(View V){
        Intent intentIni = new Intent(activity_perfil.this, CambioTelefono.class);
        startActivity(intentIni);
   }

    public void btnRegresar1(View V){
        Intent intentIni = new Intent(activity_perfil.this, Inicio.class);
        startActivity(intentIni);
    }
}