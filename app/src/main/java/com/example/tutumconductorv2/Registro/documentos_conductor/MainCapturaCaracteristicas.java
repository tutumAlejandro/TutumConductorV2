package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;
import com.google.android.material.textfield.TextInputLayout;

public class MainCapturaCaracteristicas extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spFabs,spModelo;

    private ImageView btn_retroceso_caracteristicas;
    private TextInputLayout model_input, matricula_input;

    private TextView fabs;

    private String rol;
    private String [] fabricantes = {"Fabricante del Vehiculo","Audi","BMW", "Buick", "Chevrolet", "Fiat", "Ford", "Honda", "Hyundai", "Kia",
            "Mazda", "Mercedes Benz", "Mitsubishi", "Nissan", "Peugeot", "Renault","Subaru", "Suzuki","Toyota","Volkswagen"};
    private String [] modelos_audi = {"A1","A2","A3","A4","A5","A6","A7","A8","Q2","Q3","Q5","Q7","Q8","TT","R8"};
    private String [] modelos_BW = {"Serie 1","Serie 2","Serie 3","Serie 4","Serie 5","Serie 7","Serie 8", "X1","X2","X3","X4","X5","X6","X7",
            "M2 Competition","M3 Competition","M4 Competition","i3","i8","Z4"};
    private String [] modelos_Buick = {"Encore","ENVISION","Enclave"};
    private String [] modelos_Chevrolet = {"Beat","Spark","Aveo","Onix","Cavalier","Cheyenne","Camaro","Colorado","Corvette","Tahoe","Silverado","Suburban","Blazer",};
    private String [] modelos_Fiat = {"Mobi","Uno","Argo","Ducato"};
    private String [] modelos_Ford = {"Figo","Fusion","Escape","Edge","Explorer","Expedition","Mustang","GT"};
    private String [] modelos_Honda = {"Accord","BR-V","City","Civic","CR-V","HR-V","Insight","Odyssey","Pilot"};
    private String [] modelos_Hyundai = {"Grand i10","Accent", "Elantra","Ioniq"};
    private String [] modelos_Kia = {"Forte","Optima","New Sportage","Niro","Rio","Sedona","Seltos","Sorento","Soul","Stinger"};
    private String [] modelos_Mazda = {"Mazda 2","Mazda 3","Mazda 6"};
    private String [] modelos_Mercedez_Benz = {"Clase A", "Clase C", "Clase E", "Clase S"};
    private String [] modelos_Mitsubishi = {"Mirage G4","Montero","Outlander"};
    private String [] modelos_Nissan = {"Altima","GTR","March","Sentra","Strail","Tsuru","Versa","V-Drive","370z"};
    private String [] modelos_Peugeot = {"208","301","2008","3008","5008","Rifter","Partner"};
    private String [] modelos_Renault = {"Captur","Duster","Kwid","Koleos","Oroch"};
    private String [] modelos_Subaru = {"Subaru BRZ","Subaru Forester","Subaru WRX/Sti","Subaru XV"};
    private String [] modelos_Susuki = {"Ciaz","Ertiga","Ignis","Jimny","S-Cross","Swift","Vitara"};
    private String [] modelos_Toyota = {"Avanza","Camry","Corolla","Higlander","Prius","Rav4","Sienna","Yaris"};
    private String [] modelos_Volkswagen = {"Cross Sport","Golf","Jetta","Polo","Saveiro","Taos","T-Cross","Teramont","Tiguan","Vento","Virtus"};
    private String [] nulo ={"Modelo"};

    ArrayAdapter <String> m1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_caracteristicas);
        rol = getIntent().getStringExtra("rol");

        btn_retroceso_caracteristicas = findViewById(R.id.img_retroceso_caracteristicas);
        spFabs = (Spinner)findViewById(R.id.spFabricantes);
        spModelo = (Spinner)findViewById(R.id.spModelo);

        model_input = findViewById(R.id.input_modelo);
        matricula_input = findViewById(R.id.input_matricula);

        spFabs.setOnItemSelectedListener(this);


        //Declaracion del vector que va a contener los fabricantes de vehiculos
        fabs = findViewById(R.id.test_fabs);

        btn_retroceso_caracteristicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaCaracteristicas.this, MainSocioDocumentos.class);
                    cadenas_documentos.check_caracteristicas1=false;
                    startActivity(main_socio_documentos);
                    finish();
                } else{
                    Intent main_conductor_documentos = new Intent(MainCapturaCaracteristicas.this, MainConductorDocumentos.class);
                    cadenas_documentos.check_caracteristicas2=false;
                    startActivity(main_conductor_documentos);
                    finish();
                }
            }
        });

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_style_1,fabricantes);
         m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,nulo);
        spFabs.setAdapter(adapter);
        spModelo.setAdapter(m1);
    }

    private boolean check_model(String year)
    {
        if (year.isEmpty()){
            model_input.setErrorEnabled(true);
            model_input.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            model_input.setError("Campo Requerido");
            return false;
        }else if(Integer.parseInt(year)<2012)
        {
            model_input.setErrorEnabled(true);
            model_input.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            model_input.setError("Solo vehiculos con atiguedad menor a 10 años");
            return false;
        }
        else {
            model_input.setErrorEnabled(false);
            return true;
        }
    }
    private boolean check_matricula(String matricula)
    {
        if (matricula.isEmpty()){
            matricula_input.setErrorEnabled(true);
            matricula_input.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            matricula_input.setError("Campo Requerido");
            return false;
        }
        else if (matricula.length() >= 7)
        {
            matricula_input.setErrorEnabled(true);
            matricula_input.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            matricula_input.setError("Formato Invalido");
            return false;
        }else {
            matricula_input.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // Toast.makeText(this,"fabricante: "+fabricantes[position],Toast.LENGTH_LONG).show(); // si jala esta pendejada
        switch (fabricantes[position])
        {
            case "Fabricante del Vehiculo": {spModelo.setAdapter(m1);}break;
            case "Audi": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_audi);
                          spModelo.setAdapter(m1);
                          cadenas_documentos.fabricante=fabricantes[position];
                          spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                              @Override
                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                  cadenas_documentos.modelo=modelos_audi[i];
                              }

                              @Override
                              public void onNothingSelected(AdapterView<?> parent) {

                              }
                          } ); }break;
            case "BMW":  {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_BW);
                          spModelo.setAdapter(m1);
                          cadenas_documentos.fabricante=fabricantes[position];
                          spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                          @Override
                          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                          cadenas_documentos.modelo=modelos_BW[i];
                            }

                           @Override
                           public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Buick":{m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Buick);
                          spModelo.setAdapter(m1);
                          spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                          @Override
                           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                              cadenas_documentos.modelo=modelos_Buick[i]; }

                          @Override
                          public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Chevrolet": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Chevrolet);
                               spModelo.setAdapter(m1);
                               spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               @Override
                               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                               cadenas_documentos.modelo=modelos_Chevrolet[i];
                               }
                               @Override
                               public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Fiat": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Fiat);
                          spModelo.setAdapter(m1);
                          spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                          @Override
                          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                          cadenas_documentos.modelo=modelos_Fiat[i];
                          }
                          @Override
                          public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Ford": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Ford);
                          spModelo.setAdapter(m1);
                          spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                              @Override
                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                              cadenas_documentos.modelo=modelos_Ford[i];
                              }
                              @Override
                              public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Honda": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Honda);
                           spModelo.setAdapter(m1);
                           spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                           @Override
                           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            cadenas_documentos.modelo=modelos_Honda[i];
                           }
                           @Override
                           public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Hyundai": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Hyundai);
                             spModelo.setAdapter(m1);
                             spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                             @Override
                             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                              cadenas_documentos.modelo=modelos_Hyundai[i];
                             }
                              @Override
                             public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Kia": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Kia);
                         spModelo.setAdapter(m1);
                         spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                         @Override
                         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                          cadenas_documentos.modelo=modelos_Kia[i];
                         }
                         @Override
                         public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Mazda": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Mazda);
                           spModelo.setAdapter(m1);
                           spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               @Override
                               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                cadenas_documentos.modelo=modelos_Mazda[i];
                                }
                              @Override
                              public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Mercedes Benz": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Mercedez_Benz);
                                   spModelo.setAdapter(m1);
                                   spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        cadenas_documentos.modelo=modelos_Mercedez_Benz[i];
                                        }
                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Mitsubishi": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Mitsubishi);
                                spModelo.setAdapter(m1);
                                spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        cadenas_documentos.modelo=modelos_Mitsubishi[i];
                                         }
                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Nissan": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Nissan);
                            spModelo.setAdapter(m1);
                            spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                       @Override
                                       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                       cadenas_documentos.modelo=modelos_Nissan[i];
                                       }
                                       @Override
                                       public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Peugeot": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Peugeot);
                             spModelo.setAdapter(m1);
                             spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                       @Override
                                       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                           cadenas_documentos.modelo=modelos_Peugeot[i];
                                       }
                                       @Override
                                      public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Renault": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Renault);
                             spModelo.setAdapter(m1);
                             spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                       @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            cadenas_documentos.modelo=modelos_Renault[i];
                                         }
                                         @Override
                                        public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Subaru": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Subaru);
                            spModelo.setAdapter(m1);
                            spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                                  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                     cadenas_documentos.modelo=modelos_Subaru[i];
                                  }
                            @Override
                                  public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Suzuki": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Susuki);
                            spModelo.setAdapter(m1);
                            spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                               cadenas_documentos.modelo=modelos_Susuki[i];
                               }
                            @Override
                               public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Toyota": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Toyota);
                            spModelo.setAdapter(m1);
                            spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                 @Override
                                 public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                   cadenas_documentos.modelo=modelos_Toyota[i];
                                  }
                                 @Override
                                  public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Volkswagen": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Volkswagen);
                                spModelo.setAdapter(m1);
                                spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                 @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    cadenas_documentos.modelo=modelos_Volkswagen[i];
                                    }
                                 @Override
                                     public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void guarda_caracterisitcas(View view)
    {
        String matricula = matricula_input.getEditText().getText().toString().trim();
        String mod = model_input.getEditText().getText().toString().trim();
        if(!check_matricula(matricula) | !check_model(mod)){
            return;
        }else {
            if (rol.matches("Socio"))
            {
                Intent main_socio_documentos = new Intent(MainCapturaCaracteristicas.this, MainSocioDocumentos.class);
                cadenas_documentos.anio=mod;
                cadenas_documentos.matricula=matricula;
                cadenas_documentos.check_caracteristicas1= true;
                startActivity(main_socio_documentos);
                finish();
            }else{
                Intent main_conductor_documentos = new Intent(MainCapturaCaracteristicas.this, MainConductorDocumentos.class);
                cadenas_documentos.check_caracteristicas2 = true;
                startActivity(main_conductor_documentos);
                finish();
            }
        }
    }
}