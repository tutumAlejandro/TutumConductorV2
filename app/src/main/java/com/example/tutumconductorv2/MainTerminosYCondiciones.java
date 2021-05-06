package com.example.tutumconductorv2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainTerminosYCondiciones extends AppCompatActivity {

    private ImageView btn_regreso_term;
    private TextView _politica;
    private TextView _terminos;

    // Cadenas para revisar
    private String rol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_terminos_y_condiciones);
        rol = getIntent().getStringExtra("rol");

        btn_regreso_term = findViewById(R.id.img_retroceso_terminos_condiciones);
        _politica = findViewById(R.id.link_politica);
        _terminos = findViewById(R.id.txt_terminos_y_condiciones);

        btn_regreso_term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rol.matches("socio"))
                {
                    Intent main_docto_socio = new Intent(MainTerminosYCondiciones.this,MainSocioDocumentos.class);
                    startActivity(main_docto_socio);
                    finish();
                }else if(rol.matches("conductor"))
                {
                    Intent main_docto_conductor = new Intent(MainTerminosYCondiciones.this, MainConductorDocumentos.class);
                    startActivity(main_docto_conductor);
                    finish();
                }else{
                    Intent main_docto_snv = new Intent(MainTerminosYCondiciones.this, MainSnvDocuemtos.class);
                    startActivity(main_docto_snv);
                    finish();
                }
            }
        });
        _politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_politica = new Intent(MainTerminosYCondiciones.this, MainTextoPoliticaPrivacidad.class);
                main_politica.putExtra("rol",rol);
                startActivity(main_politica);
                finish();
            }
        });
        _terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos = new Intent(MainTerminosYCondiciones.this, MainTextoPoliticaPrivacidad.class);
                main_terminos.putExtra("rol",rol);
                startActivity(main_terminos);
                finish();
            }
        });

    }
    public void btn_guardar_terminos(View v)
    {
        if(rol.matches("socio"))
        {
            Intent main_docto_socio = new Intent(MainTerminosYCondiciones.this,MainSocioDocumentos.class);
            main_docto_socio.putExtra("check","terminos");
            startActivity(main_docto_socio);
            finish();
        }else if(rol.matches("conductor"))
        {
            Intent main_docto_conductor = new Intent(MainTerminosYCondiciones.this, MainConductorDocumentos.class);
            main_docto_conductor.putExtra("check","terminos");
            startActivity(main_docto_conductor);
            finish();
        }else{
            Intent main_docto_snv = new Intent(MainTerminosYCondiciones.this, MainSnvDocuemtos.class);
            main_docto_snv.putExtra("check","terminos");
            startActivity(main_docto_snv);
            finish();
        }
    }
}