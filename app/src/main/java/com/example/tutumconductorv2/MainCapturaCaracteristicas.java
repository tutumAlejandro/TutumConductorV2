package com.example.tutumconductorv2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainCapturaCaracteristicas extends AppCompatActivity {

    private Spinner spFabs;

    private String rol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_caracteristicas);

        spFabs = (Spinner)findViewById(R.id.spFabricantes);

        //Declaracion del vector que va a contener los fabricantes de vehiculos

        String [] fabricantes = {"Fabricante del Vehiculo","Audi","BMW", "Buick", "Chevrolet", "Fiat", "Ford", "Honda", "Hyundai", "Jeep", "Kia", "Maruti",
                                 "Mazda", "Mercedes", "Mitsubishi", "Nissan", "Peugeot", "Renault","Subaru", "Suzuki","Toyota","Volkswagen"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,fabricantes);
        spFabs.setAdapter(adapter);

    }
}