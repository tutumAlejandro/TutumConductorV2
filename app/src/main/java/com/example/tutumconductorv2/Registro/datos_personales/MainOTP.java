package com.example.tutumconductorv2.Registro.datos_personales;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.example.tutumconductorv2.Registro.menus_rol.MainRolConductor;
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
    private TextView test_nom,test_app, test_apm, test_email, test_pass, test_tel;
    // variables para almacenar los datos del registro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_o_t_p);

        otp = findViewById(R.id.InputOTP);
        Editar = findViewById(R.id.link_editar_num);
        Reenviar = findViewById(R.id.link_editar_num);
        num_tel = findViewById(R.id.num_telefono);

        // metodos de prueba
        test_nom = findViewById(R.id.test_otp_nombre);
        test_app = findViewById(R.id.test_otp_apeidop);
        test_apm = findViewById(R.id.test_otp_apeidom);
        test_email = findViewById(R.id.test_otp_correo);
        test_pass = findViewById(R.id.test_otp_contrasena);
        test_tel = findViewById(R.id.test_otp_telefono);

        test_nom.setText(cadenas_registro.nombres);
        test_app.setText(cadenas_registro.apeido_paterno);
        test_apm.setText(cadenas_registro.apeido_materno);
        test_email.setText(cadenas_registro.email);
        test_pass.setText(cadenas_registro.password);
        test_tel.setText(cadenas_registro.telefono);
        num_tel.setText(cadenas_registro.telefono);

        Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_registro_telefono = new Intent(MainOTP.this, MainRegistroTelefono.class);
                cadenas_registro.telefono="";
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
            startActivity(main_rol);
            finish();
        }
    }
}