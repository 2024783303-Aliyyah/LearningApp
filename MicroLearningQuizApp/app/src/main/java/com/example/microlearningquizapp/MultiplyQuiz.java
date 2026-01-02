package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MultiplyQuiz extends AppCompatActivity {

    private EditText input1, input2, input3, input4;
    private Button btnFinish;
    private ImageView btnBack;

    // 🔢 Multiplication questions & answers
    private final String[] questions = {
            "2 × 2 = ?",
            "3 × 4 = ?",
            "2 × 3 = ?",
            "2 × 4 = ?"
    };

    private final int[] correctAnswers = {4, 12, 6, 8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_multiply_quiz);

        // Initialize / link views
        input1 = findViewById(R.id.multiply_input_1);
        input2 = findViewById(R.id.multiply_input_2);
        input3 = findViewById(R.id.multiply_input_3);
        input4 = findViewById(R.id.multiply_input_4);
        btnFinish = findViewById(R.id.finishMultiply);
        btnBack = findViewById(R.id.btnBackMultiply);

        // Set system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.multiplyquiz), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Back button logic
        btnBack.setOnClickListener(v -> finish());

        // Finish button logic
        btnFinish.setOnClickListener(v -> {
            checkAnswers();
        });
    }

    private void checkAnswers() {
        // Answers for multiplication questions
        // 2x2=4, 5x3=15, 10x2=20, 4x4=16
        String val1 = input1.getText().toString().trim();
        String val2 = input2.getText().toString().trim();
        String val3 = input3.getText().toString().trim();
        String val4 = input4.getText().toString().trim();

        // check empty input
        if (val1.isEmpty() || val2.isEmpty() || val3.isEmpty() || val4.isEmpty()) {
            Toast.makeText(this, "Please answer all questions!", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Integer> userAnswers = new ArrayList<>();
        userAnswers.add(Integer.parseInt(val1));
        userAnswers.add(Integer.parseInt(val2));
        userAnswers.add(Integer.parseInt(val3));
        userAnswers.add(Integer.parseInt(val4));

        int score = 0;
        for (int i = 0; i < correctAnswers.length; i++) {
            if (userAnswers.get(i).equals(correctAnswers[i])) {
                score++;
            }
        }

        goToScoreActivity(score, userAnswers);
    }

    private void goToScoreActivity(int finalScore, ArrayList<Integer> userAnswers) {

        Intent intent = new Intent(MultiplyQuiz.this, score.class);

        intent.putExtra("USER_SCORE", finalScore);
        intent.putExtra("TOTAL_QUESTIONS", correctAnswers.length);
        intent.putExtra("QUESTIONS_ARRAY", questions);
        intent.putIntegerArrayListExtra("USER_ANSWERS_LIST", userAnswers);
        intent.putExtra("CORRECT_ANSWERS_ARRAY", correctAnswers);

        startActivity(intent);
        finish(); // prevent returning to quiz
    }

    // If used via XML onClick
    public void goBack(View view) {
        finish();
    }
}
