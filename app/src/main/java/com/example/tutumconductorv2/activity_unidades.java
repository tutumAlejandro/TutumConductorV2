package com.example.tutumconductorv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.tutumconductorv2.RegistroConductor.CapturaDocumentos.MainCapturaCaracteristicas;
import com.example.tutumconductorv2.RegistroConductor.CapturaDocumentosNuevoVehiculo.MainNuevoVehiculoSocio;

public class activity_unidades extends AppCompatActivity {

    private ImageView btn_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidades);
        btn_agregar = findViewById(R.id.btnplus);

        Switch switch10 = findViewById(R.id.switch10);

        switch10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
            }
        });
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
        String type = preferences.getString("type","");

        if(type.matches("1")){
            btn_agregar.setVisibility(View.VISIBLE);
        }else{
            btn_agregar.setVisibility(View.GONE);
        }
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
        Intent intentIni = new Intent(activity_unidades.this, MainNuevoVehiculoSocio.class);
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