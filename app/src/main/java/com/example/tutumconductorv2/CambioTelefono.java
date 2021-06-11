package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;


public class CambioTelefono extends AppCompatActivity {


    private TextInputLayout phone;
    private String telefono;

    private Button backbtn;
    private Button cancelbtn;
    private Button aceptbtn;
    private boolean isSucess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_telefono);

        backbtn = findViewById(R.id.cambioTel_backbutton);
        cancelbtn = findViewById(R.id.cambioTel_cancelBtn);
        aceptbtn = findViewById(R.id.cambioTel_aceptarBtn);
        phone = findViewById(R.id.acTelefono);
        /*  telefono = phone.getEditText().getText().toString().trim();*/

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
                Intent intentIni = new Intent(CambioTelefono.this, activity_perfil.class);
                startActivity(intentIni);            }
        });

        //Guardar cambios de telefono
        /*aceptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CambioTelefono.this, "Cambios guardados",Toast.LENGTH_SHORT).show();
            }
        });*/

        aceptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone_number = phone.getEditText().getText().toString().trim();

                if(number_phone(phone_number)) {
                    Toast.makeText(CambioTelefono.this, "El campo del numero esta vacio", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CambioTelefono.this, "Cambios guardados", Toast.LENGTH_SHORT).show();

                    ProfileEdit profileEdit = new ProfileEdit();
                    profileEdit.cambioDatos(getApplicationContext());

                    onBackPressed();
                }
            }
        });

    }

    public void btnRegresarPerfil(View V){
        Intent intentIni = new Intent(CambioTelefono.this, activity_perfil.class);
        startActivity(intentIni);
    }


    private boolean number_phone (String Number){
        if(Number.isEmpty()){
            phone.setErrorEnabled(true);
            phone.setError("Campo obligatorio");
            return true;
        }
        else{

            phone.setErrorEnabled(false);
            return false;
        }
    }





}