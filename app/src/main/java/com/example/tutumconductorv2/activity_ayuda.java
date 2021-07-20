package com.example.tutumconductorv2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutumconductorv2.Actividad_Principal.Inicio_Sesion.Reportes.ReportarProblema;


public class activity_ayuda extends AppCompatActivity {

    private ImageView btnRegresar;

    private ImageView btnReportarProblema;
    private ImageView TyC;
    private ImageView privacidad;
    private String url_terminos = "https://tutumapps.com/api/driver/terms";
    private String url_privacidad = "https://tutumapps.com/api/driver/notice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        privacidad = findViewById(R.id.btnPoliticaPrivacidad);
        TyC = findViewById(R.id.btnTerminoCondicion);
        btnReportarProblema = findViewById(R.id.btnReportarProblemaTecnico);
        btnRegresar = findViewById(R.id.img_retroceso_documentos_snv);


        TyC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWebTerm = new Intent(Intent.ACTION_VIEW, Uri.parse(url_terminos));
                finish();
                startActivity(intentWebTerm);

            }
        });


        privacidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWebTerm = new Intent(Intent.ACTION_VIEW, Uri.parse(url_privacidad));
                finish();
                startActivity(intentWebTerm);

            }
        });

    }

    public void btnRegresar(View V){
        Intent intentIni = new Intent(activity_ayuda.this, Inicio.class);
        finish();
        startActivity(intentIni);

    }

    public void btnReportarProblema(View V){
        Intent intentIni = new Intent(activity_ayuda.this, ReportarProblema.class);
        finish();
        startActivity(intentIni);

    }

    public void btnReporteTecnico(View V){
        Intent intentIni = new Intent(activity_ayuda.this, ReportarProblemaTecnico.class);
        finish();
        startActivity(intentIni);

    }

    public void btnUsarApp(View V){
        Intent intentIni = new Intent(activity_ayuda.this, ComoUsarApp.class);
        finish();
        startActivity(intentIni);

    }


    @Override
    public void onBackPressed(){

    }
}