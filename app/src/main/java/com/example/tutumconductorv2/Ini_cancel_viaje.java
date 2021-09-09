package com.example.tutumconductorv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ini_cancel_viaje extends AppCompatActivity {

    private EditText tiempo, distancia, precio;
    String cad1= "256",cad2="45.50",cad3="20:50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_ini_cancel_viaje);
        precio=findViewById(R.id.PRECIO);
        tiempo=findViewById(R.id.TIEMPO);
        distancia=findViewById(R.id.DISTANCIA);
            distancia.setText(cad1+"Km");
            precio.setText("$"+cad2);
            tiempo.setText(cad3+"Min");

    }

    public void btnCancelar (View view){
        Intent btncancelar = new Intent(this, Ini_cancel_viaje.class);
        Regresar();
    }
    public void btnInicio (View view){
        Intent btninicio = new Intent(this, Ini_cancel_viaje.class);
        Iniciar();
    }

    public void Regresar(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(Ini_cancel_viaje.this);
        alerta.setMessage("Desea cancelar el viaje?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish(); //funcion en la que se debe regresar a la pantalla del viaje
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel(); //se mantiene en la misma pantalla para poder iniciar el viaje
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Confirmacion");
        titulo.show();
    }
    public void Iniciar(){
        Toast.makeText(this, "Viaje iniciado",Toast.LENGTH_SHORT).show(); //mensaje para indicar que el viaje inicio
        finish();
    }
}