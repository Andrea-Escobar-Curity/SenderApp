package com.example.senderapp;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Ensures system screenshots and screen recording are allowed on every screen.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allowScreenshots();
    }

    @Override
    protected void onResume() {
        super.onResume();
        allowScreenshots();
    }

    private void allowScreenshots() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }
}
