package com.example.microlearningquizapp;

import android.os.Bundle;
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

public class adminAddLesson extends AppCompatActivity {

    Spinner spinnerSubject;
    EditText etLessonTitle, etYears, etDescription, etVideoUrl;
    Button btnSave, btnUploadIllustration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_add_lesson);

        spinnerSubject = findViewById(R.id.spinnerSubject);
        etLessonTitle = findViewById(R.id.etLessonTitle);
        etYears = findViewById(R.id.etYears);
        etDescription = findViewById(R.id.etDescription);
        etVideoUrl = findViewById(R.id.etVideoUrl);
        btnSave = findViewById(R.id.btnSave);
        btnUploadIllustration = findViewById(R.id.btnUploadIllustration);

        //Tambah spinner
        String[] subjects = {"Mathematic", "Science", "English"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                subjects
        );
        spinnerSubject.setAdapter(adapter );

        btnSave.setOnClickListener(v -> {

            String subject = spinnerSubject.getSelectedItem().toString();
            String title = etLessonTitle.getText().toString();
            String year = etYears.getText().toString();

            Toast.makeText(this,
                    "Lesson Added:\n" + subject + " - " + title,
                    Toast.LENGTH_SHORT).show();
        });
    }
}