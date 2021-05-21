package com.example.tutumconductorv2.Registro.menus_rol;

import android.Manifest;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

    private String rol = "snv";

    private ImageView fwd_snv1,fwd_snv2,fwd_snv3,fwd_snv4,fwd_snv5;
    private ImageView ok_snv1,ok_snv2,ok_snv3,ok_snv4,ok_snv5;
    private Button btn1_snv,btn2_snv,btn3_snv,btn4_snv,btn5_snv;
    private Button btn1_ok_snv,btn2_ok_snv,btn3_ok_snv,btn4_ok_snv,btn5_ok_snv;
    private TextView hd_snv1,hd_snv2,hd_snv3,hd_snv4,hd_snv5,bd_snv1,bd_snv2,bd_snv3,bd_snv4,bd_snv5;
    private TextView hd_ok_snv1,hd_ok_snv2,hd_ok_snv3,hd_ok_snv4,hd_ok_snv5,
                     bd_ok_snv1,bd_ok_snv2,bd_ok_snv3,bd_ok_snv4,bd_ok_snv5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_snv_docuemtos);

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

        if (ContextCompat.checkSelfPermission(MainSnvDocuemtos.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainSnvDocuemtos.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainSnvDocuemtos.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);}



        if(!cadenas_documentos.check_terminos3){
            btn1_snv.setVisibility(View.VISIBLE);
            fwd_snv1.setVisibility(View.VISIBLE);
            hd_snv1.setVisibility(View.VISIBLE);
            bd_snv1.setVisibility(View.VISIBLE);
        }else{
            btn1_snv.setVisibility(View.GONE);
            fwd_snv1.setVisibility(View.GONE);
            hd_snv1.setVisibility(View.GONE);
            bd_snv1.setVisibility(View.GONE);
            btn1_ok_snv.setVisibility(View.VISIBLE);
            ok_snv1.setVisibility(View.VISIBLE);
            hd_ok_snv1.setVisibility(View.VISIBLE);
            bd_ok_snv1.setVisibility(View.VISIBLE);

        }

        if(!cadenas_documentos.check_ine3){
            btn2_snv.setVisibility(View.VISIBLE);
            fwd_snv2.setVisibility(View.VISIBLE);
            hd_snv2.setVisibility(View.VISIBLE);
            bd_snv2.setVisibility(View.VISIBLE);
        }else{
            btn2_snv.setVisibility(View.GONE);
            fwd_snv2.setVisibility(View.GONE);
            hd_snv2.setVisibility(View.GONE);
            bd_snv2.setVisibility(View.GONE);
            btn2_ok_snv.setVisibility(View.VISIBLE);
            ok_snv2.setVisibility(View.VISIBLE);
            hd_ok_snv2.setVisibility(View.VISIBLE);
            bd_ok_snv2.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_licencia3){
            btn3_snv.setVisibility(View.VISIBLE);
            fwd_snv3.setVisibility(View.VISIBLE);
            hd_snv3.setVisibility(View.VISIBLE);
            bd_snv3.setVisibility(View.VISIBLE);
        }else{
            btn3_snv.setVisibility(View.GONE);
            fwd_snv3.setVisibility(View.GONE);
            hd_snv3.setVisibility(View.GONE);
            bd_snv3.setVisibility(View.GONE);
            btn3_ok_snv.setVisibility(View.VISIBLE);
            ok_snv3.setVisibility(View.VISIBLE);
            hd_ok_snv3.setVisibility(View.VISIBLE);
            bd_ok_snv3.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_codigo3){
            btn4_snv.setVisibility(View.VISIBLE);
            fwd_snv4.setVisibility(View.VISIBLE);
            hd_snv4.setVisibility(View.VISIBLE);
            bd_snv4.setVisibility(View.VISIBLE);
        }else{
            btn4_snv.setVisibility(View.GONE);
            fwd_snv4.setVisibility(View.GONE);
            hd_snv4.setVisibility(View.GONE);
            bd_snv4.setVisibility(View.GONE);
            btn4_ok_snv.setVisibility(View.VISIBLE);
            ok_snv4.setVisibility(View.VISIBLE);
            hd_ok_snv4.setVisibility(View.VISIBLE);
            bd_ok_snv4.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_tarjeton3){
            btn5_snv.setVisibility(View.VISIBLE);
            fwd_snv5.setVisibility(View.VISIBLE);
            hd_snv5.setVisibility(View.VISIBLE);
            bd_snv5.setVisibility(View.VISIBLE);
        }else{
            btn5_snv.setVisibility(View.GONE);
            fwd_snv5.setVisibility(View.GONE);
            hd_snv5.setVisibility(View.GONE);
            bd_snv5.setVisibility(View.GONE);
            btn5_ok_snv.setVisibility(View.VISIBLE);
            ok_snv5.setVisibility(View.VISIBLE);
            hd_ok_snv5.setVisibility(View.VISIBLE);
            bd_ok_snv5.setVisibility(View.VISIBLE);
        }
        btn_regreso_snv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainSnvDocuemtos.this, MainRolConductor.class);
                startActivity(main_rol_conductor);
                finish();
            }
        });
    }
    public void terminos_snv(View view){
        Intent main_terminos_snv = new Intent(MainSnvDocuemtos.this, MainTerminosYCondiciones.class);
        main_terminos_snv.putExtra("rol",rol);
        startActivity(main_terminos_snv);
        finish();
    }
    public void ine_snv(View v){
        Intent main_ine_snv = new Intent(MainSnvDocuemtos.this, MainCapturaIne.class);
        main_ine_snv.putExtra("rol",rol);
        startActivity(main_ine_snv);
        finish();
    }
    public void licenci_snv(View v){
        Intent main_licencia_snv = new Intent(MainSnvDocuemtos.this, MainCapturaLicencia.class);
        main_licencia_snv.putExtra("rol",rol);
        startActivity(main_licencia_snv);
        finish();
    }
    public void codigo_snv(View v)
    {
        Intent main_codigo_snv = new Intent(MainSnvDocuemtos.this, MainCapturaCodigo.class);
        main_codigo_snv.putExtra("rol",rol);
        startActivity(main_codigo_snv);
        finish();
    }

    public void tarjeton_snv(View v)
    {
        Intent main_tarjeton_snv = new Intent(MainSnvDocuemtos.this, MainCapturaTarjeton.class);
        main_tarjeton_snv.putExtra("rol",rol);
        startActivity(main_tarjeton_snv);
        finish();
    }
}