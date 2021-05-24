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
//import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaCaracteristicas;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaIne;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaLicencia;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaPoliza;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjetaCirculacion;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjeton;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainTerminosYCondiciones;

public class MainSocioDocumentos extends AppCompatActivity {

    private ImageView btn_regreso_socio,fwd1,fwd2,fwd3,fwd4,fwd5,fwd6,fwd7,ok_1,ok_2,ok_3,ok_4,ok_5,ok_6,ok_7;
    private Button btn1_socio,btn2_socio,btn3_socio,btn4_socio,btn5_socio,btn6_socio,btn7_socio,
            btn1_socio_ok,btn2_socio_ok,btn3_socio_ok,btn4_socio_ok,btn5_socio_ok,btn6_socio_ok,btn7_socio_ok;
    private TextView btn_hd1,btn_hd2,btn_hd3,btn_hd4,btn_hd5,btn_hd6,btn_hd7,btn_hd1_ok,btn_hd2_ok,btn_hd3_ok,btn_hd4_ok,btn_hd5_ok,btn_hd6_ok,btn_hd7_ok;
    private TextView btn_bd1,btn_bd2,btn_bd3,btn_bd4,btn_bd5,btn_bd6,btn_bd7,btn_bd1_ok,btn_bd2_ok,btn_bd3_ok,btn_bd4_ok,btn_bd5_ok,btn_bd6_ok,btn_bd7_ok;
    private static String rol= "Socio";

    private TextView tst_lic,vig_tarjeta,vig_poliza,vig_tarjeton,tst_fabricante,tst_modelo,tst_anio,tst_matricula;

