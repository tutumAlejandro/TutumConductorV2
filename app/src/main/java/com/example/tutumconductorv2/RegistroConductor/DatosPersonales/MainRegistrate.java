package com.example.tutumconductorv2.RegistroConductor.DatosPersonales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tutumconductorv2.R;
import com.google.android.material.textfield.TextInputLayout;

public class MainRegistrate extends AppCompatActivity {

    //Declaracion de las variuables globales para el activity MainRegistrate
    private TextInputLayout nombres, apeidop, apeidom, email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registrate);

        //Asociacion del xml con el archivo .java
        nombres = findViewById(R.id.InputNombres);
        apeidop = findViewById(R.id.InputApeidoP);
        apeidom = findViewById(R.id.InputApeidoM);
        email = findViewById(R.id.InputCorreo);
        pass = findViewById(R.id.InputContraseña);
    }

    //Funcion para revisar los campos de Nombre, apeido paterno, aperido materno
    private boolean check_field(String app, TextInputLayout campo) {
        if (app.isEmpty()) {
            campo.setErrorEnabled(true);
            campo.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            campo.setError("Campo Requerido");
            return false;
        } else {
            campo.setErrorEnabled(false);
            return true;
        }
    }

    //Funcion para revisar el campo del correo electronico
    private boolean check_field_email(String correo) {
        if (correo.isEmpty()) {
            email.setErrorEnabled(true);
            email.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            email.setError("Campor Requerido");
            return false;
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(correo).matches())) {
            email.setErrorEnabled(true);
            email.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            email.setError("Formato Invalido");
            return false;
        } else {
            email.setErrorEnabled(false);
            return true;
        }
    }

    //Funcion para revisar el campo de la contraseña
    private boolean check_field_pass(String contraseña) {
        if (contraseña.isEmpty()) {
            pass.setErrorEnabled(true);
            pass.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            pass.setError("Campo Requerido");
            return false;
        } else if (contraseña.length() < 8) {
            pass.setErrorEnabled(true);
            pass.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            pass.setError("Este campo acepta un minimo de 8 caracteres");
            return false;
        } else {
            pass.setErrorEnabled(false);
            return true;
        }
    }

    public void btn_registro(View v) {
        // Revisión de todos los campos, en caso de que uno contenga error
        if (!check_field(nombres.getEditText().getText().toString().trim(), nombres) | !check_field(apeidop.getEditText().getText().toString().trim(), apeidop) | !check_field_email(email.getEditText().getText().toString().trim()) | !check_field_pass(pass.getEditText().getText().toString().trim())) {
            return;
        } else {
            createCustomDialog().show();
        }
    }
    public AlertDialog createCustomDialog(){
        //Creamos un alertDialog y su constructor (builder)
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Obtenemos el layoutInflater
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.confirm_data_layout, null);
        Button btn_aceptar = view.findViewById(R.id.btn_aceptar_datos);
        Button btn_cancelar = view.findViewById(R.id.btn_cancelar_datos);
        TextView nombre_conductor = view.findViewById(R.id.nombre_conductor);
        TextView apeidop_conductor = view.findViewById(R.id.apeidop_conductor);
        TextView apeidom_conductor = view.findViewById(R.id.apeidom_conductor);
        TextView email_conductor = view.findViewById(R.id.email_conductor);
        nombre_conductor.setText(nombres.getEditText().getText().toString().trim());
        apeidop_conductor.setText(apeidop.getEditText().getText().toString().trim());
        apeidom_conductor.setText(apeidom.getEditText().getText().toString().trim());
        email_conductor.setText(email.getEditText().getText().toString().trim());
        builder.setView(view);
        alertDialog = builder.create();
            btn_aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor obj_editor = preferences.edit();
                    obj_editor.putString("name", (nombres.getEditText().getText().toString().trim()) + " " + apeidop.getEditText().getText().toString().trim() + " " + apeidom.getEditText().getText().toString().trim());
                    obj_editor.putString("email", email.getEditText().getText().toString().trim());
                    obj_editor.putString("password", pass.getEditText().getText().toString().trim());
                    obj_editor.putInt("State", 1);
                    obj_editor.commit();
                    finish();
                    Intent main_registro_telefono = new Intent(MainRegistrate.this, MainRegistroTelefono.class);
                    startActivity(main_registro_telefono);
                    alertDialog.dismiss();
                }
            });
            btn_cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    return;
                }
            });
        return alertDialog;
    }

}
