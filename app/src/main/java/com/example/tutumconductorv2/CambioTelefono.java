package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class CambioTelefono extends AppCompatActivity {

    private Button backbtn;
    private Button cancelbtn;
    private Button aceptbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_telefono);

        backbtn = findViewById(R.id.cambioTel_backbutton);
        cancelbtn = findViewById(R.id.cambioTel_cancelBtn);
        aceptbtn = findViewById(R.id.cambioTel_aceptarBtn);

        //Regresar a perfil
        /*backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(CambioTelefono.this, PerfilFragment.class));
                Intent intent = new Intent(CambioTelefono.this.getBaseContext(),
                        PerfilViewModel.class);
                startActivity(intent);
            }
        });*/

        //Cancelar cambios de telefono
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CambioTelefono.this, "Cancelado",Toast.LENGTH_SHORT).show();
            }
        });

        //Guardar cambios de telefono
        aceptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CambioTelefono.this, "Cambios guardados",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void btnRegresarPerfil(View V){
        Intent intentIni = new Intent(CambioTelefono.this, activity_perfil.class);
        startActivity(intentIni);
    }
}