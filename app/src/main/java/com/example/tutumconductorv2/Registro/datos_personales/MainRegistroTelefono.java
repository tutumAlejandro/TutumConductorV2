package com.example.tutumconductorv2.Registro.datos_personales;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;

public class MainRegistroTelefono extends AppCompatActivity {

    private TextInputLayout telefono;
    private TextView test_nom, test_app, test_apm, test_email, test_pass,test_tel,test_check;
    private RequestQueue queue;
    private boolean res=false;
    private String res1="";
    private String tel="";
    private String url_registro="https://www.tutumapps.com/api/driver/registryDriver";
    private String url_timeline="https://www.tutumapps.com/api/driver/registryTimelineStatus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_telefono);

        telefono = findViewById(R.id.InputTelefono);

        queue = Volley.newRequestQueue(this);
        /*
        test_nom = findViewById(R.id.test_tel_nombre);
        test_app = findViewById(R.id.test_tel_app);
        test_apm = findViewById(R.id.test_tel_apm);
        test_email = findViewById(R.id.test_tel_email);
        test_pass = findViewById(R.id.test_tel_pass);
        test_tel = findViewById(R.id.test_tel_tel);
        test_check = findViewById(R.id.test_tel_check);

        test_nom.setText(cadenas_registro.nombres);
        test_app.setText(cadenas_registro.apeido_paterno);
        test_apm.setText(cadenas_registro.apeido_materno);
        test_email.setText(cadenas_registro.email);
        test_pass.setText(cadenas_registro.password);
         */
    }

    private boolean check_telefono(String num_telefono) {
        if (num_telefono.isEmpty()) {
            telefono.setErrorEnabled(true);
            telefono.setError("Campo Requerido");
            telefono.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        } else if (num_telefono.length() < 10) {
            telefono.setErrorEnabled(true);
            telefono.setError("Formato Invalido");
            telefono.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        } else {
            telefono.setErrorEnabled(false);
            return true;
        }
    }

    public void main_otp(View v) {
        tel = telefono.getEditText().getText().toString().trim();
        cadenas_registro.telefono= tel;
       if(!check_telefono(tel)){
            return;
       }else {
           if(!cadenas_registro.edit_phone){editPhoneNumber();realizarPost(url_timeline);}
           else {realizarPost(url_registro);realizarPost(url_timeline);}
       }
    }

    public void realizarPost(String url)  {

        String url1 = url;
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            jsonObject.put("name",cadenas_registro.nombres+" "+cadenas_registro.apeido_paterno+" "+cadenas_registro.apeido_materno);
            jsonObject.put("email",cadenas_registro.email);
            jsonObject.put("phone",cadenas_registro.telefono);
            jsonObject.put("password",cadenas_registro.password);
            jsonObject.put("status","ok");

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getString("success").matches("true"))
                        {
                            Log.d("My Tag","Exito!!!: "+response);
                            Intent main_popup = new Intent(MainRegistroTelefono.this, MainPopUpRegistro.class);
                            startActivity(main_popup);
                        }else
                        {
                            cadenas_registro.msg_fail= response.getString("msg");
                            Intent main_popup_fail = new Intent(MainRegistroTelefono.this, MainPopUpRegistroFail.class);
                            startActivity(main_popup_fail);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("My Tag","Error"+error);
                }
            });
            requestQueue.add(request);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
    public void editPhoneNumber(){

        String respuesta="";
        String url = "https://www.tutumapps.com/api/driver/updateRegistryPhone";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            jsonObject.put("email",cadenas_registro.email);
            jsonObject.put("phone",cadenas_registro.telefono);

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //test_check.setText(response.getString("success"));
                        if(response.getString("success").matches("true"))
                        {
                            Intent main_popup = new Intent(MainRegistroTelefono.this, MainPopUpRegistro.class);
                            startActivity(main_popup);
                        }else
                        {
                            cadenas_registro.msg_fail= response.getString("msg");
                            Intent main_popup_fail = new Intent(MainRegistroTelefono.this, MainPopUpRegistroFail.class);
                            startActivity(main_popup_fail);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("My Tag","Error"+error);
                }
            });
            requestQueue.add(request);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
