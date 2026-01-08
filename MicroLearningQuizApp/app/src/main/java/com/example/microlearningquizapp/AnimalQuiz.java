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

public class AnimalQuiz extends AppCompatActivity {

    private TextView questionText;
    private MaterialCardView[] optionCards;
    private ImageButton[] optionButtons;
    private Button btnSubmit;
    // Questions

    private String[] questions = {
            "Which animal says \"Meow\"?",
            "Which animal lives in water?",
            "Which animal lays eggs?",
            "Which animal is the biggest?"
    };

    // option answers
    private int[][] options = {
            {R.drawable.dog, R.drawable.cat, R.drawable.cow, R.drawable.chicken},   // Q1
            { R.drawable.fish, R.drawable.dog, R.drawable.lion, R.drawable.cow },   // Q2
            { R.drawable.cow, R.drawable.dog, R.drawable.chicken, R.drawable.cat }, // Q3
            { R.drawable.dog, R.drawable.monkey, R.drawable.rabbit, R.drawable.elephant } // Q4
    };

    // correct answer indices
    private int[] correctAnswers = {    // index of correct option per question
            1,  // cat
            0,  // fish
            2,  // chicken
            3   // elephant
    };

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

        // load / set images
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setImageResource(options[currentQuestion][i]);
            optionCards[i].setStrokeWidth(0); // reset selection highlight
        }

        // button text
        if (currentQuestion == questions.length - 1) {
            btnSubmit.setText("Finish");
        } else {
            btnSubmit.setText("Next");
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

        // check answer
        if (selectedOption == correctAnswers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        if (currentQuestion < questions.length) {
            loadQuestion();
        } else {
            // save score to SharedPreferences once quiz finished
            SharedPreferences prefs = getSharedPreferences("QUIZ_SCORES", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("SCIENCE_ANIMALS_SCORE", score);
            editor.putInt("SCIENCE_ANIMALS_TOTAL", questions.length);
            editor.apply();

            // go to MyScore page
            Intent intent = new Intent(AnimalQuiz.this, MyScore.class);
            startActivity(intent);
            finish();
        }
    }

    // Back button
    public void goBack(View view) {
        finish();
    }
}