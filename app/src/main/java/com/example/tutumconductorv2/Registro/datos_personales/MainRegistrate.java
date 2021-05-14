package com.example.tutumconductorv2.Registro.datos_personales;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.conexionSQLiteHelper;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.utilidades;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

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
        String Nombres = nombres.getEditText().getText().toString().trim();
        String ApeidoP = apeidop.getEditText().getText().toString().trim();
        String ApeidoM = apeidom.getEditText().getText().toString().trim();
        String Email = email.getEditText().getText().toString().trim();
        String Password = pass.getEditText().getText().toString().trim();

        if(!check_field(Nombres, nombres)  | !check_field(ApeidoP, apeidop) | !check_field_email(Email, apeidom) | ! check_field_pass(Password,pass))
        {
            return;
        }else
        {
            Intent main_registro_telefono = new Intent(MainRegistrate.this, MainRegistroTelefono.class);
            conexionSQLiteHelper conexion = new conexionSQLiteHelper(this,"datos_usuario",null,1); //datos_usuario es el nombre de la base de datos
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(utilidades.CAMPO_NOMBRE,Nombres);
            values.put(utilidades.CAMPO_APEIDO_PATERNO,ApeidoP);
            values.put(utilidades.CAMPO_APEIDO_MATERNO,ApeidoM);
            values.put(utilidades.CAMPO_EMAIL,Email);
            values.put(utilidades.CAMPO_CONTRASEÑA,Password);

            db.insert(utilidades.TABLA_REGISTRO,null,values); // Insertamos los datos en la tabla
            db.close(); // Por norma general se debe cerrar la base de datos
            Toast.makeText(this,"Datos Guardados",Toast.LENGTH_SHORT);

            startActivity(main_registro_telefono);
            finish();
        }
    }

}