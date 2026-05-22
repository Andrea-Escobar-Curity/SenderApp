package com.example.senderapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class FavoritesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshFavorites();
    }

    private void refreshFavorites() {
        LinearLayout container = findViewById(R.id.favoritesContainer);
        TextView emptyView = findViewById(R.id.favoritesEmpty);

        List<Route> favorites = FavoritesManager.getFavoriteRoutes(this);
        if (favorites.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
            container.removeAllViews();
        } else {
            emptyView.setVisibility(View.GONE);
            RouteListHelper.bindRoutes(this, container, favorites);
        }
    }
}
