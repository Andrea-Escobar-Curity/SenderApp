package com.example.senderapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        TextView profileName = findViewById(R.id.profileName);
        TextView profileProgress = findViewById(R.id.profileProgress);
        MaterialButton btnEditProfile = findViewById(R.id.btnEditProfile);

        profileName.setText(SessionManager.getUserName(this));
        int completed = 1;
        int total = RouteCatalog.getRoutes().size();
        profileProgress.setText(getString(R.string.profile_progress, completed, total));

        btnEditProfile.setOnClickListener(v ->
                Toast.makeText(this, R.string.edit_profile_toast, Toast.LENGTH_SHORT).show());
    }
}
