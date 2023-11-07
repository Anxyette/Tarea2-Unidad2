package com.example.tarea2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener{
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);
        LatLng Sucursal = new LatLng(-38.73765230278501, -72.60752841011255);
        mMap.addMarker(new MarkerOptions().position(Sucursal).title("Sucursal"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Sucursal));
    }
    @Override
    public void onMapClick(@NonNull LatLng latLng){

    }
    @Override
    public void onMapLongClick(@NonNull LatLng latLng){

    }
    public void home(View view){
        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }
}