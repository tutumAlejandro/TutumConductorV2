package com.example.tutumconductorv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class activity_problema_tecnico extends AppCompatActivity {

    private TextInputLayout title_report,body_report;
    private Button btn_acept,btn_back_report;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problema_tecnico);

        title_report = findViewById(R.id.input_title_report);
        body_report = findViewById(R.id.input_body_report);
        btn_acept = findViewById(R.id.btn_aceptar_reporte_tecnico);
        btn_back_report = findViewById(R.id.editReporte_backbutton);

        btn_acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check_title(title_report.getEditText().getText().toString()) | !check_body(body_report.getEditText().getText().toString())){

                }else {
                    SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
                    sendReportTechical(title_report.getEditText().getText().toString(), body_report.getEditText().getText().toString(),preferences.getString("api_token",""));
                }
            }
        });
        btn_back_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_reportes = new Intent(activity_problema_tecnico.this, ReportarProblemaTecnico.class);
                startActivity(main_reportes);
                finish();
            }
        });

    }
    private boolean check_title(String title){
        if(title.isEmpty()){
            title_report.setErrorEnabled(true);
            title_report.setError("Campo Requerido");
            return false;
        }else {
            title_report.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_body(String body){
        if(body.isEmpty()){
            body_report.setErrorEnabled(true);
            body_report.setError("Campo Requerido");
            return false;
        }else {
            body_report.setErrorEnabled(false);
            return true;
        }
    }

    private void sendReportTechical(String title, String body,String api_token){
        String url = "https://www.tutumapps.com/api/driver/newReport";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("email",email);
            jsonObject.put("title", title);
            jsonObject.put("type","tecnico");
            jsonObject.put("description",body);
            jsonObject.put("api_token",api_token);
            final String requestBody = jsonObject.toString();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //test_check.setText(response.getString("success"));
                        if(response.getString("success").matches("true"))
                        {
                            Log.e("Respuesta Registro","Exito!!!: "+response);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent main_inicio = new Intent(activity_problema_tecnico.this, Inicio.class);
                                    startActivity(main_inicio);
                                    finish();
                                }
                            },1000);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(activity_problema_tecnico.this,R.style.DialogTheme);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#088d30'> <b> Reporte Tecnico </b> </font>"));
                            registro_exitoso.setMessage(Html.fromHtml("<font color='#088d30'> <b>Hemos Recibido tu reporte, dentro de poco nos responderemos tu reporte</b> </font>"));
                            registro_exitoso.show();
                        }else
                        {
                            Log.e("Respuesta Registro","ERROR!!!: "+response);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            },1000);
                            AlertDialog.Builder registro_exitoso = new AlertDialog.Builder(activity_problema_tecnico.this);
                            registro_exitoso.setTitle(Html.fromHtml("<font color='#FF0000'> <b>Correo Electrónico </b></font>"));
                            registro_exitoso.setMessage(Html.fromHtml("<font color='#FF0000'> <b>"+response.getString("msg")+"</b> </font>"));
                            registro_exitoso.show();
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
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Accept", "application/json");
                    return params;}
            };

            requestQueue.add(request);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}