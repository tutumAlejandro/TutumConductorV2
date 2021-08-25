package com.example.tutumconductorv2.Registro.datos_personales;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutumconductorv2.MainVentanaPrincipal;
import com.example.tutumconductorv2.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity<restoredText> extends AppCompatActivity {

    private String IsLogged;

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {

                Intent main_login = new Intent(MainActivity.this, MainVentanaPrincipal.class);
                startActivity(main_login);
                finish();
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