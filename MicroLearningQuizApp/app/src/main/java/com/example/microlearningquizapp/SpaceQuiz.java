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
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

// Science - Space Quiz True/False Quiz
public class SpaceQuiz extends AppCompatActivity {

    // UI components
    private TextView questionText;
    private RadioGroup radioGroup;
    private RadioButton radioTrue, radioFalse;
    private Button btnSubmit;

    // Quiz questions
    private String[] questions = {
            "The Sun shines at night.",
            "The Moon is smaller than the Sun.",
            "Earth is our planet.",
            "Stars are bigger than the Sun."
    };

    // Correct answers (1 = True, 0 = False)
    private final int[] correctAnswers = { 0, 1, 1, 0 };

    // Text version of correct answers (for Score activity)
    private final String[] correctAnswersText = {
            "False", "True", "True", "False"
    };

    // Track quiz progress
    private int currentQuestion = 0;
    private int score = 0;

    // Lists to send to Score activity
    private ArrayList<String> questionsList = new ArrayList<>();
    private ArrayList<String> userAnswers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_quiz);

        // link ui components
        questionText = findViewById(R.id.questionText);
        radioGroup = findViewById(R.id.radioGroupTF);
        radioTrue = findViewById(R.id.radioTrue);
        radioFalse = findViewById(R.id.radioFalse);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Copy questions array into ArrayList
        for(String q: questions) {
            questionsList.add(q);
        }
        // Button click handler
        btnSubmit.setOnClickListener(v -> submitAnswer());

        // load first question
        loadQuestion();
    }

    // display current question
    private void loadQuestion() {
        questionText.setText(questions[currentQuestion]);
        radioGroup.clearCheck();

        // change button text on last question
        btnSubmit.setText(
                currentQuestion == questions.length - 1 ? "Finish" : "Next"
        );
    }

    // handle answer submission
    private void submitAnswer() {
        // check if user selected an option
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select True or False", Toast.LENGTH_SHORT).show();
            return;
        }

        // convert selection to integer value
        int selectedAnswer = (selectedId == R.id.radioTrue) ? 1 : 0;

        // Save user's answer as text (for Score page)
        userAnswers.add(selectedAnswer == 1 ? "True" : "False");

        // check if answer is correct
        if (selectedAnswer == correctAnswers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        // load next question OR finish quiz
        if (currentQuestion < questions.length) {
            loadQuestion();
        } else {
            goToScoreActivity();
        }
    }

    // navigate to Score activity and pass all required data
    private void goToScoreActivity() {

        Intent intent = new Intent(this, Score.class);

        intent.putExtra("USER_SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questions.length);
        intent.putStringArrayListExtra("QUESTIONS", questionsList);
        intent.putStringArrayListExtra("USER_ANSWERS", userAnswers);
        intent.putExtra("CORRECT_ANSWERS", correctAnswersText);

        startActivity(intent);
        finish(); // prevent returning to quiz
    }

    public void goBack(View view) {
        finish();
    }
}
