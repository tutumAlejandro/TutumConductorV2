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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;
//import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaCaracteristicas;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaIne;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaLicencia;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaPoliza;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjetaCirculacion;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainCapturaTarjeton;
import com.example.tutumconductorv2.Registro.documentos_conductor.MainTerminosYCondiciones;

import org.json.JSONObject;

public class MainSocioDocumentos extends AppCompatActivity {

    private ImageView btn_regreso_socio,fwd1,fwd2,fwd3,fwd4,fwd5,fwd6,fwd7,ok_1,ok_2,ok_3,ok_4,ok_5,ok_6,ok_7,error_1,error_2,error_3,error_4,error_5,error_6,error_7;
    private Button btn1_socio,btn2_socio,btn3_socio,btn4_socio,btn5_socio,btn6_socio,btn7_socio,
            btn1_socio_ok,btn2_socio_ok,btn3_socio_ok,btn4_socio_ok,btn5_socio_ok,btn6_socio_ok,btn7_socio_ok,
            btn1_socio_error,btn2_socio_error,btn3_socio_error,btn4_socio_error,btn5_socio_error,btn6_socio_error,btn7_socio_error;
    private TextView btn_hd1,btn_hd2,btn_hd3,btn_hd4,btn_hd5,btn_hd6,btn_hd7,btn_hd1_ok,btn_hd2_ok,btn_hd3_ok,btn_hd4_ok,btn_hd5_ok,
            btn_hd6_ok,btn_hd7_ok,btn_hd1_er,btn_hd2_er,btn_hd3_er,btn_hd4_er,btn_hd5_er,btn_hd6_er,btn_hd7_er;
    private TextView btn_bd1,btn_bd2,btn_bd3,btn_bd4,btn_bd5,btn_bd6,btn_bd7,btn_bd1_ok,btn_bd2_ok,btn_bd3_ok,btn_bd4_ok,btn_bd5_ok,
            btn_bd6_ok,btn_bd7_ok,btn_bd1_er,btn_bd2_er,btn_bd3_er,btn_bd4_er,btn_bd5_er, btn_bd6_er,btn_bd7_er,btn1_bd1_er,btn2_bd2_er,btn3_bd3_er,btn4_bd4_er,btn5_bd5_er, btn6_bd6_er,btn7_bd7_er;
    private static String rol= "Socio";
    private String url_timeline="https://www.tutumapps.com/api/driver/registryTimelineStatus";

    private String msg1,msg2,msg3,msg4,msg5,msg6,msg7;

