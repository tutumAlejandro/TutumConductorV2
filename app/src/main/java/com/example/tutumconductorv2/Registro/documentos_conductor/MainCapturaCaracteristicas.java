package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_registro;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainCapturaCaracteristicas extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spFabs,spModelo;

    private ImageView btn_retroceso_caracteristicas;
    private TextInputLayout model_input, matricula_input;
    private ImageView btn_frente_carac,btn_trasera_carac,btn_lateral_carac;

    private TextView fabs;
    private int model_id=0;
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

    private boolean check_carac_frente = false;
    private boolean check_carac_reverso = false;
    private boolean check_carac_lateral = false;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    int codigoBoton = 0;
    int factor = 1;
    int year=0;
    int quality_image=30;

    private String image_code1="";
    private String image_code2="";
    private String image_code3="";

    private String matricula="";
    private String mod="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_captura_caracteristicas);
        SharedPreferences preferencias_caracteristicas = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        rol = preferencias_caracteristicas.getString("rol","");

        btn_retroceso_caracteristicas = findViewById(R.id.img_retroceso_caracteristicas);
        spFabs = (Spinner)findViewById(R.id.spFabricantes);
        spModelo = (Spinner)findViewById(R.id.spModelo);

        model_input = findViewById(R.id.input_modelo);
        matricula_input = findViewById(R.id.input_matricula);

        btn_frente_carac = findViewById(R.id.btn_caracteristicas_frontal);
        btn_trasera_carac = findViewById(R.id.btn_caracteristicas_trasera);
        btn_lateral_carac = findViewById(R.id.btn_caracteristicas_lateral);

        spFabs.setOnItemSelectedListener(this);


        //Declaracion del vector que va a contener los fabricantes de vehiculos
        fabs = findViewById(R.id.test_fabs);

        if (ContextCompat.checkSelfPermission(MainCapturaCaracteristicas.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainCapturaCaracteristicas.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainCapturaCaracteristicas.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        btn_retroceso_caracteristicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferencias_caracteristicas = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
                SharedPreferences.Editor obj_editor = preferencias_caracteristicas.edit();
                if (rol.matches("Socio")) {
                    Intent main_socio_documentos = new Intent(MainCapturaCaracteristicas.this, MainSocioDocumentos.class);
                    obj_editor.putString("caracteristicas1","0");
                    obj_editor.commit();
                    startActivity(main_socio_documentos);
                    finish();
                } else{
                    Intent main_conductor_documentos = new Intent(MainCapturaCaracteristicas.this, MainConductorDocumentos.class);
                    obj_editor.putString("caracteristicas2","0");
                    obj_editor.commit();
                    startActivity(main_conductor_documentos);
                    finish();
                }
            }
        });

        btn_frente_carac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codigoBoton=1;
                tomarFoto(view,"frente_vehiculo");
                check_carac_frente=true;
            }
        });

        btn_trasera_carac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codigoBoton=2;
                tomarFoto(view,"trasera_vehiculo");
                check_carac_reverso=true;
            }
        });
        btn_lateral_carac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codigoBoton=3;
                tomarFoto(view,"lateral_vehiculo");
                check_carac_lateral = true;
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
        }else if(year.length() > 4){
            model_input.setErrorEnabled(true);
            model_input.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            model_input.setError("Formato Invalido");
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
                            cadenas_documentos.fabricante=fabricantes[position];
                          spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                          @Override
                           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                              cadenas_documentos.modelo=modelos_Buick[i]; }

                          @Override
                          public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Chevrolet": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Chevrolet);
                               spModelo.setAdapter(m1);
                               cadenas_documentos.fabricante=fabricantes[position];
                               spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               @Override
                               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                               cadenas_documentos.modelo=modelos_Chevrolet[i];
                               }
                               @Override
                               public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Fiat": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Fiat);
                          spModelo.setAdapter(m1);
                          cadenas_documentos.fabricante=fabricantes[position];
                          spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                          @Override
                          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                          cadenas_documentos.modelo=modelos_Fiat[i];
                          }
                          @Override
                          public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Ford": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Ford);
                          spModelo.setAdapter(m1);
                          cadenas_documentos.fabricante=fabricantes[position];
                          spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                              @Override
                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                              cadenas_documentos.modelo=modelos_Ford[i];
                              }
                              @Override
                              public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Honda": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Honda);
                           spModelo.setAdapter(m1);
                           cadenas_documentos.fabricante=fabricantes[position];
                           spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                           @Override
                           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            cadenas_documentos.modelo=modelos_Honda[i];
                           }
                           @Override
                           public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Hyundai": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Hyundai);
                             spModelo.setAdapter(m1);
                             cadenas_documentos.fabricante=fabricantes[position];
                             spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                             @Override
                             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                              cadenas_documentos.modelo=modelos_Hyundai[i];
                             }
                              @Override
                             public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Kia": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Kia);
                         spModelo.setAdapter(m1);
                         cadenas_documentos.fabricante=fabricantes[position];
                         spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                         @Override
                         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                          cadenas_documentos.modelo=modelos_Kia[i];
                         }
                         @Override
                         public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Mazda": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Mazda);
                           spModelo.setAdapter(m1);
                           cadenas_documentos.fabricante=fabricantes[position];
                           spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                               @Override
                               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                cadenas_documentos.modelo=modelos_Mazda[i];
                                }
                              @Override
                              public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Mercedes Benz": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Mercedez_Benz);
                                   spModelo.setAdapter(m1);
                                   cadenas_documentos.fabricante=fabricantes[position];
                                   spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        cadenas_documentos.modelo=modelos_Mercedez_Benz[i];
                                        }
                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Mitsubishi": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Mitsubishi);
                                spModelo.setAdapter(m1);
                                cadenas_documentos.fabricante=fabricantes[position];
                                spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        cadenas_documentos.modelo=modelos_Mitsubishi[i];
                                         }
                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Nissan": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Nissan);
                            spModelo.setAdapter(m1);
                            cadenas_documentos.fabricante=fabricantes[position];
                            spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                       @Override
                                       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                       cadenas_documentos.modelo=modelos_Nissan[i];
                                       }
                                       @Override
                                       public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Peugeot": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Peugeot);
                             spModelo.setAdapter(m1);
                             cadenas_documentos.fabricante=fabricantes[position];
                             spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                       @Override
                                       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                           cadenas_documentos.modelo=modelos_Peugeot[i];
                                       }
                                       @Override
                                      public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Renault": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Renault);
                             spModelo.setAdapter(m1);
                             cadenas_documentos.fabricante=fabricantes[position];
                             spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                       @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            cadenas_documentos.modelo=modelos_Renault[i];
                                         }
                                         @Override
                                        public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Subaru": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Subaru);
                            spModelo.setAdapter(m1);
                            cadenas_documentos.fabricante=fabricantes[position];
                            spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                                  public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                     cadenas_documentos.modelo=modelos_Subaru[i];
                                  }
                            @Override
                                  public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Suzuki": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Susuki);
                            spModelo.setAdapter(m1);
                            cadenas_documentos.fabricante=fabricantes[position];
                            spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                               cadenas_documentos.modelo=modelos_Susuki[i];
                               }
                            @Override
                               public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Toyota": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Toyota);
                            spModelo.setAdapter(m1);
                             cadenas_documentos.fabricante=fabricantes[position];
                            spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                 @Override
                                 public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                   cadenas_documentos.modelo=modelos_Toyota[i];
                                  }
                                 @Override
                                  public void onNothingSelected(AdapterView<?> parent) {} } ); }break;
            case "Volkswagen": {m1 = new ArrayAdapter<String>(this,R.layout.spinner_style_1,modelos_Volkswagen);
                                spModelo.setAdapter(m1);
                                 cadenas_documentos.fabricante=fabricantes[position];
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

    private void model_id_vehicle(){
        switch (cadenas_documentos.modelo) {
            //Modelos Audi
            case "A1":
                model_id = 1;
                break;
            case "A2":
                model_id = 2;
                break;
            case "A3":
                model_id = 3;
                break;
            case "A4":
                model_id = 4;
                break;
            case "A5":
                model_id = 5;
                break;
            case "A6":
                model_id = 6;
                break;
            case "A7":
                model_id = 7;
                break;
            case "A8":
                model_id = 8;
                break;
            case "Q2":
                model_id = 9;
                break;
            case "Q3":
                model_id = 10;
                break;
            case "Q5":
                model_id = 11;
                break;
            case "Q7":
                model_id = 12;
                break;
            case "Q8":
                model_id = 13;
                break;
            case "TT":
                model_id = 14;
                break;
            case "R8":
                model_id = 15;
                break;
            //Modelos BMW
            case "Serie 1":
                model_id = 16;
                break;
            case "Serie 2":
                model_id = 17;
                break;
            case "Serie 3":
                model_id = 18;
                break;
            case "Serie 4":
                model_id = 19;
                break;
            case "Serie 5":
                model_id = 20;
                break;
            case "Serie 7":
                model_id = 21;
                break;
            case "Serie 8":
                model_id = 22;
                break;
            case "X1":
                model_id = 23;
                break;
            case "X2":
                model_id = 24;
                break;
            case "X3":
                model_id = 25;
                break;
            case "X4":
                model_id = 26;
                break;
            case "X5":
                model_id = 27;
                break;
            case "X6":
                model_id = 28;
                break;
            case "X7":
                model_id = 29;
                break;
            case "M2 Competition":
                model_id = 30;
                break;
            case "M3 Competition":
                model_id = 31;
                break;
            case "M4 Competition":
                model_id = 32;
                break;
            case "i3":
                model_id = 33;
                break;
            case "i8":
                model_id = 34;
                break;
            case "Z4":
                model_id = 35;
                break;
            //Modelos Buick
            case "Encore":
                model_id = 36;
                break;
            case "ENVISION":
                model_id = 37;
                break;
            case "Enclave":
                model_id = 38;
                break;
            //Modelos Chevrolet
            case "Beat":
                model_id = 39;
                break;
            case "Spark":
                model_id = 40;
                break;
            case "Aveo":
                model_id = 41;
                break;
            case "Onix":
                model_id = 42;
                break;
            case "Cavalier":
                model_id = 43;
                break;
            case "Cheyenne":
                model_id = 44;
                break;
            case "Camaro":
                model_id = 45;
                break;
            case "Colorado":
                model_id = 46;
                break;
            case "Corvette":
                model_id = 47;
                break;
            case "Tahoe":
                model_id = 48;
                break;
            case "Silverado":
                model_id = 49;
                break;
            case "Suburban":
                model_id = 50;
                break;
            case "Blazer":
                model_id = 51;
                break;
            // Modelos Fiat
            case "Mobi":
                model_id = 52;
                break;
            case "Uno":
                model_id = 53;
                break;
            case "Argo":
                model_id = 54;
                break;
            case "Ducato":
                model_id = 55;
                break;
            //Modelos Ford
            case "Figo":
                model_id = 56;
                break;
            case "Fusion":
                model_id = 57;
                break;
            case "Escape":
                model_id = 58;
                break;
            case "Edge":
                model_id = 59;
                break;
            case "Explorer":
                model_id = 60;
                break;
            case "Expedition":
                model_id = 61;
                break;
            case "Mustang":
                model_id = 62;
                break;
            case "GT":
                model_id = 63;
                break;
            //Modelos Honda
            case "Accord":
                model_id = 64;
                break;
            case "BR-V":
                model_id = 65;
                break;
            case "City":
                model_id = 66;
                break;
            case "Civic":
                model_id = 67;
                break;
            case "CR-V":
                model_id = 68;
                break;
            case "HR-V":
                model_id = 69;
                break;
            case "Insight":
                model_id = 70;
                break;
            case "Odyssey":
                model_id = 71;
                break;
            case "Pilot":
                model_id = 72;
                break;

            case "Grand i10":
                model_id = 73;
                break;
            case "Accent":
                model_id = 74;
                break;
            case "Elantra":
                model_id = 75;
                break;
            case "Ioniq":
                model_id = 76;
                break;

            case "Forte":
                model_id = 77;
                break;
            case "Optima":
                model_id = 78;
                break;
            case "New Sportage":
                model_id = 79;
                break;
            case "Niro":
                model_id = 80;
                break;
            case "Rio":
                model_id = 81;
                break;
            case "Sedona":
                model_id = 82;
                break;
            case "Seltos":
                model_id = 83;
                break;
            case "Sorento":
                model_id = 84;
                break;
            case "Soul":
                model_id = 85;
                break;
            case "Stinger":
                model_id = 86;
                break;
            //Modelos Mazda
            case "Mazda 2":
                model_id = 87;
                break;
            case "Mazda 3":
                model_id = 88;
                break;
            case "Mazda 6":
                model_id = 89;
                break;

            case "Clase A":
                model_id = 90;
                break;
            case "Clase C":
                model_id = 91;
                break;
            case "Clase E":
                model_id = 92;
                break;
            case "Clase S":
                model_id = 93;
                break;

            case "Mirage G4":
                model_id = 94;
                break;
            case "Montero":
                model_id = 95;
                break;
            case "Outlander":
                model_id = 96;
                break;

            case "Altima":
                model_id = 97;
                break;
            case "GTR":
                model_id = 98;
                break;
            case "March":
                model_id = 99;
                break;
            case "Sentra":
                model_id = 100;
                break;
            case "Strail":
                model_id = 101;
                break;
            case "Tsuru":
                model_id = 102;
                break;
            case "Versa":
                model_id = 103;
                break;
            case "V-Drive":
                model_id = 104;
                break;
            case "370z":
                model_id = 105;
                break;
            //modelos_Peugeot
            case "208":
                model_id = 106;
                break;
            case "301":
                model_id = 107;
                break;
            case "2008":
                model_id = 108;
                break;
            case "3008":
                model_id = 109;
                break;
            case "5008":
                model_id = 110;
                break;
            case "Rifter":
                model_id = 111;
            case "Partner":
                model_id = 112;
                break;
            //modelos_Renault = {
            case "Captur":
                model_id = 113;
                break;
            case "Duster":
                model_id = 114;
                break;
            case "Kwid":
                model_id = 115;
                break;
            case "Koleos":
                model_id = 116;
                break;
            case "Oroch":
                model_id = 117;
                break;
            //modelos_Subaru
            case "Subaru BRZ":
                model_id = 118;
                break;
            case "Subaru Forester":
                model_id = 119;
                break;
            case "Subaru WRX/Sti":
                model_id = 120;
                break;
            case "Subaru XV":
                model_id = 121;
                break;
            //modelos_Susuki
            case "Ciaz":
                model_id = 122;
                break;
            case "Ertiga":
                model_id = 123;
                break;
            case "Ignis":
                model_id = 124;
                break;
            case "Jimny":
                model_id = 125;
                break;
            case "S-Cross":
                model_id = 126;
                break;
            case "Swift":
                model_id = 127;
                break;
            case "Vitara":
                model_id = 128;
                break;
            //modelos_Toyota
            case "Avanza":
                model_id = 129;
                break;
            case "Camry":
                model_id = 130;
                break;
            case "Corolla":
                model_id = 131;
                break;
            case "Higlander":
                model_id = 132;
                break;
            case "Prius":
                model_id = 133;
                break;
            case "Rav4":
                model_id = 134;
                break;
            case "Sienna":
                model_id = 135;
                break;
            case "Yaris":
                model_id = 136;
                break;
            //modelos_Volkswagen
            case "Cross Sport": model_id=137;break;
            case "Golf": model_id=138;break;
            case "Jetta": model_id=139;break;
            case "Polo": model_id=140;break;
            case "Saveiro": model_id=141;break;
            case "Taos": model_id=142;break;
            case "T-Cross": model_id=143;break;
            case "Teramont": model_id=144;break;
            case "Tiguan": model_id=145;break;
            case "Vento": model_id=146;break;
            case "Virtus": model_id=147;break;

        }
    }
    public void guarda_caracterisitcas(View view)
    {
        SharedPreferences preferencias_caracteristicas = getSharedPreferences("Datos_Usuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias_caracteristicas.edit();
        matricula = matricula_input.getEditText().getText().toString().trim();
        mod = model_input.getEditText().getText().toString().trim();
        year = Integer.parseInt(mod);
        if(!check_matricula(matricula) | !check_model(mod) | !check_carac_frente | !check_carac_reverso | !check_carac_lateral){
            return;
        }else {
            if (rol.matches("Socio"))
            {
                realizarPost();
                Intent main_socio_documentos = new Intent(MainCapturaCaracteristicas.this, MainSocioDocumentos.class);
                obj_editor.putString("caracteristicas1","1");
                obj_editor.commit();
                startActivity(main_socio_documentos);
                finish();
            }else{
                realizarPost();
                Intent main_conductor_documentos = new Intent(MainCapturaCaracteristicas.this, MainConductorDocumentos.class);
                obj_editor.putString("caracteristicas2","1");
                obj_editor.commit();
                startActivity(main_conductor_documentos);
                finish();
            }
        }
    }

    /** Pasos para crear abrir la camara del telefono, capturar una imagen y mostrar su previsualización
     *
     * 1.- Primero debemos añadir los permisos de uso de la camara y almacenamiento externo de la aplicación
     *     ver en el AndroidManifest.xml el apartado 2
     * 2.- Segundo se debe añadir los paths(rutas de almacenamiento de las imagenes) ver apartado 3 y crear
     *     el archivo file_paths.xml (puede tener el nombre que quieras pero debe ser igual al que se
     *     definio en el manifest apartado 3)
     */


    String mCurrentPhotoPath; //path donde se va a almacenar las imagenes
    private File createImageFile(String nombreFoto) throws IOException {
        // Funcion para crear un archivo del tipo imagen y asignación de un nombre anti-colisiones(evita que tengan el mismo nombre repetido)
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = nombreFoto + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // En la funcion onActivityResult es donde se activa la camara y se almacena la imagen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Primero se pregunta se el resultado fue correcto y si hay una solicitud de captura de imagen
        if(resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE){
            MediaScannerConnection.scanFile(MainCapturaCaracteristicas.this, new String[]{mCurrentPhotoPath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) { }
            });
        }
        // Se debe de redimensionar la imagen  antes de cargarla en los ImageButton(Se usa ImageButton para no consumir tantos recursos)
        int targetW = btn_frente_carac.getWidth();
        int targetH = btn_frente_carac.getHeight();

        //Obtener las dimensiones del Bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        //Determinar el factor de escalamiento de la imagenes bitmap
        int scaleFactor = Math.min((photoW/(targetW*factor)),(photoH/(targetH*factor)));

        //Decodificar  el archivo de la imagen dentro del tamaño del Bitmap para llenar la vista
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        if(codigoBoton==1){
            btn_frente_carac.setImageBitmap(bitmap);
            btn_frente_carac.setBackgroundColor(0x00000000);
            ByteArrayOutputStream array = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
            byte[] imageByte = array.toByteArray();
            image_code1 = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);
        }else if(codigoBoton ==2){
            btn_trasera_carac.setImageBitmap(bitmap);
            btn_trasera_carac.setBackgroundColor(0x00000000);
            ByteArrayOutputStream array = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
            byte[] imageByte = array.toByteArray();
            image_code2 = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);
        }else {
            btn_lateral_carac.setImageBitmap(bitmap);
            btn_lateral_carac.setBackgroundColor(0x00000000);
            ByteArrayOutputStream array = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality_image,array);
            byte[] imageByte = array.toByteArray();
            image_code3 = android.util.Base64.encodeToString(imageByte, android.util.Base64.DEFAULT);

        }

    }
    public void tomarFoto(View view,String nomFoto){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createImageFile(nomFoto);
            }catch (IOException e){

            }
            if(photoFile != null){
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePictureIntent,REQUEST_TAKE_PHOTO);
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                File f = new File(mCurrentPhotoPath);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);
            }
        }
    }

    public void realizarPost() {
        String tel="";
        SharedPreferences preferences = getSharedPreferences("Datos_Usuario", Context.MODE_PRIVATE);
        tel=preferences.getString("phone","");
        String url = "https://tutumapps.com/api/driver/uploadVehicleDetails";
        model_id_vehicle();
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone", tel);
            jsonObject.put("vehicle_model_id",model_id);
            jsonObject.put("vehicle_year",year);
            jsonObject.put("vehicle_plates",matricula);
            jsonObject.put("img_front",image_code1);
            jsonObject.put("img_back",image_code2);
            jsonObject.put("img_side",image_code3);


            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("My Tag","Exito!!!!! "+response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("My Tag","Error"+error);
                }
            });
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}