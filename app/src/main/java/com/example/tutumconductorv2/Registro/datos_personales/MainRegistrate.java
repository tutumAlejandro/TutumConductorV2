package com.example.tutumconductorv2.Registro.datos_personales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

public class MainRegistrate extends AppCompatActivity {

    private TextInputLayout nombres,apeidop,apeidom,email,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registrate);
        nombres = findViewById(R.id.InputNombres);
        apeidop = findViewById(R.id.InputApeidoP);
        apeidom = findViewById(R.id.InputApeidoM);
        email = findViewById(R.id.InputCorreo);
        pass = findViewById(R.id.InputContraseña);

    }

    private boolean check_field(String app, TextInputLayout campo)
    {
        if(app.isEmpty())
        {
            campo.setErrorEnabled(true);
            campo.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            campo.setError("Campo Requerido");
            return false;
        }
        else
        {
            campo.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_field_email(String correo, TextInputLayout campo)
    {
        if(correo.isEmpty())
        {
            email.setErrorEnabled(true);
            email.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            email.setError("Campor Requerido");
            return false;
        }else if(!(Patterns.EMAIL_ADDRESS.matcher(correo).matches()))
        {
            email.setErrorEnabled(true);
            email.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            email.setError("Formato Invalido");
            return false;
        }else
        {
            email.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_field_pass(String contraseña,TextInputLayout campo)
    {
        if(contraseña.isEmpty())
        {
            pass.setErrorEnabled(true);
            pass.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            pass.setError("Campo Requerido");
            return false;
        }else if(contraseña.length() < 8)
        {
            pass.setErrorEnabled(true);
            pass.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            pass.setError("Este campo acepta un minimo de 8 caracteres");
            return false;
        }
        else
        {
            pass.setErrorEnabled(false);
            return true;
        }
    }

    public void btn_registro(View v)
    {
        if(!check_field(nombres.getEditText().getText().toString().trim(), nombres)  | !check_field(apeidop.getEditText().getText().toString().trim(), apeidop) |
                !check_field_email(email.getEditText().getText().toString().trim(), email) |
                ! check_field_pass(pass.getEditText().getText().toString().trim(),pass))
        {
            return;
        }else
        {
            SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
            SharedPreferences.Editor obj_editor = preferences.edit();
            obj_editor.putString("name",(nombres.getEditText().getText().toString().trim())+" "+apeidop.getEditText().getText().toString().trim()+" "+ apeidom.getEditText().getText().toString().trim());
            obj_editor.putString("email",email.getEditText().getText().toString().trim());
            obj_editor.putString("password",pass.getEditText().getText().toString().trim());
            obj_editor.putInt("State",1);
            obj_editor.commit();
            Intent main_registro_telefono = new Intent(MainRegistrate.this, MainRegistroTelefono.class);
            startActivity(main_registro_telefono);
            finish();
        }
    }

}