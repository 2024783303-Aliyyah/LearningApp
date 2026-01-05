package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class AnimalQuiz extends AppCompatActivity {

    private TextView questionText;
    private MaterialCardView[] optionCards;
    private ImageButton[] optionButtons;
    private Button btnSubmit;

    private String[] questions = {
            "Which animal says 'Meow'?",

    };

    private int[][] options = {
            {R.drawable.dog, R.drawable.cat, R.drawable.cow, R.drawable.chicken},

    };

    private int[] correctAnswers = {1, 1, 0}; // index of correct option per question

    private int currentQuestion = 0;
    private int selectedOption = -1; // -1 means no selection yet
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_quiz);

        // Link views
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

        // Set click listeners for options
        for (int i = 0; i < 4; i++) {
            final int index = i;
            optionCards[i].setOnClickListener(v -> selectOption(index));
            optionButtons[i].setOnClickListener(v -> selectOption(index));
        }

        // Set click listener for submit button
        btnSubmit.setOnClickListener(v -> submitAnswer());

        // Load the first question
        loadQuestion();
    }

    private void loadQuestion() {
        selectedOption = -1; // reset selection
        questionText.setText(questions[currentQuestion]);

        // Set images
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setImageResource(options[currentQuestion][i]);
            optionCards[i].setStrokeWidth(0); // reset border
        }
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
            // Quiz finished, go to score page
            Intent intent = new Intent(AnimalQuiz.this, Score.class);
            intent.putExtra("USER_SCORE", score);
            intent.putExtra("TOTAL_QUESTIONS", questions.length);
            startActivity(intent);
            finish();
        }
    }

    // Back button
    public void goBack(View view) {
        finish();
    }
}