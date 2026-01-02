package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_view_lesson);

        rvLessons = findViewById(R.id.rvLessons);
        btnAddLesson = findViewById(R.id.btnAddLesson);

        lessonList = new ArrayList<>();

        // Dummy Data (contoh)
        lessonList.add(new Lesson("Addition", "Mathematics", "Year 1"));

        adapter = new LessonAdapter(this, lessonList);

        rvLessons.setLayoutManager(new LinearLayoutManager(this));
        rvLessons.setAdapter(adapter);

        //button adminaddlesson yang akan pergi ke page add lesson
        btnAddLesson.setOnClickListener(v -> {
            Intent intent = new Intent(adminViewLesson.this, adminAddLesson.class);
            startActivity(intent);
        });

    }
}