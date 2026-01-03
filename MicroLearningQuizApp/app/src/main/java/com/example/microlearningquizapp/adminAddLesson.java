// Fail: adminAddLesson.java
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

public class adminAddLesson extends AppCompatActivity {

    // --- Deklarasi View ---
    Spinner spinnerSubject;
    EditText etLessonTitle, etYears, etDescription;
    Button btnSave, btnUploadIllustration, btnPickVideo;
    TextView tvSelectedVideo;

    // --- Pembolehubah untuk menyimpan URI video ---
    private Uri videoUri; // Untuk menyimpan path video yang dipilih

    // --- Launcher untuk mendapatkan hasil dari pemilih fail ---
    private final ActivityResultLauncher<Intent> pickVideoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    // Dapatkan URI video yang dipilih
                    videoUri = result.getData().getData();
                    // Paparkan nama fail (atau URI) pada TextView
                    tvSelectedVideo.setText(videoUri.getLastPathSegment());
                    Toast.makeText(this, "Video selected", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_lesson);

        // --- Pautkan View ---
        spinnerSubject = findViewById(R.id.spinnerSubject);
        etLessonTitle = findViewById(R.id.etLessonTitle);
        etYears = findViewById(R.id.etYears);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);
        btnUploadIllustration = findViewById(R.id.btnUploadIllustration);
        // Pautkan View baru untuk video
        btnPickVideo = findViewById(R.id.btnPickVideo);
        tvSelectedVideo = findViewById(R.id.tvSelectedVideo);

        // --- Logik Spinner (kekal sama) ---
        String[] subjects = {"Mathematic", "Science", "English"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subjects);
        spinnerSubject.setAdapter(adapter);

        // --- Logik untuk Butang "Pick Video" ---
        btnPickVideo.setOnClickListener(v -> {
            // Cipta Intent untuk membuka pemilih fail video
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("video/*"); // Hanya tunjukkan fail video
            pickVideoLauncher.launch(intent);
        });

        // --- Logik untuk Butang "Save" ---
        btnSave.setOnClickListener(v -> {
            String subject = spinnerSubject.getSelectedItem().toString();
            String title = etLessonTitle.getText().toString();
            String year = etYears.getText().toString();
            String description = etDescription.getText().toString();

            // Semak jika video telah dipilih
            if (videoUri == null) {
                Toast.makeText(this, "Please select a video", Toast.LENGTH_SHORT).show();
                return;
            }

            // DI SINI: Anda akan simpan semua data, termasuk videoUri.toString() ke pangkalan data
            // Contoh: Lesson newLesson = new Lesson(..., ..., videoUri.toString(), ...);
            //         databaseReference.push().setValue(newLesson);

            Toast.makeText(this, "Lesson Added: " + title, Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void goBack(View view) {
        finish();
    }
}
