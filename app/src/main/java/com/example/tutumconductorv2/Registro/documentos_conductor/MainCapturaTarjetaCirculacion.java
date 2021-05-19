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
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

import java.util.Calendar;

public class MainCapturaTarjetaCirculacion extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_regreso_tarjeta_circulacion;
    private EditText vigencia_tarjeta;
    private String rol;
    private String vig_tarjeta=" ";
    private int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_tarjeta_circulacion);
        btn_regreso_tarjeta_circulacion = findViewById(R.id.img_retroceso_tarjeta);

        vigencia_tarjeta = findViewById(R.id.VigenciaTarjeta);
        vigencia_tarjeta.setOnClickListener(this);

        rol = getIntent().getStringExtra("rol");


        btn_regreso_tarjeta_circulacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainSocioDocumentos.class);
                    cadenas_documentos.check_tarjeta1=false;
                    startActivity(main_socio_documentos);
                    finish();
                } else {
                    Intent main_conductor_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainConductorDocumentos.class);
                    cadenas_documentos.check_tarjeta2=false;
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
                vigencia_tarjeta.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
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
                cadenas_documentos.vigTarjeta=vig;
                cadenas_documentos.check_tarjeta1=true;
                startActivity(main_socio_documentos);
                finish();
            }else
            {
                Intent main_conductor_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainConductorDocumentos.class);
                cadenas_documentos.vigTarjeta=vig;
                cadenas_documentos.check_tarjeta2=true;
                startActivity(main_conductor_documentos);
                finish();
            }
        }
    }
}