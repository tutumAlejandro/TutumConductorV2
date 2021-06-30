package com.example.tutumconductorv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_perfil extends AppCompatActivity {

    private TextView name, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        name = findViewById(R.id.profile_driver_name);
        phone = findViewById(R.id.profile_driver_phone);
        email = findViewById(R.id.profile_driver_email);

        SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
        name.setText(preferences.getString("name",""));
        phone.setText(preferences.getString("phone",""));
        email.setText(preferences.getString("email",""));
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