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
        startActivity(main_doc_socio);
    }
    public void main_doctos_conductor(View v)
    {
        Intent main_doc_conductor = new Intent(MainRolConductor.this, MainConductorDocumentos.class);
        startActivity(main_doc_conductor);
    }
    public void main_doctos_snv(View v)
    {
        Intent main_doc_snv = new Intent(MainRolConductor.this, MainSnvDocuemtos.class);
        startActivity(main_doc_snv);

    }
}