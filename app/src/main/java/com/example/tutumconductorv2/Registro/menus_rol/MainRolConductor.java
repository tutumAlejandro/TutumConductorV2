package com.example.tutumconductorv2.Registro.menus_rol;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;

public class MainRolConductor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rol_conductor);

    }
    public void main_doctos_socio(View v) {
        Intent main_doc_socio = new Intent(MainRolConductor.this, MainSocioDocumentos.class);
        cadenas_documentos.check_terminos1=false;
        cadenas_documentos.check_ine1=false;
        cadenas_documentos.check_licencia1=false;
        cadenas_documentos.check_caracteristicas1=false;
        cadenas_documentos.check_tarjeta1=false;
        cadenas_documentos.check_poliza1=false;
        cadenas_documentos.check_tarjeton1=false;
        startActivity(main_doc_socio);
        finish();
    }
    public void main_doctos_conductor(View v)
    {
        Intent main_conductor_doctos = new Intent(MainRolConductor.this, MainConductorDocumentos.class);
        cadenas_documentos.check_terminos2=false;
        cadenas_documentos.check_ine2=false;
        cadenas_documentos.check_licencia2=false;
        cadenas_documentos.check_caracteristicas2=false;
        cadenas_documentos.check_tarjeta2=false;
        cadenas_documentos.check_poliza2=false;
        cadenas_documentos.check_tarjeton2=false;
        startActivity(main_conductor_doctos);
        finish();
    }
    public void main_doctos_snv(View v)
    {
        Intent main_doc_snv = new Intent(MainRolConductor.this, MainSnvDocuemtos.class);
        cadenas_documentos.check_terminos3=false;
        cadenas_documentos.check_ine3=false;
        cadenas_documentos.check_licencia3=false;
        cadenas_documentos.check_codigo3=false;
        cadenas_documentos.check_tarjeton3=false;
        startActivity(main_doc_snv);
        finish();

    }
}