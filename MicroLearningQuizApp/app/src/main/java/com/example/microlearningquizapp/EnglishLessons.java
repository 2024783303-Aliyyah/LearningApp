package com.example.microlearningquizapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EnglishLessons extends AppCompatActivity {

    RecyclerView rvLessons;
    ArrayList<Lesson> lessonList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_english_lessons);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.english), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvLessons = findViewById(R.id.rvLessons);
        rvLessons.setLayoutManager(new LinearLayoutManager(this));

        lessonList = new ArrayList<>();

        // Add lessons with all fields
        lessonList.add(new Lesson(
                "Alphabet Adventures",
                "English",
                "Year 1",
                "Learn alphabet",
                "alphabet content goes here",
                "android.resource://" + getPackageName() + "/"
                //+ R.raw.addition_video
        ));

        lessonList.add(new Lesson(
                "Fun with Synonyms",
                "English",
                "Year 1",
                "Learn basic synonyms",
                "Synonyms content goes here",
                "android.resource://" + getPackageName() + "/"
                //+ R.raw.subtraction_video
        ));


        LessonAdapter adapter = new LessonAdapter(this, lessonList, false);
        rvLessons.setAdapter(adapter);
    }

    public void goBack(View view)
    {
        finish();
    }
}