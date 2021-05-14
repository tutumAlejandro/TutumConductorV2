package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.menus_rol.MainSnvDocuemtos;
import com.google.android.material.textfield.TextInputLayout;

public class MainCapturaCodigo extends AppCompatActivity {

    private TextInputLayout codigo_vehiculo;
    private ImageView btn_regreso_codigo;

    private boolean terminos_codigo, ine_codigo, licencia_codigo, caracteristicas_codigo, tarjeta_codigo, poliza_codigo, tarjeton_codigo, codigo_codigo;
    private String rol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_codigo);

        rol = getIntent().getStringExtra("rol");
        terminos_codigo = getIntent().getBooleanExtra("terminos_snv", false);
        ine_codigo = getIntent().getBooleanExtra("ine_snv", false);
        licencia_codigo = getIntent().getBooleanExtra("licencia_snv",false);
        caracteristicas_codigo = false;
        tarjeta_codigo = false;
        poliza_codigo = false;
        tarjeton_codigo = getIntent().getBooleanExtra("tarjeton_snv",false);
        codigo_codigo = false;

        btn_regreso_codigo = findViewById(R.id.img_retroceso_codigo);
        codigo_vehiculo = findViewById(R.id.InputCodigo);

        btn_regreso_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_snv_documentos = new Intent(MainCapturaCodigo.this, MainSnvDocuemtos.class);
                main_snv_documentos.putExtra("terminos_snv",terminos_codigo);
                main_snv_documentos.putExtra("ine_snv",ine_codigo);
                main_snv_documentos.putExtra("licencia_snv",licencia_codigo);
                main_snv_documentos.putExtra("caracteristicas_snv",caracteristicas_codigo);
                main_snv_documentos.putExtra("tarjeta_snv",tarjeta_codigo);
                main_snv_documentos.putExtra("poliza_snv",poliza_codigo);
                main_snv_documentos.putExtra("tarjeton_snv",tarjeton_codigo);
                main_snv_documentos.putExtra("codigo_snv",false);
                startActivity(main_snv_documentos);
                finish();
            }
        });
    }

    private boolean check_codigo(String code)
    {
        if(code.isEmpty())
        {
            codigo_vehiculo.setErrorEnabled(true);
            codigo_vehiculo.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            codigo_vehiculo.setError("Campo Requerido");
            return false;
        }else if(code.length() < 5)
        {
            codigo_vehiculo.setErrorEnabled(true);
            codigo_vehiculo.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            codigo_vehiculo.setError("Formato Invalido");
            return false;
        }
       else{
            codigo_vehiculo.setErrorEnabled(false);
            return true;
        }
    }

    public void btn_guardar_codigo(View v)
    {
        String code2 = codigo_vehiculo.getEditText().getText().toString().trim();
        if(!check_codigo(code2)){
            return;
        }else{
            Intent main_snv_documentos = new Intent(MainCapturaCodigo.this,MainSnvDocuemtos.class);
            main_snv_documentos.putExtra("codigo",code2);
            main_snv_documentos.putExtra("terminos_snv",terminos_codigo);
            main_snv_documentos.putExtra("ine_snv",ine_codigo);
            main_snv_documentos.putExtra("caracteristicas_snv",caracteristicas_codigo);
            main_snv_documentos.putExtra("tarjeta_snv",tarjeta_codigo);
            main_snv_documentos.putExtra("poliza_snv",poliza_codigo);
            main_snv_documentos.putExtra("tarjeton_snv",tarjeton_codigo);
            main_snv_documentos.putExtra("codigo_snv",true);
            startActivity(main_snv_documentos);
            finish();
        }
    }
}