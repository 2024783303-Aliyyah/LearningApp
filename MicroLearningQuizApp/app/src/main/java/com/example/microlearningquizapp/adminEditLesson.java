// Fail: adminEditLesson.java
package com.example.microlearningquizapp;

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

import androidx.appcompat.app.AppCompatActivity;
// ... (import lain jika perlu) ...

public class adminEditLesson extends AppCompatActivity {

    // --- Deklarasi View ---
    Spinner spinnerSubject;
    EditText etLessonTitle, etYears, etDescription, etContent; // Tambah etContent jika ada
    Button btnSave, btnPickVideo, btnPickImage;
    TextView tvSelectedVideo, tvSelectedImage;

    // ... (kod untuk launcher video dan imej) ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_lesson);

        // --- Pautkan View ---
        spinnerSubject = findViewById(R.id.spinnerSubject);
        etLessonTitle = findViewById(R.id.etLessonTitle);
        etYears = findViewById(R.id.etYears);
        etDescription = findViewById(R.id.etDescription);
        // pautkan semua view lain...
        btnSave = findViewById(R.id.btnSave);

        // --- Sediakan Spinner ---
        String[] subjects = {"Mathematic", "Science", "English"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subjects);
        spinnerSubject.setAdapter(adapter);

        // =============================================================
        // --- BAHAGIAN UTAMA: AMBIL DATA DARI INTENT DAN PAPARKANNYA ---
        // =============================================================
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Ambil semua data dari Intent menggunakan kunci yang betul
            String lessonTitle = extras.getString("LESSON_TITLE");
            String subject = extras.getString("LESSON_SUBJECT");
            String year = extras.getString("LESSON_YEAR");
            String description = extras.getString("LESSON_DESCRIPTION");
            // Ambil data lain seperti content, video path, dan image id...

            // Tetapkan data yang diterima ke dalam medan yang sepadan
            etLessonTitle.setText(lessonTitle);
            etYears.setText(year);
            etDescription.setText(description);

            // Tetapkan pilihan spinner berdasarkan subjek
            if (subject != null) {
                int spinnerPosition = adapter.getPosition(subject);
                if (spinnerPosition >= 0) {
                    spinnerSubject.setSelection(spinnerPosition);
                }
            }

            // (Pilihan) Lakukan perkara yang sama untuk imej dan video
            // cth: tvSelectedVideo.setText(extras.getString("LESSON_VIDEO_PATH"));
        }

        // --- Logik untuk butang Simpan ---
        btnSave.setOnClickListener(v -> {
            // ... (logik untuk menyimpan perubahan) ...
            Toast.makeText(adminEditLesson.this, "Lesson Updated!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void goBack(View view) {
        finish();
    }

    public void openAdminActivity(View view)
    {
        int id = view.getId();
        if(id == R.id.cardLessons)
        {
            startActivity(new Intent(this, adminViewLesson.class));
        }
        else if(id == R.id.cardScores)
        {
            startActivity(new Intent(this, AdminReportActivity.class));
        }


    }
}
