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
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSnvDocuemtos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

import java.util.Calendar;

public class MainCapturaLicencia extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_regreso_licencia;
    private EditText vigenciaLicencia;
    private int year, month, day;
    private String rol;
    private String VigLicencia = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_licencia);
        rol = getIntent().getStringExtra("rol");
        vigenciaLicencia = findViewById(R.id.VigenciaLicencia);
        btn_regreso_licencia = findViewById(R.id.img_retroceso_licencia);

        vigenciaLicencia.setOnClickListener(this);
        btn_regreso_licencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaLicencia.this, MainSocioDocumentos.class);
                    cadenas_documentos.check_licencia1=false;
                    startActivity(main_socio_documentos);
                    finish();
                } else if (rol.matches("Conductor")) {
                    Intent main_conductor_documentos = new Intent(MainCapturaLicencia.this, MainConductorDocumentos.class);
                    cadenas_documentos.check_licencia2=false;
                    startActivity(main_conductor_documentos);
                    finish();
                } else {
                    Intent main_snv_documentos = new Intent(MainCapturaLicencia.this, MainSnvDocuemtos.class);
                    cadenas_documentos.check_licencia3=false;
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
                cadenas_documentos.vigLicencia=vig;
                cadenas_documentos.check_licencia1=true;
                startActivity(main_socio_documentos);
                finish();
            }else if(rol.matches("Conductor"))
            {
                Intent main_conductor_documentos = new Intent(MainCapturaLicencia.this, MainConductorDocumentos.class);
                cadenas_documentos.vigLicencia=vig;
                cadenas_documentos.check_licencia2=true;
                startActivity(main_conductor_documentos);
                finish();
            }
            else
            {
                Intent main_snv_documentos = new Intent(MainCapturaLicencia.this, MainSnvDocuemtos.class);
                cadenas_documentos.vigLicencia=vig;
                cadenas_documentos.check_licencia3=true;
                startActivity(main_snv_documentos);
                finish();
            }
        }
    }
}