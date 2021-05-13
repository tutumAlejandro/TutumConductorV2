package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

public class MainCapturaLicencia extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_regreso_licencia;
    private EditText vigenciaLicencia;
    private int year, month, day;
    private String rol;
    private String VigLicencia = " ";

    private boolean terminos_licencia, ine_licencia, licencia_licencia, caracteristicas_licencia, tarjeta_licencia, poliza_licencia, tarjeton_licencia,codigo_licencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_licencia);
        rol = getIntent().getStringExtra("rol");
        if (rol.matches("Socio")) {
            terminos_licencia = getIntent().getBooleanExtra("terminos", false);
            ine_licencia = getIntent().getBooleanExtra("ine", false);
            licencia_licencia = getIntent().getBooleanExtra("licencia", false);
            caracteristicas_licencia = getIntent().getBooleanExtra("caracteristicas", false);
            tarjeta_licencia = getIntent().getBooleanExtra("tarjeta", false);
            poliza_licencia = getIntent().getBooleanExtra("poliza", false);
            tarjeton_licencia = getIntent().getBooleanExtra("tarjeton", false);
            codigo_licencia = getIntent().getBooleanExtra("codigo",false);
        } else if (rol.matches("Conductor")) {
            terminos_licencia = getIntent().getBooleanExtra("terminos_conductor", false);
            ine_licencia = getIntent().getBooleanExtra("ine_conductor", false);
            licencia_licencia = getIntent().getBooleanExtra("licencia_conductor", false);
            caracteristicas_licencia = getIntent().getBooleanExtra("caracteristicas_conductor", false);
            tarjeta_licencia = getIntent().getBooleanExtra("tarjeta_conductor", false);
            poliza_licencia = getIntent().getBooleanExtra("poliza_conductor", false);
            tarjeton_licencia = getIntent().getBooleanExtra("tarjeton_conductor", false);
            codigo_licencia = getIntent().getBooleanExtra("codigo_conductor",false);
        } else {
            terminos_licencia = getIntent().getBooleanExtra("terminos_snv", false);
            ine_licencia = getIntent().getBooleanExtra("ine_snv", false);
            licencia_licencia = getIntent().getBooleanExtra("licencia_snv", false);
            caracteristicas_licencia = getIntent().getBooleanExtra("caracteristicas_snv", false);
            tarjeta_licencia = getIntent().getBooleanExtra("tarjeta_snv", false);
            poliza_licencia = getIntent().getBooleanExtra("poliza_snv", false);
            tarjeton_licencia = getIntent().getBooleanExtra("tarjeton_snv", false);
            codigo_licencia = getIntent().getBooleanExtra("codigo_snv",false);
        }
        vigenciaLicencia = findViewById(R.id.VigenciaLicencia);
        btn_regreso_licencia = findViewById(R.id.img_retroceso_licencia);

        vigenciaLicencia.setOnClickListener(this);
        btn_regreso_licencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaLicencia.this, MainSocioDocumentos.class);
                    main_socio_documentos.putExtra("terminos", terminos_licencia);
                    main_socio_documentos.putExtra("ine", ine_licencia);
                    main_socio_documentos.putExtra("licencia", false);
                    main_socio_documentos.putExtra("caracterisitcas", caracteristicas_licencia);
                    main_socio_documentos.putExtra("tarjeta", tarjeta_licencia);
                    main_socio_documentos.putExtra("poliza", poliza_licencia);
                    main_socio_documentos.putExtra("tarjeton", tarjeton_licencia);
                    main_socio_documentos.putExtra("codigo",codigo_licencia);
                    startActivity(main_socio_documentos);
                    finish();
                } else if (rol.matches("Conductor")) {
                    Intent main_conductor_documentos = new Intent(MainCapturaLicencia.this, MainConductorDocumentos.class);
                    main_conductor_documentos.putExtra("terminos_conductor", terminos_licencia);
                    main_conductor_documentos.putExtra("ine_conductor", ine_licencia);
                    main_conductor_documentos.putExtra("licencia_conductor", false);
                    main_conductor_documentos.putExtra("caracteristicas_conductor", caracteristicas_licencia);
                    main_conductor_documentos.putExtra("tarjeta_conductor", tarjeta_licencia);
                    main_conductor_documentos.putExtra("poliza_conductor", poliza_licencia);
                    main_conductor_documentos.putExtra("tarjeton_conductor", tarjeton_licencia);
                    main_conductor_documentos.putExtra("codigo_conductor",codigo_licencia);
                    startActivity(main_conductor_documentos);
                    finish();
                } else {
                    Intent main_snv_documentos = new Intent(MainCapturaLicencia.this, MainSnvDocuemtos.class);
                    main_snv_documentos.putExtra("terminos_snv", terminos_licencia);
                    main_snv_documentos.putExtra("ine_snv", ine_licencia);
                    main_snv_documentos.putExtra("licencia_snv", false);
                    main_snv_documentos.putExtra("caracteristicas_snv", caracteristicas_licencia);
                    main_snv_documentos.putExtra("tarjeta_snv", tarjeta_licencia);
                    main_snv_documentos.putExtra("poliza_snv", poliza_licencia);
                    main_snv_documentos.putExtra("tarjeton_snv", tarjeton_licencia);
                    main_snv_documentos.putExtra("codigo_snv",codigo_licencia);
                    startActivity(main_snv_documentos);
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                vigenciaLicencia.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                VigLicencia = (dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);
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


    public void guarda_licencia(View v)
    {
        String vig = vigenciaLicencia.getText().toString().trim();
        if(!check_vigencia_licencia(vig))
        {
            return;
        }else {
            if(rol.matches("Socio"))
            {
                Intent main_socio_documentos = new Intent(MainCapturaLicencia.this, MainSocioDocumentos.class);
                main_socio_documentos.putExtra("VigenciaLicencia",vig);
                main_socio_documentos.putExtra("terminos",terminos_licencia);
                main_socio_documentos.putExtra("ine",ine_licencia);
                main_socio_documentos.putExtra("licencia",true);
                main_socio_documentos.putExtra("caracterisitcas",caracteristicas_licencia);
                main_socio_documentos.putExtra("tarjeta",tarjeta_licencia);
                main_socio_documentos.putExtra("poliza",poliza_licencia);
                main_socio_documentos.putExtra("tarjeton",tarjeton_licencia);
                main_socio_documentos.putExtra("codigo",codigo_licencia);
                startActivity(main_socio_documentos);
                finish();
            }else if(rol.matches("Conductor"))
            {
                Intent main_conductor_documentos = new Intent(MainCapturaLicencia.this, MainConductorDocumentos.class);
                main_conductor_documentos.putExtra("VigenciaLicencia",vig);
                main_conductor_documentos.putExtra("terminos_conductor",terminos_licencia);
                main_conductor_documentos.putExtra("ine_conductor",ine_licencia);
                main_conductor_documentos.putExtra("licencia_conductor",true);
                main_conductor_documentos.putExtra("caracteristicas_conductor",caracteristicas_licencia);
                main_conductor_documentos.putExtra("tarjeta_conductor",tarjeta_licencia);
                main_conductor_documentos.putExtra("poliza_conductor",poliza_licencia);
                main_conductor_documentos.putExtra("tarjeton_conductor",tarjeton_licencia);
                main_conductor_documentos.putExtra("codigo_conductor",codigo_licencia);
                startActivity(main_conductor_documentos);
                finish();
            }
            else
            {
                Intent main_snv_documentos = new Intent(MainCapturaLicencia.this, MainSnvDocuemtos.class);
                main_snv_documentos.putExtra("VigenciaLicencia",vig);
                main_snv_documentos.putExtra("terminos_snv",terminos_licencia);
                main_snv_documentos.putExtra("ine_snv",ine_licencia);
                main_snv_documentos.putExtra("licencia_snv",true);
                main_snv_documentos.putExtra("caracteristicas_snv",caracteristicas_licencia);
                main_snv_documentos.putExtra("tarjeta_snv",tarjeta_licencia);
                main_snv_documentos.putExtra("poliza_snv",poliza_licencia);
                main_snv_documentos.putExtra("tarjeton_snv",tarjeton_licencia);
                main_snv_documentos.putExtra("codigo_snv",codigo_licencia);
                startActivity(main_snv_documentos);
                finish();
            }
        }
    }
}