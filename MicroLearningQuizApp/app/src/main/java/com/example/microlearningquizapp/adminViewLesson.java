// Fail: adminViewLesson.java
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

    // Gunakan jenis data yang betul
    ArrayList<LessonAdmin> lessonList;
    LessonAdapterAdmin adapter; // Gunakan adapter yang betul

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_lesson);

        rvLessons = findViewById(R.id.rvLessons);
        btnAddLesson = findViewById(R.id.btnAddLesson);

        // Muatkan data pelajaran untuk admin
        loadAllLessonsForAdmin();

        // Cipta dan tetapkan adapter yang betul
        adapter = new LessonAdapterAdmin(this, lessonList);
        rvLessons.setLayoutManager(new LinearLayoutManager(this));
        rvLessons.setAdapter(adapter);

        btnAddLesson.setOnClickListener(v -> {
            Intent intent = new Intent(adminViewLesson.this, adminAddLesson.class);
            startActivity(intent);
        });
    }


    // KOD BARU YANG BETUL
    private void loadAllLessonsForAdmin() {
        lessonList = new ArrayList<>();

        // Panggil constructor dengan 7 argumen. Guna "" jika data belum ada.
        // Format: (id, title, subject, year, description, content, videoPath)

        lessonList.add(new LessonAdmin("math001", "Addition", "Mathematics", "Year 1", "Learn addition...", "Content here", ""));
        lessonList.add(new LessonAdmin("math002", "Subtraction", "Mathematics", "Year 1", "Learn subtraction...", "Content here", ""));
        lessonList.add(new LessonAdmin("sci001", "Living Things", "Science", "Year 2", "About living things...", "Content here", ""));
        lessonList.add(new LessonAdmin("sci002", "The Solar System", "Science", "Year 3", "About planets...", "Content here", ""));
        lessonList.add(new LessonAdmin("eng001", "Nouns", "English", "Year 1", "About nouns...", "Content here", ""));
        lessonList.add(new LessonAdmin("eng002", "Verbs", "English", "Year 2", "About verbs...", "Content here", ""));
    }


    // Fungsi untuk butang kembali (jika ada dalam XML anda)
    public void goBack(View view) {
        finish();
    }
}
