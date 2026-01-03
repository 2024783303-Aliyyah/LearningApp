package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class SubtractQuiz extends AppCompatActivity {

    private EditText answ1, answ2, answ3, answ4;
    private Button btnfinish;

    private final String[] questions = {
            " 4 - 2 ? ",
            " 3 - 1 ?",
            " 6 - 2 ?",
            " 5 - 3 ?"
    };
    private final int[] correctAnswers = {2, 2, 4, 1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_subtract_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.subtractquiz), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        answ1 = findViewById(R.id.answer_input_1);
        answ2 = findViewById(R.id.answer_input_2);
        answ3 = findViewById(R.id.answer_input_3);
        answ4 = findViewById(R.id.answer_input_4);
        btnfinish = findViewById(R.id.finish);


        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void checkAnswer() {
        int score = 0;

        String answer1 = answ1.getText().toString().trim();
        String answer2 = answ2.getText().toString().trim();
        String answer3 = answ3.getText().toString().trim();
        String answer4 = answ4.getText().toString().trim();

        if (answer1.isEmpty() || answer2.isEmpty() || answer3.isEmpty() || answer4.isEmpty()) {
            Toast.makeText(this, "Please answer all questions!", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Integer> userAnswers = new ArrayList<>();
        userAnswers.add(Integer.parseInt(answer1));
        userAnswers.add(Integer.parseInt(answer2));
        userAnswers.add(Integer.parseInt(answer3));
        userAnswers.add(Integer.parseInt(answer4));

        for (int i = 0; i < correctAnswers.length; i++) {
            if (userAnswers.get(i) == correctAnswers[i]) {
                score++;
            }
        }

        goToScoreActivity(score, userAnswers);
    }

    private void goToScoreActivity(int finalScore, ArrayList<Integer> userAnswers) {

        Intent intent = new Intent(SubtractQuiz.this, score.class);

        // 1. Skor akhir pengguna
        intent.putExtra("USER_SCORE", finalScore);
        // 2. Jumlah soalan
        intent.putExtra("TOTAL_QUESTIONS", correctAnswers.length);
        // 3. Senarai soalan (String array)
        intent.putExtra("QUESTIONS_ARRAY", questions);
        // 4. Senarai jawapan pengguna (ArrayList)
        intent.putIntegerArrayListExtra("USER_ANSWERS_LIST", userAnswers);
        // 5. Senarai jawapan yang betul (int array)
        intent.putExtra("CORRECT_ANSWERS_ARRAY", correctAnswers);

        // Mulakan ScoreActivity
        startActivity(intent);

        finish();
    }

    // Tambah fungsi ini
    public void goBack(View view) {
        finish(); // 'finish()' akan menutup aktiviti semasa dan kembali ke skrin sebelumnya
    }

}
