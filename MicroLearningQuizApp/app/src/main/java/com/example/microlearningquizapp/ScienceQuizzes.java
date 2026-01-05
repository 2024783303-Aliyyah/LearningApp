package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScienceQuizzes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_science_quizzes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sciencequiz), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void opensciencequiz(View view) {
        int id = view.getId();
        if (id == R.id.AnimalQuiz) {
            startActivity(new Intent(this, AnimalQuiz.class));
        } else if (id == R.id.BodyQuiz) {
            startActivity(new Intent(this, BodyQuiz.class));
        } else if (id == R.id.SpaceQuiz) {
            startActivity(new Intent(this, SpaceQuiz.class));
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

    public void goBack(View view) {
        finish();
    }

}