    @Override
    //txt_btn1_head
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_socio_documentos);
        //Vinculacion de las imagenes
        btn_regreso_socio = findViewById(R.id.img_retroceso_documentos_socio);
        fwd1 = findViewById(R.id.fwd_socio_1);
        fwd2 = findViewById(R.id.fwd_socio_2);
        fwd3 = findViewById(R.id.fwd_socio_3);
        fwd4 = findViewById(R.id.fwd_socio_4);
        fwd5 = findViewById(R.id.fwd_socio_5);
        fwd6 = findViewById(R.id.fwd_socio_6);
        fwd7 = findViewById(R.id.fwd_socio_7);
        ok_1 = findViewById(R.id.fwd_socio_1_ok);
        ok_2 = findViewById(R.id.fwd_socio_2_ok);
        ok_3 = findViewById(R.id.fwd_socio_3_ok);
        ok_4 = findViewById(R.id.fwd_socio_4_ok);
        ok_5 = findViewById(R.id.fwd_socio_5_ok);
        ok_6 = findViewById(R.id.fwd_socio_6_ok);
        ok_7 = findViewById(R.id.fwd_socio_7_ok);
        //Vicular los botones
        btn1_socio = findViewById(R.id.button1_socio);
        btn2_socio = findViewById(R.id.button2_socio);
        btn3_socio = findViewById(R.id.button3_socio);
        btn4_socio = findViewById(R.id.button4_socio);
        btn5_socio = findViewById(R.id.button5_socio);
        btn6_socio = findViewById(R.id.button6_socio);
        btn7_socio = findViewById(R.id.button7_socio);
        btn1_socio_ok = findViewById(R.id.button1_socio_ok);
        btn2_socio_ok = findViewById(R.id.button2_socio_ok);
        btn3_socio_ok = findViewById(R.id.button3_socio_ok);
        btn4_socio_ok = findViewById(R.id.button4_socio_ok);
        btn5_socio_ok = findViewById(R.id.button5_socio_ok);
        btn6_socio_ok = findViewById(R.id.button6_socio_ok);
        btn7_socio_ok = findViewById(R.id.button7_socio_ok);
        //Vinculacion de los textos head
        btn_hd1 = findViewById(R.id.txt_btn1_head);
        btn_hd2 = findViewById(R.id.txt_btn2_head);
        btn_hd3 = findViewById(R.id.txt_btn3_head);
        btn_hd4 = findViewById(R.id.txt_btn4_head);
        btn_hd5 = findViewById(R.id.txt_btn5_head);
        btn_hd6 = findViewById(R.id.txt_btn6_head);
        btn_hd7 = findViewById(R.id.txt_btn7_head);
        btn_hd1_ok = findViewById(R.id.txt_btn1_head_ok);
        btn_hd2_ok = findViewById(R.id.txt_btn2_head_ok);
        btn_hd3_ok = findViewById(R.id.txt_btn3_head_ok);
        btn_hd4_ok = findViewById(R.id.txt_btn4_head_ok);
        btn_hd5_ok = findViewById(R.id.txt_btn5_head_ok);
        btn_hd6_ok = findViewById(R.id.txt_btn6_head_ok);
        btn_hd7_ok = findViewById(R.id.txt_btn7_head_ok);
        //Vinculacion para los text body
        btn_bd1 = findViewById(R.id.body_btn1);
        btn_bd2 = findViewById(R.id.body_btn2);
        btn_bd3 = findViewById(R.id.body_btn3);
        btn_bd4 = findViewById(R.id.body_btn4);
        btn_bd5 = findViewById(R.id.body_btn5);
        btn_bd6 = findViewById(R.id.body_btn6);
        btn_bd7 = findViewById(R.id.body_btn7);
        btn_bd1_ok = findViewById(R.id.body_btn1_ok);
        btn_bd2_ok = findViewById(R.id.body_btn2_ok);
        btn_bd3_ok = findViewById(R.id.body_btn3_ok);
        btn_bd4_ok = findViewById(R.id.body_btn4_ok);
        btn_bd5_ok = findViewById(R.id.body_btn5_ok);
        btn_bd6_ok = findViewById(R.id.body_btn6_ok);
        btn_bd7_ok = findViewById(R.id.body_btn7_ok);

        // Text View de prueba para corroborar la captura de datos
        tst_lic = findViewById(R.id.test_viglicen);
        tst_fabricante = findViewById(R.id.test_fabricantes);
        tst_modelo = findViewById(R.id.test_modelos);
        tst_anio = findViewById(R.id.test_anio);
        tst_matricula = findViewById(R.id.test_matricula);
        vig_tarjeta = findViewById(R.id.test_vigtarjeta);
        vig_poliza = findViewById(R.id.test_vig_poliza);
        vig_tarjeton = findViewById(R.id.test_vig_tarjeton);

        tst_lic.setText("Vigencia Licencia:"+cadenas_documentos.vigLicencia);
        tst_fabricante.setText("fabricante:"+cadenas_documentos.fabricante);
        tst_modelo.setText("modelo:"+cadenas_documentos.modelo);
        tst_anio.setText("Año:"+cadenas_documentos.anio);
        tst_matricula.setText("Matricula:"+cadenas_documentos.matricula);
        vig_tarjeta.setText("Vigencia Tarjeta:"+cadenas_documentos.vigTarjeta);
        vig_poliza.setText("Vigenia Poliza: "+cadenas_documentos.vigPoliza);
        vig_tarjeton.setText("Vigencia Tarjeton:"+cadenas_documentos.vigTarjeton);

        if (ContextCompat.checkSelfPermission(MainSocioDocumentos.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainSocioDocumentos.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainSocioDocumentos.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);}


        //Condicionales para ver si ya se subio algun documento
        if(!cadenas_documentos.check_terminos1){
            btn1_socio.setVisibility(View.VISIBLE);
            btn_hd1.setVisibility(View.VISIBLE);
            btn_bd1.setVisibility(View.VISIBLE);
            fwd1.setVisibility(View.VISIBLE);
        }else{
            btn1_socio.setVisibility(View.GONE);
            btn_hd1.setVisibility(View.GONE);
            btn_bd1.setVisibility(View.GONE);
            fwd1.setVisibility(View.GONE);
            btn1_socio_ok.setVisibility(View.VISIBLE);
            btn_hd1_ok.setVisibility(View.VISIBLE);
            btn_bd1_ok.setVisibility(View.VISIBLE);
            ok_1.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_ine1){
            btn2_socio.setVisibility(View.VISIBLE);
            btn_hd2.setVisibility(View.VISIBLE);
            btn_bd2.setVisibility(View.VISIBLE);
            fwd2.setVisibility(View.VISIBLE);
            btn2_socio_ok.setVisibility(View.GONE);
            btn_hd2_ok.setVisibility(View.GONE);
            btn_bd2_ok.setVisibility(View.GONE);
            ok_2.setVisibility(View.GONE);
        }else{
            btn2_socio.setVisibility(View.GONE);
            btn_hd2.setVisibility(View.GONE);
            btn_bd2.setVisibility(View.GONE);
            fwd2.setVisibility(View.GONE);
            btn2_socio_ok.setVisibility(View.VISIBLE);
            btn_hd2_ok.setVisibility(View.VISIBLE);
            btn_bd2_ok.setVisibility(View.VISIBLE);
            ok_2.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_licencia1){
            btn3_socio.setVisibility(View.VISIBLE);
            btn_hd3.setVisibility(View.VISIBLE);
            btn_bd3.setVisibility(View.VISIBLE);
            fwd3.setVisibility(View.VISIBLE);
            btn3_socio_ok.setVisibility(View.GONE);
            btn_hd3_ok.setVisibility(View.GONE);
            btn_bd3_ok.setVisibility(View.GONE);
            ok_3.setVisibility(View.GONE);
        }else{
            btn3_socio.setVisibility(View.GONE);
            btn_hd3.setVisibility(View.GONE);
            btn_bd3.setVisibility(View.GONE);
            fwd3.setVisibility(View.GONE);
            btn3_socio_ok.setVisibility(View.VISIBLE);
            btn_hd3_ok.setVisibility(View.VISIBLE);
            btn_bd3_ok.setVisibility(View.VISIBLE);
            ok_3.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_caracteristicas1){
            btn4_socio.setVisibility(View.VISIBLE);
            btn_hd4.setVisibility(View.VISIBLE);
            btn_bd4.setVisibility(View.VISIBLE);
            fwd4.setVisibility(View.VISIBLE);
            btn4_socio_ok.setVisibility(View.GONE);
            btn_hd4_ok.setVisibility(View.GONE);
            btn_bd4_ok.setVisibility(View.GONE);
            ok_4.setVisibility(View.GONE);
        }else{
            btn4_socio.setVisibility(View.GONE);
            btn_hd4.setVisibility(View.GONE);
            btn_bd4.setVisibility(View.GONE);
            fwd4.setVisibility(View.GONE);
            btn4_socio_ok.setVisibility(View.VISIBLE);
            btn_hd4_ok.setVisibility(View.VISIBLE);
            btn_bd4_ok.setVisibility(View.VISIBLE);
            ok_4.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_tarjeta1){
            btn5_socio.setVisibility(View.VISIBLE);
            btn_hd5.setVisibility(View.VISIBLE);
            btn_bd5.setVisibility(View.VISIBLE);
            fwd5.setVisibility(View.VISIBLE);
            btn5_socio_ok.setVisibility(View.GONE);
            btn_hd5_ok.setVisibility(View.GONE);
            btn_bd5_ok.setVisibility(View.GONE);
            ok_5.setVisibility(View.GONE);
        }else{
            btn5_socio.setVisibility(View.GONE);
            btn_hd5.setVisibility(View.GONE);
            btn_bd5.setVisibility(View.GONE);
            fwd5.setVisibility(View.GONE);
            btn5_socio_ok.setVisibility(View.VISIBLE);
            btn_hd5_ok.setVisibility(View.VISIBLE);
            btn_bd5_ok.setVisibility(View.VISIBLE);
            ok_5.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_poliza1){
            btn6_socio.setVisibility(View.VISIBLE);
            btn_hd6.setVisibility(View.VISIBLE);
            btn_bd6.setVisibility(View.VISIBLE);
            fwd6.setVisibility(View.VISIBLE);
            btn6_socio_ok.setVisibility(View.GONE);
            btn_hd6_ok.setVisibility(View.GONE);
            btn_bd6_ok.setVisibility(View.GONE);
            ok_6.setVisibility(View.GONE);
        }else{
            btn6_socio.setVisibility(View.GONE);
            btn_hd6.setVisibility(View.GONE);
            btn_bd6.setVisibility(View.GONE);
            fwd6.setVisibility(View.GONE);
            btn6_socio_ok.setVisibility(View.VISIBLE);
            btn_hd6_ok.setVisibility(View.VISIBLE);
            btn_bd6_ok.setVisibility(View.VISIBLE);
            ok_6.setVisibility(View.VISIBLE);
        }

        if(!cadenas_documentos.check_tarjeton1){
            btn7_socio.setVisibility(View.VISIBLE);
            btn_hd7.setVisibility(View.VISIBLE);
            btn_bd7.setVisibility(View.VISIBLE);
            fwd7.setVisibility(View.VISIBLE);
            btn7_socio_ok.setVisibility(View.GONE);
            btn_hd7_ok.setVisibility(View.GONE);
            btn_bd7_ok.setVisibility(View.GONE);
            ok_7.setVisibility(View.GONE);
        }else{
            btn7_socio.setVisibility(View.GONE);
            btn_hd7.setVisibility(View.GONE);
            btn_bd7.setVisibility(View.GONE);
            fwd7.setVisibility(View.GONE);
            btn7_socio_ok.setVisibility(View.VISIBLE);
            btn_hd7_ok.setVisibility(View.VISIBLE);
            btn_bd7_ok.setVisibility(View.VISIBLE);
            ok_7.setVisibility(View.VISIBLE);
        }

        btn_regreso_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainSocioDocumentos.this, MainRolConductor.class);
                startActivity(main_rol_conductor);
                finish();
            }
        });

        if(cadenas_documentos.check_ine1 & cadenas_documentos.check_licencia1 & cadenas_documentos.check_caracteristicas1 & cadenas_documentos.check_tarjeta1 & cadenas_documentos.check_poliza1 & cadenas_documentos.check_tarjeton1){
            
        }

    }
    public void terminos_socio(View view)
    {
        Intent main_terminos_socio = new Intent(MainSocioDocumentos.this,MainTerminosYCondiciones.class);
        main_terminos_socio.putExtra("rol",rol);
        startActivity(main_terminos_socio);
        finish();
    }
    public void ine_socio(View view)
    {
        Intent main_ine_socio = new Intent(MainSocioDocumentos.this,MainCapturaIne.class);
        main_ine_socio.putExtra("rol",rol);
        startActivity(main_ine_socio);
        finish();
    }
    public void licencia_socio(View view)
    {
        Intent main_licencia_socio = new Intent(MainSocioDocumentos.this, MainCapturaLicencia.class);
        main_licencia_socio.putExtra("rol",rol);
        startActivity(main_licencia_socio);
        finish();

    }
    public void caracterisitcas_socio(View view)
    {
        Intent main_caracteristicas_socio = new Intent(MainSocioDocumentos.this, MainCapturaCaracteristicas.class);
        main_caracteristicas_socio.putExtra("rol",rol);
        startActivity(main_caracteristicas_socio);
        finish();
    }
    public void tarjeta_socio(View view)
    {
        Intent main_tarjeta_socio = new Intent(MainSocioDocumentos.this, MainCapturaTarjetaCirculacion.class);
        main_tarjeta_socio.putExtra("rol",rol);
        startActivity(main_tarjeta_socio);
        finish();
    }
    public void poliza_socio(View view)
    {
        Intent main_poliza_socio = new Intent(MainSocioDocumentos.this, MainCapturaPoliza.class);
        main_poliza_socio.putExtra("rol",rol);
        startActivity(main_poliza_socio);
        finish();
    }
    public void tarjeton_socio(View view)
    {
        Intent main_tarjeton_socio = new Intent(MainSocioDocumentos.this, MainCapturaTarjeton.class);
        main_tarjeton_socio.putExtra("rol",rol);
        startActivity(main_tarjeton_socio);
        finish();
    }

}