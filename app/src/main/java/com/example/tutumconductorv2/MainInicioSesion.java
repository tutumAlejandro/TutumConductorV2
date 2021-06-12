package com.example.tutumconductorv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.tutumconductorv2.databinding.ActivityMapsBinding;
import com.example.tutumconductorv2.home.HomeFragment;
import com.example.tutumconductorv2.home.HomeViewModel;
import com.google.android.libraries.maps.CameraUpdate;
import com.google.android.libraries.maps.CameraUpdateFactory;
import com.google.android.libraries.maps.GoogleMap;
import com.google.android.libraries.maps.OnMapReadyCallback;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.android.libraries.maps.model.BitmapDescriptorFactory;
import com.google.android.libraries.maps.model.CameraPosition;
import com.google.android.libraries.maps.model.LatLng;
import com.google.android.libraries.maps.model.Marker;
import com.google.android.libraries.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainInicioSesion extends Fragment {
    private boolean flag = false, flagMap = false;
    private int time = 0;
    private GoogleMap mMap;
    private Marker marcador;
    private final int FINE_LOCATION_CODE = 150;
    private double lat = 0.0, lng = 0.0;
    private Bitmap smallMarker;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel;

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.activity_main_inicio_sesion, container, false);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.pin);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        smallMarker = Bitmap.createScaledBitmap(bitmap, width*16, height*16, false);



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

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
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

        /*  googleMap.setOnMarkerClickListener(HomeFragment.this);
            googleMap.setOnMarkerDragListener(HomeFragment.this);*/

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
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 30);
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
}






/*FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocalizacion();
    }

    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
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
        mMap.setMyLocationEnabled(true);


        LocationManager locationManager = (LocationManager) MainInicioSesion.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(miUbicacion).title("ubicacion actual"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(miUbicacion)
                        .zoom(14)
                        .bearing(90)
                        .tilt(45)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


    }
*/
