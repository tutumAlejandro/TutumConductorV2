package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_ayuda extends AppCompatActivity {

    private ImageView btnReportarProblema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        btnReportarProblema = findViewById(R.id.btnReportarProblemaTecnico);



//        btnReportarProblema.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent problema = new Intent(activity_ayuda.this, ReportarProblema.class);
//                startActivity(problema);
//
//            }
//        });



    }
}