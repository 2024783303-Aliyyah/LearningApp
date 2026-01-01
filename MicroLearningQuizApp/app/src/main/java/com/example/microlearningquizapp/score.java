package com.example.microlearningquizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

// Pastikan nama kelas ini 'score' sama seperti nama fail 'score.java'
public class score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Pautkan view dari XML ke pembolehubah Java
        TextView textFinalScore = findViewById(R.id.textFinalScore);
        TextView correctionsHeader = findViewById(R.id.correctionsHeader);
        LinearLayout correctionsContainer = findViewById(R.id.correctionsContainer);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            // Bahagian 1: Paparkan Skor Utama
            int userScore = extras.getInt("USER_SCORE");
            int totalQuestions = extras.getInt("TOTAL_QUESTIONS");

            String scoreText = userScore + " / " + totalQuestions;
            textFinalScore.setText(scoreText);

            // Bahagian 2: Paparkan Pembetulan Jawapan
            String[] questions = extras.getStringArray("QUESTIONS_ARRAY");
            // --- PEMBETULAN DI SINI ---
            ArrayList<Integer> userAnswers = getIntent().getIntegerArrayListExtra("USER_ANSWERS_LIST");
            int[] correctAnswers = extras.getIntArray("CORRECT_ANSWERS_ARRAY");

            boolean adaJawapanSalah = false;

            if (questions != null && userAnswers != null && correctAnswers != null) {
                for (int i = 0; i < totalQuestions; i++) {
                    // Bandingkan jawapan pengguna dengan jawapan betul
                    if (!userAnswers.get(i).equals(correctAnswers[i])) {
                        adaJawapanSalah = true;

                        // Cipta TextView untuk SOALAN
                        TextView questionTextView = new TextView(this);
                        questionTextView.setText(questions[i]);
                        questionTextView.setTextSize(18);
                        questionTextView.setTextColor(ContextCompat.getColor(this, R.color.black));

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        params.setMargins(0, 24, 0, 0);
                        questionTextView.setLayoutParams(params);

                        // Cipta TextView untuk PEMBETULAN
                        TextView correctionTextView = new TextView(this);
                        String correctionText = "Your Answer: " + userAnswers.get(i) + " (Correct answer: " + correctAnswers[i] + ")";
                        correctionTextView.setText(correctionText);
                        correctionTextView.setTextSize(16);
                        correctionTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));

                        // Tambah kedua-dua TextView ke dalam container
                        correctionsContainer.addView(questionTextView);
                        correctionsContainer.addView(correctionTextView);
                    }
                }
            }

            // Bahagian 3: Tayangkan Tajuk Pembetulan
            if (adaJawapanSalah) {
                correctionsHeader.setVisibility(View.VISIBLE);
            }
        }
    }

    // Fungsi untuk butang kembali
    public void goBack(View view) {
        finish();
    }
}
