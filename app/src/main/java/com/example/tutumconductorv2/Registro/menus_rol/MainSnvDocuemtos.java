package com.example.tutumconductorv2.Registro.menus_rol;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaCaracteristicas;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaCodigo;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaIne;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaLicencia;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaPoliza;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjetaCirculacion;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjeton;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainTerminosYCondiciones;

public class MainSnvDocuemtos extends AppCompatActivity {

    private ImageView btn_regreso_snv;

    private ImageView fwd_snv1,fwd_snv2,fwd_snv3,fwd_snv4,fwd_snv5;
    private ImageView ok_snv1,ok_snv2,ok_snv3,ok_snv4,ok_snv5;
    private ImageView er_snv1,er_snv2,er_snv3,er_snv4,er_snv5;

    private Button btn1_snv,btn2_snv,btn3_snv,btn4_snv,btn5_snv;
    private Button btn1_ok_snv,btn2_ok_snv,btn3_ok_snv,btn4_ok_snv,btn5_ok_snv;
    private Button btn1_er_snv,btn2_er_snv,btn3_er_snv,btn4_er_snv,btn5_er_snv;

    private TextView hd_snv1,hd_snv2,hd_snv3,hd_snv4,hd_snv5,bd_snv1,bd_snv2,bd_snv3,bd_snv4,bd_snv5;
    private TextView hd_ok_snv1,hd_ok_snv2,hd_ok_snv3,hd_ok_snv4,hd_ok_snv5,
                     bd_ok_snv1,bd_ok_snv2,bd_ok_snv3,bd_ok_snv4,bd_ok_snv5;