    @Override
    //txt_btn1_head
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_socio_documentos);
        //Vinculacion de las imagenes

        SharedPreferences preferncias_socio = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        msg1= preferncias_socio.getString("error1","");
        msg2= preferncias_socio.getString("error2","");
        msg3= preferncias_socio.getString("error3","");
        msg4= preferncias_socio.getString("error4","");
        msg5= preferncias_socio.getString("error5","");
        msg6= preferncias_socio.getString("error6","");
        msg7= preferncias_socio.getString("error7","");

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
        error_1 = findViewById(R.id.fwd_socio_1_error);
        error_2 = findViewById(R.id.fwd_socio_2_error);
        error_3 = findViewById(R.id.fwd_socio_3_error);
        error_4 = findViewById(R.id.fwd_socio_4_error);
        error_5 = findViewById(R.id.fwd_socio_5_error);
        error_6 = findViewById(R.id.fwd_socio_6_error);
        error_7 = findViewById(R.id.fwd_socio_7_error);
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
        btn1_socio_error = findViewById(R.id.button1_socio_error);
        btn2_socio_error = findViewById(R.id.button2_socio_error);
        btn3_socio_error = findViewById(R.id.button3_socio_error);
        btn4_socio_error = findViewById(R.id.button4_socio_error);
        btn5_socio_error = findViewById(R.id.button5_socio_error);
        btn6_socio_error = findViewById(R.id.button6_socio_error);
        btn7_socio_error = findViewById(R.id.button7_socio_error);
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
        btn_hd1_er = findViewById(R.id.txt_btn1_head_error_socio);
        btn_hd2_er = findViewById(R.id.txt_btn2_head_error_socio);
        btn_hd3_er = findViewById(R.id.txt_btn3_head_error_socio);
        btn_hd4_er = findViewById(R.id.txt_btn4_head_error_socio);
        btn_hd5_er = findViewById(R.id.txt_btn5_head_error_socio);
        btn_hd6_er = findViewById(R.id.txt_btn6_head_error_socio);
        btn_hd7_er = findViewById(R.id.txt_btn7_head_error_socio);
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
        btn_bd1_er = findViewById(R.id.body_btn1_error_socio);
        btn_bd2_er = findViewById(R.id.body_btn2_error_socio);
        btn_bd3_er = findViewById(R.id.body_btn3_error_socio);
        btn_bd4_er = findViewById(R.id.body_btn4_error_socio);
        btn_bd5_er = findViewById(R.id.body_btn5_error_socio);
        btn_bd6_er = findViewById(R.id.body_btn6_error_socio);
        btn_bd7_er = findViewById(R.id.body_btn7_error_socio);

        btn1_bd1_er = findViewById(R.id.body2_btn1_error_socio);
        btn2_bd2_er = findViewById(R.id.body2_btn2_error_socio);
        btn3_bd3_er = findViewById(R.id.body2_btn3_error_socio);
        btn4_bd4_er = findViewById(R.id.body2_btn4_error_socio);
        btn5_bd5_er = findViewById(R.id.body2_btn5_error_socio);
        btn6_bd6_er = findViewById(R.id.body2_btn6_error_socio);
        btn7_bd7_er = findViewById(R.id.body2_btn7_error_socio);

        String term1 = preferncias_socio.getString("terminos1","0");
        String ine1 = preferncias_socio.getString("ine1","0");
        String licencia1 = preferncias_socio.getString("licencia1","0");
        String caracteristicas1 = preferncias_socio.getString("caracteristicas1","0");
        String tarjeta1 = preferncias_socio.getString("tarjeta1","0");
        String poliza1 = preferncias_socio.getString("poliza1","0");
        String tarjeton1 = preferncias_socio.getString("tarjeton1","0");

        //Condicionales para ver si ya se subio algun documento
        if(term1.matches("0")){
            btn1_socio.setVisibility(View.VISIBLE);
            btn_hd1.setVisibility(View.VISIBLE);
            btn_bd1.setVisibility(View.VISIBLE);
            fwd1.setVisibility(View.VISIBLE);
            btn1_socio_ok.setVisibility(View.GONE);
            btn_hd1_ok.setVisibility(View.GONE);
            btn_bd1_ok.setVisibility(View.GONE);
            ok_1.setVisibility(View.GONE);
            btn1_socio_error.setVisibility(View.GONE);
            btn_hd1_er.setVisibility(View.GONE);
            btn_bd1_er.setVisibility(View.GONE);
            error_1.setVisibility(View.GONE);
            btn1_bd1_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else if(term1.matches("1")){
            btn1_socio.setVisibility(View.GONE);
            btn_hd1.setVisibility(View.GONE);
            btn_bd1.setVisibility(View.GONE);
            fwd1.setVisibility(View.GONE);
            btn1_socio_ok.setVisibility(View.VISIBLE);
            btn_hd1_ok.setVisibility(View.VISIBLE);
            btn_bd1_ok.setVisibility(View.VISIBLE);
            ok_1.setVisibility(View.VISIBLE);
            btn1_socio_error.setVisibility(View.GONE);
            btn_hd1_er.setVisibility(View.GONE);
            btn_bd1_er.setVisibility(View.GONE);
            error_1.setVisibility(View.GONE);
            btn1_bd1_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else{
            btn1_socio.setVisibility(View.GONE);
            btn_hd1.setVisibility(View.GONE);
            btn_bd1.setVisibility(View.GONE);
            fwd1.setVisibility(View.GONE);
            btn1_socio_ok.setVisibility(View.GONE);
            btn_hd1_ok.setVisibility(View.GONE);
            btn_bd1_ok.setVisibility(View.GONE);
            ok_1.setVisibility(View.GONE);
            btn1_socio_error.setVisibility(View.VISIBLE);
            btn_hd1_er.setVisibility(View.VISIBLE);
            btn_bd1_er.setVisibility(View.VISIBLE);
            error_1.setVisibility(View.VISIBLE);
            btn1_bd1_er.setVisibility(View.GONE);
            btn1_bd1_er.setText(msg1);
            btn_regreso_socio.setVisibility(View.GONE);
        }

        if(ine1.matches("0")){
            btn2_socio.setVisibility(View.VISIBLE);
            btn_hd2.setVisibility(View.VISIBLE);
            btn_bd2.setVisibility(View.VISIBLE);
            fwd2.setVisibility(View.VISIBLE);
            btn2_socio_ok.setVisibility(View.GONE);
            btn_hd2_ok.setVisibility(View.GONE);
            btn_bd2_ok.setVisibility(View.GONE);
            ok_2.setVisibility(View.GONE);
            btn2_socio_error.setVisibility(View.GONE);
            btn_hd2_er.setVisibility(View.GONE);
            btn_bd2_er.setVisibility(View.GONE);
            error_2.setVisibility(View.GONE);
            btn2_bd2_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else if(ine1.matches("1")){
            btn2_socio.setVisibility(View.GONE);
            btn_hd2.setVisibility(View.GONE);
            btn_bd2.setVisibility(View.GONE);
            fwd2.setVisibility(View.GONE);
            btn2_socio_ok.setVisibility(View.VISIBLE);
            btn_hd2_ok.setVisibility(View.VISIBLE);
            btn_bd2_ok.setVisibility(View.VISIBLE);
            ok_2.setVisibility(View.VISIBLE);
            btn1_socio_error.setVisibility(View.GONE);
            btn_hd2_er.setVisibility(View.GONE);
            btn_bd2_er.setVisibility(View.GONE);
            error_2.setVisibility(View.GONE);
            btn2_bd2_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else {
            btn2_socio.setVisibility(View.GONE);
            btn_hd2.setVisibility(View.GONE);
            btn_bd2.setVisibility(View.GONE);
            fwd2.setVisibility(View.GONE);
            btn2_socio_ok.setVisibility(View.GONE);
            btn_hd2_ok.setVisibility(View.GONE);
            btn_bd2_ok.setVisibility(View.GONE);
            ok_2.setVisibility(View.GONE);
            btn2_socio_error.setVisibility(View.VISIBLE);
            btn_hd2_er.setVisibility(View.VISIBLE);
            btn_bd2_er.setVisibility(View.VISIBLE);
            error_2.setVisibility(View.VISIBLE);
            btn2_bd2_er.setVisibility(View.VISIBLE);
            btn2_bd2_er.setText(msg2);
            btn_regreso_socio.setVisibility(View.GONE);
        }

        if(licencia1.matches("0")){
            btn3_socio.setVisibility(View.VISIBLE);
            btn_hd3.setVisibility(View.VISIBLE);
            btn_bd3.setVisibility(View.VISIBLE);
            fwd3.setVisibility(View.VISIBLE);
            btn3_socio_ok.setVisibility(View.GONE);
            btn_hd3_ok.setVisibility(View.GONE);
            btn_bd3_ok.setVisibility(View.GONE);
            ok_3.setVisibility(View.GONE);
            btn3_socio_error.setVisibility(View.GONE);
            btn_hd3_er.setVisibility(View.GONE);
            btn_bd3_er.setVisibility(View.GONE);
            error_3.setVisibility(View.GONE);
            btn3_bd3_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else if(licencia1.matches("1")){
            btn3_socio.setVisibility(View.GONE);
            btn_hd3.setVisibility(View.GONE);
            btn_bd3.setVisibility(View.GONE);
            fwd3.setVisibility(View.GONE);
            btn3_socio_ok.setVisibility(View.VISIBLE);
            btn_hd3_ok.setVisibility(View.VISIBLE);
            btn_bd3_ok.setVisibility(View.VISIBLE);
            ok_3.setVisibility(View.VISIBLE);
            btn3_socio_error.setVisibility(View.GONE);
            btn_hd3_er.setVisibility(View.GONE);
            btn_bd3_er.setVisibility(View.GONE);
            error_3.setVisibility(View.GONE);
            btn3_bd3_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else{
            btn3_socio.setVisibility(View.GONE);
            btn_hd3.setVisibility(View.GONE);
            btn_bd3.setVisibility(View.GONE);
            fwd3.setVisibility(View.GONE);
            btn3_socio_ok.setVisibility(View.GONE);
            btn_hd3_ok.setVisibility(View.GONE);
            btn_bd3_ok.setVisibility(View.GONE);
            ok_3.setVisibility(View.GONE);
            btn3_socio_error.setVisibility(View.VISIBLE);
            btn_hd3_er.setVisibility(View.VISIBLE);
            btn_bd3_er.setVisibility(View.VISIBLE);
            error_3.setVisibility(View.VISIBLE);
            btn3_bd3_er.setVisibility(View.VISIBLE);
            btn3_bd3_er.setText(msg3);
            btn_regreso_socio.setVisibility(View.GONE);
        }

        if(caracteristicas1.matches("0")){
            btn4_socio.setVisibility(View.VISIBLE);
            btn_hd4.setVisibility(View.VISIBLE);
            btn_bd4.setVisibility(View.VISIBLE);
            fwd4.setVisibility(View.VISIBLE);
            btn4_socio_ok.setVisibility(View.GONE);
            btn_hd4_ok.setVisibility(View.GONE);
            btn_bd4_ok.setVisibility(View.GONE);
            ok_4.setVisibility(View.GONE);
            btn4_socio_error.setVisibility(View.GONE);
            btn_hd4_er.setVisibility(View.GONE);
            btn_bd4_er.setVisibility(View.GONE);
            error_4.setVisibility(View.GONE);
            btn4_bd4_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else if(caracteristicas1.matches("1")){
            btn4_socio.setVisibility(View.GONE);
            btn_hd4.setVisibility(View.GONE);
            btn_bd4.setVisibility(View.GONE);
            fwd4.setVisibility(View.GONE);
            btn4_socio_ok.setVisibility(View.VISIBLE);
            btn_hd4_ok.setVisibility(View.VISIBLE);
            btn_bd4_ok.setVisibility(View.VISIBLE);
            ok_4.setVisibility(View.VISIBLE);
            btn4_socio_error.setVisibility(View.GONE);
            btn_hd4_er.setVisibility(View.GONE);
            btn_bd4_er.setVisibility(View.GONE);
            error_4.setVisibility(View.GONE);
            btn4_bd4_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else{
            btn4_socio.setVisibility(View.GONE);
            btn_hd4.setVisibility(View.GONE);
            btn_bd4.setVisibility(View.GONE);
            fwd4.setVisibility(View.GONE);
            btn4_socio_ok.setVisibility(View.GONE);
            btn_hd4_ok.setVisibility(View.GONE);
            btn_bd4_ok.setVisibility(View.GONE);
            ok_4.setVisibility(View.GONE);
            btn4_socio_error.setVisibility(View.VISIBLE);
            btn_hd4_er.setVisibility(View.VISIBLE);
            btn_bd4_er.setVisibility(View.VISIBLE);
            error_4.setVisibility(View.VISIBLE);
            btn4_bd4_er.setVisibility(View.VISIBLE);
            btn4_bd4_er.setText(msg4);
            btn_regreso_socio.setVisibility(View.GONE);
        }

        if(tarjeta1.matches("0")){
            btn5_socio.setVisibility(View.VISIBLE);
            btn_hd5.setVisibility(View.VISIBLE);
            btn_bd5.setVisibility(View.VISIBLE);
            fwd5.setVisibility(View.VISIBLE);
            btn5_socio_ok.setVisibility(View.GONE);
            btn_hd5_ok.setVisibility(View.GONE);
            btn_bd5_ok.setVisibility(View.GONE);
            ok_5.setVisibility(View.GONE);
            btn5_socio_error.setVisibility(View.GONE);
            btn_hd5_er.setVisibility(View.GONE);
            btn_bd5_er.setVisibility(View.GONE);
            error_5.setVisibility(View.GONE);
            btn5_bd5_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else if(tarjeta1.matches("1")){
            btn5_socio.setVisibility(View.GONE);
            btn_hd5.setVisibility(View.GONE);
            btn_bd5.setVisibility(View.GONE);
            fwd5.setVisibility(View.GONE);
            btn5_socio_ok.setVisibility(View.VISIBLE);
            btn_hd5_ok.setVisibility(View.VISIBLE);
            btn_bd5_ok.setVisibility(View.VISIBLE);
            ok_5.setVisibility(View.VISIBLE);
            btn5_socio_error.setVisibility(View.GONE);
            btn_hd5_er.setVisibility(View.GONE);
            btn_bd5_er.setVisibility(View.GONE);
            error_5.setVisibility(View.GONE);
            btn5_bd5_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else{
            btn5_socio.setVisibility(View.GONE);
            btn_hd5.setVisibility(View.GONE);
            btn_bd5.setVisibility(View.GONE);
            fwd5.setVisibility(View.GONE);
            btn5_socio_ok.setVisibility(View.GONE);
            btn_hd5_ok.setVisibility(View.GONE);
            btn_bd5_ok.setVisibility(View.GONE);
            ok_5.setVisibility(View.GONE);
            btn5_socio_error.setVisibility(View.VISIBLE);
            btn_hd5_er.setVisibility(View.VISIBLE);
            btn_bd5_er.setVisibility(View.VISIBLE);
            error_5.setVisibility(View.VISIBLE);
            btn5_bd5_er.setVisibility(View.VISIBLE);
            btn5_bd5_er.setText(msg5);
            btn_regreso_socio.setVisibility(View.GONE);
        }

        if(poliza1.matches("0")){
            btn6_socio.setVisibility(View.VISIBLE);
            btn_hd6.setVisibility(View.VISIBLE);
            btn_bd6.setVisibility(View.VISIBLE);
            fwd6.setVisibility(View.VISIBLE);
            btn6_socio_ok.setVisibility(View.GONE);
            btn_hd6_ok.setVisibility(View.GONE);
            btn_bd6_ok.setVisibility(View.GONE);
            ok_6.setVisibility(View.GONE);
            btn6_socio_error.setVisibility(View.GONE);
            btn_hd6_er.setVisibility(View.GONE);
            btn_bd6_er.setVisibility(View.GONE);
            error_6.setVisibility(View.GONE);
            btn6_bd6_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else if(poliza1.matches("1")){
            btn6_socio.setVisibility(View.GONE);
            btn_hd6.setVisibility(View.GONE);
            btn_bd6.setVisibility(View.GONE);
            fwd6.setVisibility(View.GONE);
            btn6_socio_ok.setVisibility(View.VISIBLE);
            btn_hd6_ok.setVisibility(View.VISIBLE);
            btn_bd6_ok.setVisibility(View.VISIBLE);
            ok_6.setVisibility(View.VISIBLE);
            btn6_socio_error.setVisibility(View.GONE);
            btn_hd6_er.setVisibility(View.GONE);
            btn_bd6_er.setVisibility(View.GONE);
            error_6.setVisibility(View.GONE);
            btn6_bd6_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else{
            btn6_socio.setVisibility(View.GONE);
            btn_hd6.setVisibility(View.GONE);
            btn_bd6.setVisibility(View.GONE);
            fwd6.setVisibility(View.GONE);
            btn6_socio_ok.setVisibility(View.GONE);
            btn_hd6_ok.setVisibility(View.GONE);
            btn_bd6_ok.setVisibility(View.GONE);
            ok_6.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.GONE);
            btn6_socio_error.setVisibility(View.VISIBLE);
            btn_hd6_er.setVisibility(View.VISIBLE);
            btn_bd6_er.setVisibility(View.VISIBLE);
            error_6.setVisibility(View.VISIBLE);
            btn6_bd6_er.setVisibility(View.VISIBLE);
            btn6_bd6_er.setText(msg6);
        }

        if(tarjeton1.matches("0")){
            btn7_socio.setVisibility(View.VISIBLE);
            btn_hd7.setVisibility(View.VISIBLE);
            btn_bd7.setVisibility(View.VISIBLE);
            fwd7.setVisibility(View.VISIBLE);
            btn7_socio_ok.setVisibility(View.GONE);
            btn_hd7_ok.setVisibility(View.GONE);
            btn_bd7_ok.setVisibility(View.GONE);
            ok_7.setVisibility(View.GONE);
            btn7_socio_error.setVisibility(View.GONE);
            btn_hd7_er.setVisibility(View.GONE);
            btn_bd7_er.setVisibility(View.GONE);
            error_7.setVisibility(View.GONE);
            btn7_bd7_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else if(tarjeton1.matches("1")){
            btn7_socio.setVisibility(View.GONE);
            btn_hd7.setVisibility(View.GONE);
            btn_bd7.setVisibility(View.GONE);
            fwd7.setVisibility(View.GONE);
            btn7_socio_ok.setVisibility(View.VISIBLE);
            btn_hd7_ok.setVisibility(View.VISIBLE);
            btn_bd7_ok.setVisibility(View.VISIBLE);
            ok_7.setVisibility(View.VISIBLE);
            btn7_socio_error.setVisibility(View.GONE);
            btn_hd7_er.setVisibility(View.GONE);
            btn_bd7_er.setVisibility(View.GONE);
            error_7.setVisibility(View.GONE);
            btn7_bd7_er.setVisibility(View.GONE);
            btn_regreso_socio.setVisibility(View.VISIBLE);
        }else {
            btn7_socio.setVisibility(View.GONE);
            btn_hd7.setVisibility(View.GONE);
            btn_bd7.setVisibility(View.GONE);
            fwd7.setVisibility(View.GONE);
            btn7_socio_ok.setVisibility(View.GONE);
            btn_hd7_ok.setVisibility(View.GONE);
            btn_bd7_ok.setVisibility(View.GONE);
            ok_7.setVisibility(View.GONE);
            btn7_socio_error.setVisibility(View.VISIBLE);
            btn_hd7_er.setVisibility(View.VISIBLE);
            btn_bd7_er.setVisibility(View.VISIBLE);
            error_7.setVisibility(View.VISIBLE);
            btn7_bd7_er.setVisibility(View.VISIBLE);
            btn7_bd7_er.setText(msg7);
            btn_regreso_socio.setVisibility(View.GONE);
        }


        btn_regreso_socio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_rol_conductor = new Intent(MainSocioDocumentos.this, MainRolConductor.class);
                startActivity(main_rol_conductor);
                finish();
            }
        });

        if(preferncias_socio.getString("terminos1","0").matches("1") & preferncias_socio.getString("ine1","0").matches("1") & preferncias_socio.getString("licencia1","0").matches("1") & preferncias_socio.getString("caracteristicas1","0").matches("1") &
                preferncias_socio.getString("tarjeta1","0").matches("1") & preferncias_socio.getString("poliza1","0").matches("1") & preferncias_socio.getString("tarjeton1","0").matches("1")){

            Intent main_documentos_ok = new Intent(MainSocioDocumentos.this, MainDocumentosOk.class);
            startActivity(main_documentos_ok);
            finish();
        }
        realizarPost(url_timeline);

    }



    public void terminos_socio(View view)
    {
        Intent main_terminos_socio = new Intent(MainSocioDocumentos.this,MainTerminosYCondiciones.class);
        startActivity(main_terminos_socio);
        finish();
    }
    public void ine_socio(View view)
    {
        Intent main_ine_socio = new Intent(MainSocioDocumentos.this,MainCapturaIne.class);
        startActivity(main_ine_socio);
        finish();
    }
    public void licencia_socio(View view)
    {
        Intent main_licencia_socio = new Intent(MainSocioDocumentos.this, MainCapturaLicencia.class);
        startActivity(main_licencia_socio);
        finish();

    }
    public void caracterisitcas_socio(View view)
    {
        Intent main_caracteristicas_socio = new Intent(MainSocioDocumentos.this, MainCapturaCaracteristicas.class);
        startActivity(main_caracteristicas_socio);
        finish();
    }
    public void tarjeta_socio(View view)
    {
        Intent main_tarjeta_socio = new Intent(MainSocioDocumentos.this, MainCapturaTarjetaCirculacion.class);;
        startActivity(main_tarjeta_socio);
        finish();
    }
    public void poliza_socio(View view)
    {
        Intent main_poliza_socio = new Intent(MainSocioDocumentos.this, MainCapturaPoliza.class);
        startActivity(main_poliza_socio);
        finish();
    }
    public void tarjeton_socio(View view)
    {
        Intent main_tarjeton_socio = new Intent(MainSocioDocumentos.this, MainCapturaTarjeton.class);
        startActivity(main_tarjeton_socio);
        finish();
    }

    public void realizarPost(String url) {
        String tel="";
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        tel=preferences.getString("phone","");
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);


            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!!!! "+response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("My Tag","Error"+error);
                }
            });
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
