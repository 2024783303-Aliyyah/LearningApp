package com.example.microlearningquizapp;

import android.content.Intent;
import android.graphics.Color;import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AlphabetQuiz extends AppCompatActivity {

    // 1. Deklarasi untuk komponen UI
    private ImageView questionImage;
    private TextView feedbackText;
    private Button option1Button, option2Button, option3Button, nextButton;

    // 2. Struktur data untuk soalan dan pembolehubah kuiz
    private List<QuizQuestion> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    // Inner class untuk menyimpan data satu soalan
    private static class QuizQuestion {
        int imageResource;
        String option1, option2, option3;
        String correctAnswer;

        QuizQuestion(int imageResource, String option1, String option2, String option3, String correctAnswer) {
            this.imageResource = imageResource;
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.correctAnswer = correctAnswer;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_quiz);

        // Sambungkan komponen UI
        questionImage = findViewById(R.id.question_image);
        feedbackText = findViewById(R.id.feedback_text);
        option1Button = findViewById(R.id.option_1_button);
        option2Button = findViewById(R.id.option_2_button);
        option3Button = findViewById(R.id.option_3_button);
        nextButton = findViewById(R.id.next_question_button);

        // Sediakan semua 5 soalan
        prepareQuestions();

        // Muatkan soalan pertama
        loadQuestion(currentQuestionIndex);

        // Tetapkan fungsi klik untuk butang jawapan
        option1Button.setOnClickListener(v -> checkAnswer(option1Button));
        option2Button.setOnClickListener(v -> checkAnswer(option2Button));
        option3Button.setOnClickListener(v -> checkAnswer(option3Button));

        // Kemas kini logik butang "Next"
        nextButton.setOnClickListener(v -> {
            currentQuestionIndex++; // Pindah ke indeks soalan seterusnya
            if (currentQuestionIndex < questionList.size()) {
                loadQuestion(currentQuestionIndex); // Muatkan soalan seterusnya jika ada
            } else {
                //====================== START OF FIX ======================
                // Jika tiada lagi soalan, pergi ke skrin 'score' anda

                // Gunakan nama kelas 'score.class' yang sebenar dari fail anda
                // Saya andaikan nama fail anda ialah score.java dan nama kelasnya ialah 'score'
                Intent intent = new Intent(AlphabetQuiz.this, score.class);

                // Hantar data mengikut format yang diperlukan oleh score.java anda
                intent.putExtra("USER_SCORE", score);
                intent.putExtra("TOTAL_QUESTIONS", questionList.size());

                // Bahagian pembetulan jawapan tidak dihantar kerana formatnya berbeza (String vs Integer)
                // dan anda kata tidak boleh ubah score.java

                startActivity(intent);
                finish(); // Tutup aktiviti kuiz supaya pengguna tidak boleh kembali
                //======================= END OF FIX =======================
            }
        });
    }

    private void prepareQuestions() {
        questionList = new ArrayList<>();
        // Pastikan nama fail Java anda ialah AlphabetQuiz.java dan nama kelasnya ialah 'AlphabetQuiz'
        // Soalan 1: BUKU (Book)
        questionList.add(new QuizQuestion(R.drawable.apple, "C", "A", "B", "A"));
        // Soalan 2: SKOR (Score) - Ini gambar dari kod anda
        questionList.add(new QuizQuestion(R.drawable.doggie, "A", "E", "D", "D"));
        // Soalan 3: MATEMATIK (Maths) - Ini gambar dari kod anda
        questionList.add(new QuizQuestion(R.drawable.bicycle, "D", "C", "B", "B"));
        // Soalan 4: KUIZ (Quiz) - Ini gambar dari kod anda
        questionList.add(new QuizQuestion(R.drawable.smartphone, "C", "P", "A", "P"));
        // Soalan 5: KUIZ MATEMATIK (mathquiz) - Ini gambar dari kod anda
        questionList.add(new QuizQuestion(R.drawable.train, "T", "D", "E", "T"));
        // Pastikan anda ada semua gambar ini dalam folder res/drawable
    }

    private void loadQuestion(int questionIndex) {
        QuizQuestion currentQuestion = questionList.get(questionIndex);
        questionImage.setImageResource(currentQuestion.imageResource);
        option1Button.setText(currentQuestion.option1);
        option2Button.setText(currentQuestion.option2);
        option3Button.setText(currentQuestion.option3);
        resetUI();
    }

    private void checkAnswer(Button selectedButton) {
        setOptionsEnabled(false);
        String selectedAnswer = selectedButton.getText().toString();
        String correctAnswer = questionList.get(currentQuestionIndex).correctAnswer;

        if (selectedAnswer.equals(correctAnswer)) {
            score++;
            feedbackText.setText("Correct!");
            feedbackText.setTextColor(Color.parseColor("#006400"));
        } else {
            feedbackText.setText("Wrong! The answer is " + correctAnswer);
            feedbackText.setTextColor(Color.RED);
        }
        nextButton.setVisibility(View.VISIBLE);
    }

    private void resetUI() {
        feedbackText.setText("");
        nextButton.setVisibility(View.GONE);
        setOptionsEnabled(true);
    }

    private void setOptionsEnabled(boolean enabled) {
        option1Button.setEnabled(enabled);
        option2Button.setEnabled(enabled);
        option3Button.setEnabled(enabled);
    }

    // Di dalam AlphabetQuiz.java

// ... (kod anda yang lain)

    // FUNGSI goBack() YANG TELAH DIKEMAS KINI
    public void goBack(View view) {
        // Semak sama ada kita BUKAN di soalan pertama
        if (currentQuestionIndex > 0) {
            // Jika ya, pergi ke soalan sebelumnya
            currentQuestionIndex--; // Kurangkan indeks untuk kembali ke soalan sebelumnya
            loadQuestion(currentQuestionIndex); // Muatkan data soalan sebelumnya

            // PENTING: Semak semula skor jika pengguna kembali ke soalan sebelumnya
            // Pendekatan paling mudah: Anggap pengguna belum menjawab soalan ini semula.
            // Anda boleh tambah logik yang lebih kompleks jika perlu, tetapi ini sudah mencukupi.

        } else {
            // Jika kita SUDAH di soalan pertama (indeks 0), barulah keluar dari aktiviti kuiz
            super.onBackPressed(); // Ini sama seperti memanggil finish(), tetapi lebih standard
        }
    }

}
