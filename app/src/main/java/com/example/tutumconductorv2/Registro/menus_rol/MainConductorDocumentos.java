package com.example.tutumconductorv2.Registro.menus_rol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaCaracteristicas;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaIne;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaLicencia;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaPoliza;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjetaCirculacion;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjeton;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainTerminosYCondiciones;

public class MainConductorDocumentos extends AppCompatActivity {

    private ImageView btn_regresar_conductor;

    private String rol = "Conductor";

    private ImageView fwd_conductor1,fwd_conductor2,fwd_conductor3,fwd_conductor4,fwd_conductor5,fwd_conductor6,fwd_conductor7;
    private ImageView ok_conductor1,ok_conductor2,ok_conductor3,ok_conductor4,ok_conductor5,ok_conductor6,ok_conductor7;
    private ImageView er_conductor1,er_conductor2,er_conductor3,er_conductor4,er_conductor5,er_conductor6,er_conductor7;

    private Button btn1_conductor,btn2_conductor,btn3_conductor,btn4_conductor,btn5_conductor,btn6_conductor,btn7_conductor;
    private Button btn1_ok_conductor,btn2_ok_conductor,btn3_ok_conductor,btn4_ok_conductor,btn5_ok_conductor,btn6_ok_conductor,btn7_ok_conductor;
    private Button btn1_er_conductor,btn2_er_conductor,btn3_er_conductor,btn4_er_conductor,btn5_er_conductor,btn6_er_conductor,btn7_er_conductor;

    private TextView hd_conductor1,hd_conductor2,hd_conductor3,hd_conductor4,hd_conductor5,hd_conductor6,hd_conductor7,
                     bd_conductor1,bd_conductor2,bd_conductor3,bd_conductor4,bd_conductor5,bd_conductor6,bd_conductor7;

    private TextView hd_ok_conductor1,hd_ok_conductor2,hd_ok_conductor3,hd_ok_conductor4,hd_ok_conductor5,hd_ok_conductor6,hd_ok_conductor7,
                     bd_ok_conductor1,bd_ok_conductor2,bd_ok_conductor3,bd_ok_conductor4,bd_ok_conductor5,bd_ok_conductor6,bd_ok_conductor7;

