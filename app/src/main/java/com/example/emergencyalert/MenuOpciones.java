package com.example.emergencyalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MenuOpciones extends AppCompatActivity {

    Button btnSalir, btnReporte, btnAlarma, btnLlamar, btnPreguntas, btnInforme, btnRecomendaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);

        btnReporte = findViewById(R.id.btnReportar);
        btnLlamar = findViewById(R.id.btnLlamar);
        btnAlarma = findViewById(R.id.btnAlertar);
        btnInforme = findViewById(R.id.btnInforme);
        btnPreguntas = findViewById(R.id.btnPreguntas);
        btnRecomendaciones = findViewById(R.id.btnRecomendaciones);
        btnSalir = findViewById(R.id.btnRegresarLogin);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        btnReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Reporte.class);
                startActivity(intent);
                finish();
            }
        });

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuLlamada.class);
                startActivity(intent);
                finish();
            }
        });

        btnAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mensaje.class);
                startActivity(intent);
                finish();
            }
        });
        btnInforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Informe.class);
                startActivity(intent);
                finish();
            }
        });
    }
}