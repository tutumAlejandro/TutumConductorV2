package com.example.tutumconductorv2.Registro.menus_rol;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaCaracteristicas;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaIne;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaLicencia;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaPoliza;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjetaCirculacion;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjeton;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainTerminosYCondiciones;

public class MainConductorDocumentos extends AppCompatActivity {

    private ImageView btn_regreso_conductor;
    private ImageView btn_terminos_conductor;
    private ImageView btn_ine_conductor;
    private ImageView btn_licencia_conductor;
    private ImageView btn_caracteristicas_conductor;
    private ImageView btn_tarjeta_conductor;
    private ImageView btn_poliza_conductor;
    private ImageView btn_tarjeton_conductor;
    // Declaracion de los botones OK
    private ImageView btn_terminos_conductor_ok;
    private ImageView btn_ine_conductor_ok;
    private ImageView btn_licencia_conductor_ok;
    private ImageView btn_caracteristicas_conductor_ok;
    private ImageView btn_tarjeta_conductor_ok;
    private ImageView btn_poliza_conductor_ok;
    private ImageView btn_tarjeton_conductor_ok;

    private String rol= "Conductor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conductor_documentos);

        //Asociacion de la parte logica con la parte grafica
        btn_regreso_conductor = findViewById(R.id.img_retroceso_documentos_conductor);
        btn_terminos_conductor = findViewById(R.id.btn_terminos_conductor);
        btn_ine_conductor = findViewById(R.id.btn_ine_conductor);
        btn_licencia_conductor = findViewById(R.id.btn_licencia_conductor);
        btn_caracteristicas_conductor = findViewById(R.id.btn_caracteristicas_conductor);
        btn_tarjeta_conductor = findViewById(R.id.btn_tarjeta_conductor);
        btn_poliza_conductor = findViewById(R.id.btn_poliza_conductor);
        btn_tarjeton_conductor = findViewById(R.id.btn_tarjeton_conductor);

        btn_terminos_conductor_ok = findViewById(R.id.btn_terminos_conductor_ok);
        btn_ine_conductor_ok = findViewById(R.id.btn_ine_conductor_ok);
        btn_licencia_conductor_ok = findViewById(R.id.btn_licencia_conductor_ok);
        btn_caracteristicas_conductor_ok = findViewById(R.id.btn_caracteristicas_socio_ok);
        btn_tarjeta_conductor_ok = findViewById(R.id.btn_tarjeta_conductor_ok);
        btn_poliza_conductor_ok = findViewById(R.id.btn_poliza_conductor_ok);
        btn_tarjeton_conductor_ok = findViewById(R.id.btn_tarjeton_conductor_ok);

        // Obtencion de las cadenas de control para mostrar o ocultar los botones de los documentos

        // if para mostrar o ocultar el boton de terminos y condiciones
        /*
        if(cadenas_registro.check_terminos2){
            btn_terminos_conductor.setVisibility(View.GONE);
            btn_terminos_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_terminos_conductor.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de INE
        if(cadenas_registro.check_ine2){
            btn_ine_conductor.setVisibility(View.GONE);
            btn_ine_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_ine_conductor.setVisibility(View.VISIBLE);
        }
        // if para mostrar o ocultar el boton de Licencia
        if(cadenas_registro.check_licencia2){
            btn_licencia_conductor.setVisibility(View.GONE);
            btn_licencia_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_licencia_conductor.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de las caracteristicas
        if(cadenas_registro.check_caracterisitcas2){
            btn_caracteristicas_conductor.setVisibility(View.GONE);
            btn_caracteristicas_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_caracteristicas_conductor.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de la tarjeta
        if(cadenas_registro.check_tarjeta2){
            btn_tarjeta_conductor.setVisibility(View.GONE);
            btn_tarjeta_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_tarjeta_conductor.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de la poliza
        if(cadenas_registro.check_poliza2){
            btn_poliza_conductor.setVisibility(View.GONE);
            btn_poliza_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_poliza_conductor.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton del tarjeton
        if(cadenas_registro.check_tarjeton2){
            btn_tarjeton_conductor.setVisibility(View.GONE);
            btn_tarjeton_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_tarjeton_conductor.setVisibility(View.VISIBLE);
        }
        */
        btn_regreso_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainConductorDocumentos.this, MainRolConductor.class);
                startActivity(main_rol_conductor);
                finish();
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
                Intent main_licencia_conductor = new Intent(MainConductorDocumentos.this, MainCapturaLicencia.class);
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