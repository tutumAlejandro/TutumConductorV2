package com.example.tutumconductorv2.home;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tutumconductorv2.NotificationsClass;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.providers.AuthProvider;
import com.example.tutumconductorv2.providers.GeofireProvider;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.libraries.maps.CameraUpdate;
import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.BitmapDescriptorFactory;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.Marker;
import com.google.android.libraries.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

//import com.example.tutumconductorv2.providers.TokenProvider;

public class HomeFragment  extends Fragment {
    private boolean flag = false, flagMap = false;
    private int time = 0;
    private GoogleMap mMap;
    private Marker marcador;
    private final int FINE_LOCATION_CODE = 150;
    private double lat = 0.0, lng = 0.0;
    private Bitmap smallMarker;
    //private TokenProvider mTokenProvider;
    public AuthProvider mAuthProvider = new AuthProvider();
    private com.google.android.gms.maps.model.LatLng mCurrentLatLng;
    public GeofireProvider mGeofireProvider;
    public FusedLocationProviderClient mFusedLocation;
    //public MapVali mMapvali = new MapVali();
    public Button mButtonConnect;
    public ToggleButton toggle;
    public boolean mIsConnect = false;
    private LocationRequest mLocationRequest;

    DatabaseReference mDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       // mMapvali = new MapVali();
        mAuthProvider = new AuthProvider();
        mGeofireProvider = new GeofireProvider("active_drivers");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //mButtonConnect = mButtonConnect.findViewById(R.id.on_off);
//        toggle = toggle.findViewById(R.id.on_off);




        HomeViewModel homeViewModel;

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.activity_main_inicio_sesion, container, false);

       // mTokenProvider = new TokenProvider();

        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.pin2);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        smallMarker = Bitmap.createScaledBitmap(bitmap, width*2, height*2, false);



        verificarPermisoUbicacion();

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag = true;
                time = 0;
                if(checkGpsState()){
                    int permisoFinoLocation = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION);
                    if(permisoFinoLocation == PackageManager.PERMISSION_GRANTED){
                        agregarMarcador(lat, lng);
                    }
                    else{
                        verificarPermisoUbicacion();
                    }
                }
            }
        });
        return root;


    }

    public OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            //miUbicacion();
            if(!checkGpsState()) {


                LatLng home = new LatLng(21.883226752408518, -102.29655237109232);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 15));
            }
            else{
                LatLng home = new LatLng(21.883226752408518, -102.29655237109232);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 15));
                locationStart();
            }
/*
googleMap.setOnMarkerClickListener(HomeFragment.this);
            googleMap.setOnMarkerDragListener(MainInicioSesion.this);*/

            updateLocation();
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }


    private void updateLocation() {
        if (mAuthProvider.existSession() && mCurrentLatLng != null) {
            mGeofireProvider.saveLocation(mAuthProvider.driver_id(), mCurrentLatLng);
        }
    }

    private boolean checkGpsState(){
        LocationManager mlocManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Encender GPS");
            builder.setMessage("Activa el GPS para poder obtener los servicios de ubicacion necesarios para el correcto funcionamiento de la app");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(settingsIntent);
                }
            });
            builder.show();
            return false;
        }
        else{
            return true;
        }
    }


   /* private boolean gpsActived() {
        boolean isActive = false;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            isActive = true;
        }
        return isActive;
    }*/



   /* private void disconnect() {

        if (mFusedLocation != null) {
            mButtonConnect.setText("Conectarse");
            mIsConnect = false;
            mFusedLocation.removeLocationUpdates((LocationCallback) callback);
            if (mAuthProvider.existSession()) {
                mGeofireProvider.removeLocation(mAuthProvider.driver_id());
            }
        }
        else {
        }
    }*/


    private void locationStart(){
        LocationManager mlocManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        //Localizacion Local = new Localizacion();
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //ActivityCompat.requestPermissions((Activity) requireContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);

           /* if (gpsActived()) {
                mButtonConnect.setText("Desconectarse");
                mIsConnect = true;
                mFusedLocation.requestLocationUpdates(mLocationRequest, (LocationCallback) callback, Looper.myLooper());
            }*/
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            actualizarUbicacion(location);
            time = 2000;
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            Toast.makeText(getContext(), lat + "GPS Desactivado" + lng, Toast.LENGTH_SHORT).show();
            checkGpsState();
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            Toast.makeText(getContext(), lat + "GPS Activado" + lng, Toast.LENGTH_SHORT).show();
            locationStart();
        }
    };

    private void actualizarUbicacion(Location location) {
        if (location != null) {

            mCurrentLatLng = new com.google.android.gms.maps.model.LatLng(location.getLatitude(), location.getLongitude());

            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
            Log.d(TAG, "LATITUD: " + lat);
            Log.d(TAG, "LONGITUD: " + lat);

            /*SUBIR UBICACION EN TIEPO REAL A FIREBASE*/

            /*Map<String,Object> latlang = new HashMap<>();
            latlang.put("latitud", location.getLatitude());
            latlang.put("longitud", location.getLongitude());
            mDatabase.child("Locations").push().setValue(latlang);*/

        }
    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 15);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Mi posicion Actual")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        if(flag){
            mMap.animateCamera(miUbicacion);
            flag = false;
        }
    }

    private void verificarPermisoUbicacion (){
        int permisoLocation = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        int permisoFinoLocation = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION);

        if(!(permisoLocation == PackageManager.PERMISSION_GRANTED && permisoFinoLocation == PackageManager.PERMISSION_GRANTED)){
            flagMap = false;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) || shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
                    NotificationsClass notificationsClass = new NotificationsClass();
                    notificationsClass.createNotification(requireContext(),"Permisos de Ubicacion Necesarios", "Concede los permisos de Ubicacion a la app, " +
                            "son necesarios para el correcto funcionamiento de la app",R.drawable.icon);
                    notificationsClass.createNotificationChannel(requireContext());
                }
                else{
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},FINE_LOCATION_CODE);
                    //requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},LOCATION_CODE);
                }
            }
            else{
                NotificationsClass notificationsClass = new NotificationsClass();
                notificationsClass.createNotification(requireContext(),"Permisos de Ubicacion Necesarios", "Concede los permisos de Ubicacion a la app, " +
                        "son necesarios para el correcto funcionamiento de la app",R.drawable.icon);
                notificationsClass.createNotificationChannel(requireContext());
            }
        }else{
            flagMap = true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FINE_LOCATION_CODE) {
            int permisoFinoLocation = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION);
            if (permisoFinoLocation == PackageManager.PERMISSION_DENIED) {
                NotificationsClass notificationsClass = new NotificationsClass();
                notificationsClass.createNotification(requireContext(),"Permisos de Ubicacion Necesarios", "Concede los permisos de Ubicacion a la app, " +
                        "son necesarios para el correcto funcionamiento de la app",R.drawable.icon);
                notificationsClass.createNotificationChannel(requireContext());
            }
            else{
                locationStart();
            }
        }
    }


   /* void generateToken() {
        mTokenProvider.create(mAuthProvider.getId());
    }*/

}


