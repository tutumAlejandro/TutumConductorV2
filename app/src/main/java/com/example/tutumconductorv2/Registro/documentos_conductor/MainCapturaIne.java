package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSnvDocuemtos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

public class MainCapturaIne extends AppCompatActivity {

    private ImageView btn_retroceso_ine;
    private String rol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_ine);

        rol = getIntent().getStringExtra("rol");

        btn_retroceso_ine = findViewById(R.id.img_retroceso_ine);

        btn_retroceso_ine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rol.matches("Socio")){
                    Intent main_socio_documentos = new Intent(MainCapturaIne.this, MainSocioDocumentos.class);
                    cadenas_documentos.check_ine1=false;
                    startActivity(main_socio_documentos);
                    finish();
                }else if(rol.matches("Conductor")){
                    Intent main_conductor_documentos = new Intent(MainCapturaIne.this, MainConductorDocumentos.class);
                    cadenas_documentos.check_ine2=false;
                    startActivity(main_conductor_documentos);
                    finish();
                }else{
                    Intent main_snv_documentos = new Intent(MainCapturaIne.this, MainSnvDocuemtos.class);
                    cadenas_documentos.check_ine3=false;
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
            cadenas_documentos.check_ine1=true;
            startActivity(main_socio_documentos);
            finish();
        }else if(rol.matches("Conductor")){
            Intent main_conductor_documentos = new Intent(MainCapturaIne.this, MainConductorDocumentos.class);
            cadenas_documentos.check_ine2=true;
            startActivity(main_conductor_documentos);
            finish();
        }else
        {
            Intent main_snv_documentos = new Intent(MainCapturaIne.this, MainSnvDocuemtos.class);
            cadenas_documentos.check_ine3=true;
            startActivity(main_snv_documentos);
            finish();
        }
    }
}