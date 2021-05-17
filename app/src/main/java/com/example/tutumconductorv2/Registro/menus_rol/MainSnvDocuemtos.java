package com.example.tutumconductorv2.Registro.menus_rol;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaCodigo;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaIne;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaLicencia;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjeton;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainTerminosYCondiciones;

public class MainSnvDocuemtos extends AppCompatActivity {

    private ImageView btn_regreso_snv;
    private ImageView btn_terminos_snv;
    private ImageView btn_ine_snv;
    private ImageView btn_licencia_snv;
    private ImageView btn_codigo_snv;
    private ImageView btn_tarjeton_snv;
    // botones ok
    private ImageView btn_terminos_snv_ok, btn_ine_snv_ok, btn_licencia_snv_ok, btn_codigo_snv_ok, btn_tarjeton_snv_ok;
    // text view para mostrar datos bien
    private TextView txt_terminos,txt_ine, txt_licencia, txt_codigo, txt_tarjeton;
    private String rol="snv";

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
        // botones ok
        btn_terminos_snv_ok = findViewById(R.id.btn_snv_terminos_ok);
        btn_ine_snv_ok = findViewById(R.id.btn_snv_ine_ok);
        btn_licencia_snv_ok = findViewById(R.id.btn_snv_licencia_ok);
        btn_codigo_snv_ok = findViewById(R.id.btn_snv_codigo_ok);
        btn_tarjeton_snv_ok = findViewById(R.id.btn_snv_tarjeton_ok);

        /* Asosiacion de los txt view para los documentos*/
        txt_terminos = findViewById(R.id.TxViewTerminosSnv);
        txt_ine = findViewById(R.id.TxViewIneSnv);
        txt_licencia = findViewById(R.id.TxViewLicenciaSnv);
        txt_codigo = findViewById(R.id.TxViewCodigoSnv);
        txt_tarjeton = findViewById(R.id.TxViewTarjetonSnv);
        /*

        // if para mostrar o ocultar el boton de terminos y condiciones
        if(cadenas_registro.check_terminos3){
            btn_terminos_snv.setVisibility(View.GONE);
            btn_terminos_snv_ok.setVisibility(View.VISIBLE);
            txt_terminos.setVisibility(View.VISIBLE);
        }else{
            btn_terminos_snv.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de INE
        if(cadenas_registro.check_ine3){
            btn_ine_snv.setVisibility(View.GONE);
            btn_ine_snv_ok.setVisibility(View.VISIBLE);
            txt_ine.setVisibility(View.VISIBLE);
        }else{
            btn_ine_snv.setVisibility(View.VISIBLE);
        }
        // if para mostrar o ocultar el boton de Licencia
        if(cadenas_registro.check_licencia3){
            btn_licencia_snv.setVisibility(View.GONE);
            btn_licencia_snv_ok.setVisibility(View.VISIBLE);
            txt_licencia.setVisibility(View.VISIBLE);
            //txt_licencia.setText("Licencia OK:" + vig_licencia);

        }else{
            btn_licencia_snv.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton del tarjeton
        if(cadenas_registro.check_tarjeton3){
            btn_tarjeton_snv.setVisibility(View.GONE);
            btn_tarjeton_snv_ok.setVisibility(View.VISIBLE);
            txt_tarjeton.setVisibility(View.VISIBLE);
            //txt_tarjeton.setText("Tarjeton OK:" + vig_tarjeton);
        }else{
            btn_tarjeton_snv.setVisibility(View.VISIBLE);
        }
        if(cadenas_registro.check_codigo3){
            btn_codigo_snv.setVisibility(View.GONE);
            btn_codigo_snv_ok.setVisibility(View.VISIBLE);
            txt_codigo.setVisibility(View.VISIBLE);
            //txt_codigo.setText("Codigo OK:"+codigo);

        }else{
            btn_codigo_snv.setVisibility(View.VISIBLE);
        }

          */
        btn_regreso_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainSnvDocuemtos.this, MainRolConductor.class);
                startActivity(main_rol_conductor);
                finish();
            }
        });


        btn_terminos_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos_snv = new Intent(MainSnvDocuemtos.this, MainTerminosYCondiciones.class);
                main_terminos_snv.putExtra("rol",rol);
                startActivity(main_terminos_snv);
            }
        });
        btn_ine_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_ine_snv = new Intent(MainSnvDocuemtos.this, MainCapturaIne.class);
                main_ine_snv.putExtra("rol",rol);
                startActivity(main_ine_snv);
            }
        });
        btn_licencia_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_licencia_snv = new Intent(MainSnvDocuemtos.this, MainCapturaLicencia.class);
                main_licencia_snv.putExtra("rol",rol);
                startActivity(main_licencia_snv);
            }
        });
        btn_codigo_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_codigo_snv = new Intent(MainSnvDocuemtos.this, MainCapturaCodigo.class);
                main_codigo_snv.putExtra("rol",rol);
                startActivity(main_codigo_snv);
            }
        });
        btn_tarjeton_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeton_snv = new Intent(MainSnvDocuemtos.this, MainCapturaTarjeton.class);
                main_tarjeton_snv.putExtra("rol",rol);
                startActivity(main_tarjeton_snv);
            }
        });
    }
}