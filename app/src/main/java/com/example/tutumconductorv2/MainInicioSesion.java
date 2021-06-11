package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


public class MainInicioSesion extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inicio_sesion);





        Switch sw = (Switch) findViewById(R.id.on_off);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainInicioSesion.this, " Habilitado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainInicioSesion.this, " Deshabilitado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
