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

    public void goBack(View view) {
        finish();
    }

    /*public void quiz1(View view) {
        Intent intent = new Intent(this, Quiz1.class);
        startActivity(intent);

    }
    public void quiz2(View view) {
        Intent intent = new Intent(this, Quiz2.class);
        startActivity(intent);
    }
    public void quiz3(View view) {
        Intent intent = new Intent(this, Quiz3.class);
        startActivity(intent);
    }*/
}