package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MathQuizzes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_math_quizzes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mathquiz), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void goBack(View view) {
        finish();
    }

    public void openmathquiz(View view) {
        int id = view.getId();
        if (id == R.id.addquiz) {
            startActivity(new Intent(this, AddQuiz.class));
        } else if (id == R.id.subtractionquiz) {
            startActivity(new Intent(this, SubtractQuiz.class));
        } else if (id == R.id.multiplyquiz) {
            startActivity(new Intent(this, MultiplyQuiz.class));
        }
    }
}