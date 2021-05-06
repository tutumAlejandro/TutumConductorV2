package com.example.tutumconductorv2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainSnvDocuemtos extends AppCompatActivity {

    private ImageView btn_regreso_snv;
    private ImageView btn_terminos_snv;
    private ImageView btn_ine_snv;
    private ImageView btn_licencia_snv;
    private ImageView btn_codigo_snv;
    private ImageView btn_tarjeton_snv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_snv_docuemtos);

        btn_regreso_snv = findViewById(R.id.img_retroceso_documentos_snv);
        btn_terminos_snv = findViewById(R.id.btn_snv_terminos);
        btn_ine_snv = findViewById(R.id.btn_snv_ine);
        btn_licencia_snv = findViewById(R.id.btn_snv_licencia);
        btn_codigo_snv = findViewById(R.id.btn_snv_codigo);
        btn_tarjeton_snv = findViewById(R.id.btn_snv_tarjeton);

        btn_regreso_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_snv = new Intent(MainSnvDocuemtos.this, MainRolConductor.class);
                startActivity(main_rol_snv);
            }
        });
        btn_terminos_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos_snv = new Intent(MainSnvDocuemtos.this,MainTerminosYCondiciones.class);
                main_terminos_snv.putExtra("rol","snv");
                startActivity(main_terminos_snv);
            }
        });
        btn_ine_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_ine_snv = new Intent(MainSnvDocuemtos.this, MainCapturaIne.class);
                main_ine_snv.putExtra("rol","snv");
                startActivity(main_ine_snv);
            }
        });
        btn_licencia_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_licencia_snv = new Intent(MainSnvDocuemtos.this,MainCapturaLicencia.class);
                main_licencia_snv.putExtra("rol","snv");
                startActivity(main_licencia_snv);
            }
        });
        btn_codigo_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_codigo_snv = new Intent(MainSnvDocuemtos.this,MainCapturaCodigo.class);
                main_codigo_snv.putExtra("rol","snv");
                startActivity(main_codigo_snv);
            }
        });
        btn_tarjeton_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeton_snv = new Intent(MainSnvDocuemtos.this, MainCapturaTarjeton.class);
                main_tarjeton_snv.putExtra("rol","snv");
                startActivity(main_tarjeton_snv);
            }
        });
    }
}