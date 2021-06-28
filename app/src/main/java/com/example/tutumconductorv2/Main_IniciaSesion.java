package com.example.tutumconductorv2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class Main_IniciaSesion extends AppCompatActivity {

    private TextInputLayout email;
    private TextInputLayout pass;

    private TextView recupera;
    private String correo;
    private String contrasena;
    String _url_recupera = "https://tutumapps.com/password/reset";
    private boolean isSucess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicia_sesion);


        email = findViewById(R.id.Input_Email);
        pass = findViewById(R.id.Input_Contraseña);
        recupera = findViewById(R.id.recupera_contraseña);
       /* String email_usuario = "usuario@gmail.com";
        String pass_usuario = "123456789";*/



        recupera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Uri _link = Uri.parse(_url_recupera);
                Intent pag_recupera = new Intent(Intent.ACTION_VIEW,_link);
                startActivity(pag_recupera);
            }
        });
    }

    private boolean check_email(String correo)
    {
        if(correo.isEmpty())
        {
            email.setErrorEnabled(true);
            email.setError("Campo Requerido");
            return false;
        }else if(!correo.contains("@"))
        {
            email.setErrorEnabled(true);
            email.setError("Formato Invalido");
            return false;
        }
        else{
            email.setErrorEnabled(false);
            return true;
        }

    }

    private boolean check_password(String password)
    {
        if(password.isEmpty())
        {
            pass.setErrorEnabled(true);
            pass.setError("Campo Requerido");
            return false;
        }else if(password.length() < 8)
        {
            pass.setErrorEnabled(true);
            pass.setError("La constraseña debe ser mayor a 8 caracteres");
            return false;
        }
        else{
            pass.setErrorEnabled(false);
            return true;
        }
    }

    public void menu_principal(View v)
    {
        /*String contraseña = pass.getEditText().getText().toString().trim();
        String correo = email.getEditText().getText().toString().trim();*/

        correo = email.getEditText().getText().toString().trim();
        contrasena = pass.getEditText().getText().toString().trim();
        if(!check_password(contrasena) | !check_email(correo))
        {
            return ;
        }
        else {
            inicioSesion();
           /* Intent pag_inicial = new Intent(Main_IniciaSesion.this, Inicio.class);
            startActivity(pag_inicial);*/

            //finish();
        }

    }


    private void inicioSesion(){
        String url = "https://www.tutumapps.com/api/driver/login";
        try{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final JSONObject jsonObject = new JSONObject();

            jsonObject.put("email", correo);
            jsonObject.put("password", contrasena);

            final String requestBody = jsonObject.toString();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("TAG", "Success! :D" + " " + response);
                    try {
                        isSucess = response.getBoolean("success");
                        if(isSucess){
                            saveUserData(response);
                            Intent intent = new Intent(Main_IniciaSesion.this, MainPopUpUbicacion.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Toast.makeText(Main_IniciaSesion.this, "Inicio Correcto", Toast.LENGTH_SHORT).show();
                            startActivity(intent);


                        }
                        else{
                            String getMsg = response.getString("msg");
                            Toast.makeText(Main_IniciaSesion.this, getMsg, Toast.LENGTH_LONG).show();
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

    private void saveUserData(JSONObject response){

        try {
            PerfilUser user = new PerfilUser();
            JSONObject data = response.getJSONObject("data");
            JSONObject passenger = data.getJSONObject("driver");

            user.setName(passenger.getString("name"));
            user.setJob("TUTUM");
            user.setJob_dir("Sierra del Laurel 420, Bosques del prado");
            user.setHome("Vizcaya xD");
            user.setHome_dir("Vizcaya 307, Barranca de Gpe");
            user.setEmailVerified(passenger.getBoolean("email_verified"));
            user.setFb_id(passenger.getString("facebook_id"));
            user.setGoogle_id(passenger.getString("google_id"));
            user.setPassengerId(passenger.getString("passenger_id"));//3617
            user.setPassengerImg(passenger.getString("passenger_img"));
            user.setTravels(passenger.getString("travels"));
            user.setUserId(String.valueOf(passenger.getInt("user_id")));//4071
            user.setCalification(passenger.getString("calification"));
            user.setApi_token(data.getString("api_token"));
            user.setEmail(passenger.getString("email"));
            user.setPassword(contrasena);
            user.setTelefono(passenger.getString("phone"));
        } catch (JSONException e) {
            Log.e("MyTAG", "hubo un error obteniendo los valores del servidor");
        }

    }

}