package com.example.senderapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SessionManager.isLoggedIn(this)) {
            openDashboard();
            return;
        }

        setContentView(R.layout.activity_welcome);

        MaterialButton btnStartLogin = findViewById(R.id.btnStartLogin);
        MaterialButton btnRegister = findViewById(R.id.btnRegister);

        btnStartLogin.setOnClickListener(v ->
                startActivity(new Intent(this, LoginActivity.class)));

        btnRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void openDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
