package com.example.senderapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        TextView txtUserName = findViewById(R.id.txtUserName);
        txtUserName.setText(getString(R.string.welcome, SessionManager.getUserName(this)));

        findViewById(R.id.menuExplore).setOnClickListener(v ->
                startActivity(new Intent(this, ExploreRoutesActivity.class)));

        findViewById(R.id.menuFavorites).setOnClickListener(v ->
                startActivity(new Intent(this, FavoritesActivity.class)));

        findViewById(R.id.menuLiveMap).setOnClickListener(v ->
                startActivity(new Intent(this, LiveMapActivity.class)));

        findViewById(R.id.menuProfile).setOnClickListener(v ->
                startActivity(new Intent(this, ProfileActivity.class)));

        findViewById(R.id.menuSettings).setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));
    }
}
