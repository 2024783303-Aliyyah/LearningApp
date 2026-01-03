// Fail: lessonActivity.java
package com.example.microlearningquizapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

// Tidak perlu import Glide
public class lessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        // 1. Pautkan semua view termasuk ImageView
        TextView textSubject = findViewById(R.id.textSubject);
        TextView textTitle = findViewById(R.id.textTitle);
        TextView textYear = findViewById(R.id.textYear);
        TextView textDescription = findViewById(R.id.textDescription);
        TextView textContent = findViewById(R.id.textContent);
        ImageView headerImageView = findViewById(R.id.headerImage); // Pastikan ID ini wujud dalam XML
        VideoView videoView = findViewById(R.id.videoView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Tetapkan data teks
            textSubject.setText(extras.getString("subject"));
            textTitle.setText(extras.getString("title"));
            textYear.setText(extras.getString("year"));
            textDescription.setText(extras.getString("description"));
            textContent.setText(extras.getString("content"));

            // --- 2. LOGIK BARU UNTUK IMEJ DARI DRAWABLE ---
            // Dapatkan ID imej dari Intent. Guna 0 sebagai nilai lalai jika tiada ID.
            int imageResId = extras.getInt("image_resource_id", 0);

            if (imageResId != 0) { // Semak jika ada ID yang sah (bukan 0)
                headerImageView.setVisibility(View.VISIBLE);
                // Terus tetapkan imej menggunakan ID sumbernya
                headerImageView.setImageResource(imageResId);
            } else {
                // Jika tiada ID imej, pastikan ImageView disembunyikan
                headerImageView.setVisibility(View.GONE);
            }
            // ----------------------------------------------------

            // Logik untuk video (kekal sama)
            String videoPath = extras.getString("video");
            if (videoPath != null && !videoPath.isEmpty()) {
                videoView.setVisibility(View.VISIBLE);
                Uri videoUri = Uri.parse(videoPath);
                videoView.setVideoURI(videoUri);
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.start();
            } else {
                videoView.setVisibility(View.GONE);
            }
        }
    }

    public void goBack(View view) {
        finish();
    }
}
