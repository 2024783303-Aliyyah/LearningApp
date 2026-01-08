package com.example.microlearningquizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SpaceQuiz extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup radioGroup;
    private RadioButton radioTrue, radioFalse;
    private Button btnSubmit;

    private String[] questions = {
            "The Sun shines at night.",
            "The Moon is smaller than the Sun.",
            "Earth is our planet.",
            "Stars are bigger than the Sun."
    };

    // 1 = True, 0 = False
    private int[] correctAnswers = { 0, 1, 1, 0 };

    private int currentQuestion = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_quiz);

        questionText = findViewById(R.id.questionText);
        radioGroup = findViewById(R.id.radioGroupTF);
        radioTrue = findViewById(R.id.radioTrue);
        radioFalse = findViewById(R.id.radioFalse);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> submitAnswer());

        loadQuestion();
    }

    private void loadQuestion() {
        questionText.setText(questions[currentQuestion]);
        radioGroup.clearCheck();

        btnSubmit.setText(
                currentQuestion == questions.length - 1 ? "Finish" : "Next"
        );
    }

    private void submitAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select True or False", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedAnswer = (selectedId == R.id.radioTrue) ? 1 : 0;

        if (selectedAnswer == correctAnswers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        if (currentQuestion < questions.length) {
            loadQuestion();
        } else {
            SharedPreferences prefs = getSharedPreferences("QUIZ_SCORES", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("SCIENCE_SPACE_SCORE", score);
            editor.putInt("SCIENCE_SPACE_TOTAL", questions.length);
            editor.apply();

            startActivity(new Intent(this, MyScore.class));
            finish();
        }
    }

    public void goBack(View view) {
        finish();
    }
}
