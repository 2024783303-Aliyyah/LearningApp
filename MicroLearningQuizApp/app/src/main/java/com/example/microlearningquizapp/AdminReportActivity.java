// Fail: AdminReportActivity.java
package com.example.microlearningquizapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AdminReportActivity extends AppCompatActivity {

    RecyclerView rvStudentScores;
    AdminScoreAdapter adapter;
    List<StudentOverallScore> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_report);

        rvStudentScores = findViewById(R.id.rvStudentScores);
        rvStudentScores.setLayoutManager(new LinearLayoutManager(this));

        // Muatkan data (dalam aplikasi sebenar, data ini datang dari database)
        loadDummyData();

        // Tetapkan adapter
        adapter = new AdminScoreAdapter(this, studentList);
        rvStudentScores.setAdapter(adapter);
    }

    private void loadDummyData() {
        studentList = new ArrayList<>();

        // === Data untuk Pelajar 1: aliyyah ===
        StudentOverallScore student1 = new StudentOverallScore("aliyyah");

        // Skor subjek Matematik untuk Aliyyah
        SubjectScore mathScores1 = new SubjectScore("Mathematics");
        mathScores1.addQuizScore(new QuizScore("Add it Up!", 5, 5));
        mathScores1.addQuizScore(new QuizScore("The Subtract Quest", 4, 5));

        // Skor subjek English untuk Aliyyah
        SubjectScore englishScores1 = new SubjectScore("English");
        englishScores1.addQuizScore(new QuizScore("Alphabet Adventure", 10, 10));

        student1.addSubjectScore(mathScores1);
        student1.addSubjectScore(englishScores1);

        // === Data untuk Pelajar 2: budi ===
        StudentOverallScore student2 = new StudentOverallScore("budi");

        // Skor subjek Matematik untuk Budi
        SubjectScore mathScores2 = new SubjectScore("Mathematics");
        mathScores2.addQuizScore(new QuizScore("Add it Up!", 3, 5));
        // Budi belum ambil kuiz tolak

        student2.addSubjectScore(mathScores2);

        // Tambah pelajar ke dalam senarai utama
        studentList.add(student1);
        studentList.add(student2);
    }

    public void goBack(View view) {
        finish();
    }
}
