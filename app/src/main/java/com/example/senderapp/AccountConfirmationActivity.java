package com.example.senderapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class AccountConfirmationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_confirmation);

        MaterialButton btnGoLogin = findViewById(R.id.btnGoLogin);
        btnGoLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
