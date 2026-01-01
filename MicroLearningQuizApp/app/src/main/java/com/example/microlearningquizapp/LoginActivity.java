package com.example.microlearningquizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    // UI components
    EditText edtUsername, edtPassword;
    MaterialButton btnLogin;
    ProgressBar progressBar;
    LinearLayout loginForm;

    List<UserVO> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    // Connect UI
    edtUsername = findViewById(R.id.edtUsername);
    edtPassword = findViewById(R.id.edtPassword);
    btnLogin = findViewById(R.id.btnLogin);
    progressBar = findViewById(R.id.progressBar);
    loginForm = findViewById(R.id.loginForm);

    // prepare dummy users
    users = fetchAllUsers();

    // app loading
    progressBar.setVisibility(View.VISIBLE);
    loginForm.setVisibility(View.GONE);

    loginForm.postDelayed(() -> {
        progressBar.setVisibility(View.GONE);
        loginForm.setVisibility(View.VISIBLE);
        }, 2000);

        // login button
        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter username and password",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        loginForm.setVisibility(View.GONE);

        edtUsername.postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            loginForm.setVisibility(View.VISIBLE);

            boolean isLoginSuccess = false;

            // check dummy data list
            for (int i = 0; i < users.size(); i++) {
                UserVO user = users.get(i);

                if (user.getUsername().equals(username)
                && user.getPassword().equals(password)) {
                    isLoginSuccess = true;

                    // navigate to StudentDashboardActivity
                    Intent intent = new Intent(LoginActivity.this, StudentDashboardActivity.class);
                    startActivity(intent);
                    finish();

                    break;
                }
            }

            if (!isLoginSuccess) {
                Toast.makeText(this, "Invalid username or password",
                        Toast.LENGTH_SHORT).show();
            }

        }, 1500);
    }

    public List<UserVO> fetchAllUsers() {
        List<UserVO> users = new ArrayList<>();

        users.add(new UserVO("admin", "1234", "Admin"));
        users.add(new UserVO("student", "1111", "Student"));

        return users;
    }
}