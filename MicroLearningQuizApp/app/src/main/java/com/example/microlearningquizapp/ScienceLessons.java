package com.example.microlearningquizapp;

import android.content.Intent;
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

        // Add lessons with all fields
        lessonList.add(new Lesson(
                "Living vs Non-living",
                "Science",
                "Year 1",
                "Learn basic living and non living things",
                "content goes here",
                "android.resource://" + getPackageName() + "/",
                //+ R.raw.addition_video
                R.drawable.reading
        ));

        lessonList.add(new Lesson(
                "Five Sense",
                "Science",
                "Year 1",
                "Learn human Senses",
                "Human Senses content goes here",
                "android.resource://" + getPackageName() + "/",
                //+ R.raw.subtraction_video
                R.drawable.reading
        ));

        LessonAdapter adapter = new LessonAdapter(this, lessonList, false);
        rvLessons.setAdapter(adapter);
    }

    public void openMenu(View view)
    {
        int id = view.getId();
        if (id == R.id.navProfile) {
            startActivity(new Intent(this, UserProfileActivity.class));
        } else if (id == R.id.navLessons) {
            startActivity(new Intent(this, learningmodules.class));
        } else if (id == R.id.navHome) {
            startActivity(new Intent(this, StudentDashboardActivity.class));
        } else if (id == R.id.navQuiz) {
            startActivity(new Intent(this, quizzesmodules.class));
        }
    }

    public void goBack(View view)
    {
        finish();
    }
}