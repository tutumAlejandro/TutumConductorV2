package com.example.tutumconductorv2.Registro.datos_personales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutumconductorv2.Inicio;
import com.example.tutumconductorv2.MainVentanaPrincipal;
import com.example.tutumconductorv2.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity<restoredText> extends AppCompatActivity {

    private ImageView logo_tutum;
    private TextView txt_tutum, txt_by;

    // Esta actividad Funciona correctamente fue revisada el dia 03/09/2021
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazamiento de los objetos graficos
        logo_tutum = findViewById(R.id.img_logo_tutum);
        txt_tutum = findViewById(R.id.text_tutum);
        txt_by = findViewById(R.id.text_by);

        //Cargar las animaciones para el logo y los textos
        Animation des_arriba = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_vertical);
        Animation des_abajo = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_vertical2);

        //Asociar las animaciones a los objetos
        logo_tutum.setAnimation(des_arriba);
        txt_by.setAnimation(des_abajo);
        txt_tutum.setAnimation(des_abajo);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                //Buscar en la base de datos si el usuario esta logeado
                SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
                if(preferences.getBoolean("isLoggeg",false)){
                    //Intent inicio = new Intent(MainActivity.this,Inicio.class);
                    startActivity(new Intent(MainActivity.this,Inicio.class));
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }else{
                    //Intent ventanaPrincipal = new Intent(MainActivity.this, MainVentanaPrincipal.class);
                    startActivity( new Intent(MainActivity.this, MainVentanaPrincipal.class));
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            }
        },3000);

    }
}