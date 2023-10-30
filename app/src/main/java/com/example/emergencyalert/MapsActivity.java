package com.example.emergencyalert;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.emergencyalert.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LatLng unLugar;
    double mLat, mLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    private void EnviarDatos(LatLng latLng){
        Intent i = new Intent(MapsActivity.this,  Reporte.class);
        i.putExtra("Ubicacion", latLng);
        startActivity(i);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
        // Add a marker in Sydney and move the camera
        unLugar = new LatLng(-12.047613928826992, -75.19870320257941);
        mMap.addMarker(new MarkerOptions().position(unLugar).title("SOL"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unLugar));

        CameraPosition cameraPosition=CameraPosition.builder().target(unLugar).zoom(17).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                mMap.clear();
                EnviarDatos(latLng);
                mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.moto))
                        .title("Punto")
                        .snippet("Yo estube aqui")
                        .position(latLng).anchor(0.5F, 0.5f));
                mLat = latLng.latitude;
                mLong = latLng.longitude;
                Toast.makeText(MapsActivity.this, mLat+"<>"+mLong, Toast.LENGTH_SHORT).show();
                Toast.makeText(MapsActivity.this, "Ubicación Establecida", Toast.LENGTH_SHORT).show();
                llamar_Registrar();
            }
        });
    }
    public void llamar_Registrar(){

        Intent i = new Intent(MapsActivity.this, Reporte.class);
        i.putExtra("latitudEnviada",mLat);
        i.putExtra("longitudEnviada",mLong);
        startActivity(i);

    }
}