package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.tutumconductorv2.R;

public class MainTextoPoliticaPrivacidad extends AppCompatActivity {

    private WebView web_politivas;
    private ImageView btn_regreso_terminos;
    private String url="https://tutumapps.com/api/driver/notice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_texto_politica_privacidad);

        web_politivas = findViewById(R.id.WB_politicas);
        btn_regreso_terminos = findViewById(R.id.img_retroceso_politicas_text);

        web_politivas.setWebViewClient(new WebViewClient());
        web_politivas.loadUrl(url);


        btn_regreso_terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos_condiciones = new Intent(MainTextoPoliticaPrivacidad.this,MainTerminosYCondiciones.class);
                startActivity(main_terminos_condiciones);
                finish();
            }
        });
    }
}