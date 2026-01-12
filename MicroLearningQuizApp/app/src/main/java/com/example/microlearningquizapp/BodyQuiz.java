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

import java.util.ArrayList;

// Science - Body Quiz (Image-based MCQ)
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

    // Correct answers as text (for Score activity)
    private final String[] correctAnswersText = {"Eye", "Ear", "Nose", "Hand"};

    // Text for each option (for Score corrections)
    private final String[][] optionTexts = {
            {"Ear", "Eye", "Nose", "Hand"},
            {"Eye", "Nose", "Ear", "Leg"},
            {"Nose", "Mouth", "Hand", "Ear"},
            {"Eye", "Hand", "Ear", "Nose"}
    };

    private int currentQuestion = 0;
    private int selectedOption = -1;
    private int score = 0;

    // Lists for Score.java
    private ArrayList<String> questionsList = new ArrayList<>();
    private ArrayList<String> userAnswers = new ArrayList<>();

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

        // Copy questions into ArrayList
        for (String q : questions) questionsList.add(q);

        // option click listeners
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

        // Save user's selected answer as text
        userAnswers.add(getOptionText(currentQuestion, selectedOption));

        // check correct answer
        if (selectedOption == correctAnswers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        if (currentQuestion < questions.length) {
            loadQuestion();
        } else {
            goToScoreActivity();
        }
    }

    private String getOptionText(int questionIndex, int optionIndex) {
        return optionTexts[questionIndex][optionIndex];
    }

    private void goToScoreActivity() {
        Intent intent = new Intent(this, Score.class);
        intent.putExtra("USER_SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questions.length);
        intent.putStringArrayListExtra("QUESTIONS", questionsList);
        intent.putStringArrayListExtra("USER_ANSWERS", userAnswers);
        intent.putExtra("CORRECT_ANSWERS", correctAnswersText);

        startActivity(intent);
        finish();
    }

    public void goBack(View view) {
        finish();
    }
}
