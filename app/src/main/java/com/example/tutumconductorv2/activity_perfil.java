package com.example.tutumconductorv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class activity_perfil extends AppCompatActivity {

    private TextView name, phone, email, vehicle_model,calification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Switch switch11 = findViewById(R.id.switch11);
int a=0;
        switch11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
            }
        });
        name = findViewById(R.id.profile_driver_name);
        phone = findViewById(R.id.profile_driver_phone);
        email = findViewById(R.id.profile_driver_email);
        vehicle_model = findViewById(R.id.vehiculo_asociado);
        calification = findViewById(R.id.NumCal);
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
        name.setText(preferences.getString("name",""));
        phone.setText(preferences.getString("phone",""));
        email.setText(preferences.getString("email",""));
        vehicle_model.setText(preferences.getString("vehicle_manufacturer","")+" "+preferences.getString("vehicle_model",""));
        calification.setText(String.valueOf(preferences.getInt("calification", 0)));

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
    public void CerrarSesion(View view){
        Intent intSingOut = new Intent(activity_perfil.this, MainVentanaPrincipal.class);
        intSingOut.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intSingOut.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intSingOut);
    }



    @Override
    public void onBackPressed(){

    }
}