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
import android.widget.ImageView; // <-- Import ImageView
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
    ImageView ivImagePreview; // <-- Deklarasi ImageView

    // --- Pembolehubah untuk menyimpan URI ---
    private Uri videoUri;
    private Uri imageUri; // <-- Deklarasi URI untuk imej

    // --- Launcher untuk video ---
    private final ActivityResultLauncher<Intent> pickVideoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    videoUri = result.getData().getData();
                    tvSelectedVideo.setText(videoUri.getLastPathSegment());
                }
            }
    );

    // --- Launcher BARU untuk imej ---
    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    // Paparkan imej yang dipilih dalam ImageView
                    ivImagePreview.setImageURI(imageUri);
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
        btnPickVideo = findViewById(R.id.btnPickVideo);
        tvSelectedVideo = findViewById(R.id.tvSelectedVideo);
        ivImagePreview = findViewById(R.id.ivImagePreview); // <-- Pautkan ImageView

        // --- Logik Spinner ---
        String[] subjects = {"Mathematic", "Science", "English"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subjects);
        spinnerSubject.setAdapter(adapter);

        // --- Logik untuk Butang "Pick Image" ---
        btnUploadIllustration.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*"); // Hanya tunjukkan fail imej
            pickImageLauncher.launch(intent);
        });

        // --- Logik untuk Butang "Pick Video" ---
        btnPickVideo.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("video/*");
            pickVideoLauncher.launch(intent);
        });

        // --- Logik untuk Butang "Save" ---
        btnSave.setOnClickListener(v -> {
            // ... (logik anda sedia ada) ...

            // Semak jika imej dan video telah dipilih
            if (imageUri == null) {
                Toast.makeText(this, "Please pick a header image", Toast.LENGTH_SHORT).show();
                return;
            }
            if (videoUri == null) {
                Toast.makeText(this, "Please pick a lesson video", Toast.LENGTH_SHORT).show();
                return;
            }

            // DI SINI: Anda akan simpan semua data, termasuk imageUri.toString() dan videoUri.toString()
            // Contoh: Lesson newLesson = new Lesson(..., ..., videoUri.toString(), imageUri.toString());
            //         databaseReference.push().setValue(newLesson);

            Toast.makeText(this, "Lesson Added!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void goBack(View view) {
        finish();
    }
    public void openadminMenu(View view)
    {
        int id = view.getId();
        if (id == R.id.adminnavProfile) {
            startActivity(new Intent(this, AdminProfile.class));
        } else if (id == R.id.adminnavHome) {
            startActivity(new Intent(this, adminDashboard.class));
        } else if (id == R.id.adminnavScores) {
            startActivity(new Intent(this, AdminReportActivity.class));
        }
    }
}
