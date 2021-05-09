package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class CambioContrasena extends AppCompatActivity {

    private Button backbtn;
    private Button cancelbtn;
    private Button aceptbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contrasena);

        backbtn = findViewById(R.id.cambioContra_backbutton);
        cancelbtn = findViewById(R.id.cambioContra_cancelBtn);
        aceptbtn = findViewById(R.id.cambioContra_aceptarBtn);

        //Regresar a perfil
        /*backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CambioContrasena.this, PerfilFragment.class));
            }
        });*/

        //Cancelar cambios de contraseña
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CambioContrasena.this, "Cancelado",Toast.LENGTH_SHORT).show();
            }
        });

        //Guardar cambios de contraseña
        aceptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CambioContrasena.this, "Cambios guardados",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void btnRegresarPerfil2(View V){
        Intent intentIni = new Intent(CambioContrasena.this, activity_perfil.class);
        startActivity(intentIni);
    }
}