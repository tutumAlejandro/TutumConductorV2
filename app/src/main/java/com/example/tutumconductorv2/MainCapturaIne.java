package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.nio.channels.InterruptedByTimeoutException;

public class MainCapturaIne extends AppCompatActivity {

    private ImageView btn_retroceso_ine;
    private String rol,ine_ok;
    private boolean terminos,ine,licencia,caracteristicas,tarjeta,poliza,tarjeton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_ine);

        rol = getIntent().getStringExtra("rol");
        terminos= getIntent().getBooleanExtra("terminos",false);
        ine = getIntent().getBooleanExtra("ine",false);
        licencia = getIntent().getBooleanExtra("licencia",false);
        caracteristicas = getIntent().getBooleanExtra("caracteristicas",false);
        tarjeta = getIntent().getBooleanExtra("tarjeta",false);
        poliza = getIntent().getBooleanExtra("poliza",false);
        tarjeton = getIntent().getBooleanExtra("tarjeton",false);

        btn_retroceso_ine = findViewById(R.id.img_retroceso_ine);

        btn_retroceso_ine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rol.matches("Socio")){
                    Intent main_socio_documentos = new Intent(MainCapturaIne.this, MainSocioDocumentos.class);
                    main_socio_documentos.putExtra("terminos",terminos);
                    main_socio_documentos.putExtra("ine",false);
                    main_socio_documentos.putExtra("licencia",licencia);
                    main_socio_documentos.putExtra("caracteristicas",caracteristicas);
                    main_socio_documentos.putExtra("tarjeta",tarjeta);
                    main_socio_documentos.putExtra("poliza",poliza);
                    main_socio_documentos.putExtra("tarjeton",tarjeton);
                    startActivity(main_socio_documentos);
                    finish();
                }else if(rol.matches("Conductor")){
                    Intent main_conductor_documentos = new Intent(MainCapturaIne.this, MainConductorDocumentos.class);
                    main_conductor_documentos.putExtra("ine_conductor","ine");
                    startActivity(main_conductor_documentos);
                    finish();
                }else
                {
                    Intent main_snv_codnuctor = new Intent(MainCapturaIne.this, MainSnvDocuemtos.class);
                    main_snv_codnuctor.putExtra("ine_snv","ine");
                    startActivity(main_snv_codnuctor);
                    finish();
                }
            }
        });
    }

    public void guardar_ine (View V)
    {
        if(rol.matches("Socio")){
            Intent main_socio_documentos = new Intent(MainCapturaIne.this, MainSocioDocumentos.class);
            main_socio_documentos.putExtra("terminos",terminos);
            main_socio_documentos.putExtra("ine",true);
            main_socio_documentos.putExtra("licencia",licencia);
            main_socio_documentos.putExtra("caracteristicas",caracteristicas);
            main_socio_documentos.putExtra("tarjeta",tarjeta);
            main_socio_documentos.putExtra("poliza",poliza);
            main_socio_documentos.putExtra("tarjeton",tarjeton);
            startActivity(main_socio_documentos);
            finish();
        }else if(rol.matches("Conductor")){
            Intent main_conductor_documentos = new Intent(MainCapturaIne.this, MainConductorDocumentos.class);
            main_conductor_documentos.putExtra("ine_conductor","ine_conductor_ok");
            startActivity(main_conductor_documentos);
            finish();
        }else
        {
            Intent main_snv_codnuctor = new Intent(MainCapturaIne.this, MainSnvDocuemtos.class);
            main_snv_codnuctor.putExtra("ine_snv","ine_snv_ok");
            startActivity(main_snv_codnuctor);
            finish();
        }
    }
}