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
import android.view.View;

public class MainRegistroTelefono extends AppCompatActivity {

    private TextInputLayout telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_telefono);
        telefono = findViewById(R.id.InputTelefono);

        // Metodos para obtener los valores del registro
        
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

            conexionSQLiteHelper conexion = new conexionSQLiteHelper(this,"datos_usuario",null,1);
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(utilidades.CAMPO_TELEFONO,num);
            db.insert(utilidades.TABLA_REGISTRO,null,values);
            db.close();

            /* Poner metodo para subir el Json a la base de datos para ver si el telefono o el correo electronico ya estan registrados */

            // Si todoo sale bien iniciar la activity del otp
            startActivity(main_otp);
            finish();
        }
    }
}