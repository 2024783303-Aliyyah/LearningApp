package com.example.microlearningquizapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

// Quiz Result Page
public class Score extends AppCompatActivity {

    TextView textFinalScore;
    TextView correctionsHeader;
    LinearLayout correctionsContainer;  // container for wrong answers

    // quiz results passed from previous activity
    ArrayList<String> questions;
    ArrayList<String> userAnswers;
    String[] correctAnswers;
    int totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score);

        // Apply system bar insets to root view
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.score), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Link xml views
        textFinalScore = findViewById(R.id.textFinalScore);
        correctionsHeader = findViewById(R.id.correctionsHeader);
        correctionsContainer = findViewById(R.id.correctionsContainer);

        // get data passed from quiz activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            int userScore = extras.getInt("USER_SCORE");
            totalQuestions = extras.getInt("TOTAL_QUESTIONS");

            // arrays for corrections
            questions = extras.getStringArrayList("QUESTIONS");
            userAnswers = extras.getStringArrayList("USER_ANSWERS");
            correctAnswers = extras.getStringArray("CORRECT_ANSWERS");

            // display final score
            textFinalScore.setText(userScore + " / " + totalQuestions);

            // save score (SharedPreferences)
            saveScoresToPrefs(extras);

            // show corrections
            showCorrections();
        }
    }

    private void saveScoresToPrefs(Bundle extras) {
        SharedPreferences prefs = getSharedPreferences("QUIZ_SCORES", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Math topics
        editor.putInt("MATH_ADD_IT_UP_SCORE", extras.getInt("MATH_ADD_IT_UP_SCORE", 0));
        editor.putInt("MATH_ADD_IT_UP_TOTAL", extras.getInt("MATH_ADD_IT_UP_TOTAL", 5));

        editor.putInt("MATH_SUBTRACT_QUEST_SCORE", extras.getInt("MATH_SUBTRACT_QUEST_SCORE", 0));
        editor.putInt("MATH_SUBTRACT_QUEST_TOTAL", extras.getInt("MATH_SUBTRACT_QUEST_TOTAL", 5));

        editor.putInt("MATH_MULTIPLICATION_SCORE", extras.getInt("MATH_MULTIPLICATION_SCORE", 0));
        editor.putInt("MATH_MULTIPLICATION_TOTAL", extras.getInt("MATH_MULTIPLICATION_TOTAL", 5));

        // English topics
        editor.putInt("ENGLISH_ALPHABET_ADVENTURE_SCORE", extras.getInt("ENGLISH_ALPHABET_ADVENTURE_SCORE", 0));
        editor.putInt("ENGLISH_ALPHABET_ADVENTURE_TOTAL", extras.getInt("ENGLISH_ALPHABET_ADVENTURE_TOTAL", 5));

        editor.putInt("ENGLISH_FUN_SYNONYM_SCORE", extras.getInt("ENGLISH_FUN_SYNONYM_SCORE", 0));
        editor.putInt("ENGLISH_FUN_SYNONYM_TOTAL", extras.getInt("ENGLISH_FUN_SYNONYM_TOTAL", 5));

        // Science topics
        editor.putInt("SCIENCE_ANIMALS_SCORE", extras.getInt("SCIENCE_ANIMALS_SCORE", 0));
        editor.putInt("SCIENCE_ANIMALS_TOTAL", extras.getInt("SCIENCE_ANIMALS_TOTAL", 5));

        editor.putInt("SCIENCE_BODY_SCORE", extras.getInt("SCIENCE_BODY_SCORE", 0));
        editor.putInt("SCIENCE_BODY_TOTAL", extras.getInt("SCIENCE_BODY_TOTAL", 5));

        editor.putInt("SCIENCE_SPACE_SCORE", extras.getInt("SCIENCE_SPACE_SCORE", 0));
        editor.putInt("SCIENCE_SPACE_TOTAL", extras.getInt("SCIENCE_SPACE_TOTAL", 5));

        editor.apply(); // save changes
    }

    // show corrections if any
    private void showCorrections() {
        if (questions == null || userAnswers == null || correctAnswers == null)
        return;

        boolean hasWrongAnswer = false;

        for (int i = 0; i < totalQuestions; i++) {
            // Bandingkan jawapan pengguna dengan jawapan betul
            if (!correctAnswers[i].equals(userAnswers.get(i))) {
                hasWrongAnswer = true;

                    // question text
                    TextView questionText = new TextView(this);
                    questionText.setText(questions.get(i));
                    questionText.setTextSize(18);
                    questionText.setTextColor(ContextCompat.getColor(this, R.color.black));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0, 24, 0, 4);
                    questionText.setLayoutParams(params);

                    // correction text
                    TextView correctionText = new TextView(this);
                    correctionText.setText("Your Answer: " + userAnswers.get(i) + " | Correct answer: " + correctAnswers[i]);
                    // correctionText.setText(correction);
                    correctionText.setTextSize(16);
                    correctionText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));

                    // add to container
                    correctionsContainer.addView(questionText);
                    correctionsContainer.addView(correctionText);
                }
            }
        // show header if needed
        if (hasWrongAnswer) {
            correctionsHeader.setVisibility(View.VISIBLE);
        }
    }

    // back button
    public void goBack(View view) {
        finish();
    }
}