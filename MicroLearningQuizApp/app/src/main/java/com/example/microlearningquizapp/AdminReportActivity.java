// Fail: AdminReportActivity.java
package com.example.microlearningquizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintManager;
import android.view.View;
import android.widget.Button; // <-- Import Button

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AdminReportActivity extends AppCompatActivity {

    RecyclerView rvStudentScores;
    AdminScoreAdapter adapter;
    List<StudentOverallScore> studentList;
    Button btnPrintReport; // <-- Deklarasi butang print

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_report);

        // Pautkan semua view
        rvStudentScores = findViewById(R.id.rvStudentScores);
        btnPrintReport = findViewById(R.id.btnPrintReport); // <-- Pautkan butang print

        rvStudentScores.setLayoutManager(new LinearLayoutManager(this));

        // Muatkan data skor
        loadDummyData();

        // Cipta dan tetapkan adapter
        adapter = new AdminScoreAdapter(this, studentList);
        rvStudentScores.setAdapter(adapter);

        // --- LOGIK BARU UNTUK BUTANG PRINT ---
        btnPrintReport.setOnClickListener(v -> {
            doPrint();
        });
    }

    // FUNGSI BARU UNTUK MEMULAKAN PROSES CETAKAN
    private void doPrint() {
        if (studentList == null || studentList.isEmpty()) {
            // Jangan buat apa-apa jika tiada data
            return;
        }

        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        String jobName = getString(R.string.app_name) + " Student Report";

        // Mula proses cetakan dengan adapter yang telah kita cipta
        printManager.print(jobName, new StudentScorePrintAdapter(this, studentList), null);
    }

    // Fungsi loadDummyData() anda kekal di sini
    private void loadDummyData() {
        studentList = new ArrayList<>(); // Cipta senarai kosong

        // === DATA UNTUK PELAJAR 1: aliyyah ===
        StudentOverallScore student1 = new StudentOverallScore("aliyyah");

        // Skor subjek Matematik untuk Aliyyah
        SubjectScore mathScores1 = new SubjectScore("Mathematics");
        mathScores1.addQuizScore(new QuizScore("Add it Up!", 5, 5));
        mathScores1.addQuizScore(new QuizScore("The Subtract Quest", 4, 5));

        // Skor subjek English untuk Aliyyah
        SubjectScore englishScores1 = new SubjectScore("English");
        englishScores1.addQuizScore(new QuizScore("Alphabet Adventure", 10, 10));
        englishScores1.addQuizScore(new QuizScore("Match The Synonyms", 8, 10));

        // Tambah skor subjek ke dalam data pelajar
        student1.addSubjectScore(mathScores1);
        student1.addSubjectScore(englishScores1);


        // === DATA UNTUK PELAJAR 2: budi ===
        StudentOverallScore student2 = new StudentOverallScore("budi");

        // Skor subjek Matematik untuk Budi
        SubjectScore mathScores2 = new SubjectScore("Mathematics");
        mathScores2.addQuizScore(new QuizScore("Add it Up!", 3, 5));
        // Budi belum ambil kuiz tolak

        // Skor subjek Sains untuk Budi
        SubjectScore scienceScores2 = new SubjectScore("Science");
        scienceScores2.addQuizScore(new QuizScore("Living Things", 7, 10));

        // Tambah skor subjek ke dalam data pelajar
        student2.addSubjectScore(mathScores2);
        student2.addSubjectScore(scienceScores2);


        // === TAMBAH SEMUA PELAJAR KE DALAM SENARAI UTAMA ===
        studentList.add(student1);
        studentList.add(student2);
    }

    public void openadminMenu(View view) {
        int id = view.getId();
        if (id == R.id.adminnavProfile) {
            startActivity(new Intent(this, AdminProfile.class));
        } else if (id == R.id.adminnavHome) {
            startActivity(new Intent(this, adminDashboard.class));
        } else if (id == R.id.adminnavScores) {
            startActivity(new Intent(this, AdminReportActivity.class));

        }
    }


    public void goBack(View view) {
        finish();
    }
}
