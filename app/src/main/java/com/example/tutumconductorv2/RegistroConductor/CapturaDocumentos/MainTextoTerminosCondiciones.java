package com.example.tutumconductorv2.RegistroConductor.CapturaDocumentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.tutumconductorv2.R;

public class MainTextoTerminosCondiciones extends AppCompatActivity {

    private WebView web_terminos;
    private ImageView btn_regreso_terminos;
    private String url="https://tutumapps.com/api/driver/terms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_texto_terminos_condiciones);

        web_terminos = findViewById(R.id.WB_terminos);
        btn_regreso_terminos = findViewById(R.id.img_retroceso_terminos_condiciones_text);


        web_terminos.setWebViewClient(new WebViewClient());
        web_terminos.loadUrl(url);


        btn_regreso_terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_terminos_condiciones = new Intent(MainTextoTerminosCondiciones.this, MainTerminosYCondiciones.class);
                startActivity(main_terminos_condiciones);
                finish();
            }
        });
    }
}