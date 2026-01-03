package com.example.microlearningquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class adminEditLesson extends AppCompatActivity {

    Spinner spinnerSubject;
    EditText etLessonTitle, etYears, etDescription, etVideoUrl;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_edit_lesson);

        spinnerSubject = findViewById(R.id.spinnerSubject);
        etLessonTitle = findViewById(R.id.etLessonTitle);
        etYears = findViewById(R.id.etYears);
        etDescription = findViewById(R.id.etDescription);
        etVideoUrl = findViewById(R.id.etVideoUrl);
        btnSave = findViewById(R.id.btnSave);

        // spinner (subject)
        String[] subjects = {"Mathematic", "Science", "English"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                subjects
        );
        spinnerSubject.setAdapter(adapter);

        //Terima data dari Intent
        Intent intent = getIntent();
        String lessonTitle = intent.getStringExtra("lessonTitle");
        String subject = intent.getStringExtra("Subject");
        String year = intent.getStringExtra("Year");
        String description = intent.getStringExtra("description");
        String videoUrl = intent.getStringExtra("videoUrl");

        // Display data in field
        etLessonTitle.setText(lessonTitle);
        etYears.setText(year);
        etDescription.setText(description);
        etVideoUrl.setText(videoUrl);

        //Choice subject pada spinner
        if (subject != null) {
            int spinnerPosition = adapter.getPosition(subject);
            spinnerSubject.setSelection(spinnerPosition);
        }

        //button save
        btnSave.setOnClickListener(v -> {
            // Ambil input terbaru
            String updatedTitle = etLessonTitle.getText().toString();
            String updatedSubject = spinnerSubject.getSelectedItem().toString();
            String updatedYear = etYears.getText().toString();
            String updatedDescription = etDescription.getText().toString();
            String updatedVideoUrl = etVideoUrl.getText().toString();

            // Toast
            Toast.makeText(adminEditLesson.this, "Lesson Updated: " + updatedTitle + " "
                    + updatedSubject, Toast.LENGTH_SHORT).show();

            //CLOSE activity after save
            finish();
        });

    }

    // Tambah fungsi ini
    public void goBack(View view) {
        finish(); // 'finish()' akan menutup aktiviti semasa dan kembali ke skrin sebelumnya
    }

}