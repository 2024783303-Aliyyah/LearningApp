package com.example.microlearningquizapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScienceLessons extends AppCompatActivity {

    RecyclerView rvLessons;
    ArrayList<Lesson> lessonList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_science_lessons);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.science), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvLessons = findViewById(R.id.rvLessons);
        rvLessons.setLayoutManager(new LinearLayoutManager(this));

        lessonList = new ArrayList<>();

        lessonList.add(new Lesson("Living vs Non-Living"));
        lessonList.add(new Lesson("Five senses"));

        LessonAdapter adapter = new LessonAdapter(this, lessonList);
        rvLessons.setAdapter(adapter);
    }
}