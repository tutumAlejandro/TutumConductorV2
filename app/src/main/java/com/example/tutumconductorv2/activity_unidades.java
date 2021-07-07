package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaCaracteristicas;

public class activity_unidades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidades);
    }


    public void addUnidad(View view)
    {
        Intent nuevaUnidad = new Intent(activity_unidades.this, MainCapturaCaracteristicas.class);
        startActivity(nuevaUnidad);
        //finish();
    }

    public void terminarSubir(View view)
    {
        Intent terminardoctos = new Intent(activity_unidades.this, MainCapturaCaracteristicas .class);
        startActivity(terminardoctos);
        //finish();
    }

    public void btnRegresar4(View V){
        Intent intentIni = new Intent(activity_unidades.this, Inicio.class);
        startActivity(intentIni);
    }

    public void btnNuevaUnidad(View V){
        Intent intentIni = new Intent(activity_unidades.this, caracteristicas_nueva_unidad.class);
        startActivity(intentIni);
    }

    public void btnTerminaSubirDoctos(View V){
        Intent intentIni = new Intent(activity_unidades.this, activity_termina_subir_documento.class);
        startActivity(intentIni);
    }

    public void btnDetallesUnidad(View V){
        Intent intentIni = new Intent(activity_unidades.this, activity_detalle_unidad.class);
        startActivity(intentIni);
    }


    @Override
    public void onBackPressed(){

    }
}