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

public class MainCapturaTarjeton extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_regreso_tarjeton;
    private EditText vigenciaTarjeton;
    private int year, month, day;
    private String rol;
    private String VigTarjeton= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_tarjeton);
        rol = getIntent().getStringExtra("rol");

        vigenciaTarjeton = findViewById(R.id.VigenciaTarjeton);
        btn_regreso_tarjeton = findViewById(R.id.img_retroceso_tarjeton);

        vigenciaTarjeton.setOnClickListener(this);
        btn_regreso_tarjeton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaTarjeton.this, MainSocioDocumentos.class);
                    cadenas_documentos.check_tarjeton1=false;
                    startActivity(main_socio_documentos);
                    finish();
                } else if (rol.matches("Conductor")) {
                    Intent main_conductor_documentos = new Intent(MainCapturaTarjeton.this, MainConductorDocumentos.class);
                    cadenas_documentos.check_tarjeton2=false;
                    startActivity(main_conductor_documentos);
                    finish();
                } else {
                    Intent main_snv_documentos = new Intent(MainCapturaTarjeton.this, MainSnvDocuemtos.class);
                    cadenas_documentos.check_tarjeton3=false;
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
                cadenas_documentos.check_tarjeton1=true;
                cadenas_documentos.vigTarjeton = vig;
                startActivity(main_socio_documentos);
                finish();
            }else if(rol.matches("Conductor"))
            {
                Intent main_conductor_documentos = new Intent(MainCapturaTarjeton.this, MainConductorDocumentos.class);
                cadenas_documentos.check_tarjeton2=true;
                cadenas_documentos.vigTarjeton = vig;
                startActivity(main_conductor_documentos);
                finish();
            }
            else
            {
                Intent main_snv_documentos = new Intent(MainCapturaTarjeton.this, MainSnvDocuemtos.class);
                cadenas_documentos.check_tarjeton3=true;
                cadenas_documentos.vigTarjeton = vig;
                startActivity(main_snv_documentos);
                finish();
            }
        }
    }
}