    private TextView hd_er_snv1,hd_er_snv2,hd_er_snv3,hd_er_snv4,hd_er_snv5,
                     bd_er_snv1,bd_er_snv2,bd_er_snv3,bd_er_snv4,bd_er_snv5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_snv_docuemtos);
        SharedPreferences prefencias_snv = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);

        btn_regreso_snv = findViewById(R.id.img_retroceso_documentos_snv);

        //Asociacion de los botones
        btn1_snv = findViewById(R.id.button1_snv);
        btn2_snv = findViewById(R.id.button2_snv);
        btn3_snv = findViewById(R.id.button3_snv);
        btn4_snv = findViewById(R.id.button4_snv);
        btn5_snv = findViewById(R.id.button5_snv);
        btn1_ok_snv = findViewById(R.id.button1_snv_ok);
        btn2_ok_snv = findViewById(R.id.button2_snv_ok);
        btn3_ok_snv = findViewById(R.id.button3_snv_ok);
        btn4_ok_snv = findViewById(R.id.button4_snv_ok);
        btn5_ok_snv = findViewById(R.id.button5_snv_ok);
        btn1_er_snv = findViewById(R.id.button1_snv_error);
        btn2_er_snv = findViewById(R.id.button2_snv_error);
        btn3_er_snv = findViewById(R.id.button3_snv_error);
        btn4_er_snv = findViewById(R.id.button4_snv_error);
        btn5_er_snv = findViewById(R.id.button5_snv_error);

        //Asociacion de las imagenes
        fwd_snv1 = findViewById(R.id.fwd_snv_1);
        fwd_snv2 = findViewById(R.id.fwd_snv_2);
        fwd_snv3 = findViewById(R.id.fwd_snv_3);
        fwd_snv4 = findViewById(R.id.fwd_snv_4);
        fwd_snv5 = findViewById(R.id.fwd_snv_5);
        ok_snv1 = findViewById(R.id.fwd_snv_1_ok);
        ok_snv2 = findViewById(R.id.fwd_snv_2_ok);
        ok_snv3 = findViewById(R.id.fwd_snv_3_ok);
        ok_snv4 = findViewById(R.id.fwd_snv_4_ok);
        ok_snv5 = findViewById(R.id.fwd_snv_5_ok);
        er_snv1 = findViewById(R.id.fwd_snv_1_error);
        er_snv2 = findViewById(R.id.fwd_snv_2_error);
        er_snv3 = findViewById(R.id.fwd_snv_3_error);
        er_snv4 = findViewById(R.id.fwd_snv_4_error);
        er_snv5 = findViewById(R.id.fwd_snv_5_error);

        //Asociacion de los textos
        hd_snv1 = findViewById(R.id.txt_btn1_head_snv);
        hd_snv2 = findViewById(R.id.txt_btn2_head_snv);
        hd_snv3 = findViewById(R.id.txt_btn3_head_snv);
        hd_snv4 = findViewById(R.id.txt_btn4_head_snv);
        hd_snv5 = findViewById(R.id.txt_btn5_head_snv);
        bd_snv1 = findViewById(R.id.body_btn1_snv);
        bd_snv2 = findViewById(R.id.body_btn2_snv);
        bd_snv3 = findViewById(R.id.body_btn3_snv);
        bd_snv4 = findViewById(R.id.body_btn4_snv);
        bd_snv5 = findViewById(R.id.body_btn5_snv);

        hd_ok_snv1 = findViewById(R.id.txt_btn1_head_ok_snv);
        hd_ok_snv2 = findViewById(R.id.txt_btn2_head_ok_snv);
        hd_ok_snv3 = findViewById(R.id.txt_btn3_head_ok_snv);
        hd_ok_snv4 = findViewById(R.id.txt_btn4_head_ok_snv);
        hd_ok_snv5 = findViewById(R.id.txt_btn5_head_ok_snv);
        bd_ok_snv1 = findViewById(R.id.body_btn1_ok_snv);
        bd_ok_snv2 = findViewById(R.id.body_btn2_ok_snv);
        bd_ok_snv3 = findViewById(R.id.body_btn3_ok_snv);
        bd_ok_snv4 = findViewById(R.id.body_btn4_ok_snv);
        bd_ok_snv5 = findViewById(R.id.body_btn5_ok_snv);

        hd_er_snv1 = findViewById(R.id.txt_btn1_head_error_snv);
        hd_er_snv2 = findViewById(R.id.txt_btn2_head_error_snv);
        hd_er_snv3 = findViewById(R.id.txt_btn3_head_error_snv);
        hd_er_snv4 = findViewById(R.id.txt_btn4_head_error_snv);
        hd_er_snv5 = findViewById(R.id.txt_btn5_head_error_snv);
        bd_er_snv1 = findViewById(R.id.body_btn1_error_snv);
        bd_er_snv2 = findViewById(R.id.body_btn2_error_snv);
        bd_er_snv3 = findViewById(R.id.body_btn3_error_snv);
        bd_er_snv4 = findViewById(R.id.body_btn4_error_snv);
        bd_er_snv5 = findViewById(R.id.body_btn5_error_snv);

        String term3 = prefencias_snv.getString("terminos3","0");
        String ine3 = prefencias_snv.getString("ine3","0");
        String licencia3 = prefencias_snv.getString("licencia3","0");
        String codigo3 = prefencias_snv.getString("codigo3","0");
        String tarjeton3 = prefencias_snv.getString("tarjeton3","0");

        if(term3.matches("0")){
            btn1_snv.setVisibility(View.VISIBLE);
            fwd_snv1.setVisibility(View.VISIBLE);
            hd_snv1.setVisibility(View.VISIBLE);
            bd_snv1.setVisibility(View.VISIBLE);
            btn1_ok_snv.setVisibility(View.GONE);
            ok_snv1.setVisibility(View.GONE);
            hd_ok_snv1.setVisibility(View.GONE);
            bd_ok_snv1.setVisibility(View.GONE);
            btn1_er_snv.setVisibility(View.GONE);
            er_snv1.setVisibility(View.GONE);
            hd_er_snv1.setVisibility(View.GONE);
            bd_er_snv1.setVisibility(View.GONE);

        }else if(term3.matches("1")){
            btn1_snv.setVisibility(View.GONE);
            fwd_snv1.setVisibility(View.GONE);
            hd_snv1.setVisibility(View.GONE);
            bd_snv1.setVisibility(View.GONE);
            btn1_ok_snv.setVisibility(View.VISIBLE);
            ok_snv1.setVisibility(View.VISIBLE);
            hd_ok_snv1.setVisibility(View.VISIBLE);
            bd_ok_snv1.setVisibility(View.VISIBLE);
            btn1_er_snv.setVisibility(View.GONE);
            er_snv1.setVisibility(View.GONE);
            hd_er_snv1.setVisibility(View.GONE);
            bd_er_snv1.setVisibility(View.GONE);
        }else{
            btn1_snv.setVisibility(View.GONE);
            fwd_snv1.setVisibility(View.GONE);
            hd_snv1.setVisibility(View.GONE);
            bd_snv1.setVisibility(View.GONE);
            btn1_ok_snv.setVisibility(View.GONE);
            ok_snv1.setVisibility(View.GONE);
            hd_ok_snv1.setVisibility(View.GONE);
            bd_ok_snv1.setVisibility(View.GONE);
            btn1_er_snv.setVisibility(View.VISIBLE);
            er_snv1.setVisibility(View.VISIBLE);
            hd_er_snv1.setVisibility(View.VISIBLE);
            bd_er_snv1.setVisibility(View.VISIBLE);
        }

        if(ine3.matches("0")){
            btn2_snv.setVisibility(View.VISIBLE);
            fwd_snv2.setVisibility(View.VISIBLE);
            hd_snv2.setVisibility(View.VISIBLE);
            bd_snv2.setVisibility(View.VISIBLE);
            btn2_ok_snv.setVisibility(View.GONE);
            ok_snv2.setVisibility(View.GONE);
            hd_ok_snv2.setVisibility(View.GONE);
            bd_ok_snv2.setVisibility(View.GONE);
            btn2_er_snv.setVisibility(View.GONE);
            er_snv2.setVisibility(View.GONE);
            hd_er_snv2.setVisibility(View.GONE);
            bd_er_snv2.setVisibility(View.GONE);
        }else if(ine3.matches("1")){
            btn2_snv.setVisibility(View.GONE);
            fwd_snv2.setVisibility(View.GONE);
            hd_snv2.setVisibility(View.GONE);
            bd_snv2.setVisibility(View.GONE);
            btn2_ok_snv.setVisibility(View.VISIBLE);
            ok_snv2.setVisibility(View.VISIBLE);
            hd_ok_snv2.setVisibility(View.VISIBLE);
            bd_ok_snv2.setVisibility(View.VISIBLE);
            btn2_er_snv.setVisibility(View.GONE);
            er_snv2.setVisibility(View.GONE);
            hd_er_snv2.setVisibility(View.GONE);
            bd_er_snv2.setVisibility(View.GONE);
        }else{
            btn2_snv.setVisibility(View.GONE);
            fwd_snv2.setVisibility(View.GONE);
            hd_snv2.setVisibility(View.GONE);
            bd_snv2.setVisibility(View.GONE);
            btn2_ok_snv.setVisibility(View.GONE);
            ok_snv2.setVisibility(View.GONE);
            hd_ok_snv2.setVisibility(View.GONE);
            bd_ok_snv2.setVisibility(View.GONE);
            btn2_er_snv.setVisibility(View.VISIBLE);
            er_snv2.setVisibility(View.VISIBLE);
            hd_er_snv2.setVisibility(View.VISIBLE);
            bd_er_snv2.setVisibility(View.VISIBLE);
        }

        if(licencia3.matches("0")){
            btn3_snv.setVisibility(View.VISIBLE);
            fwd_snv3.setVisibility(View.VISIBLE);
            hd_snv3.setVisibility(View.VISIBLE);
            bd_snv3.setVisibility(View.VISIBLE);
            btn3_ok_snv.setVisibility(View.GONE);
            ok_snv3.setVisibility(View.GONE);
            hd_ok_snv3.setVisibility(View.GONE);
            bd_ok_snv3.setVisibility(View.GONE);
            btn3_er_snv.setVisibility(View.GONE);
            er_snv3.setVisibility(View.GONE);
            hd_er_snv3.setVisibility(View.GONE);
            bd_er_snv3.setVisibility(View.GONE);
        }else if(licencia3.matches("1")){
            btn3_snv.setVisibility(View.GONE);
            fwd_snv3.setVisibility(View.GONE);
            hd_snv3.setVisibility(View.GONE);
            bd_snv3.setVisibility(View.GONE);
            btn3_ok_snv.setVisibility(View.VISIBLE);
            ok_snv3.setVisibility(View.VISIBLE);
            hd_ok_snv3.setVisibility(View.VISIBLE);
            bd_ok_snv3.setVisibility(View.VISIBLE);
            btn3_er_snv.setVisibility(View.GONE);
            er_snv3.setVisibility(View.GONE);
            hd_er_snv3.setVisibility(View.GONE);
            bd_er_snv3.setVisibility(View.GONE);
        }else {
            btn3_snv.setVisibility(View.GONE);
            fwd_snv3.setVisibility(View.GONE);
            hd_snv3.setVisibility(View.GONE);
            bd_snv3.setVisibility(View.GONE);
            btn3_ok_snv.setVisibility(View.GONE);
            ok_snv3.setVisibility(View.GONE);
            hd_ok_snv3.setVisibility(View.GONE);
            bd_ok_snv3.setVisibility(View.GONE);
            btn3_er_snv.setVisibility(View.VISIBLE);
            er_snv3.setVisibility(View.VISIBLE);
            hd_er_snv3.setVisibility(View.VISIBLE);
            bd_er_snv3.setVisibility(View.VISIBLE);
        }

        if(codigo3.matches("0")){
            btn4_snv.setVisibility(View.VISIBLE);
            fwd_snv4.setVisibility(View.VISIBLE);
            hd_snv4.setVisibility(View.VISIBLE);
            bd_snv4.setVisibility(View.VISIBLE);
            btn4_ok_snv.setVisibility(View.GONE);
            ok_snv4.setVisibility(View.GONE);
            hd_ok_snv4.setVisibility(View.GONE);
            bd_ok_snv4.setVisibility(View.GONE);
            btn4_er_snv.setVisibility(View.GONE);
            er_snv4.setVisibility(View.GONE);
            hd_er_snv4.setVisibility(View.GONE);
            bd_er_snv4.setVisibility(View.GONE);
        }else if(codigo3.matches("1")){
            btn4_snv.setVisibility(View.GONE);
            fwd_snv4.setVisibility(View.GONE);
            hd_snv4.setVisibility(View.GONE);
            bd_snv4.setVisibility(View.GONE);
            btn4_ok_snv.setVisibility(View.VISIBLE);
            ok_snv4.setVisibility(View.VISIBLE);
            hd_ok_snv4.setVisibility(View.VISIBLE);
            bd_ok_snv4.setVisibility(View.VISIBLE);
            btn4_er_snv.setVisibility(View.GONE);
            er_snv4.setVisibility(View.GONE);
            hd_er_snv4.setVisibility(View.GONE);
            bd_er_snv4.setVisibility(View.GONE);
        }else {
            btn4_snv.setVisibility(View.GONE);
            fwd_snv4.setVisibility(View.GONE);
            hd_snv4.setVisibility(View.GONE);
            bd_snv4.setVisibility(View.GONE);
            btn4_ok_snv.setVisibility(View.GONE);
            ok_snv4.setVisibility(View.GONE);
            hd_ok_snv4.setVisibility(View.GONE);
            bd_ok_snv4.setVisibility(View.GONE);
            btn4_er_snv.setVisibility(View.VISIBLE);
            er_snv4.setVisibility(View.VISIBLE);
            hd_er_snv4.setVisibility(View.VISIBLE);
            bd_er_snv4.setVisibility(View.VISIBLE);
        }

        if(tarjeton3.matches("0")){
            btn5_snv.setVisibility(View.VISIBLE);
            fwd_snv5.setVisibility(View.VISIBLE);
            hd_snv5.setVisibility(View.VISIBLE);
            bd_snv5.setVisibility(View.VISIBLE);
            btn5_ok_snv.setVisibility(View.GONE);
            ok_snv5.setVisibility(View.GONE);
            hd_ok_snv5.setVisibility(View.GONE);
            bd_ok_snv5.setVisibility(View.GONE);
            btn5_er_snv.setVisibility(View.GONE);
            er_snv5.setVisibility(View.GONE);
            hd_er_snv5.setVisibility(View.GONE);
            bd_er_snv5.setVisibility(View.GONE);
        }else if(tarjeton3.matches("1")){
            btn5_snv.setVisibility(View.GONE);
            fwd_snv5.setVisibility(View.GONE);
            hd_snv5.setVisibility(View.GONE);
            bd_snv5.setVisibility(View.GONE);
            btn5_ok_snv.setVisibility(View.VISIBLE);
            ok_snv5.setVisibility(View.VISIBLE);
            hd_ok_snv5.setVisibility(View.VISIBLE);
            bd_ok_snv5.setVisibility(View.VISIBLE);
            btn5_er_snv.setVisibility(View.GONE);
            er_snv5.setVisibility(View.GONE);
            hd_er_snv5.setVisibility(View.GONE);
            bd_er_snv5.setVisibility(View.GONE);
        }else {
            btn5_snv.setVisibility(View.GONE);
            fwd_snv5.setVisibility(View.GONE);
            hd_snv5.setVisibility(View.GONE);
            bd_snv5.setVisibility(View.GONE);
            btn5_ok_snv.setVisibility(View.GONE);
            ok_snv5.setVisibility(View.GONE);
            hd_ok_snv5.setVisibility(View.GONE);
            bd_ok_snv5.setVisibility(View.GONE);
            btn5_er_snv.setVisibility(View.VISIBLE);
            er_snv5.setVisibility(View.VISIBLE);
            hd_er_snv5.setVisibility(View.VISIBLE);
            bd_er_snv5.setVisibility(View.VISIBLE);
        }


        btn_regreso_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainSnvDocuemtos.this, MainRolConductor.class);
                startActivity(main_rol_conductor);
                finish();
            }
        });

        if(term3.matches("1") & ine3.matches("1") & licencia3.matches("1") & codigo3.matches("1") & tarjeton3.matches("1")){

            Intent main_documentos_ok = new Intent(MainSnvDocuemtos.this, MainDocumentosOk.class);
            startActivity(main_documentos_ok);
            finish();
        }
    }
    public void terminos_snv(View view){
        Intent main_terminos_snv = new Intent(MainSnvDocuemtos.this, MainTerminosYCondiciones.class);
        startActivity(main_terminos_snv);
        finish();
    }
    public void ine_snv(View v){
        Intent main_ine_snv = new Intent(MainSnvDocuemtos.this, MainCapturaIne.class);
        startActivity(main_ine_snv);
        finish();
    }
    public void licenci_snv(View v){
        Intent main_licencia_snv = new Intent(MainSnvDocuemtos.this, MainCapturaLicencia.class);
        startActivity(main_licencia_snv);
        finish();
    }
    public void codigo_snv(View v)
    {
        Intent main_codigo_snv = new Intent(MainSnvDocuemtos.this, MainCapturaCodigo.class);
        startActivity(main_codigo_snv);
        finish();
    }

    public void tarjeton_snv(View v)
    {
        Intent main_tarjeton_snv = new Intent(MainSnvDocuemtos.this, MainCapturaTarjeton.class);
        startActivity(main_tarjeton_snv);
        finish();
    }
}