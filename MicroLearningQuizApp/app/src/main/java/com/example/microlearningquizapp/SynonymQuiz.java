package com.example.microlearningquizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
// import android.widget.Button; // Tidak diperlukan lagi
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynonymQuiz extends AppCompatActivity {

    private TextView word1Left, word2Left, word3Left, word4Left;
    private TextView word1Right, word2Right, word3Right, word4Right;
    private TextView feedbackText;
    // private Button resetButton; // Tidak diperlukan lagi

    private Map<String, String> synonymPairs;
    private TextView selectedWordView = null;
    private int correctMatches = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synonym_quiz);

        // Pautkan semua View
        feedbackText = findViewById(R.id.feedback_text);
        // resetButton = findViewById(R.id.reset_button); // Tidak diperlukan lagi

        word1Left = findViewById(R.id.word_1_left);
        word2Left = findViewById(R.id.word_2_left);
        word3Left = findViewById(R.id.word_3_left);
        word4Left = findViewById(R.id.word_4_left);

        word1Right = findViewById(R.id.word_1_right);
        word2Right = findViewById(R.id.word_2_right);
        word3Right = findViewById(R.id.word_3_right);
        word4Right = findViewById(R.id.word_4_right);

        // Sediakan data dan mulakan kuiz
        setupQuiz();

        // Tetapkan listener untuk semua perkataan
        View.OnClickListener wordClickListener = v -> onWordClicked((TextView) v);
        word1Left.setOnClickListener(wordClickListener);
        word2Left.setOnClickListener(wordClickListener);
        word3Left.setOnClickListener(wordClickListener);
        word4Left.setOnClickListener(wordClickListener);
        word1Right.setOnClickListener(wordClickListener);
        word2Right.setOnClickListener(wordClickListener);
        word3Right.setOnClickListener(wordClickListener);
        word4Right.setOnClickListener(wordClickListener);

        // Listener untuk butang reset tidak diperlukan lagi
        // resetButton.setOnClickListener(v -> setupQuiz());
    }

    private void setupQuiz() {
        // ... (Fungsi setupQuiz() kekal sama, tetapi kita boleh padamkan baris terakhir)
        synonymPairs = new HashMap<>();
        synonymPairs.put("Besar", "Luas");
        synonymPairs.put("Gembira", "Suka");
        synonymPairs.put("Cepat", "Laju");
        synonymPairs.put("Cantik", "Ayu");

        List<String> leftWords = Arrays.asList("Besar", "Gembira", "Cepat", "Cantik");
        List<String> rightWords = Arrays.asList("Luas", "Suka", "Laju", "Ayu");

        Collections.shuffle(leftWords);
        Collections.shuffle(rightWords);

        TextView[] leftTextViews = {word1Left, word2Left, word3Left, word4Left};
        TextView[] rightTextViews = {word1Right, word2Right, word3Right, word4Right};

        for (int i = 0; i < leftTextViews.length; i++) {
            leftTextViews[i].setText(leftWords.get(i));
            rightTextViews[i].setText(rightWords.get(i));
            leftTextViews[i].setBackgroundColor(Color.parseColor("#F0D9B5"));
            rightTextViews[i].setBackgroundColor(Color.parseColor("#F0D9B5"));
            leftTextViews[i].setClickable(true);
            rightTextViews[i].setClickable(true);
        }

        correctMatches = 0;
        selectedWordView = null;
        feedbackText.setText("");
        // resetButton.setVisibility(View.GONE); // Tidak diperlukan lagi
    }

    private void onWordClicked(TextView clickedView) {
        if (selectedWordView == null) {
            selectedWordView = clickedView;
            selectedWordView.setBackgroundColor(Color.CYAN);
            feedbackText.setText("");
        } else {
            if (selectedWordView == clickedView) return;

            if (isSameColumn(selectedWordView, clickedView)) {
                feedbackText.setText("Please select one word from each column.");
                selectedWordView.setBackgroundColor(Color.parseColor("#F0D9B5"));
                selectedWordView = null;
                return;
            }

            String word1 = selectedWordView.getText().toString();
            String word2 = clickedView.getText().toString();

            if (isCorrectPair(word1, word2)) {
                feedbackText.setText("Correct!");
                feedbackText.setTextColor(Color.parseColor("#006400"));

                selectedWordView.setBackgroundColor(Color.LTGRAY);
                clickedView.setBackgroundColor(Color.LTGRAY);
                selectedWordView.setClickable(false);
                clickedView.setClickable(false);

                correctMatches++;
            } else {
                feedbackText.setText("Wrong, try again!");
                feedbackText.setTextColor(Color.RED);

                final Handler mainHandler = new Handler(Looper.getMainLooper());
                final TextView finalSelectedView = selectedWordView;

                mainHandler.postDelayed(() -> {
                    if (finalSelectedView != null) {
                        finalSelectedView.setBackgroundColor(Color.parseColor("#F0D9B5"));
                    }
                    feedbackText.setText("");
                }, 1000);
            }

            selectedWordView = null;

            // ====================== PERUBAHAN UTAMA DI SINI ======================
            // Semak jika semua telah dipadankan
            if (correctMatches == synonymPairs.size()) {
                feedbackText.setText("Quiz Complete!");
                feedbackText.setTextColor(Color.BLUE);

                // Tunggu sekejap (cth: 1.5 saat) sebelum pergi ke skrin skor
                // supaya pengguna sempat melihat mesej "Quiz Complete!"
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    // Cipta Intent untuk pergi ke 'score.class'
                    Intent intent = new Intent(SynonymQuiz.this, score.class);

                    // Hantar data skor. Untuk kuiz ini, skor sentiasa penuh jika ia tamat.
                    intent.putExtra("USER_SCORE", synonymPairs.size());
                    intent.putExtra("TOTAL_QUESTIONS", synonymPairs.size());

                    startActivity(intent);
                    finish(); // Tutup aktiviti kuiz
                }, 1500); // 1500 milisaat = 1.5 saat
            }
            // ====================== AKHIR PERUBAHAN ======================
        }
    }

    private boolean isCorrectPair(String word1, String word2) {
        return (synonymPairs.containsKey(word1) && synonymPairs.get(word1).equals(word2)) ||
                (synonymPairs.containsKey(word2) && synonymPairs.get(word2).equals(word1));
    }

    private boolean isSameColumn(TextView view1, TextView view2) {
        View parent1 = (View) view1.getParent();
        View parent2 = (View) view2.getParent();
        return parent1.getId() == parent2.getId();
    }

    public void goBack(View view) {
        finish();
    }

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
        }
    }
}
