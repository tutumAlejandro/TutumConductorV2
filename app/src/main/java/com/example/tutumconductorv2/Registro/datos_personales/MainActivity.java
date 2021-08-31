package com.example.tutumconductorv2.Registro.datos_personales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutumconductorv2.Inicio;
import com.example.tutumconductorv2.MainVentanaPrincipal;
import com.example.tutumconductorv2.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity<restoredText> extends AppCompatActivity {

    private boolean isUserLogin = true;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    /*Actividad princiipal la cual muestra el logo de Tutum y una pequeña animacion que dura 2 seg
       En esta actividad se revisa si existe un correo almacenado en la base de datos Shared Preferences
       En caso de que encuentre un correo almacenado en la base de datos la aplicacion omite el inicio de sesion
       y manda al usuario a la pantalla principal

    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
                editor = preferences.edit();

                if(preferences.contains("correo")){
                    Intent main_inicio = new Intent(MainActivity.this, Inicio.class);
                    startActivity(main_inicio);
                    finish();
                }else{
                    Intent main_login = new Intent(MainActivity.this, MainVentanaPrincipal.class);
                    startActivity(main_login);
                    finish();
                }

            }
        },2000);
    }
}