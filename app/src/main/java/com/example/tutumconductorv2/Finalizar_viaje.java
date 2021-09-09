package com.example.tutumconductorv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class Finalizar_viaje extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText tiempo_fin, distancia_fin, precio_fin,comentario;
    String cad1= "145",cad2="60.75",cad3="15:20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar);
        precio_fin=findViewById(R.id.PRECIO1);
        tiempo_fin=findViewById(R.id.TIEMPO1);
        distancia_fin=findViewById(R.id.DISTANCIA1);
        comentario=findViewById(R.id.COMENTARIO);

        distancia_fin.setText(cad1+"Km");
        precio_fin.setText("$"+cad2);
        tiempo_fin.setText(cad3+"Min");

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float stars, boolean b) {
                Toast.makeText(Finalizar_viaje.this,"Haz calificado con " + stars,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void btnpruebaregreso(View view) { //metodo de control del boton REGRESAR
        Intent regreso = new Intent(Finalizar_viaje.this, MainVentanaPrincipal.class);
        Regreso();
    }
    public void btnpruebafinal(View view){ //metodo de control del boton FINALIZAR
        Intent Fin = new Intent(this,MainVentanaPrincipal.class);
        Final();
    }

    public void Regreso() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(Finalizar_viaje.this);
        alerta.setMessage("Desea continuar con el viaje?")
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
                        dialogInterface.cancel(); //se mantiene en la misma pantalla para poder finalizar el viaje
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Confirmacion");
        titulo.show();

    }
    public void Final(){
        Toast.makeText(getApplicationContext(),"Viaje Finalizado",Toast.LENGTH_SHORT).show();
         String comenta = comentario.getText().toString();
        Toast.makeText(getApplicationContext(),comenta+"",Toast.LENGTH_LONG).show();
        finish();
        // pasar a pantalla principal o pantalla de finalizacion de viaje


    }
}