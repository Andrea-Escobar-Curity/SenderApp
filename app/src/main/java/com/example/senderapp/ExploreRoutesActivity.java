package com.example.senderapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ExploreRoutesActivity extends BaseActivity {

    private static final String[] LOCATIONS = {"Todas", "Bogotá", "Medellín", "Manizales"};
    private static final String[] DIFFICULTIES = {"Todas", "Media", "Media-Alta", "Alta"};
    private static final String[] DISTANCES = {"Todas", "≤ 6 km", "6–10 km", "> 10 km"};

    private int locationIndex;
    private int difficultyIndex;
    private int distanceIndex;

    private LinearLayout routesContainer;
    private TextView filterStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_routes);

        routesContainer = findViewById(R.id.routesContainer);
        filterStatus = findViewById(R.id.filterStatus);

        MaterialButton filterLocation = findViewById(R.id.filterLocation);
        MaterialButton filterDifficulty = findViewById(R.id.filterDifficulty);
        MaterialButton filterDistance = findViewById(R.id.filterDistance);

        filterLocation.setOnClickListener(v -> {
            locationIndex = (locationIndex + 1) % LOCATIONS.length;
            applyFilters();
        });
        filterDifficulty.setOnClickListener(v -> {
            difficultyIndex = (difficultyIndex + 1) % DIFFICULTIES.length;
            applyFilters();
        });
        filterDistance.setOnClickListener(v -> {
            distanceIndex = (distanceIndex + 1) % DISTANCES.length;
            applyFilters();
        });

        applyFilters();
    }

    private void applyFilters() {
        filterStatus.setText(getString(R.string.filter_all) + " · "
                + LOCATIONS[locationIndex] + " · "
                + DIFFICULTIES[difficultyIndex] + " · "
                + DISTANCES[distanceIndex]);

        List<Route> filtered = new ArrayList<>();
        for (Route route : RouteCatalog.getRoutes()) {
            if (matchesFilters(route)) {
                filtered.add(route);
            }
        }
        RouteListHelper.bindRoutes(this, routesContainer, filtered);
    }

    private boolean matchesFilters(Route route) {
        if (locationIndex > 0 && !route.getLocation().equals(LOCATIONS[locationIndex])) {
            return false;
        }
        if (difficultyIndex > 0 && !route.getDifficulty().equals(DIFFICULTIES[difficultyIndex])) {
            return false;
        }
        if (distanceIndex == 1 && route.getDistanceKm() > 6f) {
            return false;
        }
        if (distanceIndex == 2 && (route.getDistanceKm() <= 6f || route.getDistanceKm() > 10f)) {
            return false;
        }
        if (distanceIndex == 3 && route.getDistanceKm() <= 10f) {
            return false;
        }
        return true;
    }
}
