package com.example.senderapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ForgotPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        EditText inputRecoveryEmail = findViewById(R.id.inputRecoveryEmail);
        MaterialButton btnSendRecovery = findViewById(R.id.btnSendRecovery);

        btnSendRecovery.setOnClickListener(v -> {
            String email = inputRecoveryEmail.getText().toString().trim();
            if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, R.string.error_email, Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, R.string.recovery_sent, Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
