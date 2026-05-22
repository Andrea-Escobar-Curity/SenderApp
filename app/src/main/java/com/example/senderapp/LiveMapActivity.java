package com.example.senderapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.button.MaterialButton;

public class LiveMapActivity extends BaseActivity {

    private static final int REQUEST_LOCATION = 1001;

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_map);

        TextView nearbyList = findViewById(R.id.nearbyList);
        StringBuilder builder = new StringBuilder();
        for (Route route : RouteCatalog.getRoutes()) {
            builder.append("• ").append(route.getName())
                    .append(" (").append(route.getDistanceKm()).append(" km)\n");
        }
        nearbyList.setText(builder.toString().trim());

        MaterialButton btnRequestGps = findViewById(R.id.btnRequestGps);
        btnRequestGps.setOnClickListener(v -> requestLocationPermission());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.liveMapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(map -> {
                googleMap = map;
                MapsHelper.applyAppStyle(this, googleMap);
                MapsHelper.showAllRoutes(googleMap, RouteCatalog.getRoutes());
                enableMyLocationIfAllowed();
            });
        }
    }

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            enableMyLocationIfAllowed();
            Toast.makeText(this, R.string.gps_permission_granted, Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_LOCATION);
    }

    private void enableMyLocationIfAllowed() {
        if (googleMap == null) {
            return;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try {
            googleMap.setMyLocationEnabled(true);
            TextView gpsStatus = findViewById(R.id.gpsStatus);
            gpsStatus.setText(R.string.gps_active);
        } catch (SecurityException ignored) {
            // Permission revoked between check and enable.
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocationIfAllowed();
                Toast.makeText(this, R.string.gps_permission_granted, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.gps_permission_denied, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
