package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

public class MainCapturaCaracteristicas extends AppCompatActivity {

    private Spinner spFabs;

    private ImageView btn_retroceso_caracteristicas;

    private String rol;

    private boolean terminos_caracteristicas, ine_caracteristicas, licencia_caracteristicas, caracteristicas_caracteristicas, tarjeta_caracteristicas, poliza_caracteristicas, tarjeton_caracteristicas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_caracteristicas);
        rol = getIntent().getStringExtra("rol");
        if (rol.matches("Socio")) {
            terminos_caracteristicas = getIntent().getBooleanExtra("terminos", false);
            ine_caracteristicas = getIntent().getBooleanExtra("ine", false);
            licencia_caracteristicas = getIntent().getBooleanExtra("licencia", false);
            caracteristicas_caracteristicas = getIntent().getBooleanExtra("caracteristicas", false);
            tarjeta_caracteristicas = getIntent().getBooleanExtra("tarjeta", false);
            poliza_caracteristicas = getIntent().getBooleanExtra("poliza", false);
            tarjeton_caracteristicas = getIntent().getBooleanExtra("tarjeton", false);
        } else {
            terminos_caracteristicas = getIntent().getBooleanExtra("terminos_conductor", false);
            ine_caracteristicas = getIntent().getBooleanExtra("ine_conductor", false);
            licencia_caracteristicas = getIntent().getBooleanExtra("licencia_conductor", false);
            caracteristicas_caracteristicas = getIntent().getBooleanExtra("caracteristicas_conductor", false);
            tarjeta_caracteristicas = getIntent().getBooleanExtra("tarjeta_conductor", false);
            poliza_caracteristicas = getIntent().getBooleanExtra("poliza_conductor", false);
            tarjeton_caracteristicas = getIntent().getBooleanExtra("tarjeton_conductor", false);
        }
        btn_retroceso_caracteristicas = findViewById(R.id.img_retroceso_caracteristicas);
        spFabs = (Spinner)findViewById(R.id.spFabricantes);

        //Declaracion del vector que va a contener los fabricantes de vehiculos

        btn_retroceso_caracteristicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaCaracteristicas.this, MainSocioDocumentos.class);
                    main_socio_documentos.putExtra("terminos", terminos_caracteristicas);
                    main_socio_documentos.putExtra("ine", ine_caracteristicas);
                    main_socio_documentos.putExtra("licencia", licencia_caracteristicas);
                    main_socio_documentos.putExtra("caracterisitcas", false);
                    main_socio_documentos.putExtra("tarjeta", tarjeta_caracteristicas);
                    main_socio_documentos.putExtra("poliza", poliza_caracteristicas);
                    main_socio_documentos.putExtra("tarjeton", tarjeton_caracteristicas);
                    startActivity(main_socio_documentos);
                    finish();
                } else{
                    Intent main_conductor_documentos = new Intent(MainCapturaCaracteristicas.this, MainConductorDocumentos.class);
                    main_conductor_documentos.putExtra("terminos_conductor", terminos_caracteristicas);
                    main_conductor_documentos.putExtra("ine_conductor", ine_caracteristicas);
                    main_conductor_documentos.putExtra("licencia_conductor", licencia_caracteristicas);
                    main_conductor_documentos.putExtra("caracteristicas_conductor", false);
                    main_conductor_documentos.putExtra("tarjeta_conductor", tarjeta_caracteristicas);
                    main_conductor_documentos.putExtra("poliza_conductor", poliza_caracteristicas);
                    main_conductor_documentos.putExtra("tarjeton_conductor", tarjeton_caracteristicas);
                    startActivity(main_conductor_documentos);
                    finish();
                }
            }
        });

        String [] fabricantes = {"Fabricante del Vehiculo","Audi","BMW", "Buick", "Chevrolet", "Fiat", "Ford", "Honda", "Hyundai", "Jeep", "Kia", "Maruti",
                                 "Mazda", "Mercedes", "Mitsubishi", "Nissan", "Peugeot", "Renault","Subaru", "Suzuki","Toyota","Volkswagen"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_style_1,fabricantes);
        spFabs.setAdapter(adapter);

    }
}