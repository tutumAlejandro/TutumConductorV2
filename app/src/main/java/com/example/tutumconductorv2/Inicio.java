package com.example.tutumconductorv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.Actividad_Principal.Inicio_Sesion.Main_IniciaSesion;
import com.example.tutumconductorv2.Pop_Up.MainPopUpUbicacion;
import com.example.tutumconductorv2.Registro.datos_personales.MainActivity;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Inicio extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Button toPerfil,toUnidad,signOut,toAyuda,toGanancia,toHistorial;
    private ImageView foto_perfil;
    private boolean upButton;
    private String url_imagen="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
        editStatus(preferences.getString("api_token",""),"0");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.button_perfil, R.id.button_ganancia, R.id.button_ayuda, R.id.button_unidad, R.id.button_historial)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        toPerfil = findViewById(R.id.button_perfil);
        toUnidad = findViewById(R.id.button_ganancia);
        signOut = findViewById(R.id.button_sign_out);
        toAyuda = findViewById(R.id.button_ayuda);
        toGanancia = findViewById(R.id.button_unidad);
        toHistorial = findViewById(R.id.button_historial);
        foto_perfil = findViewById(R.id.User_img);

        url_imagen = preferences.getString("driver_img","");
        url_imagen = url_imagen.replace("https:\\/\\/www.tutumapps.com\\/media\\/profile\\","https://www.tutumapps.com/media/profile/");
        Picasso.get().load(url_imagen).into(foto_perfil);

        toPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToPerfil = new Intent(Inicio.this, activity_perfil.class);
                startActivity(intToPerfil);
            }
        });

        toUnidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAjustes = new Intent(Inicio.this, activity_ganancias.class);
                startActivity(intToAjustes);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSingOut = new Intent(Inicio.this, MainActivity.class);
                intSingOut.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intSingOut.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intSingOut);
            }
        });

        toAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intAyuda = new Intent(Inicio.this, activity_ayuda.class);
                startActivity(intAyuda);
            }
        });

        toGanancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intPago = new Intent(Inicio.this, activity_unidades.class);
                startActivity(intPago);
            }
        });

        toHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intHistorial = new Intent(Inicio.this, activity_historial_viajes.class);
                startActivity(intHistorial);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private static final int INTERVALO = 2000; //2 segundos para salir
    private long tiempoPrimerClick;

    @Override
    public void onBackPressed(){
        if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show();
        }
        tiempoPrimerClick = System.currentTimeMillis();
    }

    private void editStatus(String api_token,String status){
        String url = "https://www.tutumapps.com/api/driver/checkChangeStatus";
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario_Login",Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_edit = preferences.edit();
        obj_edit.putString("status",status);
        obj_edit.commit();
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();

            //jsonObject.put("email",email);
            jsonObject.put("status", status);
            jsonObject.put("api_token",api_token);


            final String requestBody = jsonObject.toString();


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                   Log.e("Status","Status:"+response);
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
