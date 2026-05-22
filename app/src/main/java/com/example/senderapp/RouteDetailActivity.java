package com.example.senderapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.button.MaterialButton;

public class RouteDetailActivity extends BaseActivity {

    public static final String EXTRA_ROUTE_ID = "route_id";

    private Route route;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_detail);

        int routeId = getIntent().getIntExtra(EXTRA_ROUTE_ID, 0);
        route = RouteCatalog.getById(routeId);

        ImageView routePhoto = findViewById(R.id.routePhoto);
        TextView routeTitle = findViewById(R.id.routeTitle);
        TextView routeLocation = findViewById(R.id.routeLocation);
        TextView routeInfo = findViewById(R.id.routeInfo);
        TextView routeRecommendations = findViewById(R.id.routeRecommendations);
        TextView routeDescription = findViewById(R.id.routeDescription);
        TextView mapErrorHint = findViewById(R.id.mapErrorHint);
        MaterialButton btnFavorite = findViewById(R.id.btnFavorite);
        MaterialButton btnStartRoute = findViewById(R.id.btnStartRoute);

        routePhoto.setImageResource(route.getImageResId());
        routeTitle.setText(route.getName());
        routeLocation.setText(route.getLocation());
        routeInfo.setText(route.getLength() + " · Duración: " + route.getDuration()
                + " · Dificultad: " + route.getDifficulty());
        routeRecommendations.setText(route.getRecommendations());
        routeDescription.setText(route.getDescription());

        if (!MapsHelper.isApiKeyConfigured(this)) {
            mapErrorHint.setVisibility(View.VISIBLE);
            mapErrorHint.setText(R.string.map_api_key_missing);
        } else if (!isPlayServicesReady()) {
            mapErrorHint.setVisibility(View.VISIBLE);
            mapErrorHint.setText(R.string.play_services_missing);
        } else {
            setupMap(mapErrorHint);
        }

        updateFavoriteButton(btnFavorite);

        btnFavorite.setOnClickListener(v -> {
            FavoritesManager.toggleFavorite(this, route.getId());
            updateFavoriteButton(btnFavorite);
            boolean isFavorite = FavoritesManager.isFavorite(this, route.getId());
            Toast.makeText(this,
                    isFavorite ? R.string.favorite_added : R.string.favorite_removed,
                    Toast.LENGTH_SHORT).show();
        });

        btnStartRoute.setOnClickListener(v ->
                Toast.makeText(this, getString(R.string.route_started, route.getName()),
                        Toast.LENGTH_SHORT).show());
    }

    private boolean isPlayServicesReady() {
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance().getErrorDialog(this, status, 9000);
            return false;
        }
        return true;
    }

    private void setupMap(TextView mapErrorHint) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.routeMapFragment);
        if (mapFragment == null) {
            return;
        }

        mapFragment.getMapAsync(googleMap -> {
            MapsHelper.applyAppStyle(this, googleMap);
            MapsHelper.showRoute(googleMap, route);
            enableMapGesturesInScroll(mapFragment, googleMap);

            handler.postDelayed(() -> {
                if (googleMap.getCameraPosition().zoom > 0) {
                    mapErrorHint.setVisibility(View.GONE);
                }
            }, 2500);
        });

        mapErrorHint.setText(R.string.maps_load_hint);
        mapErrorHint.setVisibility(View.VISIBLE);
        handler.postDelayed(() -> {
            if (mapErrorHint.getVisibility() == View.VISIBLE) {
                mapErrorHint.setText(R.string.maps_auth_hint);
            }
        }, 4000);
    }

    private void enableMapGesturesInScroll(SupportMapFragment mapFragment, GoogleMap googleMap) {
        View mapView = mapFragment.getView();
        if (mapView == null) {
            return;
        }
        mapView.setOnTouchListener((v, event) -> {
            if (v.getParent() instanceof ViewGroup) {
                ((ViewGroup) v.getParent()).requestDisallowInterceptTouchEvent(true);
            }
            return false;
        });
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
    }

    private void updateFavoriteButton(MaterialButton btnFavorite) {
        if (FavoritesManager.isFavorite(this, route.getId())) {
            btnFavorite.setText(R.string.btn_remove_favorite);
        } else {
            btnFavorite.setText(R.string.btn_add_favorite);
        }
    }
}
