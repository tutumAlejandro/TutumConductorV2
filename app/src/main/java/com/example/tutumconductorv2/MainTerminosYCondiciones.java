package com.example.tutumconductorv2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainTerminosYCondiciones extends AppCompatActivity {

    private ImageView btn_regreso_term;
    private TextView _politica;
    private TextView _terminos;

    // Cadenas para revisar
    private String rol;
    private boolean terminos_terminos, ine_terminos, licencia_terminos, caracteristicas_terminos, tarjeta_terminos, poliza_terminos, tarjeton_terminos, codigo_terminos;
    private String vigLicencia_ine, vigTarjeta_ine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_terminos_y_condiciones);
        rol = getIntent().getStringExtra("rol");

        if (rol.matches("Socio"))
        {
            terminos_terminos = getIntent().getBooleanExtra("terminos",false);
            ine_terminos = getIntent().getBooleanExtra("ine",false);
            licencia_terminos = getIntent().getBooleanExtra("licencia",false);
            caracteristicas_terminos = getIntent().getBooleanExtra("caracteristicas",false);
            tarjeta_terminos = getIntent().getBooleanExtra("tarjeta",false);
            poliza_terminos = getIntent().getBooleanExtra("poliza",false);
            tarjeton_terminos = getIntent().getBooleanExtra("tarjeton",false);
            codigo_terminos = getIntent().getBooleanExtra("codigo",false);
        }else if(rol.matches("Conductor")){
            terminos_terminos = getIntent().getBooleanExtra("terminos_conductor",false);
            ine_terminos = getIntent().getBooleanExtra("ine_conductor",false);
            licencia_terminos = getIntent().getBooleanExtra("licencia_conductor",false);
            caracteristicas_terminos = getIntent().getBooleanExtra("caracteristicas_conductor",false);
            tarjeta_terminos = getIntent().getBooleanExtra("tarjeta_conductor",false);
            poliza_terminos = getIntent().getBooleanExtra("poliza_conductor",false);
            tarjeton_terminos = getIntent().getBooleanExtra("tarjeton_conductor",false);
            codigo_terminos = getIntent().getBooleanExtra("codigo_conductor",false);
        }else{
            terminos_terminos = getIntent().getBooleanExtra("terminos_snv",false);
            ine_terminos = getIntent().getBooleanExtra("ine_snv",false);
            licencia_terminos = getIntent().getBooleanExtra("licencia_snv",false);
            caracteristicas_terminos = getIntent().getBooleanExtra("caracteristicas_snv",false);
            tarjeta_terminos = getIntent().getBooleanExtra("tarjeta_snv",false);
            poliza_terminos = getIntent().getBooleanExtra("poliza_snv",false);
            tarjeton_terminos = getIntent().getBooleanExtra("tarjeton_snv",false);
            codigo_terminos = getIntent().getBooleanExtra("codigo_snv",false);
        }


        btn_regreso_term = findViewById(R.id.img_retroceso_terminos_condiciones);
        _politica = findViewById(R.id.link_politica);
        _terminos = findViewById(R.id.txt_terminos_y_condiciones);

        btn_regreso_term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rol.matches("Socio")){
                    Intent main_socio_documentos = new Intent(MainTerminosYCondiciones.this, MainSocioDocumentos.class);
                    main_socio_documentos.putExtra("terminos",false);
                    main_socio_documentos.putExtra("ine",ine_terminos);
                    main_socio_documentos.putExtra("licencia",licencia_terminos);
                    main_socio_documentos.putExtra("caracterisitcas",caracteristicas_terminos);
                    main_socio_documentos.putExtra("tarjeta",tarjeta_terminos);
                    main_socio_documentos.putExtra("poliza",poliza_terminos);
                    main_socio_documentos.putExtra("tarjeton",tarjeton_terminos);
                    main_socio_documentos.putExtra("codigo",codigo_terminos);
                    startActivity(main_socio_documentos);
                    finish();
                }else if(rol.matches("Conductor")){
                    Intent main_conductor_documentos = new Intent(MainTerminosYCondiciones.this, MainConductorDocumentos.class);
                    main_conductor_documentos.putExtra("terminos_conductor",false);
                    main_conductor_documentos.putExtra("ine_conductor",ine_terminos);
                    main_conductor_documentos.putExtra("licencia_conductor",licencia_terminos);
                    main_conductor_documentos.putExtra("caracteristicas_conductor",caracteristicas_terminos);
                    main_conductor_documentos.putExtra("tarjeta_conductor",tarjeta_terminos);
                    main_conductor_documentos.putExtra("poliza_conductor",poliza_terminos);
                    main_conductor_documentos.putExtra("tarjeton_conductor",tarjeton_terminos);
                    main_conductor_documentos.putExtra("codigo_conductor",codigo_terminos);
                    startActivity(main_conductor_documentos);
                    finish();
                }else{
                    Intent main_snv_documentos = new Intent(MainTerminosYCondiciones.this, MainSnvDocuemtos.class);
                    main_snv_documentos.putExtra("terminos_snv",false);
                    main_snv_documentos.putExtra("ine_snv",ine_terminos);
                    main_snv_documentos.putExtra("licencia_snv",licencia_terminos);
                    main_snv_documentos.putExtra("caracteristicas_snv",caracteristicas_terminos);
                    main_snv_documentos.putExtra("tarjeta_snv",tarjeta_terminos);
                    main_snv_documentos.putExtra("poliza_snv",poliza_terminos);
                    main_snv_documentos.putExtra("tarjeton_snv",tarjeton_terminos);
                    main_snv_documentos.putExtra("codigo_snv",codigo_terminos);
                    startActivity(main_snv_documentos);
                    finish();
                }
            }
        });

        _politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_politica = new Intent(MainTerminosYCondiciones.this, MainTextoPoliticaPrivacidad.class);
                main_politica.putExtra("rol",rol);
                startActivity(main_politica);
                finish();
            }
        });
        _terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos = new Intent(MainTerminosYCondiciones.this, MainTextoPoliticaPrivacidad.class);
                main_terminos.putExtra("rol",rol);
                startActivity(main_terminos);
                finish();
            }
        });

    }
    public void btn_guardar_terminos(View v)
    {
        if(rol.matches("Socio")){
            Intent main_socio_documentos = new Intent(MainTerminosYCondiciones.this, MainSocioDocumentos.class);
            main_socio_documentos.putExtra("terminos",true);
            main_socio_documentos.putExtra("ine",ine_terminos);
            main_socio_documentos.putExtra("licencia",licencia_terminos);
            main_socio_documentos.putExtra("caracterisitcas",caracteristicas_terminos);
            main_socio_documentos.putExtra("tarjeta",tarjeta_terminos);
            main_socio_documentos.putExtra("poliza",poliza_terminos);
            main_socio_documentos.putExtra("tarjeton",tarjeton_terminos);
            main_socio_documentos.putExtra("codigo",codigo_terminos);
            startActivity(main_socio_documentos);
            finish();
        }else if(rol.matches("Conductor")){
            Intent main_conductor_documentos = new Intent(MainTerminosYCondiciones.this, MainConductorDocumentos.class);
            main_conductor_documentos.putExtra("terminos_conductor",true);
            main_conductor_documentos.putExtra("ine_conductor",ine_terminos);
            main_conductor_documentos.putExtra("licencia_conductor",licencia_terminos);
            main_conductor_documentos.putExtra("caracteristicas_conductor",caracteristicas_terminos);
            main_conductor_documentos.putExtra("tarjeta_conductor",tarjeta_terminos);
            main_conductor_documentos.putExtra("poliza_conductor",poliza_terminos);
            main_conductor_documentos.putExtra("tarjeton_conductor",tarjeton_terminos);
            main_conductor_documentos.putExtra("codigo_conductor",codigo_terminos);
            startActivity(main_conductor_documentos);
            finish();
        }else{
            Intent main_snv_documentos = new Intent(MainTerminosYCondiciones.this, MainSnvDocuemtos.class);
            main_snv_documentos.putExtra("terminos_snv",true);
            main_snv_documentos.putExtra("ine_snv",ine_terminos);
            main_snv_documentos.putExtra("licencia_snv",licencia_terminos);
            main_snv_documentos.putExtra("caracteristicas_snv",caracteristicas_terminos);
            main_snv_documentos.putExtra("tarjeta_snv",tarjeta_terminos);
            main_snv_documentos.putExtra("poliza_snv",poliza_terminos);
            main_snv_documentos.putExtra("tarjeton_snv",tarjeton_terminos);
            main_snv_documentos.putExtra("codigo_snv",codigo_terminos);
            startActivity(main_snv_documentos);
            finish();
        }
    }
}