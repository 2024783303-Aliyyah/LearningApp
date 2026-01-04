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

    ArrayList<LessonAdmin> lessonList;
    LessonAdapterAdmin adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_lesson);

        rvLessons = findViewById(R.id.rvLessons);
        btnAddLesson = findViewById(R.id.btnAddLesson);

        loadAllLessonsForAdmin();

        adapter = new LessonAdapterAdmin(this, lessonList);
        rvLessons.setLayoutManager(new LinearLayoutManager(this));
        rvLessons.setAdapter(adapter);

        btnAddLesson.setOnClickListener(v -> {
            Intent intent = new Intent(adminViewLesson.this, adminAddLesson.class);
            startActivity(intent);
        });
    }

    private void loadAllLessonsForAdmin() {
        lessonList = new ArrayList<>();

        // FIX: Added the 8th argument (an integer) to each constructor call.
        // Assuming '0' is a suitable default value.
        // Format: (id, title, subject, year, description, content, videoPath, someNumber)

        lessonList.add(new LessonAdmin("math001", "Addition", "Mathematics", "Year 1", "Learn addition...", "Content here", "", 0));
        lessonList.add(new LessonAdmin("math002", "Subtraction", "Mathematics", "Year 1", "Learn subtraction...", "Content here", "", 0));
        lessonList.add(new LessonAdmin("sci001", "Living Things", "Science", "Year 2", "About living things...", "Content here", "", 0));
        lessonList.add(new LessonAdmin("sci002", "The Solar System", "Science", "Year 3", "About planets...", "Content here", "", 0));
        lessonList.add(new LessonAdmin("eng001", "Nouns", "English", "Year 1", "About nouns...", "Content here", "", 0));
        lessonList.add(new LessonAdmin("eng002", "Verbs", "English", "Year 2", "About verbs...", "Content here", "", 0));
    }

    public void openadminMenu(View view)
    {
        int id = view.getId();
        if (id == R.id.adminnavProfile) {
            startActivity(new Intent(this, AdminProfile.class));
        } else if (id == R.id.adminnavHome) {
            startActivity(new Intent(this, adminDashboard.class));
        } else if (id == R.id.adminnavScores) {
            startActivity(new Intent(this, score.class));
        }
    }

    public void goBack (View view)
    {
        finish();
    }
}
