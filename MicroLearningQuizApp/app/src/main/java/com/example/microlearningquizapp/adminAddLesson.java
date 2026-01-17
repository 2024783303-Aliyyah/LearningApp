package com.example.microlearningquizapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.microlearningquizapp.model.ApiResponse;
import com.example.microlearningquizapp.remote.LessonService;
import com.example.microlearningquizapp.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adminAddLesson extends AppCompatActivity {

    Spinner spinnerSubject;
    EditText etLessonTitle, etYears, etDescription;
    Button btnSave, btnUploadIllustration, btnPickVideo;
    TextView tvSelectedVideo;
    ImageView ivImagePreview;

    private Uri videoUri;
    private Uri imageUri;

    private final ActivityResultLauncher<Intent> pickVideoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    videoUri = result.getData().getData();
                    tvSelectedVideo.setText(videoUri.getLastPathSegment());
                }
            }
    );

    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    ivImagePreview.setImageURI(imageUri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_lesson);

        spinnerSubject = findViewById(R.id.spinnerSubject);
        etLessonTitle = findViewById(R.id.etLessonTitle);
        etYears = findViewById(R.id.etYears);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);
        btnUploadIllustration = findViewById(R.id.btnUploadIllustration);
        btnPickVideo = findViewById(R.id.btnPickVideo);
        tvSelectedVideo = findViewById(R.id.tvSelectedVideo);
        ivImagePreview = findViewById(R.id.ivImagePreview);

        String[] subjects = {"Mathematics", "Science", "English"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subjects);
        spinnerSubject.setAdapter(adapterSpinner);

        btnUploadIllustration.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            pickImageLauncher.launch(intent);
        });

        btnPickVideo.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("video/*");
            pickVideoLauncher.launch(intent);
        });

        btnSave.setOnClickListener(v -> saveLesson());
    }

    private void saveLesson() {
        if (imageUri == null) {
            Toast.makeText(this, "Please pick a header image", Toast.LENGTH_SHORT).show();
            return;
        }
        if (videoUri == null) {
            Toast.makeText(this, "Please pick a lesson video", Toast.LENGTH_SHORT).show();
            return;
        }

        String title = etLessonTitle.getText().toString().trim();
        String years = etYears.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String subject = spinnerSubject.getSelectedItem().toString();

        if (title.isEmpty() || years.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        LessonService service = RetrofitClient.getRetrofitInstance().create(LessonService.class);
        Call<ApiResponse> call = service.addLesson(
                "1",
                subject,
                title,
                years,
                description,
                imageUri.toString(),
                videoUri.toString()
        );

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getStatus().equals("success")) {
                        Toast.makeText(adminAddLesson.this, "Lesson Added!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(adminAddLesson.this, "Failed: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(adminAddLesson.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(adminAddLesson.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goBack(View view) {
        finish();
    }
}


