package com.example.tutumconductorv2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class CambioCorreo extends AppCompatActivity {

    private Button backbtn;
    private Button cancelbtn;
    private Button aceptbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_correo);

        backbtn = findViewById(R.id.cambioCorreo_backbutton);
        cancelbtn = findViewById(R.id.cambioCorreo_cancelBtn);
        aceptbtn = findViewById(R.id.cambioCorreo_aceptarBtn);

        //Regresar a perfil
        /*backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CambioCorreo.this, PerfilFragment.class));
            }
        });*/

        //Cancelar cambios de correo
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CambioCorreo.this, "Cancelado",Toast.LENGTH_SHORT).show();
            }
        });

        //Guardar cambios de correo
        aceptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CambioCorreo.this, "Cambios guardados",Toast.LENGTH_SHORT).show();
            }
        });

    }
}