package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class adminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.adminDashboard), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void openAdminActivity(View view)
    {
       int id = view.getId();
       if(id == R.id.cardLessons)
       {
           startActivity(new Intent(this, adminViewLesson.class));
       }
       else if(id == R.id.cardScores) //kena edit balik link ke view score
       {
           startActivity(new Intent(this, Score.class));
       }
       else if(id == R.id.cardReport) {
           startActivity(new Intent(this, Report.class));
       }


    }
}