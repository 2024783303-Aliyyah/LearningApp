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

    public void Addquiz(View view) {
        Intent intent = new Intent(this, AddQuiz.class);
        startActivity(intent);

    }
/*
    public void Subtractquiz(View view) {
        Intent intent = new Intent(this, SubtractQuiz.class);
        startActivity(intent);
    }

    public void Multiplyquiz(View view) {
        Intent intent = new Intent(this, MultiplyQuiz.class);
        startActivity(intent);
    }*/
}