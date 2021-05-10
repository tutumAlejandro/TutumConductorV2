package com.example.tutumconductorv2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainRolConductor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rol_conductor);
    }
    public void main_doctos_socio(View v)
    {
        Intent main_doc_socio = new Intent(MainRolConductor.this,MainSocioDocumentos.class);

        main_doc_socio.putExtra("terminos",false);
        main_doc_socio.putExtra("ine",false);
        main_doc_socio.putExtra("licencia",false);
        main_doc_socio.putExtra("caracteristicas",false);
        main_doc_socio.putExtra("tarjeta",false);
        main_doc_socio.putExtra("poliza",false);
        main_doc_socio.putExtra("tarjeton",false);
        startActivity(main_doc_socio);
        finish();
    }
    public void main_doctos_conductor(View v)
    {
        Intent main_doc_conductor = new Intent(MainRolConductor.this, MainConductorDocumentos.class);
        main_doc_conductor.putExtra("terminos_conductor",false);
        main_doc_conductor.putExtra("ine_conductor",false);
        main_doc_conductor.putExtra("licencia_conductor",false);
        main_doc_conductor.putExtra("caracteristicas_conductor",false);
        main_doc_conductor.putExtra("tarjeta_conductor",false);
        main_doc_conductor.putExtra("poliza_conductor",false);
        main_doc_conductor.putExtra("tarjeton_conductor",false);
        startActivity(main_doc_conductor);
    }
    public void main_doctos_snv(View v)
    {
        Intent main_doc_snv = new Intent(MainRolConductor.this, MainSnvDocuemtos.class);
        main_doc_snv.putExtra("terminos_snv",false);
        main_doc_snv.putExtra("ine_snv",false);
        main_doc_snv.putExtra("licencia_snv",false);
        main_doc_snv.putExtra("codigo_snv",false);
        main_doc_snv.putExtra("tarjeton_snv",false);
        startActivity(main_doc_snv);

    }
}