    private TextView hd_er_conductor1,hd_er_conductor2,hd_er_conductor3,hd_er_conductor4,hd_er_conductor5,hd_er_conductor6,hd_er_conductor7,
                     bd_er_conductor1,bd_er_conductor2,bd_er_conductor3,bd_er_conductor4,bd_er_conductor5,bd_er_conductor6,bd_er_conductor7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conductor_documentos);
        SharedPreferences prefencias_conductor = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);

        btn_regresar_conductor = findViewById(R.id.img_retroceso_documentos_conductor);

        //Asociacion de los botones
        btn1_conductor = findViewById(R.id.button1_conductor);
        btn2_conductor = findViewById(R.id.button2_conductor);
        btn3_conductor = findViewById(R.id.button3_conductor);
        btn4_conductor = findViewById(R.id.button4_conductor);
        btn5_conductor = findViewById(R.id.button5_conductor);
        btn6_conductor = findViewById(R.id.button6_conductor);
        btn7_conductor = findViewById(R.id.button7_conductor);
        btn1_ok_conductor = findViewById(R.id.button1_conductor_ok);
        btn2_ok_conductor = findViewById(R.id.button2_conductor_ok);
        btn3_ok_conductor = findViewById(R.id.button3_conductor_ok);
        btn4_ok_conductor = findViewById(R.id.button4_conductor_ok);
        btn5_ok_conductor = findViewById(R.id.button5_conductor_ok);
        btn6_ok_conductor = findViewById(R.id.button6_conductor_ok);
        btn7_ok_conductor = findViewById(R.id.button7_conductor_ok);
        btn1_er_conductor = findViewById(R.id.button1_conductor_error);
        btn2_er_conductor = findViewById(R.id.button2_conductor_error);
        btn3_er_conductor = findViewById(R.id.button3_conductor_error);
        btn4_er_conductor = findViewById(R.id.button4_conductor_error);
        btn5_er_conductor = findViewById(R.id.button5_conductor_error);
        btn6_er_conductor = findViewById(R.id.button6_conductor_error);
        btn7_er_conductor = findViewById(R.id.button7_conductor_error);

        //Asociacion de las imagenes
        fwd_conductor1 = findViewById(R.id.fwd_conductor_1);
        fwd_conductor2 = findViewById(R.id.fwd_conductor_2);
        fwd_conductor3 = findViewById(R.id.fwd_conductor_3);
        fwd_conductor4 = findViewById(R.id.fwd_conductor_4);
        fwd_conductor5 = findViewById(R.id.fwd_conductor_5);
        fwd_conductor6 = findViewById(R.id.fwd_conductor_6);
        fwd_conductor7 = findViewById(R.id.fwd_conductor_7);
        ok_conductor1 = findViewById(R.id.fwd_conductor_1_ok);
        ok_conductor2 = findViewById(R.id.fwd_conductor_2_ok);
        ok_conductor3 = findViewById(R.id.fwd_conductor_3_ok);
        ok_conductor4 = findViewById(R.id.fwd_conductor_4_ok);
        ok_conductor5 = findViewById(R.id.fwd_conductor_5_ok);
        ok_conductor6 = findViewById(R.id.fwd_conductor_6_ok);
        ok_conductor7 = findViewById(R.id.fwd_conductor_7_ok);
        er_conductor1 = findViewById(R.id.fwd_conductor_1_error);
        er_conductor2 = findViewById(R.id.fwd_conductor_2_error);
        er_conductor3 = findViewById(R.id.fwd_conductor_3_error);
        er_conductor4 = findViewById(R.id.fwd_conductor_4_error);
        er_conductor5 = findViewById(R.id.fwd_conductor_5_error);
        er_conductor6 = findViewById(R.id.fwd_conductor_6_error);
        er_conductor7 = findViewById(R.id.fwd_conductor_7_error);

        //Asociacion de los textos
        hd_conductor1 = findViewById(R.id.txt_btn1_head_conductor);
        hd_conductor2 = findViewById(R.id.txt_btn2_head_conductor);
        hd_conductor3 = findViewById(R.id.txt_btn3_head_conductor);
        hd_conductor4 = findViewById(R.id.txt_btn4_head_conductor);
        hd_conductor5 = findViewById(R.id.txt_btn5_head_conductor);
        hd_conductor6 = findViewById(R.id.txt_btn6_head_conductor);
        hd_conductor7 = findViewById(R.id.txt_btn7_head_conductor);
        bd_conductor1 = findViewById(R.id.body_btn1_conductor);
        bd_conductor2 = findViewById(R.id.body_btn2_conductor);
        bd_conductor3 = findViewById(R.id.body_btn3_conductor);
        bd_conductor4 = findViewById(R.id.body_btn4_conductor);
        bd_conductor5 = findViewById(R.id.body_btn5_conductor);
        bd_conductor6 = findViewById(R.id.body_btn6_conductor);
        bd_conductor7 = findViewById(R.id.body_btn7_conductor);


        hd_ok_conductor1 = findViewById(R.id.txt_btn1_head_ok_conductor);
        hd_ok_conductor2 = findViewById(R.id.txt_btn2_head_ok_conductor);
        hd_ok_conductor3 = findViewById(R.id.txt_btn3_head_ok_conductor);
        hd_ok_conductor4 = findViewById(R.id.txt_btn4_head_ok_conductor);
        hd_ok_conductor5 = findViewById(R.id.txt_btn5_head_ok_conductor);
        hd_ok_conductor6 = findViewById(R.id.txt_btn6_head_ok_conductor);
        hd_ok_conductor7 = findViewById(R.id.txt_btn7_head_ok_conductor);
        bd_ok_conductor1 = findViewById(R.id.body_btn1_ok_conductor);
        bd_ok_conductor2 = findViewById(R.id.body_btn2_ok_conductor);
        bd_ok_conductor3 = findViewById(R.id.body_btn3_ok_conductor);
        bd_ok_conductor4 = findViewById(R.id.body_btn4_ok_conductor);
        bd_ok_conductor5 = findViewById(R.id.body_btn5_ok_conductor);
        bd_ok_conductor6 = findViewById(R.id.body_btn6_ok_conductor);
        bd_ok_conductor7 = findViewById(R.id.body_btn7_ok_conductor);

        hd_er_conductor1 = findViewById(R.id.txt_btn1_head_error_conductor);
        hd_er_conductor2 = findViewById(R.id.txt_btn2_head_error_conductor);
        hd_er_conductor3 = findViewById(R.id.txt_btn3_head_error_conductor);
        hd_er_conductor4 = findViewById(R.id.txt_btn4_head_error_conductor);
        hd_er_conductor5 = findViewById(R.id.txt_btn5_head_error_conductor);
        hd_er_conductor6 = findViewById(R.id.txt_btn6_head_error_conductor);
        hd_er_conductor7 = findViewById(R.id.txt_btn7_head_error_conductor);
        bd_er_conductor1 = findViewById(R.id.body_btn1_error_conductor);
        bd_er_conductor2 = findViewById(R.id.body_btn2_error_conductor);
        bd_er_conductor3 = findViewById(R.id.body_btn3_error_conductor);
        bd_er_conductor4 = findViewById(R.id.body_btn4_error_conductor);
        bd_er_conductor5 = findViewById(R.id.body_btn5_error_conductor);
        bd_er_conductor6 = findViewById(R.id.body_btn6_error_conductor);
        bd_er_conductor7 = findViewById(R.id.body_btn7_error_conductor);

        String term2 = prefencias_conductor.getString("terminos2","0");
        String ine2 = prefencias_conductor.getString("ine2","0");
        String licencia2 = prefencias_conductor.getString("licencia2","0");
        String caracteristicas2 = prefencias_conductor.getString("caracteristicas2","0");
        String tarjeta2 = prefencias_conductor.getString("tarjeta2","0");
        String poliza2 = prefencias_conductor.getString("poliza2","0");
        String tarjeton2 = prefencias_conductor.getString("tarjeton2","0");

        if(term2.matches("0")){
            btn1_conductor.setVisibility(View.VISIBLE);
            fwd_conductor1.setVisibility(View.VISIBLE);
            hd_conductor1.setVisibility(View.VISIBLE);
            bd_conductor1.setVisibility(View.VISIBLE);

            btn1_ok_conductor.setVisibility(View.GONE);
            ok_conductor1.setVisibility(View.GONE);
            hd_ok_conductor1.setVisibility(View.GONE);
            bd_ok_conductor1.setVisibility(View.GONE);

            btn1_er_conductor.setVisibility(View.GONE);
            er_conductor1.setVisibility(View.GONE);
            hd_er_conductor1.setVisibility(View.GONE);
            bd_er_conductor1.setVisibility(View.GONE);
        }else if(term2.matches("1")){
            btn1_conductor.setVisibility(View.GONE);
            fwd_conductor1.setVisibility(View.GONE);
            hd_conductor1.setVisibility(View.GONE);
            bd_conductor1.setVisibility(View.GONE);

            btn1_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor1.setVisibility(View.VISIBLE);
            hd_ok_conductor1.setVisibility(View.VISIBLE);
            bd_ok_conductor1.setVisibility(View.VISIBLE);

            btn1_er_conductor.setVisibility(View.GONE);
            er_conductor1.setVisibility(View.GONE);
            hd_er_conductor1.setVisibility(View.GONE);
            bd_er_conductor1.setVisibility(View.GONE);
        }else{
            btn1_conductor.setVisibility(View.GONE);
            fwd_conductor1.setVisibility(View.GONE);
            hd_conductor1.setVisibility(View.GONE);
            bd_conductor1.setVisibility(View.GONE);

            btn1_ok_conductor.setVisibility(View.GONE);
            ok_conductor1.setVisibility(View.GONE);
            hd_ok_conductor1.setVisibility(View.GONE);
            bd_ok_conductor1.setVisibility(View.GONE);

            btn1_er_conductor.setVisibility(View.VISIBLE);
            er_conductor1.setVisibility(View.VISIBLE);
            hd_er_conductor1.setVisibility(View.VISIBLE);
            bd_er_conductor1.setVisibility(View.VISIBLE);
        }

        if(ine2.matches("0")){
            btn2_conductor.setVisibility(View.VISIBLE);
            fwd_conductor2.setVisibility(View.VISIBLE);
            hd_conductor2.setVisibility(View.VISIBLE);
            bd_conductor2.setVisibility(View.VISIBLE);

            btn2_ok_conductor.setVisibility(View.GONE);
            ok_conductor2.setVisibility(View.GONE);
            hd_ok_conductor2.setVisibility(View.GONE);
            bd_ok_conductor2.setVisibility(View.GONE);

            btn2_er_conductor.setVisibility(View.GONE);
            er_conductor2.setVisibility(View.GONE);
            hd_er_conductor2.setVisibility(View.GONE);
            bd_er_conductor2.setVisibility(View.GONE);
        }else if(ine2.matches("1")){
            btn2_conductor.setVisibility(View.GONE);
            fwd_conductor2.setVisibility(View.GONE);
            hd_conductor2.setVisibility(View.GONE);
            bd_conductor2.setVisibility(View.GONE);

            btn2_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor2.setVisibility(View.VISIBLE);
            hd_ok_conductor2.setVisibility(View.VISIBLE);
            bd_ok_conductor2.setVisibility(View.VISIBLE);

            btn2_er_conductor.setVisibility(View.GONE);
            er_conductor2.setVisibility(View.GONE);
            hd_er_conductor2.setVisibility(View.GONE);
            bd_er_conductor2.setVisibility(View.GONE);
        }else {
            btn2_conductor.setVisibility(View.GONE);
            fwd_conductor2.setVisibility(View.GONE);
            hd_conductor2.setVisibility(View.GONE);
            bd_conductor2.setVisibility(View.GONE);

            btn2_ok_conductor.setVisibility(View.GONE);
            ok_conductor2.setVisibility(View.GONE);
            hd_ok_conductor2.setVisibility(View.GONE);
            bd_ok_conductor2.setVisibility(View.GONE);

            btn2_er_conductor.setVisibility(View.VISIBLE);
            er_conductor2.setVisibility(View.VISIBLE);
            hd_er_conductor2.setVisibility(View.VISIBLE);
            bd_er_conductor2.setVisibility(View.VISIBLE);
        }

        if(licencia2.matches("0")){
            btn3_conductor.setVisibility(View.VISIBLE);
            fwd_conductor3.setVisibility(View.VISIBLE);
            hd_conductor3.setVisibility(View.VISIBLE);
            bd_conductor3.setVisibility(View.VISIBLE);

            btn3_ok_conductor.setVisibility(View.GONE);
            ok_conductor3.setVisibility(View.GONE);
            hd_ok_conductor3.setVisibility(View.GONE);
            bd_ok_conductor3.setVisibility(View.GONE);

            btn3_er_conductor.setVisibility(View.GONE);
            er_conductor3.setVisibility(View.GONE);
            hd_er_conductor3.setVisibility(View.GONE);
            bd_er_conductor3.setVisibility(View.GONE);
        }else if(licencia2.matches("1")){
            btn3_conductor.setVisibility(View.GONE);
            fwd_conductor3.setVisibility(View.GONE);
            hd_conductor3.setVisibility(View.GONE);
            bd_conductor3.setVisibility(View.GONE);

            btn3_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor3.setVisibility(View.VISIBLE);
            hd_ok_conductor3.setVisibility(View.VISIBLE);
            bd_ok_conductor3.setVisibility(View.VISIBLE);

            btn3_er_conductor.setVisibility(View.GONE);
            er_conductor3.setVisibility(View.GONE);
            hd_er_conductor3.setVisibility(View.GONE);
            bd_er_conductor3.setVisibility(View.GONE);
        }else{
            btn3_conductor.setVisibility(View.GONE);
            fwd_conductor3.setVisibility(View.GONE);
            hd_conductor3.setVisibility(View.GONE);
            bd_conductor3.setVisibility(View.GONE);

            btn3_ok_conductor.setVisibility(View.GONE);
            ok_conductor3.setVisibility(View.GONE);
            hd_ok_conductor3.setVisibility(View.GONE);
            bd_ok_conductor3.setVisibility(View.GONE);

            btn3_er_conductor.setVisibility(View.VISIBLE);
            er_conductor3.setVisibility(View.VISIBLE);
            hd_er_conductor3.setVisibility(View.VISIBLE);
            bd_er_conductor3.setVisibility(View.VISIBLE);
        }

        if(caracteristicas2.matches("0")){
            btn4_conductor.setVisibility(View.VISIBLE);
            fwd_conductor4.setVisibility(View.VISIBLE);
            hd_conductor4.setVisibility(View.VISIBLE);
            bd_conductor4.setVisibility(View.VISIBLE);

            btn4_ok_conductor.setVisibility(View.GONE);
            ok_conductor4.setVisibility(View.GONE);
            hd_ok_conductor4.setVisibility(View.GONE);
            bd_ok_conductor4.setVisibility(View.GONE);

            btn4_er_conductor.setVisibility(View.GONE);
            er_conductor4.setVisibility(View.GONE);
            hd_er_conductor4.setVisibility(View.GONE);
            bd_er_conductor4.setVisibility(View.GONE);
        }else if(caracteristicas2.matches("1")){
            btn4_conductor.setVisibility(View.GONE);
            fwd_conductor4.setVisibility(View.GONE);
            hd_conductor4.setVisibility(View.GONE);
            bd_conductor4.setVisibility(View.GONE);

            btn4_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor4.setVisibility(View.VISIBLE);
            hd_ok_conductor4.setVisibility(View.VISIBLE);
            bd_ok_conductor4.setVisibility(View.VISIBLE);

            btn4_er_conductor.setVisibility(View.GONE);
            er_conductor4.setVisibility(View.GONE);
            hd_er_conductor4.setVisibility(View.GONE);
            bd_er_conductor4.setVisibility(View.GONE);
        }else{
            btn4_conductor.setVisibility(View.GONE);
            fwd_conductor4.setVisibility(View.GONE);
            hd_conductor4.setVisibility(View.GONE);
            bd_conductor4.setVisibility(View.GONE);

            btn4_ok_conductor.setVisibility(View.GONE);
            ok_conductor4.setVisibility(View.GONE);
            hd_ok_conductor4.setVisibility(View.GONE);
            bd_ok_conductor4.setVisibility(View.GONE);

            btn4_er_conductor.setVisibility(View.VISIBLE);
            er_conductor4.setVisibility(View.VISIBLE);
            hd_er_conductor4.setVisibility(View.VISIBLE);
            bd_er_conductor4.setVisibility(View.VISIBLE);
        }

        if(tarjeta2.matches("0")){
            btn5_conductor.setVisibility(View.VISIBLE);
            fwd_conductor5.setVisibility(View.VISIBLE);
            hd_conductor5.setVisibility(View.VISIBLE);
            bd_conductor5.setVisibility(View.VISIBLE);

            btn5_ok_conductor.setVisibility(View.GONE);
            ok_conductor5.setVisibility(View.GONE);
            hd_ok_conductor5.setVisibility(View.GONE);
            bd_ok_conductor5.setVisibility(View.GONE);

            btn5_er_conductor.setVisibility(View.GONE);
            er_conductor5.setVisibility(View.GONE);
            hd_er_conductor5.setVisibility(View.GONE);
            bd_er_conductor5.setVisibility(View.GONE);
        }else if(tarjeta2.matches("1")){
            btn5_conductor.setVisibility(View.GONE);
            fwd_conductor5.setVisibility(View.GONE);
            hd_conductor5.setVisibility(View.GONE);
            bd_conductor5.setVisibility(View.GONE);

            btn5_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor5.setVisibility(View.VISIBLE);
            hd_ok_conductor5.setVisibility(View.VISIBLE);
            bd_ok_conductor5.setVisibility(View.VISIBLE);

            btn5_er_conductor.setVisibility(View.GONE);
            er_conductor5.setVisibility(View.GONE);
            hd_er_conductor5.setVisibility(View.GONE);
            bd_er_conductor5.setVisibility(View.GONE);
        }else{
            btn5_conductor.setVisibility(View.GONE);
            fwd_conductor5.setVisibility(View.GONE);
            hd_conductor5.setVisibility(View.GONE);
            bd_conductor5.setVisibility(View.GONE);

            btn5_ok_conductor.setVisibility(View.GONE);
            ok_conductor5.setVisibility(View.GONE);
            hd_ok_conductor5.setVisibility(View.GONE);
            bd_ok_conductor5.setVisibility(View.GONE);

            btn5_er_conductor.setVisibility(View.GONE);
            er_conductor5.setVisibility(View.GONE);
            hd_er_conductor5.setVisibility(View.GONE);
            bd_er_conductor5.setVisibility(View.GONE);
        }

        if(poliza2.matches("0")){
            btn6_conductor.setVisibility(View.VISIBLE);
            fwd_conductor6.setVisibility(View.VISIBLE);
            hd_conductor6.setVisibility(View.VISIBLE);
            bd_conductor6.setVisibility(View.VISIBLE);

            btn6_ok_conductor.setVisibility(View.GONE);
            ok_conductor6.setVisibility(View.GONE);
            hd_ok_conductor6.setVisibility(View.GONE);
            bd_ok_conductor6.setVisibility(View.GONE);

            btn6_er_conductor.setVisibility(View.GONE);
            er_conductor6.setVisibility(View.GONE);
            hd_er_conductor6.setVisibility(View.GONE);
            bd_er_conductor6.setVisibility(View.GONE);
        }else if(poliza2.matches("1")){
            btn6_conductor.setVisibility(View.GONE);
            fwd_conductor6.setVisibility(View.GONE);
            hd_conductor6.setVisibility(View.GONE);
            bd_conductor6.setVisibility(View.GONE);

            btn6_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor6.setVisibility(View.VISIBLE);
            hd_ok_conductor6.setVisibility(View.VISIBLE);
            bd_ok_conductor6.setVisibility(View.VISIBLE);

            btn6_er_conductor.setVisibility(View.GONE);
            er_conductor6.setVisibility(View.GONE);
            hd_er_conductor6.setVisibility(View.GONE);
            bd_er_conductor6.setVisibility(View.GONE);
        }else {
            btn6_conductor.setVisibility(View.GONE);
            fwd_conductor6.setVisibility(View.GONE);
            hd_conductor6.setVisibility(View.GONE);
            bd_conductor6.setVisibility(View.GONE);

            btn6_ok_conductor.setVisibility(View.GONE);
            ok_conductor6.setVisibility(View.GONE);
            hd_ok_conductor6.setVisibility(View.GONE);
            bd_ok_conductor6.setVisibility(View.GONE);

            btn6_er_conductor.setVisibility(View.VISIBLE);
            er_conductor6.setVisibility(View.VISIBLE);
            hd_er_conductor6.setVisibility(View.VISIBLE);
            bd_er_conductor6.setVisibility(View.VISIBLE);
        }

        if(tarjeton2.matches("0")){
            btn7_conductor.setVisibility(View.VISIBLE);
            fwd_conductor7.setVisibility(View.VISIBLE);
            hd_conductor7.setVisibility(View.VISIBLE);
            bd_conductor7.setVisibility(View.VISIBLE);

            btn7_ok_conductor.setVisibility(View.GONE);
            ok_conductor7.setVisibility(View.GONE);
            hd_ok_conductor7.setVisibility(View.GONE);
            bd_ok_conductor7.setVisibility(View.GONE);

            btn7_er_conductor.setVisibility(View.GONE);
            er_conductor7.setVisibility(View.GONE);
            hd_er_conductor7.setVisibility(View.GONE);
            bd_er_conductor7.setVisibility(View.GONE);
        }else if(tarjeton2.matches("1")){
            btn7_conductor.setVisibility(View.GONE);
            fwd_conductor7.setVisibility(View.GONE);
            hd_conductor7.setVisibility(View.GONE);
            bd_conductor7.setVisibility(View.GONE);

            btn7_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor7.setVisibility(View.VISIBLE);
            hd_ok_conductor7.setVisibility(View.VISIBLE);
            bd_ok_conductor7.setVisibility(View.VISIBLE);

            btn7_er_conductor.setVisibility(View.GONE);
            er_conductor7.setVisibility(View.GONE);
            hd_er_conductor7.setVisibility(View.GONE);
            bd_er_conductor7.setVisibility(View.GONE);
        }else {
            btn7_conductor.setVisibility(View.GONE);
            fwd_conductor7.setVisibility(View.GONE);
            hd_conductor7.setVisibility(View.GONE);
            bd_conductor7.setVisibility(View.GONE);

            btn7_ok_conductor.setVisibility(View.GONE);
            ok_conductor7.setVisibility(View.GONE);
            hd_ok_conductor7.setVisibility(View.GONE);
            bd_ok_conductor7.setVisibility(View.GONE);

            btn7_er_conductor.setVisibility(View.VISIBLE);
            er_conductor7.setVisibility(View.VISIBLE);
            hd_er_conductor7.setVisibility(View.VISIBLE);
            bd_er_conductor7.setVisibility(View.VISIBLE);
        }


        btn_regresar_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainConductorDocumentos.this, MainRolConductor.class);
                startActivity(main_rol_conductor);
                finish();
            }
        });

        if(term2.matches("1") & ine2.matches("1") & licencia2.matches("1") & caracteristicas2.matches("1") & tarjeta2.matches("1") & poliza2.matches("1") & tarjeton2.matches("1")){
            Intent main_documentos_ok = new Intent(MainConductorDocumentos.this, MainDocumentosOk.class);
            startActivity(main_documentos_ok);
            finish();
        }
    }

    public void terminos_conductor(View view){
        Intent main_terminos_conductor = new Intent(MainConductorDocumentos.this, MainTerminosYCondiciones.class);
        startActivity(main_terminos_conductor);
        finish();
    }
    public void ine_conductor(View v){
        Intent main_ine_conductor = new Intent(MainConductorDocumentos.this, MainCapturaIne.class);
        startActivity(main_ine_conductor);
        finish();
    }
    public void licencia_conductor(View v){
        Intent main_licencia_conductor = new Intent(MainConductorDocumentos.this, MainCapturaLicencia.class);
        startActivity(main_licencia_conductor);
        finish();
    }
    public void caracteristicas_conductor(View v)
    {
        Intent main_caracteristicas_conductor = new Intent(MainConductorDocumentos.this, MainCapturaCaracteristicas.class);
        startActivity(main_caracteristicas_conductor);
        finish();
    }
    public void tarjeta_conductor(View v)
    {
        Intent main_tarjeta_conductor = new Intent(MainConductorDocumentos.this, MainCapturaTarjetaCirculacion.class);
        startActivity(main_tarjeta_conductor);
        finish();
    }
    public void poliz_conductor(View v)
    {
        Intent main_poliza_conductor = new Intent(MainConductorDocumentos.this, MainCapturaPoliza.class);
        startActivity(main_poliza_conductor);
        finish();
    }
    public void tarjeton_conductor(View v)
    {
        Intent main_tarjeton_conductor = new Intent(MainConductorDocumentos.this, MainCapturaTarjeton.class);
        startActivity(main_tarjeton_conductor);
        finish();
    }
}