package com.example.tutumconductorv2.RegistroConductor.DatosPersonales;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.RegistroConductor.SeleccionRol.MainRolConductor;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainOTP extends AppCompatActivity {
    private TextInputLayout otp;
    private TextView Editar;
    private TextView Reenviar;
    private TextView num_tel;
    private Button ReOtp;

    public static String telefono="";
    private boolean isSucess;
    private final int INTERVALO = 5000;
    private long tiempoPrimerClick;
    private String CodigoOTP;
    com.hbb20.CountryCodePicker ccp;


    public MainOTP() {
    }
    // variables para almacenar los datos del registro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_o_t_p);

        otp = findViewById(R.id.InputOTP);
        Editar = findViewById(R.id.link_editar_num);
        Reenviar = findViewById(R.id.link_editar_num);
        num_tel = findViewById(R.id.num_telefono);
        ReOtp = findViewById(R.id.link_reenviar_otp);

        Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences_user = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
                SharedPreferences.Editor obj_editor = preferences_user.edit();
                obj_editor.putString("phone","");
                obj_editor.putBoolean("edit_phone",false);
                obj_editor.commit();
                Intent main_registro_telefono = new Intent(MainOTP.this, MainRegistroTelefono.class);
                startActivity(main_registro_telefono);
                finish();
            }
        });


        ReOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()){
                    Toast.makeText(MainOTP.this, "Espera unos segundos", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    solicitarOTP();
                }
                tiempoPrimerClick = System.currentTimeMillis();
            }
        });
    }
    private boolean check_otp(String otp_code)
    {

        if (otp_code.isEmpty()){
            otp.setErrorEnabled(true);
            otp.setError("Campo Obligatorio");
            return false;
        }
        else{
            try{
                Float.parseFloat(otp_code);
                if(otp_code.length() != 6){
                    otp.setErrorEnabled(true);
                    otp.setError("El codigo es solo de 6 digitos");
                    return false;
                }
                else {
                    otp.setErrorEnabled(false);
                    return true;
                }
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
                otp.setErrorEnabled(true);
                otp.setError("Solo se admiten numeros");
                return false;
            }
        }
    }

    public void editar(View v)
    {
        SharedPreferences preferences_user = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences_user.edit();
        obj_editor.putString("phone","");
        obj_editor.putBoolean("edit_phone",false);
        obj_editor.commit();
        Intent main_registro_telefono = new Intent(MainOTP.this, MainRegistroTelefono.class);
        startActivity(main_registro_telefono);
        finish();
    }
    public void main_rol(View v)
    {

        String otp_cd = otp.getEditText().getText().toString().trim();
        if(!check_otp(otp_cd))
        {
            return;
        }else
        {
            SharedPreferences preferences_user = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
            SharedPreferences.Editor obj_editor = preferences_user.edit();
            obj_editor.putInt("State",3);
            obj_editor.commit();
            Intent main_rol = new Intent(MainOTP.this, MainRolConductor.class);
            startActivity(main_rol);
            finish();
        }
    }




    public void btn_ver_otp(View v){

         CodigoOTP = otp.getEditText().getText().toString().trim();

        if(check_otp(CodigoOTP)){
            chekOTP(CodigoOTP);
        }

    }


    private void solicitarOTP(){
        SharedPreferences sss = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        String ccc = sss.getString("phone","");
        String url = "https://www.tutumapps.com/api/driver/otp";

        try{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JSONObject jsonObject = new JSONObject();

            jsonObject.put("phone",  ccc );

            final String requestBody = jsonObject.toString();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("TAG", "Success! :D" + " " + response);
                    try {
                        isSucess = response.getBoolean("success");
                        if(isSucess){
                            Toast.makeText(MainOTP.this, "OTP", Toast.LENGTH_SHORT).show();
                            SharedPreferences preferences_user = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
                            SharedPreferences.Editor obj_editor = preferences_user.edit();
                            obj_editor.putInt("State",3);
                            obj_editor.commit();
                            return;
                        }
                        else{
                            Toast.makeText(MainOTP.this, "Hubo un error con el codigo OTP", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("TAG", "Error: " + error);
                }
            });
            requestQueue.add(jsonObjectRequest);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void chekOTP(String otp){
        SharedPreferences sss = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        String ccc = sss.getString("phone","");
        String url = "https://www.tutumapps.com/api/driver/checkotp";

        try{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JSONObject jsonObject = new JSONObject();

            jsonObject.put("phone", ccc  );
            jsonObject.put("otp", otp);

            final String requestBody = jsonObject.toString();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("TAG", "Success! :D" + " " + response);
                    try {
                        isSucess = response.getBoolean("success");
                        if(isSucess){
                            Toast.makeText(MainOTP.this, "Codigo Correcto", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainOTP.this, MainRolConductor.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainOTP.this, "Codigo Incorrecto", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("TAG", "Error: " + error);
                }
            });
            requestQueue.add(jsonObjectRequest);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }




}