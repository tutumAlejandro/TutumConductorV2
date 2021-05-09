package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

public class MainCapturaTarjetaCirculacion extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_regreso_tarjeta_circulacion;
    private EditText vigencia_tarjeta;
    private String rol;
    private String vig_tarjeta=" ";
    private int day,month,year;

    private boolean terminos_tarjeta,ine_tarjeta,licencia_tarjeta,caracteristicas_tarjeta,tarjeta_tarjeta,poliza_tarjeta,tarjeton_tarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_tarjeta_circulacion);
        btn_regreso_tarjeta_circulacion = findViewById(R.id.img_retroceso_tarjeta);

        vigencia_tarjeta = findViewById(R.id.VigenciaTarjeta);
        vigencia_tarjeta.setOnClickListener(this);

        rol = getIntent().getStringExtra("rol");

        if (rol.matches("Socio")) {
            terminos_tarjeta = getIntent().getBooleanExtra("terminos", false);
            ine_tarjeta = getIntent().getBooleanExtra("ine", false);
            licencia_tarjeta = getIntent().getBooleanExtra("licencia", false);
            caracteristicas_tarjeta = getIntent().getBooleanExtra("caracteristicas", false);
            tarjeta_tarjeta = getIntent().getBooleanExtra("tarjeta", false);
            poliza_tarjeta = getIntent().getBooleanExtra("poliza", false);
            tarjeton_tarjeta = getIntent().getBooleanExtra("tarjeton", false);
        } else {
            terminos_tarjeta = getIntent().getBooleanExtra("terminos_conductor", false);
            ine_tarjeta = getIntent().getBooleanExtra("ine_conductor", false);
            licencia_tarjeta = getIntent().getBooleanExtra("licencia_conductor", false);
            caracteristicas_tarjeta = getIntent().getBooleanExtra("caracteristicas_conductor", false);
            tarjeta_tarjeta = getIntent().getBooleanExtra("tarjeta_conductor", false);
            poliza_tarjeta = getIntent().getBooleanExtra("poliza_conductor", false);
            tarjeton_tarjeta = getIntent().getBooleanExtra("tarjeton_conductor", false);
        }



        btn_regreso_tarjeta_circulacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainSocioDocumentos.class);
                    main_socio_documentos.putExtra("terminos", terminos_tarjeta);
                    main_socio_documentos.putExtra("ine", ine_tarjeta);
                    main_socio_documentos.putExtra("licencia", licencia_tarjeta);
                    main_socio_documentos.putExtra("caracterisitcas", caracteristicas_tarjeta);
                    main_socio_documentos.putExtra("tarjeta", false);
                    main_socio_documentos.putExtra("poliza", poliza_tarjeta);
                    main_socio_documentos.putExtra("tarjeton", tarjeton_tarjeta);
                    startActivity(main_socio_documentos);
                    finish();
                } else {
                    Intent main_conductor_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainConductorDocumentos.class);
                    main_conductor_documentos.putExtra("terminos_conductor", terminos_tarjeta);
                    main_conductor_documentos.putExtra("ine_conductor", ine_tarjeta);
                    main_conductor_documentos.putExtra("licencia_conductor", licencia_tarjeta);
                    main_conductor_documentos.putExtra("caracteristicas_conductor", caracteristicas_tarjeta);
                    main_conductor_documentos.putExtra("tarjeta_conductor", false);
                    main_conductor_documentos.putExtra("poliza_conductor", poliza_tarjeta);
                    main_conductor_documentos.putExtra("tarjeton_conductor", tarjeton_tarjeta);
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                vigencia_tarjeta.setText(day + "/" + (month + 1) + "/" + year);
                vig_tarjeta = (dayOfMonth + "/" + (month + 1) + "/" + year);
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

    public void guarda_tarjeta(View v)
    {
        String vig = vigencia_tarjeta.getText().toString().trim();
        if(!check_vigencia_licencia(vig))
        {
            return;
        }else {
            if(rol.matches("Socio"))
            {
                Intent main_socio_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainSocioDocumentos.class);
                main_socio_documentos.putExtra("VigenciaTarjeta",vig);
                main_socio_documentos.putExtra("terminos",terminos_tarjeta);
                main_socio_documentos.putExtra("ine",ine_tarjeta);
                main_socio_documentos.putExtra("licencia",licencia_tarjeta);
                main_socio_documentos.putExtra("caracterisitcas",caracteristicas_tarjeta);
                main_socio_documentos.putExtra("tarjeta",true);
                main_socio_documentos.putExtra("poliza",poliza_tarjeta);
                main_socio_documentos.putExtra("tarjeton",tarjeton_tarjeta);
                startActivity(main_socio_documentos);
                finish();
            }else
            {
                Intent main_conductor_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainConductorDocumentos.class);
                main_conductor_documentos.putExtra("VigenciaTarjeta",vig);
                main_conductor_documentos.putExtra("terminos_conductor",terminos_tarjeta);
                main_conductor_documentos.putExtra("ine_conductor",ine_tarjeta);
                main_conductor_documentos.putExtra("licencia_conductor",licencia_tarjeta);
                main_conductor_documentos.putExtra("caracteristicas_conductor",caracteristicas_tarjeta);
                main_conductor_documentos.putExtra("tarjeta_conductor",true);
                main_conductor_documentos.putExtra("poliza_conductor",poliza_tarjeta);
                main_conductor_documentos.putExtra("tarjeton_conductor",tarjeton_tarjeta);
                startActivity(main_conductor_documentos);
                finish();
            }
        }
    }
}