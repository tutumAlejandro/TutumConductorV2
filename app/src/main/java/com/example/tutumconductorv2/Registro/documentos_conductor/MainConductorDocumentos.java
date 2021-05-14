package com.example.tutumconductorv2.Registro.documentos_conductor;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.menus_rol.MainRolConductor;

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
    private boolean terminos_conductor, ine_conductor, licencia_conductor, caracteristicas_conductor, tarjeta_conductor, poliza_conductor, tarjeton_conductor, codigo_conductor;
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
        terminos_conductor= getIntent().getBooleanExtra("terminos_conductor",false);
        ine_conductor = getIntent().getBooleanExtra("ine_conductor",false);
        licencia_conductor = getIntent().getBooleanExtra("licencia_conductor",false);
        caracteristicas_conductor = getIntent().getBooleanExtra("caracteristicas_conductor",false);
        tarjeta_conductor = getIntent().getBooleanExtra("tarjeta_conductor",false);
        poliza_conductor = getIntent().getBooleanExtra("poliza_conductor",false);
        tarjeton_conductor = getIntent().getBooleanExtra("tarjeton_conductor",false);
        codigo_conductor = false;



        // if para mostrar o ocultar el boton de terminos y condiciones
        if(terminos_conductor){
            btn_terminos_conductor.setVisibility(View.GONE);
            btn_terminos_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_terminos_conductor.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de INE
        if(ine_conductor){
            btn_ine_conductor.setVisibility(View.GONE);
            btn_ine_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_ine_conductor.setVisibility(View.VISIBLE);
        }
        // if para mostrar o ocultar el boton de Licencia
        if(licencia_conductor){
            btn_licencia_conductor.setVisibility(View.GONE);
            btn_licencia_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_licencia_conductor.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de las caracteristicas
        if(caracteristicas_conductor){
            btn_caracteristicas_conductor.setVisibility(View.GONE);
            btn_caracteristicas_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_caracteristicas_conductor.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de la tarjeta
        if(tarjeta_conductor){
            btn_tarjeta_conductor.setVisibility(View.GONE);
            btn_tarjeta_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_tarjeta_conductor.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de la poliza
        if(poliza_conductor){
            btn_poliza_conductor.setVisibility(View.GONE);
            btn_poliza_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_poliza_conductor.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton del tarjeton
        if(tarjeton_conductor){
            btn_tarjeton_conductor.setVisibility(View.GONE);
            btn_tarjeton_conductor_ok.setVisibility(View.VISIBLE);
        }else{
            btn_tarjeton_conductor.setVisibility(View.VISIBLE);
        }

        btn_regreso_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainConductorDocumentos.this, MainRolConductor.class);
                /*  Pasar los datos del registro entre ventanas*/
                startActivity(main_rol_conductor);
                finish();
            }
        });

        btn_terminos_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos_conductor = new Intent(MainConductorDocumentos.this, MainTerminosYCondiciones.class);
                main_terminos_conductor.putExtra("rol", rol);
                main_terminos_conductor.putExtra("terminos_conductor",terminos_conductor);
                main_terminos_conductor.putExtra("ine_conductor",ine_conductor);
                main_terminos_conductor.putExtra("licencia_conductor",terminos_conductor);
                main_terminos_conductor.putExtra("caracteristicas_conductor",caracteristicas_conductor);
                main_terminos_conductor.putExtra("tarjeta_conductor",tarjeta_conductor);
                main_terminos_conductor.putExtra("poliza_conductor",poliza_conductor);
                main_terminos_conductor.putExtra("tarjeton_conductor",tarjeton_conductor);
                main_terminos_conductor.putExtra("codigo_conductor",codigo_conductor);
                startActivity(main_terminos_conductor);
            }
        });
        btn_ine_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_ine_conductor = new Intent(MainConductorDocumentos.this, MainCapturaIne.class);
                main_ine_conductor.putExtra("rol",rol);
                main_ine_conductor.putExtra("terminos_conductor",terminos_conductor);
                main_ine_conductor.putExtra("ine_conductor",ine_conductor);
                main_ine_conductor.putExtra("licencia_conductor",terminos_conductor);
                main_ine_conductor.putExtra("caracteristicas_conductor",caracteristicas_conductor);
                main_ine_conductor.putExtra("tarjeta_conductor",tarjeta_conductor);
                main_ine_conductor.putExtra("poliza_conductor",poliza_conductor);
                main_ine_conductor.putExtra("tarjeton_conductor",tarjeton_conductor);
                main_ine_conductor.putExtra("codigo_conductor",codigo_conductor);
                startActivity(main_ine_conductor);
            }
        });
        btn_licencia_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_licencia_conductor = new Intent(MainConductorDocumentos.this,MainCapturaLicencia.class);
                main_licencia_conductor.putExtra("rol",rol);
                main_licencia_conductor.putExtra("terminos_conductor",terminos_conductor);
                main_licencia_conductor.putExtra("ine_conductor",ine_conductor);
                main_licencia_conductor.putExtra("licencia_conductor",terminos_conductor);
                main_licencia_conductor.putExtra("caracteristicas_conductor",caracteristicas_conductor);
                main_licencia_conductor.putExtra("tarjeta_conductor",tarjeta_conductor);
                main_licencia_conductor.putExtra("poliza_conductor",poliza_conductor);
                main_licencia_conductor.putExtra("tarjeton_conductor",tarjeton_conductor);
                main_licencia_conductor.putExtra("codigo_conductor",codigo_conductor);
                startActivity(main_licencia_conductor);
            }
        });
        btn_caracteristicas_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_caracteristicas_conductor = new Intent(MainConductorDocumentos.this, MainCapturaCaracteristicas.class);
                main_caracteristicas_conductor.putExtra("rol",rol);
                main_caracteristicas_conductor.putExtra("terminos_conductor",terminos_conductor);
                main_caracteristicas_conductor.putExtra("ine_conductor",ine_conductor);
                main_caracteristicas_conductor.putExtra("licencia_conductor",terminos_conductor);
                main_caracteristicas_conductor.putExtra("caracteristicas_conductor",caracteristicas_conductor);
                main_caracteristicas_conductor.putExtra("tarjeta_conductor",tarjeta_conductor);
                main_caracteristicas_conductor.putExtra("poliza_conductor",poliza_conductor);
                main_caracteristicas_conductor.putExtra("tarjeton_conductor",tarjeton_conductor);
                startActivity(main_caracteristicas_conductor);
            }
        });
        btn_tarjeta_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeta_conductor = new Intent(MainConductorDocumentos.this, MainCapturaTarjetaCirculacion.class);
                main_tarjeta_conductor.putExtra("rol",rol);
                main_tarjeta_conductor.putExtra("terminos_conductor",terminos_conductor);
                main_tarjeta_conductor.putExtra("ine_conductor",ine_conductor);
                main_tarjeta_conductor.putExtra("licencia_conductor",terminos_conductor);
                main_tarjeta_conductor.putExtra("caracteristicas_conductor",caracteristicas_conductor);
                main_tarjeta_conductor.putExtra("tarjeta_conductor",tarjeta_conductor);
                main_tarjeta_conductor.putExtra("poliza_conductor",poliza_conductor);
                main_tarjeta_conductor.putExtra("tarjeton_conductor",tarjeton_conductor);
                startActivity(main_tarjeta_conductor);
            }
        });
        btn_poliza_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_poliza_conductor = new Intent(MainConductorDocumentos.this, MainCapturaPoliza.class);
                main_poliza_conductor.putExtra("rol",rol);
                main_poliza_conductor.putExtra("terminos_conductor",terminos_conductor);
                main_poliza_conductor.putExtra("ine_conductor",ine_conductor);
                main_poliza_conductor.putExtra("licencia_conductor",terminos_conductor);
                main_poliza_conductor.putExtra("caracteristicas_conductor",caracteristicas_conductor);
                main_poliza_conductor.putExtra("tarjeta_conductor",tarjeta_conductor);
                main_poliza_conductor.putExtra("poliza_conductor",poliza_conductor);
                main_poliza_conductor.putExtra("tarjeton_conductor",tarjeton_conductor);
                startActivity(main_poliza_conductor);
            }
        });
        btn_tarjeton_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeton_conductor = new Intent(MainConductorDocumentos.this, MainCapturaTarjeton.class);
                main_tarjeton_conductor.putExtra("rol",rol);
                main_tarjeton_conductor.putExtra("terminos_conductor",terminos_conductor);
                main_tarjeton_conductor.putExtra("ine_conductor",ine_conductor);
                main_tarjeton_conductor.putExtra("licencia_conductor",terminos_conductor);
                main_tarjeton_conductor.putExtra("caracteristicas_conductor",caracteristicas_conductor);
                main_tarjeton_conductor.putExtra("tarjeta_conductor",tarjeta_conductor);
                main_tarjeton_conductor.putExtra("poliza_conductor",poliza_conductor);
                main_tarjeton_conductor.putExtra("tarjeton_conductor",tarjeton_conductor);
                main_tarjeton_conductor.putExtra("codigo_conductor",codigo_conductor);
                startActivity(main_tarjeton_conductor);
            }
        });
    }
}