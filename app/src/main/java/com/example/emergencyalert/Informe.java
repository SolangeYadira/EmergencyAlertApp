package com.example.emergencyalert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import Entidad.Reportes;

public class Informe extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;
    ListView lstPedidosEntregar;
    List<Reportes> listPedido=new ArrayList<Reportes>();
    ArrayAdapter<Reportes> arrayAdapterPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);
        inicializarFirebase();
        lstPedidosEntregar=findViewById(R.id.lstInforme);
        
        listarReportes();
    }

    private void listarReportes() {

        mDatabase.child("Reportes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot resultados) {
                listPedido.clear();
                for (DataSnapshot itemResultado : resultados.getChildren()){
                    Reportes p=itemResultado.getValue(Reportes.class);
                    listPedido.add(p);
                }
                arrayAdapterPedidos = new ArrayAdapter<Reportes>(Informe.this, android.R.layout.simple_list_item_1, listPedido);
                lstPedidosEntregar.setAdapter(arrayAdapterPedidos);

                lstPedidosEntregar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {

                        double mylat=listPedido.get(posicion).getLatitud();
                        double mylon=listPedido.get(posicion).getLongitud();
                        String nombre = listPedido.get(posicion).getNombre();
                        String apellidos = listPedido.get(posicion).getApellidos();
                        String telefono = listPedido.get(posicion).getTelefono();
                        String descripcion = listPedido.get(posicion).getDescripcion();

                        Intent i = new Intent(Informe.this, MapaReporte.class);
                        i.putExtra("latitudPedido",mylat);
                        i.putExtra("longitudPedido",mylon);
                        i.putExtra("nombre",nombre);
                        i.putExtra("apellidos",apellidos);
                        i.putExtra("telefono",telefono);
                        i.putExtra("descripcion",descripcion);
                        startActivity(i);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        mDatabase=db.getReference();

    }
}