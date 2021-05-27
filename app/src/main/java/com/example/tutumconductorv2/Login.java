package com.example.tutumconductorv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputLayout;


public class Login extends AppCompatActivity {
    private TextInputLayout email;
    private TextInputLayout pass1;
    private TextView recupera;
    private String email_usuario;
    private String pass_usuario;
    String _url_recupera = "https://tutumapps.com/password/reset";

    private EditText user, pass;
    private Button mSubmit, mRegister, button;

    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = findViewById(R.id.Input_Email);
        pass = findViewById(R.id.Input_Contraseña);
        recupera = findViewById(R.id.recupera_contraseña);
        String email_usuario = "usuario@gmail.com";
        String pass_usuario = "123456789";


        recupera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Uri _link = Uri.parse(_url_recupera);
                Intent pag_recupera = new Intent(Intent.ACTION_VIEW,_link);
                startActivity(pag_recupera);
            }
        });
    }
    
}
