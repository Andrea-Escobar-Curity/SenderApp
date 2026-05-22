package com.example.senderapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText inputUser = findViewById(R.id.inputUser);
        EditText inputPassword = findViewById(R.id.inputPassword);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        TextView linkForgotPassword = findViewById(R.id.linkForgotPassword);
        TextView linkRegister = findViewById(R.id.linkRegister);

        linkForgotPassword.setOnClickListener(v ->
                startActivity(new Intent(this, ForgotPasswordActivity.class)));

        linkRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));

        btnLogin.setOnClickListener(v -> {
            String user = inputUser.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();
            if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, R.string.error_login, Toast.LENGTH_SHORT).show();
                return;
            }
            SessionManager.login(this, user);
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
