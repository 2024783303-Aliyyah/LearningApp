package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adminViewLesson extends AppCompatActivity {

    RecyclerView rvLessons;
    Button btnAddLesson;

    ArrayList<Lesson> lessonList;
    LessonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // Anda boleh komen baris ini jika ia menyebabkan isu layout
        setContentView(R.layout.activity_admin_view_lesson);

        rvLessons = findViewById(R.id.rvLessons);
        btnAddLesson = findViewById(R.id.btnAddLesson);

        // Muatkan semua data pelajaran
        loadAllLessons();

        // Sediakan Adapter dan RecyclerView
        adapter = new LessonAdapter(this, lessonList, true);
        rvLessons.setLayoutManager(new LinearLayoutManager(this));
        rvLessons.setAdapter(adapter);

        // Butang untuk pergi ke halaman tambah pelajaran
        btnAddLesson.setOnClickListener(v -> {
            Intent intent = new Intent(adminViewLesson.this, adminAddLesson.class);
            startActivity(intent);
        });
    }

    private void loadAllLessons() {
        // Cipta senarai baru untuk menyimpan semua pelajaran
        lessonList = new ArrayList<>();

        // Andaikan constructor Lesson memerlukan: (title, subject, year, description, content, videoPath)
        // Gantikan dengan data sebenar anda atau biarkan kosong "" jika tiada data

        // --- Data untuk Subjek Matematik ---
        lessonList.add(new Lesson("Addition", "Mathematics", "Year 1", "Learn basic addition", "Content for addition...", "android.resource://" + getPackageName() + "/" + R.raw.addition));
        lessonList.add(new Lesson("Subtraction", "Mathematics", "Year 1", "Learn basic subtraction", "Content for subtraction...", "android.resource://" + getPackageName() + "/" + R.raw.subtract));

        // --- Data untuk Subjek Sains ---
        lessonList.add(new Lesson("Living Things", "Science", "Year 2", "Characteristics of living things", "Content for living things...", ""));
        lessonList.add(new Lesson("The Solar System", "Science", "Year 3", "Planets in our solar system", "Content for solar system...", ""));

        // --- Data untuk Subjek Bahasa Inggeris ---
        lessonList.add(new Lesson("Nouns", "English", "Year 1", "Learn about nouns", "Content for nouns...", ""));
        lessonList.add(new Lesson("Verbs", "English", "Year 2", "Learn about action words", "Content for verbs...", ""));

        // Tambah lebih banyak pelajaran di sini mengikut keperluan...
    }

    // Tambah fungsi ini
    public void goBack(View view) {
        finish(); // 'finish()' akan menutup aktiviti semasa dan kembali ke skrin sebelumnya
    }

}
