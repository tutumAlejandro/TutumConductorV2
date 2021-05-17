package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSnvDocuemtos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

import java.util.Calendar;

public class MainCapturaTarjeton extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_regreso_tarjeton;
    private EditText vigenciaTarjeton;
    private int year, month, day;
    private String rol;
    private String VigTarjeton= "";

    private boolean terminos_tarjeton, ine_tarjeton, licencia_tarjeton, caracteristicas_tarjeton, tarjeta_tarjeton, poliza_tarjeton, tarjeton_tarjeton,codigo_tarjeton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_tarjeton);
        rol = getIntent().getStringExtra("rol");
        if (rol.matches("Socio")) {
            terminos_tarjeton = getIntent().getBooleanExtra("terminos", false);
            ine_tarjeton = getIntent().getBooleanExtra("ine", false);
            licencia_tarjeton = getIntent().getBooleanExtra("licencia", false);
            caracteristicas_tarjeton = getIntent().getBooleanExtra("caracteristicas", false);
            tarjeta_tarjeton = getIntent().getBooleanExtra("tarjeta", false);
            poliza_tarjeton = getIntent().getBooleanExtra("poliza", false);
            tarjeton_tarjeton = getIntent().getBooleanExtra("tarjeton", false);
            codigo_tarjeton = getIntent().getBooleanExtra("codigo",false);
        } else if (rol.matches("Conductor")) {
            terminos_tarjeton = getIntent().getBooleanExtra("terminos_conductor", false);
            ine_tarjeton = getIntent().getBooleanExtra("ine_conductor", false);
            licencia_tarjeton = getIntent().getBooleanExtra("licencia_conductor", false);
            caracteristicas_tarjeton = getIntent().getBooleanExtra("caracteristicas_conductor", false);
            tarjeta_tarjeton = getIntent().getBooleanExtra("tarjeta_conductor", false);
            poliza_tarjeton= getIntent().getBooleanExtra("poliza_conductor", false);
            tarjeton_tarjeton = getIntent().getBooleanExtra("tarjeton_conductor", false);
            codigo_tarjeton = getIntent().getBooleanExtra("codigo_conductor",false);
        } else {
            terminos_tarjeton = getIntent().getBooleanExtra("terminos_snv", false);
            ine_tarjeton = getIntent().getBooleanExtra("ine_snv", false);
            licencia_tarjeton = getIntent().getBooleanExtra("licencia_snv", false);
            caracteristicas_tarjeton = getIntent().getBooleanExtra("caracteristicas_snv", false);
            tarjeta_tarjeton = getIntent().getBooleanExtra("tarjeta_snv", false);
            poliza_tarjeton = getIntent().getBooleanExtra("poliza_snv", false);
            tarjeton_tarjeton = getIntent().getBooleanExtra("tarjeton_snv", false);
            codigo_tarjeton = getIntent().getBooleanExtra("codigo_snv",false);
        }
        vigenciaTarjeton = findViewById(R.id.VigenciaTarjeton);
        btn_regreso_tarjeton = findViewById(R.id.img_retroceso_tarjeton);

        vigenciaTarjeton.setOnClickListener(this);
        btn_regreso_tarjeton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaTarjeton.this, MainSocioDocumentos.class);
                    main_socio_documentos.putExtra("terminos", terminos_tarjeton);
                    main_socio_documentos.putExtra("ine", ine_tarjeton);
                    main_socio_documentos.putExtra("licencia", licencia_tarjeton);
                    main_socio_documentos.putExtra("caracterisitcas", caracteristicas_tarjeton);
                    main_socio_documentos.putExtra("tarjeta", tarjeta_tarjeton);
                    main_socio_documentos.putExtra("poliza", poliza_tarjeton);
                    main_socio_documentos.putExtra("tarjeton", false);
                    main_socio_documentos.putExtra("codigo",codigo_tarjeton);
                    startActivity(main_socio_documentos);
                    finish();
                } else if (rol.matches("Conductor")) {
                    Intent main_conductor_documentos = new Intent(MainCapturaTarjeton.this, MainConductorDocumentos.class);
                    main_conductor_documentos.putExtra("terminos_conductor", terminos_tarjeton);
                    main_conductor_documentos.putExtra("ine_conductor", ine_tarjeton);
                    main_conductor_documentos.putExtra("licencia_conductor", licencia_tarjeton);
                    main_conductor_documentos.putExtra("caracteristicas_conductor", caracteristicas_tarjeton);
                    main_conductor_documentos.putExtra("tarjeta_conductor", tarjeta_tarjeton);
                    main_conductor_documentos.putExtra("poliza_conductor", poliza_tarjeton);
                    main_conductor_documentos.putExtra("tarjeton_conductor", false);
                    main_conductor_documentos.putExtra("codigo_conductor", codigo_tarjeton);
                    startActivity(main_conductor_documentos);
                    finish();
                } else {
                    Intent main_snv_documentos = new Intent(MainCapturaTarjeton.this, MainSnvDocuemtos.class);
                    main_snv_documentos.putExtra("terminos_snv", terminos_tarjeton);
                    main_snv_documentos.putExtra("ine_snv", ine_tarjeton);
                    main_snv_documentos.putExtra("licencia_snv", licencia_tarjeton);
                    main_snv_documentos.putExtra("caracteristicas_snv", caracteristicas_tarjeton);
                    main_snv_documentos.putExtra("tarjeta_snv", tarjeta_tarjeton);
                    main_snv_documentos.putExtra("poliza_snv", poliza_tarjeton);
                    main_snv_documentos.putExtra("tarjeton_snv", false);
                    main_snv_documentos.putExtra("codigo_snv",codigo_tarjeton);
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
                vigenciaTarjeton.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    private boolean check_vigencia_Tarjeton(String vigencia)
    {
        if (vigencia.isEmpty())
        {
            return false;
        }
        else{
            return true;
        }
    }

    public void guarda_tarjeton(View v)
    {
        String vig = vigenciaTarjeton.getText().toString().trim();
        if(!check_vigencia_Tarjeton(vig))
        {
            return;
        }else {
            if(rol.matches("Socio"))
            {
                Intent main_socio_documentos = new Intent(MainCapturaTarjeton.this, MainSocioDocumentos.class);
                main_socio_documentos.putExtra("VigenciaTarjeton",VigTarjeton);
                main_socio_documentos.putExtra("terminos",terminos_tarjeton);
                main_socio_documentos.putExtra("ine",ine_tarjeton);
                main_socio_documentos.putExtra("licencia",licencia_tarjeton);
                main_socio_documentos.putExtra("caracterisitcas",caracteristicas_tarjeton);
                main_socio_documentos.putExtra("tarjeta",tarjeta_tarjeton);
                main_socio_documentos.putExtra("poliza",poliza_tarjeton);
                main_socio_documentos.putExtra("tarjeton",true);
                main_socio_documentos.putExtra("codigo",codigo_tarjeton);
                startActivity(main_socio_documentos);
                finish();
            }else if(rol.matches("Conductor"))
            {
                Intent main_conductor_documentos = new Intent(MainCapturaTarjeton.this, MainConductorDocumentos.class);
                main_conductor_documentos.putExtra("VigenciaTarjeton",VigTarjeton);
                main_conductor_documentos.putExtra("terminos_conductor",terminos_tarjeton);
                main_conductor_documentos.putExtra("ine_conductor",ine_tarjeton);
                main_conductor_documentos.putExtra("licencia_conductor",licencia_tarjeton);
                main_conductor_documentos.putExtra("caracteristicas_conductor",caracteristicas_tarjeton);
                main_conductor_documentos.putExtra("tarjeta_conductor",tarjeta_tarjeton);
                main_conductor_documentos.putExtra("poliza_conductor",poliza_tarjeton);
                main_conductor_documentos.putExtra("tarjeton_conductor",true);
                main_conductor_documentos.putExtra("codigo_conductor", codigo_tarjeton);
                startActivity(main_conductor_documentos);
                finish();
            }
            else
            {
                Intent main_snv_documentos = new Intent(MainCapturaTarjeton.this, MainSnvDocuemtos.class);
                main_snv_documentos.putExtra("VigenciaTarjeton",vig);
                main_snv_documentos.putExtra("terminos_snv",terminos_tarjeton);
                main_snv_documentos.putExtra("ine_snv",ine_tarjeton);
                main_snv_documentos.putExtra("licencia_snv",licencia_tarjeton);
                main_snv_documentos.putExtra("caracteristicas_snv",caracteristicas_tarjeton);
                main_snv_documentos.putExtra("tarjeta_snv",tarjeta_tarjeton);
                main_snv_documentos.putExtra("poliza_snv",poliza_tarjeton);
                main_snv_documentos.putExtra("tarjeton_snv",true);
                main_snv_documentos.putExtra("codigo_snv",codigo_tarjeton);
                startActivity(main_snv_documentos);
                finish();
            }
        }
    }
}