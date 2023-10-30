package com.example.emergencyalert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuLlamada extends AppCompatActivity {

    private static final int CALL_PERMISSION_REQUEST_CODE = 101;

    Button btnPolicia, btnAmbulancia, btnBomberos, btnCruzRoja, btnSerenazgo, btnDesastres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_llamada);

        btnPolicia = findViewById(R.id.btnPolicia);
        btnAmbulancia = findViewById(R.id.btnAmbulancia);
        btnBomberos = findViewById(R.id.btnBomberos);
        btnCruzRoja = findViewById(R.id.btnCruzRoja);
        btnSerenazgo = findViewById(R.id.btnSerenazgo);
        btnDesastres = findViewById(R.id.btnDesastres);

        btnPolicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("064-211637");
            }
        });
        btnAmbulancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("911");
            }
        });
        btnBomberos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("064-211020");
            }
        });
        btnCruzRoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("106");
            }
        });
        btnSerenazgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("064-202050");
            }
        });
        btnDesastres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("119");
            }
        });
    }
    private void makePhoneCall(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(MenuLlamada.this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));

            if (callIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(callIntent);
            }
        } else {
            ActivityCompat.requestPermissions(MenuLlamada.this,
                    new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }
}