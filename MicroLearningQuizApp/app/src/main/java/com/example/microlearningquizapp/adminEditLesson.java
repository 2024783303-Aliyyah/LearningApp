// Fail: adminEditLesson.java
package com.example.microlearningquizapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class adminEditLesson extends AppCompatActivity {

    // --- Deklarasi View ---
    Spinner spinnerSubject;
    EditText etLessonTitle, etYears, etDescription;
    Button btnSave, btnPickVideo;
    TextView tvSelectedVideo;

    // --- Pembolehubah untuk URI video ---
    private Uri videoUri;

    // --- Launcher untuk pemilih fail ---
    private final ActivityResultLauncher<Intent> pickVideoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    videoUri = result.getData().getData();
                    tvSelectedVideo.setText(videoUri.getLastPathSegment());
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_lesson);

        // --- Pautkan View ---
        spinnerSubject = findViewById(R.id.spinnerSubject);
        etLessonTitle = findViewById(R.id.etLessonTitle);
        etYears = findViewById(R.id.etYears);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);
        btnPickVideo = findViewById(R.id.btnPickVideo);
        tvSelectedVideo = findViewById(R.id.tvSelectedVideo);

        // --- Logik Spinner ---
        String[] subjects = {"Mathematic", "Science", "English"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subjects);
        spinnerSubject.setAdapter(adapter);

        // --- Ambil data sedia ada dari Intent ---
        Intent intent = getIntent();
        etLessonTitle.setText(intent.getStringExtra("lessonTitle"));
        etYears.setText(intent.getStringExtra("year"));
        etDescription.setText(intent.getStringExtra("description"));

        String existingVideoPath = intent.getStringExtra("videoUrl");
        if (existingVideoPath != null && !existingVideoPath.isEmpty()) {
            videoUri = Uri.parse(existingVideoPath);
            tvSelectedVideo.setText("Current: " + videoUri.getLastPathSegment());
        }

        String subject = intent.getStringExtra("Subject");
        if (subject != null) {
            spinnerSubject.setSelection(adapter.getPosition(subject));
        }

        // --- Logik untuk Butang "Pick Video" ---
        btnPickVideo.setOnClickListener(v -> {
            Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
            pickIntent.setType("video/*");
            pickVideoLauncher.launch(pickIntent);
        });

        // --- Logik untuk Butang "Save Changes" ---
        btnSave.setOnClickListener(v -> {
            String updatedTitle = etLessonTitle.getText().toString();
            // ... ambil data lain ...

            if (videoUri == null) {
                Toast.makeText(this, "Please select a video", Toast.LENGTH_SHORT).show();
                return;
            }

            // DI SINI: Anda akan kemas kini data di pangkalan data menggunakan ID pelajaran
            // dan URI video yang baru (videoUri.toString())

            Toast.makeText(adminEditLesson.this, "Lesson Updated: " + updatedTitle, Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void goBack(View view) {
        finish();
    }
}
