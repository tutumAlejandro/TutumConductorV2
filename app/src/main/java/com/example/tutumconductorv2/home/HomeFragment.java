package com.example.tutumconductorv2.home;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutumconductorv2.NotificationsClass;
import com.example.tutumconductorv2.R;
import com.example.tutumconductorv2.Registro.datos_personales.MainOTP;
import com.example.tutumconductorv2.Registro.datos_personales.MainRegistroTelefono;
import com.example.tutumconductorv2.providers.AuthProvider;
import com.example.tutumconductorv2.providers.GeofireProvider;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    public ToggleButton status_button;
    public boolean mIsConnect = false;
    private LocationRequest mLocationRequest;
    private String token,phone;

    DatabaseReference mDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       // mMapvali = new MapVali();
        mAuthProvider = new AuthProvider();
        mGeofireProvider = new GeofireProvider("active_drivers");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //mButtonConnect = mButtonConnect.findViewById(R.id.on_off);




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
        SwitchCompat onOff = root.findViewById(R.id.on_off);
        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences preferences = requireContext().getSharedPreferences("Datos_Usuario_Login",Context.MODE_PRIVATE);
                if (isChecked) {
                    // The toggle is enabled
                    editStatus(preferences.getString("api_token",""),"1");
                    Log.e("Status","Status usuario activo");
                } else {
                    // The toggle is disabled
                    editStatus(preferences.getString("api_token",""),"0");
                    Log.e("Status","Status usuario inactivo");
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
        SharedPreferences preferences = getContext().getSharedPreferences("Datos_Usuario_Login", Context.MODE_PRIVATE);
        phone = preferences.getString("phone","");
        updateFMToken("https://www.tutumapps.com/api/driver/updateFCMToken");
        get_FCM();
        if (location != null) {

            mCurrentLatLng = new com.google.android.gms.maps.model.LatLng(location.getLatitude(), location.getLongitude());

            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
            Log.d(TAG, "LATITUD: " + lat);
            Log.d(TAG, "LONGITUD: " + lat);

            /*SUBIR UBICACION EN TIEPO REAL A FIREBASE*/

            mDatabase.child("Locations").child(String.valueOf(preferences.getInt("driver_id",0))).child("driver_id").setValue(preferences.getInt("driver_id",0));
            mDatabase.child("Locations").child(String.valueOf(preferences.getInt("driver_id",0))).child("grades").setValue(0);
            mDatabase.child("Locations").child(String.valueOf(preferences.getInt("driver_id",0))).child("latitud").setValue(location.getLatitude());
            mDatabase.child("Locations").child(String.valueOf(preferences.getInt("driver_id",0))).child("longitud").setValue(location.getLongitude());
            mDatabase.child("Locations").child(String.valueOf(preferences.getInt("driver_id",0))).child("name").setValue(preferences.getString("name",""));
            mDatabase.child("Locations").child(String.valueOf(preferences.getInt("driver_id",0))).child("status").setValue(preferences.getString("status",""));
            mDatabase.child("Locations").child(String.valueOf(preferences.getInt("driver_id",0))).child("unit_number").setValue(preferences.getString("vehicle_id", ""));

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

    private void editStatus(String api_token,String status){
        String url = "https://www.tutumapps.com/api/driver/checkChangeStatus";
        SharedPreferences preferences = requireContext().getSharedPreferences("Datos_Usuario_Login",Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_edit = preferences.edit();
        obj_edit.putString("status",status);
        obj_edit.commit();
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
    private void get_FCM(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful())
                {
                    Log.e("FCM","No se pudo obtener el token FCM");
                    return;
                }
                token = task.getResult();
                Log.e("Token FCM","token generado en el registro: "+token);
            }
        });
    }
    public void updateFMToken(String url)  {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            final org.json.JSONObject jsonObject = new org.json.JSONObject();
            jsonObject.put("phone",phone);
            jsonObject.put("fcm_token",token);

            final String requestBody = jsonObject.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Token","respuesta: "+response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("My Tag","Error"+error);
                }
            });
            requestQueue.add(request);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}


