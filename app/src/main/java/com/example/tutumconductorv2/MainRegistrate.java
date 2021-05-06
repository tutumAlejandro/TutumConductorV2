package com.example.tutumconductorv2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import androidx.annotation.ColorInt;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

public class MainRegistrate extends AppCompatActivity {

    private TextInputLayout nombres;
    private TextInputLayout apeidop;
    private TextInputLayout apeidom;
    private TextInputLayout email;
    private TextInputLayout pass;

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

    private boolean check_nombre(String nom)
    {
        if(nom.isEmpty())
        {
            nombres.setErrorEnabled(true);
            nombres.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            nombres.setError("Campo Requerido");
            return false;
        }
        else {
            nombres.setErrorEnabled(false);
            return true;
        }
    }

    private boolean check_apeidop(String app)
    {
        if(app.isEmpty())
        {
            apeidop.setErrorEnabled(true);
            apeidop.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            apeidop.setError("Campo Requerido");
            return false;
        }
        else
        {
            apeidop.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_apeidom(String apm)
    {
        if(apm.isEmpty())
        {
            apeidom.setErrorEnabled(true);
            apeidom.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            apeidom.setError("Campo Requerido");
            return false;
        }
        else
        {
            apeidom.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_email(String correo)
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
    private boolean check_password(String contraseña)
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
        String Nombres = nombres.getEditText().getText().toString().trim();
        String ApeidoP = apeidop.getEditText().getText().toString().trim();
        String ApeidoM = apeidom.getEditText().getText().toString().trim();
        String Email = email.getEditText().getText().toString().trim();
        String Password = pass.getEditText().getText().toString().trim();

        if(!check_nombre(Nombres) | !check_apeidom(ApeidoM) | !check_apeidop(ApeidoP) | !check_email(Email) | ! check_password(Password))
        {
            return;
        }else
        {
            Intent main_registro_telefono = new Intent(MainRegistrate.this, MainRegistroTelefono.class);
            main_registro_telefono.putExtra("Nombres",Nombres);
            main_registro_telefono.putExtra("ApeidoP",ApeidoP);
            main_registro_telefono.putExtra("ApeidoM",ApeidoM);
            main_registro_telefono.putExtra("Email",Email);
            main_registro_telefono.putExtra("Password",Password);
            startActivity(main_registro_telefono);
        }
    }

}