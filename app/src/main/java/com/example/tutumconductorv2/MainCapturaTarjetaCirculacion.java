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

public class MainCapturaTarjetaCirculacion extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_regreso_tarjeta_circulacion;
    private EditText vigenciaTarjeta;
    private int year, month, day;
    private String rol, VigTarjeta;

    private boolean terminos,ine,licencia,caracteristicas,tarjeta,poliza,tarjeton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_tarjeta_circulacion);

        // Asignacion de las variables para los Intent
        rol = getIntent().getStringExtra("rol");
        terminos= getIntent().getBooleanExtra("terminos",false);
        ine = getIntent().getBooleanExtra("ine",false);
        licencia = getIntent().getBooleanExtra("licencia",false);
        caracteristicas = getIntent().getBooleanExtra("caracteristicas",false);
        tarjeta = getIntent().getBooleanExtra("tarjeta",false);
        poliza = getIntent().getBooleanExtra("poliza",false);
        tarjeton = getIntent().getBooleanExtra("tarjeton",false);

        //Asociacion de los xml con la parte logica
        btn_regreso_tarjeta_circulacion = findViewById(R.id.img_retroceso_tarjeta);
        vigenciaTarjeta = findViewById(R.id.VigenciaTarjetaCirculacion);

        vigenciaTarjeta.setOnClickListener(this);

        btn_regreso_tarjeta_circulacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rol.matches("Socio")){
                    Intent main_socio_documentos = new Intent(MainCapturaTarjetaCirculacion.this,MainSocioDocumentos.class);
                    startActivity(main_socio_documentos);
                    finish();
                }else if(rol.matches("Conductor"))
                {
                    Intent main_conductor_documentos = new Intent(MainCapturaTarjetaCirculacion.this,MainConductorDocumentos.class);
                    startActivity(main_conductor_documentos);
                    finish();
                }else{
                    Intent main_snv_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainSnvDocuemtos.class);
                    startActivity(main_snv_documentos);
                    finish();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        final Calendar c_vigencia = Calendar.getInstance();
        year = c_vigencia.get(Calendar.YEAR);
        month = c_vigencia.get(Calendar.MONTH);
        day = c_vigencia.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                vigenciaTarjeta.setText(day + "/" + (month + 1) + "/" + year);
                VigTarjeta = (day + "/" + (month + 1) + "/" + year);
            }
        },day,month,year);
        datePickerDialog.show();
    }

    public void guarda_tarjeta(View v)
    {
        if(VigTarjeta.isEmpty())
        {
            return;
        }else
        {
            if(rol.matches("Socio"))
            {
                Intent main_socio_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainSocioDocumentos.class);
                main_socio_documentos.putExtra("VigenciaTarjeta",VigTarjeta);
                startActivity(main_socio_documentos);

            }else if(rol.matches("Conductor"))
            {
                Intent main_conductor_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainConductorDocumentos.class);
                main_conductor_documentos.putExtra("VigenciaTarjeta",VigTarjeta);
                startActivity(main_conductor_documentos);
            }
            else
            {
                Intent main_snv_documentos = new Intent(MainCapturaTarjetaCirculacion.this, MainSnvDocuemtos.class);
                main_snv_documentos.putExtra("VigenciaTarjeta",VigTarjeta);
            }
        }

    }
}