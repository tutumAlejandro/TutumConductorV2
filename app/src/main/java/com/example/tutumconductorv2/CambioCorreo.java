package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;


public class CambioCorreo extends AppCompatActivity {

    private TextInputLayout email;
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

                String phone_number = email.getEditText().getText().toString().trim();

                if(check_email(phone_number)) {
                    Toast.makeText(CambioCorreo.this, "El campo del numero esta vacio", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CambioCorreo.this, "Cambios guardados", Toast.LENGTH_SHORT).show();

                    ProfileEdit profileEdit = new ProfileEdit();
                    profileEdit.cambioDatos(getApplicationContext());

                    onBackPressed();
                }
            }
        });

    }

    public void btnRegresarPerfil1(View V){
        Intent intentIni = new Intent(CambioCorreo.this, activity_perfil.class);
        startActivity(intentIni);
    }

    private boolean check_email(String correo)
    {
        if(correo.isEmpty())
        {
            email.setErrorEnabled(true);
            email.setError("Campo Requerido");
            return false;
        }else if(!correo.contains("@"))
        {
            email.setErrorEnabled(true);
            email.setError("Formato Invalido");
            return false;
        }
        else{
            email.setErrorEnabled(false);
            return true;
        }

    }
}