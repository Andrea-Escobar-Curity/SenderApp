package com.example.senderapp;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public final class MapsHelper {

    private static final String TAG = "SenderAppMaps";
    private static final float ROUTE_ZOOM = 13f;

    private MapsHelper() {
    }

    public static boolean isApiKeyConfigured(Context context) {
        String key = context.getString(R.string.google_maps_key);
        return !TextUtils.isEmpty(key) && !key.startsWith("YOUR_");
    }

    public static void applyAppStyle(Context context, GoogleMap map) {
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setCompassEnabled(true);

        try {
            MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style_dark);
            boolean applied = map.setMapStyle(style);
            if (!applied) {
                Log.w(TAG, "Dark map style not applied; using default tiles");
            }
        } catch (Exception e) {
            Log.w(TAG, "Map style error, using default tiles", e);
        }
    }

    public static void showRoute(GoogleMap map, Route route) {
        LatLng position = new LatLng(route.getLatitude(), route.getLongitude());
        map.clear();
        map.addMarker(new MarkerOptions()
                .position(position)
                .title(route.getName())
                .snippet(route.getLocation()));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, ROUTE_ZOOM));
    }

    public static void showAllRoutes(GoogleMap map, List<Route> routes) {
        map.clear();
        if (routes.isEmpty()) {
            return;
        }

        LatLngBounds.Builder bounds = new LatLngBounds.Builder();
        for (Route route : routes) {
            LatLng position = new LatLng(route.getLatitude(), route.getLongitude());
            map.addMarker(new MarkerOptions()
                    .position(position)
                    .title(route.getName())
                    .snippet(route.getLength()));
            bounds.include(position);
        }

        if (routes.size() == 1) {
            Route only = routes.get(0);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(only.getLatitude(), only.getLongitude()),
                    ROUTE_ZOOM));
        } else {
            try {
                map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 120));
            } catch (IllegalStateException e) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(routes.get(0).getLatitude(), routes.get(0).getLongitude()),
                        ROUTE_ZOOM));
            }
        }
    }
}
