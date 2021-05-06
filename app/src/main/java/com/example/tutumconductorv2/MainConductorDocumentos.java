package com.example.tutumconductorv2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainConductorDocumentos extends AppCompatActivity {

    private ImageView btn_regreso_conductor;
    private ImageView btn_terminos_conductor;
    private ImageView btn_ine_conductor;
    private ImageView btn_licencia_conductor;
    private ImageView btn_caracteristicas_conductor;
    private ImageView btn_tarjeta_conductor;
    private ImageView btn_poliza_conductor;
    private ImageView btn_tarjeton_conductor;

    private String rol= "Conductor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conductor_documentos);

        btn_regreso_conductor = findViewById(R.id.img_retroceso_documentos_conductor);

        btn_terminos_conductor = findViewById(R.id.btn_terminos_conductor);
        btn_ine_conductor = findViewById(R.id.btn_ine_conductor);
        btn_licencia_conductor = findViewById(R.id.btn_licencia_conductor);
        btn_caracteristicas_conductor = findViewById(R.id.btn_caracteristicas_conductor);
        btn_tarjeta_conductor = findViewById(R.id.btn_tarjeta_conductor);
        btn_poliza_conductor = findViewById(R.id.btn_poliza_conductor);
        btn_tarjeton_conductor = findViewById(R.id.btn_tarjeton_conductor);


        btn_regreso_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainConductorDocumentos.this, MainRolConductor.class);
                /*  Pasar los datos del registro entre ventanas*/
                startActivity(main_rol_conductor);
            }
        });

        btn_terminos_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos_conductor = new Intent(MainConductorDocumentos.this, MainTerminosYCondiciones.class);
                main_terminos_conductor.putExtra("rol", rol);
                startActivity(main_terminos_conductor);
            }
        });
        btn_ine_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_ine_conductor = new Intent(MainConductorDocumentos.this, MainCapturaIne.class);
                main_ine_conductor.putExtra("rol",rol);
                startActivity(main_ine_conductor);
            }
        });
        btn_licencia_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_licencia_conductor = new Intent(MainConductorDocumentos.this,MainCapturaLicencia.class);
                main_licencia_conductor.putExtra("rol",rol);
                startActivity(main_licencia_conductor);
            }
        });
        btn_caracteristicas_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_caracteristicas_conductor = new Intent(MainConductorDocumentos.this, MainCapturaCaracteristicas.class);
                main_caracteristicas_conductor.putExtra("rol",rol);
                startActivity(main_caracteristicas_conductor);
            }
        });
        btn_tarjeta_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeta_conductor = new Intent(MainConductorDocumentos.this, MainCapturaTarjetaCirculacion.class);
                main_tarjeta_conductor.putExtra("rol",rol);
                startActivity(main_tarjeta_conductor);
            }
        });
        btn_poliza_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_poliza_conductor = new Intent(MainConductorDocumentos.this, MainCapturaPoliza.class);
                main_poliza_conductor.putExtra("rol",rol);
                startActivity(main_poliza_conductor);
            }
        });
        btn_tarjeton_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeton_conductor = new Intent(MainConductorDocumentos.this, MainCapturaTarjeton.class);
                main_tarjeton_conductor.putExtra("rol",rol);
                startActivity(main_tarjeton_conductor);
            }
        });
    }
}