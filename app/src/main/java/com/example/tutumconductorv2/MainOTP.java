package com.example.tutumconductorv2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainOTP extends AppCompatActivity {
    private TextInputLayout otp;
    private TextView Editar;
    private TextView Reenviar;
    private TextView num_tel;
    // variables para almacenar los datos del registro
    private String Nombres;
    private String ApeidoP;
    private String ApeidoM;
    private String Email;
    private String Password;
    private String Telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_o_t_p);

        otp = findViewById(R.id.InputOTP);
        Editar = findViewById(R.id.link_editar_num);
        Reenviar = findViewById(R.id.link_editar_num);
        num_tel = findViewById(R.id.num_telefono);

        // Metodos para obtener los valores del registro
        Nombres = getIntent().getStringExtra("Nombres");
        ApeidoP = getIntent().getStringExtra("ApeidoP");
        ApeidoM = getIntent().getStringExtra("ApeidoM");
        Email = getIntent().getStringExtra("Email");
        Password = getIntent().getStringExtra("Password");
        Telefono = getIntent().getStringExtra("Telefono");

        num_tel.setText(Telefono);

        Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_registro_telefono = new Intent(MainOTP.this, MainRegistroTelefono.class);
                main_registro_telefono.putExtra("Nombres",Nombres);
                main_registro_telefono.putExtra("ApeidoP",ApeidoP);
                main_registro_telefono.putExtra("ApeidoM",ApeidoM);
                main_registro_telefono.putExtra("Email",Email);
                main_registro_telefono.putExtra("Password",Password);
                startActivity(main_registro_telefono);
                finish();
            }
        });
    }
    private boolean check_otp(String otp_code)
    {
        if (otp_code.isEmpty())
        {
            otp.setErrorEnabled(true);
            otp.setError("Campo Requerido");
            otp.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        }else
        {
            otp.setErrorEnabled(false);
            return true;
        }
    }
    public void reenviar_otp(View v)
    {

    }
    public void editar(View v)
    {
        Intent main_registro_telefono = new Intent(MainOTP.this, MainRegistroTelefono.class);
        main_registro_telefono.putExtra("Nombres",Nombres);
        main_registro_telefono.putExtra("ApeidoP",ApeidoP);
        main_registro_telefono.putExtra("ApeidoM",ApeidoM);
        main_registro_telefono.putExtra("Email",Email);
        main_registro_telefono.putExtra("Password",Password);
        startActivity(main_registro_telefono);
        finish();
    }
    public void main_rol(View v)
    {
        String otp_cd = otp.getEditText().getText().toString().trim();
        if(!check_otp(otp_cd))
        {
            return;
        }else
        {
            Intent main_rol = new Intent(MainOTP.this, MainRolConductor.class);
            main_rol.putExtra("Nombres",Nombres);
            main_rol.putExtra("ApeidoP",ApeidoP);
            main_rol.putExtra("ApeidoM",ApeidoM);
            main_rol.putExtra("Email",Email);
            main_rol.putExtra("Password",Password);
            main_rol.putExtra("Telefono",Telefono);
            startActivity(main_rol);
        }
    }
}