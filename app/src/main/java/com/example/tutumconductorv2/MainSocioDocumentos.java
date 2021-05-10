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
    // botones ok
    private ImageView btn_terminos_socio_ok;
    private ImageView btn_ine_socio_ok;
    private ImageView btn_licencia_socio_ok;
    private ImageView btn_caracteristicas_socio_ok;
    private ImageView btn_tarjeta_socio_ok;
    private ImageView btn_poliza_socio_ok;
    private ImageView btn_tarjeton_socio_ok;

    private String rol= "Socio";
    private boolean terminos,ine,licencia,caracteristicas,tarjeta,poliza,tarjeton;

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

        // Obtencion de las cadenas de control para mostrar o ocultar los botones de los documentos
        terminos= getIntent().getBooleanExtra("terminos",false);
        ine = getIntent().getBooleanExtra("ine",false);
        licencia = getIntent().getBooleanExtra("licencia",false);
        caracteristicas = getIntent().getBooleanExtra("caracteristicas",false);
        tarjeta = getIntent().getBooleanExtra("tarjeta",false);
        poliza = getIntent().getBooleanExtra("poliza",false);
        tarjeton = getIntent().getBooleanExtra("tarjeton",false);


        // if para mostrar o ocultar el boton de terminos y condiciones
        if(terminos){
            btn_terminos_socio.setVisibility(View.GONE);
            btn_terminos_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_terminos_socio.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de INE
        if(ine){
            btn_ine_socio.setVisibility(View.GONE);
            btn_ine_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_ine_socio.setVisibility(View.VISIBLE);
        }
        // if para mostrar o ocultar el boton de Licencia
        if(licencia){
            btn_licencia_socio.setVisibility(View.GONE);
            btn_licencia_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_licencia_socio.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de las caracteristicas
        if(caracteristicas){
            btn_caracteristicas_socio.setVisibility(View.GONE);
            btn_caracteristicas_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_caracteristicas_socio.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de la tarjeta
        if(tarjeta){
            btn_tarjeta_socio.setVisibility(View.GONE);
            btn_tarjeta_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_tarjeta_socio.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton de la poliza
        if(poliza){
            btn_poliza_socio.setVisibility(View.GONE);
            btn_poliza_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_poliza_socio.setVisibility(View.VISIBLE);
        }

        // if para mostrar o ocultar el boton del tarjeton
        if(tarjeton){
            btn_tarjeton_socio.setVisibility(View.GONE);
            btn_tarjeton_socio_ok.setVisibility(View.VISIBLE);
        }else{
            btn_tarjeton_socio.setVisibility(View.VISIBLE);
        }


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
                Intent main_terminos_socio = new Intent(MainSocioDocumentos.this,MainTerminosYCondiciones.class);
                main_terminos_socio.putExtra("rol",rol);
                main_terminos_socio.putExtra("terminos",terminos);
                main_terminos_socio.putExtra("ine",ine);
                main_terminos_socio.putExtra("licencia",licencia);
                main_terminos_socio.putExtra("caracteristicas",caracteristicas);
                main_terminos_socio.putExtra("tarjeta",tarjeta);
                main_terminos_socio.putExtra("poliza",poliza);
                main_terminos_socio.putExtra("tarjeton",tarjeton);
                startActivity(main_terminos_socio);
            }
        });
        btn_ine_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_ine_socio = new Intent(MainSocioDocumentos.this, MainCapturaIne.class);
                main_ine_socio.putExtra("rol",rol);
                main_ine_socio.putExtra("terminos",terminos);
                main_ine_socio.putExtra("ine",ine);
                main_ine_socio.putExtra("licencia",licencia);
                main_ine_socio.putExtra("caracteristicas",caracteristicas);
                main_ine_socio.putExtra("tarjeta",tarjeta);
                main_ine_socio.putExtra("poliza",poliza);
                main_ine_socio.putExtra("tarjeton",tarjeton);
                startActivity(main_ine_socio);
            }
        });
        btn_licencia_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_licencia_socio = new Intent(MainSocioDocumentos.this,MainCapturaLicencia.class);
                main_licencia_socio.putExtra("rol",rol);
                main_licencia_socio.putExtra("terminos",terminos);
                main_licencia_socio.putExtra("ine",ine);
                main_licencia_socio.putExtra("licencia",licencia);
                main_licencia_socio.putExtra("caracteristicas",caracteristicas);
                main_licencia_socio.putExtra("tarjeta",tarjeta);
                main_licencia_socio.putExtra("poliza",poliza);
                main_licencia_socio.putExtra("tarjeton",tarjeton);
                startActivity(main_licencia_socio);
            }
        });
        btn_caracteristicas_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_caracteristicas_socio = new Intent(MainSocioDocumentos.this, MainCapturaCaracteristicas.class);
                main_caracteristicas_socio.putExtra("rol",rol);
                main_caracteristicas_socio.putExtra("terminos",terminos);
                main_caracteristicas_socio.putExtra("ine",ine);
                main_caracteristicas_socio.putExtra("licencia",licencia);
                main_caracteristicas_socio.putExtra("caracteristicas",caracteristicas);
                main_caracteristicas_socio.putExtra("tarjeta",tarjeton);
                main_caracteristicas_socio.putExtra("poliza",poliza);
                main_caracteristicas_socio.putExtra("tarjeton",tarjeton);
                startActivity(main_caracteristicas_socio);
            }
        });
        btn_tarjeta_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeta_socio = new Intent(MainSocioDocumentos.this, MainCapturaTarjetaCirculacion.class);
                main_tarjeta_socio.putExtra("rol",rol);
                main_tarjeta_socio.putExtra("terminos",terminos);
                main_tarjeta_socio.putExtra("ine",ine);
                main_tarjeta_socio.putExtra("licencia",licencia);
                main_tarjeta_socio.putExtra("caracteristicas",caracteristicas);
                main_tarjeta_socio.putExtra("tarjeta",tarjeta);
                main_tarjeta_socio.putExtra("poliza",poliza);
                main_tarjeta_socio.putExtra("tarjeton",tarjeton);
                startActivity(main_tarjeta_socio);
            }
        });
        btn_poliza_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_poliza_socio = new Intent(MainSocioDocumentos.this, MainCapturaPoliza.class);
                main_poliza_socio.putExtra("rol",rol);
                main_poliza_socio.putExtra("terminos",terminos);
                main_poliza_socio.putExtra("ine",ine);
                main_poliza_socio.putExtra("licencia",licencia);
                main_poliza_socio.putExtra("caracteristicas",caracteristicas);
                main_poliza_socio.putExtra("tarjeta",tarjeta);
                main_poliza_socio.putExtra("poliza",poliza);
                main_poliza_socio.putExtra("tarjeton",tarjeton);
                startActivity(main_poliza_socio);
            }
        });
        btn_tarjeton_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_tarjeton_socio = new Intent(MainSocioDocumentos.this, MainCapturaTarjeton.class);
                main_tarjeton_socio.putExtra("rol",rol);
                main_tarjeton_socio.putExtra("terminos",terminos);
                main_tarjeton_socio.putExtra("ine",ine);
                main_tarjeton_socio.putExtra("licencia",licencia);
                main_tarjeton_socio.putExtra("caracteristicas",caracteristicas);
                main_tarjeton_socio.putExtra("tarjeta",tarjeta);
                main_tarjeton_socio.putExtra("poliza",poliza);
                main_tarjeton_socio.putExtra("tarjeton",tarjeton);
                startActivity(main_tarjeton_socio);
            }
        });
    }

}