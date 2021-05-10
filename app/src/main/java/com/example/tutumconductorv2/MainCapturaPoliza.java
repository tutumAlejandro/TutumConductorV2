package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

import static android.app.AlertDialog.*;

public class MainCapturaPoliza extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_regreso_poliza, img_foto_poliza;
    private EditText vigenciaPoliza;
    private int year, month, day;
    private String rol;
    private String VigPoliza=" ";

    private boolean terminos_poliza, ine_poliza, licencia_poliza, caracteristicas_poliza, tarjeta_poliza, poliza_poliza, tarjeton_poliza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_poliza);

        rol = getIntent().getStringExtra("rol");
        if (rol.matches("Socio")) {
            terminos_poliza = getIntent().getBooleanExtra("terminos", false);
            ine_poliza = getIntent().getBooleanExtra("ine", false);
            licencia_poliza = getIntent().getBooleanExtra("licencia", false);
            caracteristicas_poliza = getIntent().getBooleanExtra("caracteristicas", false);
            tarjeta_poliza = getIntent().getBooleanExtra("tarjeta", false);
            poliza_poliza = getIntent().getBooleanExtra("poliza", false);
            tarjeton_poliza = getIntent().getBooleanExtra("tarjeton", false);
        } else {
            terminos_poliza = getIntent().getBooleanExtra("terminos_conductor", false);
            ine_poliza = getIntent().getBooleanExtra("ine_conductor", false);
            licencia_poliza = getIntent().getBooleanExtra("licencia_conductor", false);
            caracteristicas_poliza = getIntent().getBooleanExtra("caracteristicas_conductor", false);
            tarjeta_poliza = getIntent().getBooleanExtra("tarjeta_conductor", false);
            poliza_poliza = getIntent().getBooleanExtra("poliza_conductor", false);
            tarjeton_poliza = getIntent().getBooleanExtra("tarjeton_conductor", false);
        }

        vigenciaPoliza = findViewById(R.id.VigenciaPoliza);
        btn_regreso_poliza = findViewById(R.id.img_retroceso_poliza);

        vigenciaPoliza.setOnClickListener(this);

        btn_regreso_poliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaPoliza.this, MainSocioDocumentos.class);
                    main_socio_documentos.putExtra("terminos", terminos_poliza);
                    main_socio_documentos.putExtra("ine", ine_poliza);
                    main_socio_documentos.putExtra("licencia", licencia_poliza);
                    main_socio_documentos.putExtra("caracterisitcas", caracteristicas_poliza);
                    main_socio_documentos.putExtra("tarjeta", tarjeta_poliza);
                    main_socio_documentos.putExtra("poliza", false);
                    main_socio_documentos.putExtra("tarjeton", tarjeton_poliza);
                    startActivity(main_socio_documentos);
                    finish();
                } else {
                    Intent main_conductor_documentos = new Intent(MainCapturaPoliza.this, MainConductorDocumentos.class);
                    main_conductor_documentos.putExtra("terminos_conductor", terminos_poliza);
                    main_conductor_documentos.putExtra("ine_conductor", ine_poliza);
                    main_conductor_documentos.putExtra("licencia_conductor", licencia_poliza);
                    main_conductor_documentos.putExtra("caracteristicas_conductor", caracteristicas_poliza);
                    main_conductor_documentos.putExtra("tarjeta_conductor", tarjeta_poliza);
                    main_conductor_documentos.putExtra("poliza_conductor", false);
                    main_conductor_documentos.putExtra("tarjeton_conductor", tarjeton_poliza);
                    startActivity(main_conductor_documentos);
                    finish();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        final Calendar calendario = Calendar.getInstance();
        year = calendario.get(Calendar.YEAR);
        month = calendario.get(Calendar.MONTH);
        day = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                vigenciaPoliza.setText(day + "/" + (month +1) + "/" + year);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    private boolean check_vigencia_licencia(String vigencia)
    {
        if (vigencia.isEmpty())
        {
            return false;
        }
        else{
            return true;
        }
    }

    public void guarda_poliza(View v)
    {
        String vig = vigenciaPoliza.getText().toString().trim();
        if(!check_vigencia_licencia(vig))
        {
            return;
        }else {
            if(rol.matches("Socio"))
            {
                Intent main_socio_documentos = new Intent(MainCapturaPoliza.this, MainSocioDocumentos.class);
                main_socio_documentos.putExtra("VigenciaPoliza",vig);
                main_socio_documentos.putExtra("terminos",terminos_poliza);
                main_socio_documentos.putExtra("ine",ine_poliza);
                main_socio_documentos.putExtra("licencia",licencia_poliza);
                main_socio_documentos.putExtra("caracterisitcas",caracteristicas_poliza);
                main_socio_documentos.putExtra("tarjeta",tarjeta_poliza);
                main_socio_documentos.putExtra("poliza",true);
                main_socio_documentos.putExtra("tarjeton",tarjeton_poliza);
                startActivity(main_socio_documentos);
                finish();
            }else if(rol.matches("Conductor"))
            {
                Intent main_conductor_documentos = new Intent(MainCapturaPoliza.this, MainConductorDocumentos.class);
                main_conductor_documentos.putExtra("VigenciaPoliza",vig);
                main_conductor_documentos.putExtra("terminos_conductor",terminos_poliza);
                main_conductor_documentos.putExtra("ine_conductor",ine_poliza);
                main_conductor_documentos.putExtra("licencia_conductor",licencia_poliza);
                main_conductor_documentos.putExtra("caracteristicas_conductor",caracteristicas_poliza);
                main_conductor_documentos.putExtra("tarjeta_conductor",tarjeta_poliza);
                main_conductor_documentos.putExtra("poliza_conductor",true);
                main_conductor_documentos.putExtra("tarjeton_conductor",tarjeton_poliza);
                startActivity(main_conductor_documentos);
                finish();
            }
        }
    }
}