package com.example.emergencyalert;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Toast;


import com.example.emergencyalert.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaReporte extends AppCompatActivity implements OnMapReadyCallback {

    double latPed,lonPed;
    String nombre, apellidos, telefono, descripcion;
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
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Bundle extrasp = getIntent().getExtras();

        if (extrasp != null) {
            latPed = extrasp.getDouble("latitudPedido");
            lonPed = extrasp.getDouble("longitudPedido");
            nombre = extrasp.getString("nombre");
            apellidos = extrasp.getString("apellidos");
            telefono = extrasp.getString("telefono");
            descripcion = extrasp.getString("descripcion");
        } else {

        }

        // Add a marker in Sydney and move the camera
        LatLng PedidoCoor = new LatLng(latPed, lonPed);
        mMap.addMarker(
                new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.moto))
                        .position(PedidoCoor)
                        .title("Reportes")
                        .snippet("Nombre:"+nombre+" "+"Apellidos: "+apellidos+" "+"Telefono: "+telefono+" "+"Descripcion: "+descripcion ));

        CameraPosition cameraPosition = CameraPosition.builder().target(PedidoCoor).zoom(17).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        Toast.makeText(this, "Coordenadas Reporte "+latPed+"<>"+lonPed, Toast.LENGTH_SHORT).show();
    }
}