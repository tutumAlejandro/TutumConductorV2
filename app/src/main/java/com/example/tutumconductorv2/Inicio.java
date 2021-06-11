package com.example.tutumconductorv2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class Inicio extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Button toPerfil,toUnidad,signOut,toAyuda,toGanancia,toHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
}
