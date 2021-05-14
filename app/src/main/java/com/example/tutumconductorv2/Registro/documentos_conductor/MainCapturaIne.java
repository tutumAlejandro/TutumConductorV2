package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.menus_rol.MainSnvDocuemtos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

public class MainCapturaIne extends AppCompatActivity {

    private ImageView btn_retroceso_ine;
    private String rol;
    private boolean terminos_ine,ine_ine,licencia_ine,caracteristicas_ine,tarjeta_ine,poliza_ine,tarjeton_ine,codigo_ine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_ine);

        rol = getIntent().getStringExtra("rol");
        if (rol.matches("Socio"))
        {
            terminos_ine = getIntent().getBooleanExtra("terminos",false);
            ine_ine = getIntent().getBooleanExtra("ine",false);
            licencia_ine = getIntent().getBooleanExtra("licencia",false);
            caracteristicas_ine = getIntent().getBooleanExtra("caracteristicas",false);
            tarjeta_ine = getIntent().getBooleanExtra("tarjeta",false);
            poliza_ine = getIntent().getBooleanExtra("poliza",false);
            tarjeton_ine = getIntent().getBooleanExtra("tarjeton",false);
            codigo_ine  = getIntent().getBooleanExtra("codigo",false);
        }else if(rol.matches("Conductor")){
            terminos_ine = getIntent().getBooleanExtra("terminos_conductor",false);
            ine_ine = getIntent().getBooleanExtra("ine_conductor",false);
            licencia_ine = getIntent().getBooleanExtra("licencia_conductor",false);
            caracteristicas_ine = getIntent().getBooleanExtra("caracteristicas_conductor",false);
            tarjeta_ine = getIntent().getBooleanExtra("tarjeta_conductor",false);
            poliza_ine = getIntent().getBooleanExtra("poliza_conductor",false);
            tarjeton_ine = getIntent().getBooleanExtra("tarjeton_conductor",false);
            codigo_ine  = getIntent().getBooleanExtra("codigo_conductor",false);
        }else{
            terminos_ine = getIntent().getBooleanExtra("terminos_snv",false);
            ine_ine = getIntent().getBooleanExtra("ine_snv",false);
            licencia_ine = getIntent().getBooleanExtra("licencia_snv",false);
            caracteristicas_ine = getIntent().getBooleanExtra("caracteristicas_snv",false);
            tarjeta_ine = getIntent().getBooleanExtra("tarjeta_snv",false);
            poliza_ine = getIntent().getBooleanExtra("poliza_snv",false);
            tarjeton_ine = getIntent().getBooleanExtra("tarjeton_snv",false);
            codigo_ine  = getIntent().getBooleanExtra("codigo_snv",false);
        }

        btn_retroceso_ine = findViewById(R.id.img_retroceso_ine);

        btn_retroceso_ine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rol.matches("Socio")){
                    Intent main_socio_documentos = new Intent(MainCapturaIne.this, MainSocioDocumentos.class);
                    main_socio_documentos.putExtra("terminos",terminos_ine);
                    main_socio_documentos.putExtra("ine",false);
                    main_socio_documentos.putExtra("licencia",licencia_ine);
                    main_socio_documentos.putExtra("caracterisitcas",caracteristicas_ine);
                    main_socio_documentos.putExtra("tarjeta",tarjeta_ine);
                    main_socio_documentos.putExtra("poliza",poliza_ine);
                    main_socio_documentos.putExtra("tarjeton",tarjeton_ine);
                    main_socio_documentos.putExtra("codigo",codigo_ine);
                    startActivity(main_socio_documentos);
                    finish();
                }else if(rol.matches("Conductor")){
                    Intent main_conductor_documentos = new Intent(MainCapturaIne.this, MainConductorDocumentos.class);
                    main_conductor_documentos.putExtra("terminos_conductor",terminos_ine);
                    main_conductor_documentos.putExtra("ine_conductor",false);
                    main_conductor_documentos.putExtra("licencia_conductor",licencia_ine);
                    main_conductor_documentos.putExtra("caracteristicas_conductor",caracteristicas_ine);
                    main_conductor_documentos.putExtra("tarjeta_conductor",tarjeta_ine);
                    main_conductor_documentos.putExtra("poliza_conductor",poliza_ine);
                    main_conductor_documentos.putExtra("tarjeton_conductor",tarjeton_ine);
                    main_conductor_documentos.putExtra("codigo_conductor",codigo_ine);
                    startActivity(main_conductor_documentos);
                    finish();
                }else{
                    Intent main_snv_documentos = new Intent(MainCapturaIne.this, MainSnvDocuemtos.class);
                    main_snv_documentos.putExtra("terminos_snv",terminos_ine);
                    main_snv_documentos.putExtra("ine_snv",false);
                    main_snv_documentos.putExtra("licencia_snv",licencia_ine);
                    main_snv_documentos.putExtra("caracteristicas_snv",caracteristicas_ine);
                    main_snv_documentos.putExtra("tarjeta_snv",tarjeta_ine);
                    main_snv_documentos.putExtra("poliza_snv",poliza_ine);
                    main_snv_documentos.putExtra("tarjeton_snv",tarjeton_ine);
                    main_snv_documentos.putExtra("codigo_snv",codigo_ine);
                    startActivity(main_snv_documentos);
                    finish();
                }
            }
        });
    }

    public void guardar_ine (View V)
    {
        if(rol.matches("Socio")){
            Intent main_socio_documentos = new Intent(MainCapturaIne.this, MainSocioDocumentos.class);
            main_socio_documentos.putExtra("terminos",terminos_ine);
            main_socio_documentos.putExtra("ine",true);
            main_socio_documentos.putExtra("licencia",licencia_ine);
            main_socio_documentos.putExtra("caracterisitcas",caracteristicas_ine);
            main_socio_documentos.putExtra("tarjeta",tarjeta_ine);
            main_socio_documentos.putExtra("poliza",poliza_ine);
            main_socio_documentos.putExtra("tarjeton",tarjeton_ine);
            main_socio_documentos.putExtra("codigo",codigo_ine);
            startActivity(main_socio_documentos);
            finish();
        }else if(rol.matches("Conductor")){
            Intent main_conductor_documentos = new Intent(MainCapturaIne.this, MainConductorDocumentos.class);
            main_conductor_documentos.putExtra("terminos_conductor",terminos_ine);
            main_conductor_documentos.putExtra("ine_conductor",true);
            main_conductor_documentos.putExtra("licencia_conductor",licencia_ine);
            main_conductor_documentos.putExtra("caracteristicas_conductor",caracteristicas_ine);
            main_conductor_documentos.putExtra("tarjeta_conductor",tarjeta_ine);
            main_conductor_documentos.putExtra("poliza_conductor",poliza_ine);
            main_conductor_documentos.putExtra("tarjeton_conductor",tarjeton_ine);
            main_conductor_documentos.putExtra("codigo_conductor",codigo_ine);
            startActivity(main_conductor_documentos);
            finish();
        }else
        {
            Intent main_snv_documentos = new Intent(MainCapturaIne.this, MainSnvDocuemtos.class);
            main_snv_documentos.putExtra("terminos_snv",terminos_ine);
            main_snv_documentos.putExtra("ine_snv",true);
            main_snv_documentos.putExtra("licencia_snv",licencia_ine);
            main_snv_documentos.putExtra("caracteristicas_snv",caracteristicas_ine);
            main_snv_documentos.putExtra("tarjeta_snv",tarjeta_ine);
            main_snv_documentos.putExtra("poliza_snv",poliza_ine);
            main_snv_documentos.putExtra("tarjeton_snv",tarjeton_ine);
            main_snv_documentos.putExtra("codigo_snv",codigo_ine);
            startActivity(main_snv_documentos);
            finish();
        }
    }
}