package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void openadminMenu(View view)
    {
        int id = view.getId();
        if (id == R.id.adminnavProfile) {
            startActivity(new Intent(this, AdminProfile.class));
        }
        else if (id == R.id.adminnavHome) {
            startActivity(new Intent(this, adminDashboard.class));
        }
        else if (id == R.id.adminnavScores) {
            startActivity(new Intent(this, AdminReportActivity.class));
        }
    }


    // Tambah fungsi ini
    public void goBack(View view) {
        finish(); // 'finish()' akan menutup aktiviti semasa dan kembali ke skrin sebelumnya
    }

}