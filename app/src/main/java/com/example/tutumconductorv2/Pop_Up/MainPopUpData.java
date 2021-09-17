package com.example.tutumconductorv2.Pop_Up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.RegistroConductor.DatosPersonales.MainRegistrate;

public class MainPopUpData extends AppCompatActivity {

    private Button accept, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pop_up_data);

        accept = findViewById(R.id.btn_aceptar_terminos_data);
        cancel = findViewById(R.id.btn_cancelar_terminos_data);

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho*0.80), (int)(alto*0.8));

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_registrate = new Intent(MainPopUpData.this, MainRegistrate.class);
                startActivity(main_registrate);
                finish();
            }
        });


    }
    public void rechazar_terminos_data(View v){
        finish();
    }

}