package com.example.tutumconductorv2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainRegistroTelefono extends AppCompatActivity {

    private TextInputLayout telefono;
    // variables para almacenar los datos del registro
    private String Nombres;
    private String ApeidoP;
    private String ApeidoM;
    private String Email;
    private String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_telefono);

        telefono = findViewById(R.id.InputTelefono);

        // Metodos para obtener los valores del registro
        Nombres = getIntent().getStringExtra("Nombres");
        ApeidoP = getIntent().getStringExtra("ApeidoP");
        ApeidoM = getIntent().getStringExtra("ApeidoM");
        Email = getIntent().getStringExtra("Email");
        Password = getIntent().getStringExtra("Password");
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
        String num = telefono.getEditText().getText().toString().trim();
        if(!check_telefono(num))
        {
            return;
        }
        else{
            Intent main_otp = new Intent(MainRegistroTelefono.this, MainOTP.class);
            // Mandar los datos a la siguiente ventana
            main_otp.putExtra("Nombres",Nombres);
            main_otp.putExtra("ApeidoP",ApeidoP);
            main_otp.putExtra("ApeidoM",ApeidoM);
            main_otp.putExtra("Email",Email);
            main_otp.putExtra("Password",Password);
            main_otp.putExtra("Telefono",num);
            /* Poner metodo para subir el Json a la base de datos para ver si el telefono o el correo electronico ya estan registrados */

            // Si todo sale bien iniciar la activity del otp
            startActivity(main_otp);
        }
    }
}