package com.example.tutumconductorv2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainSocioDocumentos extends AppCompatActivity {

    private ImageView btn_regreso_socio;
    private ImageView btn_terminos_socio;
    private ImageView btn_ine_socio;
    private ImageView btn_licencia_socio;
    private ImageView btn_caracteristicas_socio;
    private ImageView btn_tarjeta_socio;
    private ImageView btn_poliza_socio;
    private ImageView btn_tarjeton_socio;

    private String rol= "Socio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_socio_documentos);

        btn_regreso_socio = findViewById(R.id.img_retroceso_documentos_socio);
        /**/
        btn_terminos_socio = findViewById(R.id.btn_terminos_socio);
        btn_ine_socio = findViewById(R.id.btn_ine_socio);
        btn_licencia_socio = findViewById(R.id.btn_licencia_socio);
        btn_caracteristicas_socio = findViewById(R.id.btn_caracteristicas_socio);
        btn_tarjeta_socio = findViewById(R.id.btn_tarjeta_socio);
        btn_poliza_socio = findViewById(R.id.btn_poliza_socio);
        btn_tarjeton_socio = findViewById(R.id.btn_tarjeton_socio);
        /**/
        btn_regreso_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainSocioDocumentos.this, MainRolConductor.class);
                startActivity(main_rol_conductor);
            }
        });
        btn_terminos_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos_socio = new Intent(MainSocioDocumentos.this,MainTerminosYCondiciones.class);
                main_terminos_socio.putExtra("rol",rol);
                startActivity(main_terminos_socio);
            }
        });
        btn_ine_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_ine_socio = new Intent(MainSocioDocumentos.this, MainCapturaIne.class);
                main_ine_socio.putExtra("rol",rol);
                startActivity(main_ine_socio);
            }
        });
        btn_licencia_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_licencia_socio = new Intent(MainSocioDocumentos.this,MainCapturaLicencia.class);
                main_licencia_socio.putExtra("rol",rol);
                startActivity(main_licencia_socio);
            }
        });
        btn_caracteristicas_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_caracteristicas_socio = new Intent(MainSocioDocumentos.this, MainCapturaCaracteristicas.class);
                main_caracteristicas_socio.putExtra("rol",rol);
                startActivity(main_caracteristicas_socio);
            }
        });
        btn_tarjeta_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeta_socio = new Intent(MainSocioDocumentos.this, MainCapturaTarjetaCirculacion.class);
                main_tarjeta_socio.putExtra("rol",rol);
                startActivity(main_tarjeta_socio);
            }
        });
        btn_poliza_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_poliza_socio = new Intent(MainSocioDocumentos.this, MainCapturaPoliza.class);
                main_poliza_socio.putExtra("rol",rol);
                startActivity(main_poliza_socio);
            }
        });
        btn_tarjeton_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeton_socio = new Intent(MainSocioDocumentos.this, MainCapturaTarjeton.class);
                main_tarjeton_socio.putExtra("rol",rol);
                startActivity(main_tarjeton_socio);
            }
        });
    }

}