// Fail: lessonActivity.java
package com.example.microlearningquizapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class lessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        // Pautkan view dari activity_lesson.xml
        TextView textSubject = findViewById(R.id.textSubject);
        TextView textTitle = findViewById(R.id.textTitle);
        TextView textYear = findViewById(R.id.textYear);
        TextView textDescription = findViewById(R.id.textDescription);
        TextView textContent = findViewById(R.id.textContent);
        VideoView videoView = findViewById(R.id.videoView);

        // Ambil data yang dihantar dari LessonAdapter
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textSubject.setText(extras.getString("subject"));
            textTitle.setText(extras.getString("title"));
            textYear.setText(extras.getString("year"));
            textDescription.setText(extras.getString("description"));
            textContent.setText(extras.getString("content"));

            String videoPath = extras.getString("video");
            if (videoPath != null && !videoPath.isEmpty()) {
                videoView.setVisibility(View.VISIBLE);
                Uri videoUri = Uri.parse(videoPath);

                // Tetapkan video dahulu sebelum mengikat controller
                videoView.setVideoURI(videoUri);

                // Cipta dan ikat MediaController
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);

                videoView.start(); // Mulakan video secara automatik
            } else {
                videoView.setVisibility(View.GONE); // Sembunyikan jika tiada video
            }
        }
    }

    public void goBack(View view) {
        finish();
    }
}
