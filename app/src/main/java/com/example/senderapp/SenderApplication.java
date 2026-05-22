package com.example.senderapp;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.maps.MapsInitializer;

public class SenderApplication extends Application {

    private static final String TAG = "SenderAppMaps";

    @Override
    public void onCreate() {
        super.onCreate();
        MapsInitializer.initialize(
                this,
                MapsInitializer.Renderer.LATEST,
                renderer -> Log.d(TAG, "Maps SDK ready: " + renderer));
    }
}