/*import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.tutumconductorv2.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.CameraPosition;
import com.google.android.libraries.maps.model.LatLng;

import java.security.AuthProvider;
*//*import com.example.tutumconductorv2.activities.MainActivity;
import com.example.tutumconductorv2.activities.driver.MapDriverActivity;
import com.example.tutumconductorv2.includes.MyToolbar;
import com.example.tutumconductorv2.providers.AuthProvider;*//*


public class HomeFragment extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;
    private AuthProvider mAuthProvider;

    private LocationRequest mLocationRequest;
    private FusedLocationProviderClient mFusedLocation;

    private final static int LOCATION_REQUEST_CODE = 1;
    private final static int SETTINGS_REQUEST_CODE = 2;

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                if (getApplicationContext() != null) {
                    // OBTENER LA LOCALIZACION DEL USUARIO EN TIEMPO REAL
                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                            new CameraPosition.Builder()
                                    .target(new LatLng(location.getLatitude(), location.getLongitude()))
                                    .zoom(15f)
                                    .build()
                    ));
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inicio_sesion);
        //MyToolbar.show(this, "Conductor", false);

        //mAuthProvider = new AuthProvider();

        mFusedLocation = LocationServices.getFusedLocationProviderClient(this);

        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(5);

        startLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    if (gpsActived()) {
                        mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                    } else {
                        showAlertDialogNOGPS();
                    }
                } else {
                    checkLocationPermissions();
                }
            } else {
                checkLocationPermissions();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTINGS_REQUEST_CODE && gpsActived()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        }
        else {
            showAlertDialogNOGPS();
        }
    }

    private void showAlertDialogNOGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Por favor activa tu ubicacion para continuar")
                .setPositiveButton("Configuraciones", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), SETTINGS_REQUEST_CODE);
                    }
                }).create().show();
    }

    private boolean gpsActived() {
        boolean isActive = false;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            isActive = true;
        }
        return isActive;
    }

    private void startLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (gpsActived()) {
                    mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                }
                else {
                    showAlertDialogNOGPS();
                }
            }
            else {
                checkLocationPermissions();
            }
        } else {
            if (gpsActived()) {
                mFusedLocation.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            }
            else {
                showAlertDialogNOGPS();
            }
        }
    }

    private void checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("Proporciona los permisos para continuar")
                        .setMessage("Esta aplicacion requiere de los permisos de ubicacion para poder utilizarse")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(HomeFragment.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
                            }
                        })
                        .create()
                        .show();
            }
            else {
                ActivityCompat.requestPermissions(HomeFragment.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }
        }
    }

 *//* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.driver_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }


 void logout() {
        mAuthProvider.logout();
        Intent intent = new Intent(MapClientActivity.this, Main_IniciaSesion.class);
        startActivity(intent);
        finish();
    }*//*

}*/
