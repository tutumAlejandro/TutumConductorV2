package com.example.tutumconductorv2.Registro.datos_personales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.tutumconductorv2.MainVentanaPrincipal;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainDocumentosOk;
import com.example.tutumconductorv2.Registro.menus_rol.MainRolConductor;
import com.example.tutumconductorv2.Registro.menus_rol.MainSnvDocuemtos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;

public class PopUpContinuarRegistro extends AppCompatActivity {

    private TextView etapa,nombre, email;
    private String msg="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_continuar_registro);
        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho*0.80), (int)(alto*0.8));
        etapa = findViewById(R.id.main_pop_up_etapa);
        email = findViewById(R.id.email_registrado);
        nombre = findViewById(R.id.telefono_registrado);


        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        int estado = preferences.getInt("State",0);

        Log.e("ESTADO","valor del estado:"+estado);
        switch (estado){
            case 1: {msg="Etapa: Registro del Telefono";}break;
            case 2: {msg="Etapa: Validación del OTP";}break;
            case 3: {msg="Etapa: Selección del tipo de usuario";}break;
            case 4: {msg="Etapa: Documentos Socio Administrador";}break;
            case 5: {msg="Etapa: Documentos Conductor";}break;
            case 6: {msg="Etapa: Documentos Conductor Sin Vehiculo";}break;
            case 8: {msg="Etapa: Documentos Entregados";}break;
        }
        etapa.setText(msg);
        String substring1 = preferences.getString("email","").substring(preferences.getString("email","").indexOf("@"));
        String substring2 = preferences.getString("email","").substring(0,2);
        String substring3 = substring2+"*****"+substring1;
        email.setText("Correo: "+substring3);
    }

    public void continuar_registro(View V){
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        switch (preferences.getInt("State",0)){
            case 1: {Intent main_reg_tel = new Intent(PopUpContinuarRegistro.this, MainRegistroTelefono.class);
                startActivity(main_reg_tel); finish();}break;
            case 2: {Intent main_otp = new Intent(PopUpContinuarRegistro.this, MainOTP.class);
                startActivity(main_otp); finish();}break;
            case 3: {Intent main_rol = new Intent(PopUpContinuarRegistro.this, MainRolConductor.class);
                startActivity(main_rol); finish();}break;
            case 4: {Intent main_socio = new Intent(PopUpContinuarRegistro.this, MainSocioDocumentos.class);
                startActivity(main_socio);finish();}break;
            case 5: {Intent main_conductor = new Intent(PopUpContinuarRegistro.this, MainConductorDocumentos.class);
                startActivity(main_conductor);finish();}break;
            case 6: {Intent main_snv = new Intent(PopUpContinuarRegistro.this, MainSnvDocuemtos.class);
                startActivity(main_snv); finish();}break;
            case 7: {Intent main_error = new Intent(PopUpContinuarRegistro.this, MainDocumentosOk.class);
                startActivity(main_error);}break;
            case 8: {Intent main_ok = new Intent(PopUpContinuarRegistro.this, MainDocumentosOk.class);
                startActivity(main_ok);finish();}break;
        }
    }

    public void iniciar_registro(View view)
    {
        HARD_RESET();
        Intent main_registro = new Intent(PopUpContinuarRegistro.this,MainPopUpData.class);
        startActivity(main_registro);
        finish();
    }

    private void HARD_RESET(){

            SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
            SharedPreferences.Editor obj_editor = preferences.edit();
            obj_editor.putString("name","");
            obj_editor.putString("phone","");
            obj_editor.putString("password","");
            obj_editor.putString("terminos1","0");
            obj_editor.putString("ine1","0");
            obj_editor.putString("licencia1","0");
            obj_editor.putString("caracteristicas1","0");
            obj_editor.putString("tarjeta1","0");
            obj_editor.putString("poliza1","0");
            obj_editor.putString("tarjeton1","0");

            obj_editor.putString("terminos2","0");
            obj_editor.putString("ine2","0");
            obj_editor.putString("licencia2","0");
            obj_editor.putString("caracteristicas2","0");
            obj_editor.putString("tarjeta2","0");
            obj_editor.putString("poliza2","0");
            obj_editor.putString("tarjeton2","0");

            obj_editor.putString("terminos3","0");
            obj_editor.putString("ine3","0");
            obj_editor.putString("licencia3","0");
            obj_editor.putString("codigo3","0");
            obj_editor.putString("tarjeton3","0");

            obj_editor.putString("error1","");
            obj_editor.putString("error2","");
            obj_editor.putString("error3","");
            obj_editor.putString("error4","");
            obj_editor.putString("error5","");
            obj_editor.putString("error6","");
            obj_editor.putString("error7","");
            obj_editor.commit();
    }
}