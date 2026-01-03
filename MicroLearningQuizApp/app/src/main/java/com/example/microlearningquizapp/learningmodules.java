package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import com.example.microlearningquizapp.MathLessons;
import com.example.microlearningquizapp.EnglishLessons;
import com.example.microlearningquizapp.ScienceLessons;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;

public class learningmodules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_learningmodules);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.learningmodules), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void openLessons(View view) {
        int id = view.getId();
        if (id == R.id.math) {
            startActivity(new Intent(this, MathLessons.class));
        } else if (id == R.id.english) {
            startActivity(new Intent(this, EnglishLessons.class));
        } else if (id == R.id.science) {
            startActivity(new Intent(this, ScienceLessons.class));
        }
    }

    public void openMenu(View view)
    {
        int id = view.getId();
        if (id == R.id.navProfile) {
            startActivity(new Intent(this, UserProfileActivity.class));
        } else if (id == R.id.navLessons) {
            startActivity(new Intent(this, learningmodules.class));
        } else if (id == R.id.navHome) {
            startActivity(new Intent(this, StudentDashboardActivity.class));
        } else if (id == R.id.navQuiz) {
            startActivity(new Intent(this, quizzesmodules.class));
        }
    }


}
