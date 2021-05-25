package com.example.tutumconductorv2.Registro.documentos_conductor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.BD_registro.utilidades.cadenas_documentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainConductorDocumentos;
import com.example.tutumconductorv2.Registro.menus_rol.MainSocioDocumentos;
import com.google.android.material.textfield.TextInputLayout;

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

    int SELEC_IMAGEN = 200;
    int codigoBoton = 0;
    int factor = 32;

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
    public void guarda_caracterisitcas(View view)
    {
        String matricula = matricula_input.getEditText().getText().toString().trim();
        String mod = model_input.getEditText().getText().toString().trim();
        if(!check_matricula(matricula) | !check_model(mod) | !check_carac_frente | !check_carac_reverso | !check_carac_lateral){
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

    String mCurrentPhotoPath;

    private File createImageFile(String nombreFoto) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = nombreFoto + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpeg", storageDir);

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    public void tomarFoto(View view, String nomFoto) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(nomFoto);
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                galleryAddPic();

            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }


    public void seleccionarImagen() {
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, SELEC_IMAGEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            MediaScannerConnection.scanFile(MainCapturaCaracteristicas.this, new String[]{mCurrentPhotoPath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String s, Uri uri) {

                }
            });

            int targetW = btn_frente_carac.getWidth();
            int targetH = btn_frente_carac.getHeight();

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;

            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW/(targetW*factor), photoH/(targetH*factor));

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            if (codigoBoton == 1) {
                btn_frente_carac.setImageBitmap(bitmap);
            }else if(codigoBoton == 2) {
                btn_trasera_carac.setImageBitmap(bitmap);
            }else{
                btn_lateral_carac.setImageBitmap(bitmap);
            }
        }
    }
}