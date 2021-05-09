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
    private boolean terminos,ine,licencia,caracteristicas,tarjeta,poliza,tarjeton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_terminos_y_condiciones);
        rol = getIntent().getStringExtra("rol");
        terminos= getIntent().getBooleanExtra("terminos",false);
        ine = getIntent().getBooleanExtra("ine",false);
        licencia = getIntent().getBooleanExtra("licencia",false);
        caracteristicas = getIntent().getBooleanExtra("caracteristicas",false);
        tarjeta = getIntent().getBooleanExtra("tarjeta",false);
        poliza = getIntent().getBooleanExtra("poliza",false);
        tarjeton = getIntent().getBooleanExtra("tarjeton",false);


        btn_regreso_term = findViewById(R.id.img_retroceso_terminos_condiciones);
        _politica = findViewById(R.id.link_politica);
        _terminos = findViewById(R.id.txt_terminos_y_condiciones);

        btn_regreso_term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rol.matches("Socio"))
                {
                    Intent main_docto_socio = new Intent(MainTerminosYCondiciones.this,MainSocioDocumentos.class);
                    main_docto_socio.putExtra("terminos",false);
                    main_docto_socio.putExtra("ine",ine);
                    main_docto_socio.putExtra("licencia",licencia);
                    main_docto_socio.putExtra("caracteristicas",caracteristicas);
                    main_docto_socio.putExtra("tarjeta",tarjeta);
                    main_docto_socio.putExtra("poliza",poliza);
                    main_docto_socio.putExtra("tarjeton",tarjeton);
                    startActivity(main_docto_socio);
                    finish();
                }else if(rol.matches("Conductor"))
                {
                    Intent main_docto_conductor = new Intent(MainTerminosYCondiciones.this, MainConductorDocumentos.class);
                    main_docto_conductor.putExtra("terminos_conductor","terminos");
                    startActivity(main_docto_conductor);
                    finish();
                }else{
                    Intent main_docto_snv = new Intent(MainTerminosYCondiciones.this, MainSnvDocuemtos.class);
                    main_docto_snv.putExtra("terminos_snv","terminos");
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
        if(rol.matches("Socio"))
        {
            Intent main_docto_socio = new Intent(MainTerminosYCondiciones.this,MainSocioDocumentos.class);
            main_docto_socio.putExtra("terminos",true);
            main_docto_socio.putExtra("ine",ine);
            main_docto_socio.putExtra("licencia",licencia);
            main_docto_socio.putExtra("caracteristicas",caracteristicas);
            main_docto_socio.putExtra("tarjeta",tarjeta);
            main_docto_socio.putExtra("poliza",poliza);
            main_docto_socio.putExtra("tarjeton",tarjeton);
            startActivity(main_docto_socio);
            finish();
        }else if(rol.matches("Conductor"))
        {
            Intent main_docto_conductor = new Intent(MainTerminosYCondiciones.this, MainConductorDocumentos.class);
            main_docto_conductor.putExtra("terminos",true);
            startActivity(main_docto_conductor);
            finish();
        }else{
            Intent main_docto_snv = new Intent(MainTerminosYCondiciones.this, MainSnvDocuemtos.class);
            main_docto_snv.putExtra("terminos_snv",true);
            startActivity(main_docto_snv);
            finish();
        }
    }
}