package com.example.alawan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AcceuilActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap MapAccueil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        MapAccueil = googleMap;

        // Faut modiifer ce code pour permettre d afficher les position de la bd
        LatLng sydney = new LatLng(-34, 151);
        MapAccueil.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        MapAccueil.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}