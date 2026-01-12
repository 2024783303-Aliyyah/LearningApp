package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.microlearningquizapp.model.FailLogin;
import com.example.microlearningquizapp.model.User;
import com.example.microlearningquizapp.remote.ApiUtils;
import com.example.microlearningquizapp.remote.UserService;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    // UI components
    EditText edtUsername, edtPassword;
    MaterialButton btnLogin;
    ProgressBar progressBar;
    LinearLayout loginForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });

        // connect UI
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        loginForm = findViewById(R.id.loginForm);

        // loading awal
        progressBar.setVisibility(View.VISIBLE);
        loginForm.setVisibility(View.GONE);

        loginForm.postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            loginForm.setVisibility(View.VISIBLE);
        }, 1500);

        // login button
        btnLogin.setOnClickListener(v -> doLogin());
    }

    /**
     * Call REST API to login
     */
    private void doLogin() {

        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        // validation
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this,
                    "Please enter username and password",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        loginForm.setVisibility(View.GONE);

        // get retrofit service
        UserService userService = ApiUtils.getUserService();

        // prepare API call
        Call<User> call = userService.login(username, password);

        // execute API
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call,
                                   Response<User> response) {

                progressBar.setVisibility(View.GONE);
                loginForm.setVisibility(View.VISIBLE);

                if (response.isSuccessful()) {

                    User user = response.body();

                    if (user != null && user.getToken() != null) {

                        Toast.makeText(LoginActivity.this,
                                "Login successful",
                                Toast.LENGTH_SHORT).show();

                        // go to dashboard
                        Intent intent = new Intent(
                                LoginActivity.this,
                                StudentDashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this,
                                "Login error",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    // login failed (401 etc)
                    try {
                        String errorBody =
                                response.errorBody().string();

                        FailLogin failLogin =
                                new Gson().fromJson(
                                        errorBody,
                                        FailLogin.class);

                        Toast.makeText(LoginActivity.this,
                                failLogin.getError().getMessage(),
                                Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this,
                                "Login failed",
                                Toast.LENGTH_SHORT).show();
                        Log.e("LOGIN_ERROR", e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                loginForm.setVisibility(View.VISIBLE);

                Toast.makeText(LoginActivity.this,
                        "Error connecting to server",
                        Toast.LENGTH_SHORT).show();

                Log.e("LOGIN_ERROR", t.getMessage());
            }
        });
    }
}
