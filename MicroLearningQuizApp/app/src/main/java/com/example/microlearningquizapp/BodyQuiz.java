package com.example.microlearningquizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class BodyQuiz extends AppCompatActivity {

    private TextView questionText;
    private MaterialCardView[] optionCards;
    private ImageButton[] optionButtons;
    private Button btnSubmit;

    // Questions
    private String[] questions = {
            "Which part of the body helps us see?",
            "Which part of the body helps us hear?",
            "Which part of the body do we use to smell?",
            "Which part of the body do we use to touch?"
    };

    // Options answers
    private int[][] options = {
            { R.drawable.ear, R.drawable.eye, R.drawable.nose, R.drawable.hand }, // Q1
            { R.drawable.eye, R.drawable.nose, R.drawable.ear, R.drawable.leg },  // Q2
            { R.drawable.nose, R.drawable.mouth, R.drawable.hand, R.drawable.ear }, // Q3
            {R.drawable.eye, R.drawable.hand, R.drawable.ear, R.drawable.nose}    // Q4
    };

    // Correct answers (index)
    private int[] correctAnswers = {
            1, // Eye
            2, // Ear
            0,  // Nose
            1   // Hand
    };

    private int currentQuestion = 0;
    private int selectedOption = -1;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_quiz);

        questionText = findViewById(R.id.questionText);
        btnSubmit = findViewById(R.id.btnSubmit);

        optionCards = new MaterialCardView[]{
                findViewById(R.id.cardOption1),
                findViewById(R.id.cardOption2),
                findViewById(R.id.cardOption3),
                findViewById(R.id.cardOption4)
        };

        optionButtons = new ImageButton[]{
                findViewById(R.id.option1),
                findViewById(R.id.option2),
                findViewById(R.id.option3),
                findViewById(R.id.option4)
        };

        for (int i = 0; i < 4; i++) {
            final int index = i;
            optionCards[i].setOnClickListener(v -> selectOption(index));
            optionButtons[i].setOnClickListener(v -> selectOption(index));
        }

        btnSubmit.setOnClickListener(v -> submitAnswer());

        loadQuestion();
    }

    private void loadQuestion() {
        selectedOption = -1;
        questionText.setText(questions[currentQuestion]);

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setImageResource(options[currentQuestion][i]);
            optionCards[i].setStrokeWidth(0);
        }

        btnSubmit.setText(
                currentQuestion == questions.length - 1 ? "Finish" : "Next"
        );
    }

    private void selectOption(int index) {
        selectedOption = index;

        // Highlight selected card
        for (int i = 0; i < 4; i++) {
            if (i == index) {
                optionCards[i].setStrokeWidth(6);
            } else {
                optionCards[i].setStrokeWidth(0);
            }
        }
    }

    private void submitAnswer() {
        if (selectedOption == -1) {
            Toast.makeText(this, "Please select an option!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedOption == correctAnswers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        if (currentQuestion < questions.length) {
            loadQuestion();
        } else {
            SharedPreferences prefs = getSharedPreferences("QUIZ_SCORES", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("SCIENCE_BODY_SCORE", score);
            editor.putInt("SCIENCE_BODY_TOTAL", questions.length);
            editor.apply();

            startActivity(new Intent(this, MyScore.class));
            finish();
        }
    }

    public void goBack(View view) {
        finish();
    }
}
