package com.example.tutumconductorv2.Registro.datos_personales;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tutumconductorv2.MainVentanaPrincipal;
import com.example.tutumconductorv2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent main_login = new Intent(MainActivity.this, MainVentanaPrincipal.class);
                startActivity(main_login);
                finish();
            }
        },2000);
    }
}