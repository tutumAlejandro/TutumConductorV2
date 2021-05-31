package com.example.tutumconductorv2.Registro.menus_rol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
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
    private Button btn1_conductor,btn2_conductor,btn3_conductor,btn4_conductor,btn5_conductor,btn6_conductor,btn7_conductor;
    private Button btn1_ok_conductor,btn2_ok_conductor,btn3_ok_conductor,btn4_ok_conductor,btn5_ok_conductor,btn6_ok_conductor,btn7_ok_conductor;
    private TextView hd_conductor1,hd_conductor2,hd_conductor3,hd_conductor4,hd_conductor5,hd_conductor6,hd_conductor7,
                     bd_conductor1,bd_conductor2,bd_conductor3,bd_conductor4,bd_conductor5,bd_conductor6,bd_conductor7;
    private TextView hd_ok_conductor1,hd_ok_conductor2,hd_ok_conductor3,hd_ok_conductor4,hd_ok_conductor5,hd_ok_conductor6,hd_ok_conductor7,
                     bd_ok_conductor1,bd_ok_conductor2,bd_ok_conductor3,bd_ok_conductor4,bd_ok_conductor5,bd_ok_conductor6,bd_ok_conductor7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_conductor_documentos);

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

        if (ContextCompat.checkSelfPermission(MainConductorDocumentos.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainConductorDocumentos.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainConductorDocumentos.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);}



        if(!cadenas_documentos.check_terminos2){
            btn1_conductor.setVisibility(View.VISIBLE);
            fwd_conductor1.setVisibility(View.VISIBLE);
            hd_conductor1.setVisibility(View.VISIBLE);
            bd_conductor1.setVisibility(View.VISIBLE);
        }else{
            btn1_conductor.setVisibility(View.GONE);
            fwd_conductor1.setVisibility(View.GONE);
            hd_conductor1.setVisibility(View.GONE);
            bd_conductor1.setVisibility(View.GONE);
            btn1_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor1.setVisibility(View.VISIBLE);
            hd_ok_conductor1.setVisibility(View.VISIBLE);
            bd_ok_conductor1.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_ine2){
            btn2_conductor.setVisibility(View.VISIBLE);
            fwd_conductor2.setVisibility(View.VISIBLE);
            hd_conductor2.setVisibility(View.VISIBLE);
            bd_conductor2.setVisibility(View.VISIBLE);
        }else{
            btn2_conductor.setVisibility(View.GONE);
            fwd_conductor2.setVisibility(View.GONE);
            hd_conductor2.setVisibility(View.GONE);
            bd_conductor2.setVisibility(View.GONE);
            btn2_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor2.setVisibility(View.VISIBLE);
            hd_ok_conductor2.setVisibility(View.VISIBLE);
            bd_ok_conductor2.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_licencia2){
            btn3_conductor.setVisibility(View.VISIBLE);
            fwd_conductor3.setVisibility(View.VISIBLE);
            hd_conductor3.setVisibility(View.VISIBLE);
            bd_conductor3.setVisibility(View.VISIBLE);
        }else{
            btn3_conductor.setVisibility(View.GONE);
            fwd_conductor3.setVisibility(View.GONE);
            hd_conductor3.setVisibility(View.GONE);
            bd_conductor3.setVisibility(View.GONE);
            btn3_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor3.setVisibility(View.VISIBLE);
            hd_ok_conductor3.setVisibility(View.VISIBLE);
            bd_ok_conductor3.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_caracteristicas2){
            btn4_conductor.setVisibility(View.VISIBLE);
            fwd_conductor4.setVisibility(View.VISIBLE);
            hd_conductor4.setVisibility(View.VISIBLE);
            bd_conductor4.setVisibility(View.VISIBLE);
        }else{
            btn4_conductor.setVisibility(View.GONE);
            fwd_conductor4.setVisibility(View.GONE);
            hd_conductor4.setVisibility(View.GONE);
            bd_conductor4.setVisibility(View.GONE);
            btn4_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor4.setVisibility(View.VISIBLE);
            hd_ok_conductor4.setVisibility(View.VISIBLE);
            bd_ok_conductor4.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_tarjeta2){
            btn5_conductor.setVisibility(View.VISIBLE);
            fwd_conductor5.setVisibility(View.VISIBLE);
            hd_conductor5.setVisibility(View.VISIBLE);
            bd_conductor5.setVisibility(View.VISIBLE);
        }else{
            btn5_conductor.setVisibility(View.GONE);
            fwd_conductor5.setVisibility(View.GONE);
            hd_conductor5.setVisibility(View.GONE);
            bd_conductor5.setVisibility(View.GONE);
            btn5_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor5.setVisibility(View.VISIBLE);
            hd_ok_conductor5.setVisibility(View.VISIBLE);
            bd_ok_conductor5.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_poliza2){
            btn6_conductor.setVisibility(View.VISIBLE);
            fwd_conductor6.setVisibility(View.VISIBLE);
            hd_conductor6.setVisibility(View.VISIBLE);
            bd_conductor6.setVisibility(View.VISIBLE);
        }else{
            btn6_conductor.setVisibility(View.GONE);
            fwd_conductor6.setVisibility(View.GONE);
            hd_conductor6.setVisibility(View.GONE);
            bd_conductor6.setVisibility(View.GONE);
            btn6_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor6.setVisibility(View.VISIBLE);
            hd_ok_conductor6.setVisibility(View.VISIBLE);
            bd_ok_conductor6.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_tarjeton2){
            btn7_conductor.setVisibility(View.VISIBLE);
            fwd_conductor7.setVisibility(View.VISIBLE);
            hd_conductor7.setVisibility(View.VISIBLE);
            bd_conductor7.setVisibility(View.VISIBLE);
        }else{
            btn7_conductor.setVisibility(View.GONE);
            fwd_conductor7.setVisibility(View.GONE);
            hd_conductor7.setVisibility(View.GONE);
            bd_conductor7.setVisibility(View.GONE);
            btn7_ok_conductor.setVisibility(View.VISIBLE);
            ok_conductor7.setVisibility(View.VISIBLE);
            hd_ok_conductor7.setVisibility(View.VISIBLE);
            bd_ok_conductor7.setVisibility(View.VISIBLE);
        }


        btn_regresar_conductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainConductorDocumentos.this, MainRolConductor.class);
                startActivity(main_rol_conductor);
                finish();
            }
        });

        if(cadenas_documentos.check_ine2 & cadenas_documentos.check_licencia2 & cadenas_documentos.check_caracteristicas2 & cadenas_documentos.check_tarjeta2 & cadenas_documentos.check_poliza2 & cadenas_documentos.check_tarjeton2){

            Intent main_documentos_ok = new Intent(MainConductorDocumentos.this, MainDocumentosOk.class);
            main_documentos_ok.putExtra("rol",rol);
            startActivity(main_documentos_ok);
            finish();
        }
    }

    public void terminos_conductor(View view){
        Intent main_terminos_conductor = new Intent(MainConductorDocumentos.this, MainTerminosYCondiciones.class);
        main_terminos_conductor.putExtra("rol",rol);
        startActivity(main_terminos_conductor);
        finish();
    }
    public void ine_conductor(View v){
        Intent main_ine_conductor = new Intent(MainConductorDocumentos.this, MainCapturaIne.class);
        main_ine_conductor.putExtra("rol",rol);
        startActivity(main_ine_conductor);
        finish();
    }
    public void licencia_conductor(View v){
        Intent main_licencia_conductor = new Intent(MainConductorDocumentos.this, MainCapturaLicencia.class);
        main_licencia_conductor.putExtra("rol",rol);
        startActivity(main_licencia_conductor);
        finish();
    }
    public void caracteristicas_conductor(View v)
    {
        Intent main_caracteristicas_conductor = new Intent(MainConductorDocumentos.this, MainCapturaCaracteristicas.class);
        main_caracteristicas_conductor.putExtra("rol",rol);
        startActivity(main_caracteristicas_conductor);
        finish();
    }
    public void tarjeta_conductor(View v)
    {
        Intent main_tarjeta_conductor = new Intent(MainConductorDocumentos.this, MainCapturaTarjetaCirculacion.class);
        main_tarjeta_conductor.putExtra("rol",rol);
        startActivity(main_tarjeta_conductor);
        finish();
    }
    public void poliz_conductor(View v)
    {
        Intent main_poliza_conductor = new Intent(MainConductorDocumentos.this, MainCapturaPoliza.class);
        main_poliza_conductor.putExtra("rol",rol);
        startActivity(main_poliza_conductor);
        finish();
    }
    public void tarjeton_conductor(View v)
    {
        Intent main_tarjeton_conductor = new Intent(MainConductorDocumentos.this, MainCapturaTarjeton.class);
        main_tarjeton_conductor.putExtra("rol",rol);
        startActivity(main_tarjeton_conductor);
        finish();
    }
}