package com.example.microlearningquizapp;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class lessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lessonactivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textSubject = findViewById(R.id.textSubject);
        TextView textTitle = findViewById(R.id.textTitle);
        TextView textYear = findViewById(R.id.textYear);
        TextView textDescription = findViewById(R.id.textDescription);
        TextView textContent = findViewById(R.id.textContent);
        VideoView videoView = findViewById(R.id.videoView);

        textSubject.setText(getIntent().getStringExtra("subject"));
        textTitle.setText(getIntent().getStringExtra("title"));
        textYear.setText(getIntent().getStringExtra("year"));
        textDescription.setText(getIntent().getStringExtra("description"));
        textContent.setText(getIntent().getStringExtra("content"));
        videoView.setVideoPath(getIntent().getStringExtra("video"));
        videoView.start();

    }

    public void goBack(View view)
    {
        finish();
    }
}