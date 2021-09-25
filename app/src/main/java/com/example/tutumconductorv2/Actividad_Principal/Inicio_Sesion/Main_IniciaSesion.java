package com.example.tutumconductorv2.Actividad_Principal.Inicio_Sesion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.Inicio;
import com.example.tutumconductorv2.Pop_Up.MainPopUpUbicacion;
import com.example.tutumconductorv2.R;
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

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicia_sesion);


        email = findViewById(R.id.Input_Email);
        pass = findViewById(R.id.Input_Contraseña);
        recupera = findViewById(R.id.recupera_contraseña);

       /* String email_usuario = "usuario@gmail.com";
        String pass_usuario = "123456789";*/

       preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
        editor = preferences.edit();

        if(preferences.contains("correo")){
            Intent main_inicio = new Intent(Main_IniciaSesion.this, Inicio.class);
            startActivity(main_inicio);
            finish();
        }

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
        correo = email.getEditText().getText().toString().trim();
        contrasena = pass.getEditText().getText().toString().trim();

        editor.putString("correo", correo);
        editor.putString("contrasena", contrasena);
        editor.commit();

        if(!check_password(contrasena) | !check_email(correo))
        {

        }
        else {
            inicioSesion();

        }

    }

    private  void Islogged(String correo, String  contrasena){
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_edit = preferences.edit();
        obj_edit.putString("Username", correo);
        obj_edit.putString("Password", contrasena);
        obj_edit.apply();
    }






    private boolean inicioSesion(){
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
                    Log.d("TAG Inicio Sesion","Respuesta Inicio Sesion:"+response);
                    try {
                          if(response.getString("success").matches("true")){
                            Log.e("TAG Inicio Sesion","Inicio de sesion Correcto!!!!!!");
                              Toast.makeText(Main_IniciaSesion.this, "Inicio de sesion Correcto", Toast.LENGTH_SHORT).show();

                              SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
                              SharedPreferences.Editor obj_edit = preferences.edit();
                              obj_edit.putBoolean("Logged", true);
                              obj_edit.putString("Username", correo);
                              obj_edit.putString("Password", contrasena);
                              obj_edit.apply();


                              JSONObject data = response.getJSONObject("data");
                              JSONObject driver = data.getJSONObject("driver");
                              int driver_id = driver.getInt("driver_id");
                              int user_id = driver.getInt("user_id");
                              String name = driver.getString("name");
                              String alias = driver.getString("alias");
                              String driver_img = driver.getString("driver_img");
                              String email = driver.getString("email");
                              String available = driver.getString("available");
                              String phone = driver.getString("phone");
                              String type = driver.getString("type");
                              int travels = driver.getInt("travels");
                              int driver_time = driver.getInt("driver_time");
                              int calification = driver.getInt("calification");

                              JSONObject vehicle = data.getJSONObject("vehicle");
                              int vehicle_id = vehicle.getInt("vehicle_id");
                              String vehicle_id_string = Integer.toString(vehicle_id);
                              String vehicle_title = vehicle.getString("vehicle_title");
                              int vehicle_year = vehicle.getInt("vehicle_year");
                              String vehicle_color = vehicle.getString("vehicle_color");
                              String vehicle_plate = vehicle.getString("vehicle_plate");
                              String vehicle_img = vehicle.getString("vehicle_img");
                              int vehicle_type_id = vehicle.getInt("vehicle_type_id");
                              String vehicle_model = vehicle.getString("vehicle_model");
                              String vehicle_manufacturer = vehicle.getString("vehicle_manufacturer");
                              String vehicle_status = vehicle.getString("vehicle_status");

                              // Poner aqui los datos de la tarjeta
                              String api_token = data.getString("api_token");

                              obj_edit.putInt("driver_id",driver_id);
                              obj_edit.putInt("user_id",user_id);
                              obj_edit.putString("name",name);
                              obj_edit.putString("alias",alias);
                              obj_edit.putString("driver_img",driver_img);
                              obj_edit.putString("email",email);
                              obj_edit.putString("available",available);
                              obj_edit.putString("phone",phone);
                              obj_edit.putString("type",type);
                              obj_edit.putInt("travels",travels);
                              obj_edit.putInt("driver_time",driver_time);
                              obj_edit.putInt("calification",calification);

                              obj_edit.putString("vehicle_id",vehicle_id_string);
                              obj_edit.putString("vehicle_title",vehicle_title);
                              obj_edit.putInt("vehicle_year",vehicle_year);
                              obj_edit.putString("vehicle_color",vehicle_color);
                              obj_edit.putString("vehicle_plate",vehicle_plate);
                              obj_edit.putString("vehicle_img",vehicle_img);
                              obj_edit.putInt("vehicle_type_id",vehicle_type_id);
                              obj_edit.putString("vehicle_model",vehicle_model);
                              obj_edit.putString("vehicle_manufacturer",vehicle_manufacturer);
                              obj_edit.putString("vehicle_status",vehicle_status);

                              obj_edit.putString("api_token",api_token);
                              obj_edit.commit();

                              Log.d("Respuesta Driver","Driver_id: "+driver_id);
                              Log.d("Respuesta Driver","User_id: "+user_id);
                              Log.d("Respuesta Driver","Name: "+name);
                              Log.d("Respuesta Driver","Alias: "+alias);
                              Log.d("Respuesta Driver","driver_img: "+driver_img);
                              Log.d("Respuesta Driver","email: "+email);
                              Log.d("Respuesta Driver","Available: "+available);
                              Log.d("Respuesta Driver","Phone: "+phone);
                              Log.d("Respuesta Driver","type: "+type);
                              Log.d("Respuesta Driver","travels: "+travels);
                              Log.d("Respuesta Driver","Driver Time: "+driver_time);
                              Log.d("Respuesta Driver","Calification: "+calification);

                              Log.d("Respuesta Vehicle","Vehicle id: "+vehicle_id);
                              Log.d("Respuesta Vehicle","Vehicle title: "+vehicle_title);
                              Log.d("Respuesta Vehicle","Vehicle year: "+vehicle_year);
                              Log.d("Respuesta Vehicle","Vehicle color: "+vehicle_color);
                              Log.d("Respuesta Vehicle","Vehicle plate: "+vehicle_plate);
                              Log.d("Respuesta Vehicle","Vehicle img: "+vehicle_img);
                              Log.d("Respuesta Vehicle","Vehicle title: "+vehicle_type_id);
                              Log.d("Respuesta Vehicle","Vehicle title: "+vehicle_model);
                              Log.d("Respuesta Vehicle","Vehicle title: "+vehicle_manufacturer);
                              Log.d("Respuesta Vehicle","Vehicle title: "+vehicle_status);

                              Log.d("Respuesta api token","Api Token: "+api_token);

                              Intent main_popUbicacion = new Intent(Main_IniciaSesion.this, MainPopUpUbicacion.class);
                              startActivity(main_popUbicacion);
                              finish();
                           }else{
                              Log.e("TAG Inicio Sesion","Inicio de sesion Incorrecto!!!!!!");
                              Toast.makeText(Main_IniciaSesion.this, "Inicio de sesion Incorrecto", Toast.LENGTH_SHORT).show();
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
        return false;
    }



}