package com.example.tutumconductorv2.RegistroConductor.CapturaDocumentosNuevoVehiculo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutumconductorv2.R;

public class MainNuevoVehiculoSocio extends AppCompatActivity {
    private Button caracteristicasNuevo_prev, polizaNuevo_prev, tarjetaNuevo_prev;
    private Button caracteristicasNuevo_err, polizaNuevo_err, tarjetaNuevo_err;
    private Button caracteristicasNuevo_ok, polizaNuevo_ok, tarjetaNuevo_ok;
    private TextView hd_btn1_prev, hd_btn2_prev, hd_btn3_prev, hd_btn1_er, hd_btn2_er, hd_btn3_er,hd_btn1_ok, hd_btn2_ok, hd_btn3_ok;
    private TextView bd_btn1_prev, bd_btn2_prev, bd_btn3_prev, bd_btn1_er, bd_btn2_er,bd_btn3_er, bd_btn1_ok, bd_btn2_ok, bd_btn3_ok;
    private TextView bd2_btn1_err, bd2_btn2_err, bd2_btn3_err;
    private ImageView fwd_btn1_nuevo,fwd_btn2_nuevo,fwd_btn3_nuevo;
    private ImageView err_btn1_nuevo,err_btn2_nuevo,err_btn3_nuevo;
    private ImageView ok_btn1_nuevo,ok_btn2_nuevo,ok_btn3_nuevo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nuevo_vehiculo_socio);

        //Botones antes de subir un documento
        caracteristicasNuevo_prev = findViewById(R.id.button1_vehiculo_nuevo);
        polizaNuevo_prev = findViewById(R.id.button2_vehiculo_nuevo);
        tarjetaNuevo_prev = findViewById(R.id.button3_vehiculo_nuevo);
        hd_btn1_prev = findViewById(R.id.txt_btn1_head_nuevo_vehiculo);
        hd_btn2_prev = findViewById(R.id.txt_btn2_head_nuevo_vehiculo);
        hd_btn3_prev = findViewById(R.id.txt_btn3_head_nuevo_vehiculo);
        bd_btn1_prev = findViewById(R.id.body_btn1_nuevo_vehiculo);
        bd_btn2_prev = findViewById(R.id.body_btn2_nuevo_vehiculo);
        bd_btn3_prev = findViewById(R.id.body_btn3_nuevo_vehiculo);
        fwd_btn1_nuevo = findViewById(R.id.fwd1_nuevo_vehiculo);
        fwd_btn2_nuevo = findViewById(R.id.fwd2_nuevo_vehiculo);
        fwd_btn3_nuevo = findViewById(R.id.fwd3_nuevo_vehiculo);

        //Botones para mostrar el error en un documento de un nuevo vehiculo

        caracteristicasNuevo_err = findViewById(R.id.button1_vehiculo_nuevo_error);
        polizaNuevo_err = findViewById(R.id.button2_vehiculo_nuevo_error);
        tarjetaNuevo_err = findViewById(R.id.button3_vehiculo_nuevo_error);
        hd_btn1_er = findViewById(R.id.txt_btn1_head_nuevo_vehiculo_error);
        hd_btn2_er = findViewById(R.id.txt_btn2_head_nuevo_vehiculo_error);
        hd_btn3_er = findViewById(R.id.txt_btn3_head_nuevo_vehiculo_error);
        bd_btn1_er = findViewById(R.id.body_btn1_nuevo_vehiculo_error);
        bd_btn2_er = findViewById(R.id.body_btn2_nuevo_vehiculo_error);
        bd_btn3_er = findViewById(R.id.body_btn3_nuevo_vehiculo_error);
        bd2_btn1_err = findViewById(R.id.body2_btn1_error_nuevo_vehiculo);
        bd2_btn2_err = findViewById(R.id.body2_btn2_nuevo_vehiculo_error);
        bd2_btn3_err = findViewById(R.id.body2_btn3_nuevo_vehiculo_error);
        err_btn1_nuevo = findViewById(R.id.fwd1_nuevo_vehiculo_error);
        err_btn2_nuevo = findViewById(R.id.fwd2_nuevo_vehiculo_error);
        err_btn3_nuevo = findViewById(R.id.fwd3_nuevo_vehiculo_error);

        //Botones para mostrar que el documento se a subido exitosamente

        caracteristicasNuevo_ok = findViewById(R.id.button1_vehiculo_nuevo_ok);
        polizaNuevo_ok = findViewById(R.id.button2_vehiculo_nuevo_ok);
        tarjetaNuevo_ok = findViewById(R.id.button3_vehiculo_nuevo_ok);
        hd_btn1_ok = findViewById(R.id.txt_btn1_head_nuevo_vehiculo_ok);
        hd_btn2_ok = findViewById(R.id.txt_btn2_head_nuevo_vehiculo_ok);
        hd_btn3_ok = findViewById(R.id.txt_btn3_head_nuevo_vehiculo_ok);
        bd_btn1_ok = findViewById(R.id.body_btn1_nuevo_vehiculo_ok);
        bd_btn2_ok = findViewById(R.id.body_btn2_nuevo_vehiculo_ok);
        bd_btn3_ok = findViewById(R.id.body_btn3_nuevo_vehiculo_ok);
        ok_btn1_nuevo = findViewById(R.id.fwd1_nuevo_vehiculo_ok);
        ok_btn2_nuevo = findViewById(R.id.fwd2_nuevo_vehiculo_ok);
        ok_btn3_nuevo = findViewById(R.id.fwd3_nuevo_vehiculo_ok);




    }
}
