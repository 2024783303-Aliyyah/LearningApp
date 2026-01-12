package com.example.microlearningquizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

// score overview / history page
public class MyScore extends AppCompatActivity {

    // Math
    TextView tvMathTopic1, tvMathTopic2, tvMathTopic3;
    // English
    TextView tvEnglishTopic1, tvEnglishTopic2;
    // Science
    TextView tvScienceTopic1, tvScienceTopic2, tvScienceTopic3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_score);

        // Apply system bar insets to root view
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.MyScore), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    // link TextViews from xml
    tvMathTopic1 = findViewById(R.id.tvMathTopic1);
    tvMathTopic2 = findViewById(R.id.tvMathTopic2);
    tvMathTopic3 = findViewById(R.id.tvMathTopic3);

    tvEnglishTopic1 = findViewById(R.id.tvEnglishTopic1);
    tvEnglishTopic2 = findViewById(R.id.tvEnglishTopic2);

    tvScienceTopic1 = findViewById(R.id.tvScienceTopic1);
    tvScienceTopic2 = findViewById(R.id.tvScienceTopic2);
    tvScienceTopic3 = findViewById(R.id.tvScienceTopic3);

    // read Scores from SharedPreferences
    SharedPreferences prefs = getSharedPreferences("QUIZ_SCORES", MODE_PRIVATE);

    // Math scores
    int math1 = prefs.getInt("MATH_ADD_IT_UP_SCORE", 0);
    int math1Total = prefs.getInt("MATH_ADD_IT_UP_TOTAL", 5);

    int math2 = prefs.getInt("MATH_SUBTRACT_QUEST_SCORE", 0);
    int math2Total = prefs.getInt("MATH_SUBTRACT_QUEST_TOTAL", 5);

    int math3 = prefs.getInt("MATH_MULTIPLICATION_SCORE", 0);
    int math3Total = prefs.getInt("MATH_MULTIPLICATION_TOTAL", 5);

    // English scores
    int eng1 = prefs.getInt("ENGLISH_ALPHABET_ADVENTURE_SCORE", 0);
    int eng1Total = prefs.getInt("ENGLISH_ALPHABET_ADVENTURE_TOTAL", 5);

    int eng2 = prefs.getInt("ENGLISH_FUN_SYNONYM_SCORE", 0);
    int eng2Total = prefs.getInt("ENGLISH_FUN_SYNONYM_TOTAL", 5);

    // Science scores
    int sci1 = prefs.getInt("SCIENCE_ANIMALS_SCORE", 0);
    int sci1Total = prefs.getInt("SCIENCE_ANIMALS_TOTAL", 5);

    int sci2 = prefs.getInt("SCIENCE_BODY_SCORE", 0);
    int sci2Total = prefs.getInt("SCIENCE_BODY_TOTAL", 5);

    int sci3 = prefs.getInt("SCIENCE_SPACE_SCORE", 0);
    int sci3Total = prefs.getInt("SCIENCE_SPACE_TOTAL", 5);

    // display scores
    tvMathTopic1.setText(formatScore(math1, math1Total));
    tvMathTopic2.setText(formatScore(math2, math2Total));
    tvMathTopic3.setText(formatScore(math3, math3Total));

    tvEnglishTopic1.setText(formatScore(eng1, eng1Total));
    tvEnglishTopic2.setText(formatScore(eng2, eng2Total));

    tvScienceTopic1.setText(formatScore(sci1, sci1Total));
    tvScienceTopic2.setText(formatScore(sci2, sci2Total));
    tvScienceTopic3.setText(formatScore(sci3, sci3Total));
}

    // back button
    public void goBack(View view) {
        finish(); // go back to previous activity
    }

    // bottom navigation bar
    public void openMenu(View view)
    {
        int id = view.getId();
        if (id == R.id.navProfile) {
            startActivity(new Intent(this, UserProfileActivity.class));
        } else if (id == R.id.navLessons) {
            startActivity(new Intent(this, learningmodules.class));
        } else if (id == R.id.navHome) {
            startActivity(new Intent(this, StudentDashboardActivity.class));
        } else if (id == R.id.navQuiz) {
            startActivity(new Intent(this, quizzesmodules.class));
        } else if (id == R.id.navScores) {
            // Already on MyScore page
        }
    }

    // helper function to format score
    private String formatScore(int score, int total) {
        if (total == 0) {
            return "No attempt yet";
        }
        return score + " / " + total;
    }
}