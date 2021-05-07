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
import java.util.Calendar;

public class MainCapturaLicencia extends AppCompatActivity implements View.OnClickListener {

    private EditText vigenciaLicencia;
    private int year,month,day;
     private String rol,VigLicencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_licencia);
        rol = getIntent().getStringExtra("rol");
        vigenciaLicencia = findViewById(R.id.VigenciaLicencia);
        vigenciaLicencia.setOnClickListener(this);

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
                vigenciaLicencia.setText(day + "/" + (month + 1) + "/" + year);
                VigLicencia=(day + "/" + month + "/" + year);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    public void guarda_licencia(View v)
    {
        if(VigLicencia.isEmpty())
        {
            return;
        }else
        {
            if(rol.matches("Socio"))
            {
                Intent main_socio_documentos = new Intent(MainCapturaLicencia.this, MainSocioDocumentos.class);
                main_socio_documentos.putExtra("VigenciaLicencia",VigLicencia);
                main_socio_documentos.putExtra("Licencia","ok");
                startActivity(main_socio_documentos);

            }else if(rol.matches("Conductor"))
            {
                Intent main_conductor_documentos = new Intent(MainCapturaLicencia.this, MainConductorDocumentos.class);
                main_conductor_documentos.putExtra("VigenciaLicencia",VigLicencia);
                main_conductor_documentos.putExtra("Licencia","ok");
                startActivity(main_conductor_documentos);
            }
            else
            {
                Intent main_snv_documentos = new Intent(MainCapturaLicencia.this, MainSnvDocuemtos.class);
                main_snv_documentos.putExtra("VigenciaLicencia",VigLicencia);
            }
        }

    }
}