package com.example.tutumconductorv2.Registro.datos_personales;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainRegistroTelefono extends AppCompatActivity {

    private TextInputLayout telefono;
    private TextView test_nom,test_app,test_apm,test_email, test_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_telefono);
        telefono = findViewById(R.id.InputTelefono);
        /**/
        test_nom = findViewById(R.id.test_tel_nombre);
        test_app= findViewById(R.id.test_tel_app);
        test_apm = findViewById(R.id.test_tel_apm);
        test_email = findViewById(R.id.test_tel_email);
        test_pass = findViewById(R.id.test_tel_pass);
        /**/
        test_nom.setText(cadenas_registro.nombres);
        test_app.setText(cadenas_registro.apeido_paterno);
        test_apm.setText(cadenas_registro.apeido_materno);
        test_email.setText(cadenas_registro.email);
        test_pass.setText(cadenas_registro.password);

    }
    private boolean check_telefono(String num_telefono)
    {
        if(num_telefono.isEmpty())
        {
            telefono.setErrorEnabled(true);
            telefono.setError("Campo Requerido");
            telefono.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        }else if(num_telefono.length() < 10)
        {
            telefono.setErrorEnabled(true);
            telefono.setError("Formato Invalido");
            telefono.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        }else
        {
            telefono.setErrorEnabled(false);
            return true;
        }
    }
    public void main_otp(View v)
    {
        if(!check_telefono(telefono.getEditText().getText().toString().trim()))
        {
            return;
        }
        else{
            Intent main_otp = new Intent(MainRegistroTelefono.this, MainOTP.class);
            cadenas_registro.telefono= telefono.getEditText().getText().toString().trim();
            startActivity(main_otp);
            finish();
        }
    }
}