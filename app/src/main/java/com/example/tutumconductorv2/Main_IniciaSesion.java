package com.example.tutumconductorv2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Main_IniciaSesion extends AppCompatActivity {
    private TextInputLayout email;
    private TextInputLayout pass;
    private TextView recupera;
    private String email_usuario;
    private String pass_usuario;
    String _url_recupera = "https://tutumapps.com/password/reset";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicia_sesion);

        email = findViewById(R.id.Input_Email);
        pass = findViewById(R.id.Input_Contraseña);
        recupera = findViewById(R.id.recupera_contraseña);
        String email_usuario = "usuario@gmail.com";
        String pass_usuario = "123456789";


        recupera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Uri _link = Uri.parse(_url_recupera);
                Intent pag_recupera = new Intent(Intent.ACTION_VIEW,_link);
                startActivity(pag_recupera);
            }
        });
    }

    private boolean check_email(String correo)
    {
        if(correo.isEmpty())
        {
            email.setErrorEnabled(true);
            email.setError("Campo Requerido");
            return false;
        }else if(!correo.contains("@"))
        {
            email.setErrorEnabled(true);
            email.setError("Formato Invalido");
            return false;
        }
        else{
            email.setErrorEnabled(false);
            return true;
        }

    }

    private boolean check_password(String password)
    {
        if(password.isEmpty())
        {
            pass.setErrorEnabled(true);
            pass.setError("Campo Requerido");
            return false;
        }else if(password.length() < 8)
        {
            pass.setErrorEnabled(true);
            pass.setError("La constraseña debe ser mayor a 8 caracteres");
            return false;
        }
        else{
            pass.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_user(String usuario)
    {
        return true;
    }
    private boolean check_pass(String pass)
    {
        return true;
    }

    public void menu_principal(View v)
    {
        String contraseña = pass.getEditText().getText().toString().trim();
        String correo = email.getEditText().getText().toString().trim();

        if(!check_password(contraseña) | !check_email(correo))
        {
            return ;
        }
        else {
            Intent pag_inicial = new Intent(Main_IniciaSesion.this, Inicio.class);
            startActivity(pag_inicial);
            finish();
        }

    }

}