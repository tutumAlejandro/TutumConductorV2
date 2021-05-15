package com.example.tutumconductorv2.Registro.menus_rol;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaCaracteristicas;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaIne;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaLicencia;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaPoliza;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjetaCirculacion;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjeton;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainTerminosYCondiciones;

public class MainSocioDocumentos extends AppCompatActivity {

    private ImageView btn_regreso_socio;
    private ImageView btn_terminos_socio;
    private ImageView btn_ine_socio;
    private ImageView btn_licencia_socio;
    private ImageView btn_caracteristicas_socio;
    private ImageView btn_tarjeta_socio;
    private ImageView btn_poliza_socio;
    private ImageView btn_tarjeton_socio;
    // botones ok
    private ImageView btn_terminos_socio_ok;
    private ImageView btn_ine_socio_ok;
    private ImageView btn_licencia_socio_ok;
    private ImageView btn_caracteristicas_socio_ok;
    private ImageView btn_tarjeta_socio_ok;
    private ImageView btn_poliza_socio_ok;
    private ImageView btn_tarjeton_socio_ok;

    /**/
    private TextView term_num,ine_num,lic_num,carac_num,tarjeta_num,poliza_num,tarjeton_num;
    /**/

    private static String rol= "Socio";
    private String terminos,ine,licencia,caracteristicas,tarjeta,poliza,tarjeton,codigo;

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

        btn_terminos_socio_ok = findViewById(R.id.btn_terminos_socio_ok);
        btn_ine_socio_ok = findViewById(R.id.btn_ine_socio_ok);
        btn_licencia_socio_ok = findViewById(R.id.btn_licencia_socio_ok);
        btn_caracteristicas_socio_ok = findViewById(R.id.btn_caracteristicas_socio_ok);
        btn_tarjeta_socio_ok = findViewById(R.id.btn_tarjeta_socio_ok);
        btn_poliza_socio_ok = findViewById(R.id.btn_poliza_socio_ok);
        btn_tarjeton_socio_ok = findViewById(R.id.btn_tarjeton_socio_ok);

        /**/
        term_num = findViewById(R.id.num_terminos);
        ine_num = findViewById(R.id.num_ine);
        lic_num = findViewById(R.id.num_licencia);
        carac_num = findViewById(R.id.num_caracteristicas);
        tarjeta_num = findViewById(R.id.num_tarjeta);
        poliza_num = findViewById(R.id.num_poliza);
        tarjeton_num = findViewById(R.id.num_tarjeton);
        /**/

        // Obtencion de las cadenas de control para mostrar o ocultar los botones de los documentos
        // if para mostrar o ocultar el boton de terminos y condiciones
        /*
        if(terminos.matches("ok")){
            btn_terminos_socio.setVisibility(View.GONE);
            btn_terminos_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_terminos_socio.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de INE
        if(ine.matches("ok")){
            btn_ine_socio.setVisibility(View.GONE);
            btn_ine_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_ine_socio.setVisibility(View.VISIBLE);
        }
        // if para mostrar o ocultar el boton de Licencia
        if(licencia.matches("ok")){
            btn_licencia_socio.setVisibility(View.GONE);
            btn_licencia_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_licencia_socio.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de las caracteristicas
        if(caracteristicas.matches("ok")){
            btn_caracteristicas_socio.setVisibility(View.GONE);
            btn_caracteristicas_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_caracteristicas_socio.setVisibility(View.VISIBLE);
            btn_tarjeton_socio.setVisibility(View.GONE);
        }

        // if para mostrar o ocultar el boton de la tarjeta
        if(tarjeta.matches("ok")){
            btn_tarjeta_socio.setVisibility(View.GONE);
            btn_tarjeta_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_tarjeta_socio.setVisibility(View.VISIBLE);
            btn_tarjeton_socio.setVisibility(View.GONE);
        }

        // if para mostrar o ocultar el boton de la poliza
        if(poliza.matches("ok")){
            btn_poliza_socio.setVisibility(View.GONE);
            btn_poliza_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_poliza_socio.setVisibility(View.VISIBLE);
            btn_tarjeton_socio.setVisibility(View.GONE);
        }

        // if para mostrar o ocultar el boton del tarjeton
        if(tarjeton.matches("ok")){
            btn_tarjeton_socio.setVisibility(View.GONE);
            btn_tarjeton_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_tarjeton_socio.setVisibility(View.VISIBLE);
            btn_tarjeton_socio.setVisibility(View.GONE);
        }
        */

        btn_regreso_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainSocioDocumentos.this, MainRolConductor.class);
                startActivity(main_rol_conductor);
                finish();
            }
        });
        btn_terminos_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos_socio = new Intent(MainSocioDocumentos.this, MainTerminosYCondiciones.class);
                main_terminos_socio.putExtra("rol",rol);
                startActivity(main_terminos_socio);
                finish();
            }
        });
        btn_ine_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_ine_socio = new Intent(MainSocioDocumentos.this, MainCapturaIne.class);
                main_ine_socio.putExtra("rol",rol);
                startActivity(main_ine_socio);
                finish();
            }
        });
        btn_licencia_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_licencia_socio = new Intent(MainSocioDocumentos.this, MainCapturaLicencia.class);
                main_licencia_socio.putExtra("rol",rol);
                startActivity(main_licencia_socio);
                finish();
            }
        });
        btn_caracteristicas_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_caracteristicas_socio = new Intent(MainSocioDocumentos.this, MainCapturaCaracteristicas.class);
                main_caracteristicas_socio.putExtra("rol",rol);
                startActivity(main_caracteristicas_socio);
                finish();
            }
        });
        btn_tarjeta_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeta_socio = new Intent(MainSocioDocumentos.this, MainCapturaTarjetaCirculacion.class);
                main_tarjeta_socio.putExtra("rol",rol);
                startActivity(main_tarjeta_socio);
                finish();
            }
        });
        btn_poliza_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_poliza_socio = new Intent(MainSocioDocumentos.this, MainCapturaPoliza.class);
                main_poliza_socio.putExtra("rol",rol);
                startActivity(main_poliza_socio);
                finish();
            }
        });
        btn_tarjeton_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeton_socio = new Intent(MainSocioDocumentos.this, MainCapturaTarjeton.class);
                main_tarjeton_socio.putExtra("rol",rol);
                startActivity(main_tarjeton_socio);
                finish();
            }
        });
    }

}