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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
       // initialize();

    /*    executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (IsLogged.equals("YES")) {
                    Intent i = new Intent(MainActivity.this, Inicio.class);
                    startActivity(i);
                    finish();
                }
            }
        });*/


      /*  SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);

        if (preferences.contains(“isUserLogin”)) {
            Intent intent = new Intent(MainActivity.this, HomeFragment.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, MainVentanaPrincipal .class);
            startActivity(intent);
        }*/

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

               /* Intent main_login = new Intent(MainActivity.this, MainVentanaPrincipal.class);
                startActivity(main_login);
                finish();*/
            }
        },2000);
       // inicio();





    }
















/*private void inicio(){
    if(IsLogged.equals("NO") || IsLogged.equals("isntEXIST")){

        Intent i = new Intent(MainActivity.this, MainVentanaPrincipal.class);
        startActivity(i);
        finish();

    }
    else if(IsLogged.equals("YES"))
    {
        Intent i = new Intent(MainActivity.this, Inicio.class);
        startActivity(i);
        finish();
    }
}

    private void initialize(){
        IsLogged = sharedPreferencesFunct();
    }


    private String sharedPreferencesFunct() {
        SharedPreferences sharedPreferences = getSharedPreferences("isLogged", Context.MODE_PRIVATE);
        String defaultValue = "isntEXIST";
        return sharedPreferences.getString("isLogged", defaultValue);
    }*/

    }