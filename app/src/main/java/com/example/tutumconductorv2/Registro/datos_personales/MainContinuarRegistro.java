package com.example.tutumconductorv2.Registro.datos_personales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.menus_rol.MainDocumentosOk;
import com.example.tutumconductorv2.Registro.menus_rol.MainRolConductor;

public class MainContinuarRegistro extends AppCompatActivity {

    private TextView State;
    private int state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_continuar_registro);

        State = findViewById(R.id.ultimo_estado);

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;
        getWindow().setLayout((int)(ancho*1),(int)(alto * 0.80));

        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        state = preferences.getInt("State",0);
        switch (state)
        {
            case 1: {State.setText("(Registro de Teléfono)");}break;
            case 2: { State.setText("(Confirmación el código OTP)");}break;
            case 3: {State.setText("(Selección del rol)"); }break;
            case 4: {State.setText("(Documentos entregados)");}break;
        }


    }

    public void nuevo_registro(View v){
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putInt("State",0);
        obj_editor.commit();
        Intent main_registrate = new Intent(MainContinuarRegistro.this,MainRegistrate.class);
        startActivity(main_registrate);
        finish();
    }

    public void continuar_registro(View v){
        switch (state)
        {
            case 1: {Intent main_reg_tel = new Intent(MainContinuarRegistro.this,MainRegistroTelefono.class);
                     startActivity(main_reg_tel);State.setText("(Registro de Teléfono)"); finish();}break;
            case 2: {Intent main_otp = new Intent(MainContinuarRegistro.this, MainOTP.class);
                     startActivity(main_otp); State.setText("(Confirmación el código OTP)"); finish();}break;
            case 3: {Intent main_rol = new Intent(MainContinuarRegistro.this, MainRolConductor.class);
                     startActivity(main_rol); State.setText("(Selección del rol)"); finish();}break;
            case 4: {Intent main_ok = new Intent(MainContinuarRegistro.this, MainDocumentosOk.class);
                     startActivity(main_ok); State.setText("(Documentos entregados)"); finish();}break;
        }

    }
}