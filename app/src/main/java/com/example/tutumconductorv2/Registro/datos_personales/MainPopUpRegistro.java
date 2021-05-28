package com.example.tutumconductorv2.Registro.datos_personales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.example.tutumconductorv2.MainActivity;
import com.example.tutumconductorv2.MainVentanaPrincipal;
import com.example.tutumconductorv2.R;

public class MainPopUpRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pop_up_registro);

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho*1),(int)(alto * 0.45));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent main_otp = new Intent(MainPopUpRegistro.this,MainOTP.class);
                startActivity(main_otp);
                finish();
            }
        },2000);
    }
}