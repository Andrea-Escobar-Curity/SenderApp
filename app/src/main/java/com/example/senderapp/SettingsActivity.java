package com.example.senderapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends BaseActivity {

    private static final String PREFS = "senderapp_prefs";
    private static final String KEY_DARK_MODE = "dark_mode";
    private static final String KEY_HIGH_CONTRAST = "high_contrast";
    private static final String KEY_NOTIFICATIONS = "notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);

        SwitchMaterial switchDarkMode = findViewById(R.id.switchDarkMode);
        SwitchMaterial switchHighContrast = findViewById(R.id.switchHighContrast);
        SwitchMaterial switchNotifications = findViewById(R.id.switchNotifications);
        Spinner spinnerLanguage = findViewById(R.id.spinnerLanguage);
        MaterialButton btnGpsSettings = findViewById(R.id.btnGpsSettings);
        MaterialButton btnLogout = findViewById(R.id.btnLogout);

        switchDarkMode.setChecked(prefs.getBoolean(KEY_DARK_MODE, true));
        switchHighContrast.setChecked(prefs.getBoolean(KEY_HIGH_CONTRAST, false));
        switchNotifications.setChecked(prefs.getBoolean(KEY_NOTIFICATIONS, true));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{getString(R.string.language_spanish), "English"});
        spinnerLanguage.setAdapter(adapter);

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean(KEY_DARK_MODE, isChecked).apply();
            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        });

        switchHighContrast.setOnCheckedChangeListener((buttonView, isChecked) ->
                prefs.edit().putBoolean(KEY_HIGH_CONTRAST, isChecked).apply());

        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) ->
                prefs.edit().putBoolean(KEY_NOTIFICATIONS, isChecked).apply());

        btnGpsSettings.setOnClickListener(v ->
                startActivity(new Intent(this, LiveMapActivity.class)));

        btnLogout.setOnClickListener(v -> {
            SessionManager.logout(this);
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
