package com.example.emergencyalert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Entidad.Reportes;

public class Reporte extends MapsActivity {

    EditText nombre, apellidos, telefono, descripcion;
    Button btnregistrarUbicacion,btnaceptar;
    double latEnv,lonEnv;

    Bundle receptor;
    TextView txtLatitud, txtLongitud;
    LatLng ubi;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        inicializarFirebase();

        nombre = findViewById(R.id.edtnombre);
        apellidos = findViewById(R.id.edtpellidos);
        telefono = findViewById(R.id.edttelefono);
        descripcion = findViewById(R.id.edtdescripcion);
        txtLatitud=findViewById(R.id.txtLat);
        txtLongitud=findViewById(R.id.txtLong);

        btnregistrarUbicacion = findViewById(R.id.btnRegistrarUbicacion);
        btnaceptar = findViewById(R.id.btnAceptar);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            latEnv = extras.getDouble("latitudEnviada");
            lonEnv = extras.getDouble("longitudEnviada");
            txtLatitud.setText(Double.toString(latEnv));
            txtLongitud.setText(Double.toString(lonEnv));
        } else {

        }


        btnregistrarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Reporte.this, MapsActivity.class);
                startActivity(i);
            }
        });
        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarReporte();
            }
        });


    }

    private void registrarReporte() {
        String nombres = nombre.getText().toString();
        String apellido = apellidos.getText().toString();
        String telefonos = telefono.getText().toString();
        String descripciones = descripcion.getText().toString();

        String id = mDatabase.push().getKey();

        Reportes reportes = new Reportes(id,nombres,apellido,telefonos,descripciones,latEnv,lonEnv);


        //pedido.setIdPedido(id);
        mDatabase.child("Reportes").child(id).setValue(reportes);
        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        FirebaseDatabase db = FirebaseDatabase.getInstance(); //Utiliza el archivo generado para conectarte
        mDatabase = db.getReference();
    }
}