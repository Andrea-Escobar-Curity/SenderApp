package com.example.senderapp;

import android.os.Bundle;

import com.google.android.gms.maps.MapView;

public class MapViewLifecycle {

    private final MapView mapView;

    public MapViewLifecycle(MapView mapView) {
        this.mapView = mapView;
    }

    public void onCreate(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);
    }

    public void onResume() {
        mapView.onResume();
    }

    public void onPause() {
        mapView.onPause();
    }

    public void onDestroy() {
        mapView.onDestroy();
    }

    public void onLowMemory() {
        mapView.onLowMemory();
    }

    public void onSaveInstanceState(Bundle outState) {
        mapView.onSaveInstanceState(outState);
    }
}
