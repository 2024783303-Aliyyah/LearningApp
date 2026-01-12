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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

// Import Glide jika anda mahu papar imej dari URL (sebagai alternatif)
// import com.bumptech.glide.Glide;

public class adminEditLesson extends AppCompatActivity {

    // --- Deklarasi View ---
    Spinner spinnerSubject;
    EditText etLessonTitle, etYears, etDescription;
    Button btnSave, btnPickVideo, btnUploadIllustration;
    TextView tvSelectedVideo;
    ImageView ivImagePreview;

    // --- Pembolehubah untuk menyimpan URI ---
    private Uri videoUri; // Akan memegang URI video yang baru atau sedia ada
    private Uri imageUri; // Akan memegang URI imej yang baru atau sedia ada

    // --- Launcher untuk memilih video dari storan ---
    private final ActivityResultLauncher<Intent> pickVideoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    videoUri = result.getData().getData(); // Simpan URI video yang baru dipilih
                    // Paparkan nama fail yang baru dipilih
                    tvSelectedVideo.setText(videoUri.getLastPathSegment());
                }
            }
    );

    // --- Launcher untuk memilih imej dari storan ---
    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData(); // Simpan URI imej yang baru dipilih
                    // Paparkan pratonton imej yang baru dipilih
                    ivImagePreview.setImageURI(imageUri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_lesson);

        // --- Pautkan semua View dari XML ---
        spinnerSubject = findViewById(R.id.spinnerSubject);
        etLessonTitle = findViewById(R.id.etLessonTitle);
        etYears = findViewById(R.id.etYears);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);
        btnUploadIllustration = findViewById(R.id.btnUploadIllustration);
        btnPickVideo = findViewById(R.id.btnPickVideo);
        tvSelectedVideo = findViewById(R.id.tvSelectedVideo);
        ivImagePreview = findViewById(R.id.ivImagePreview);

        // --- Sediakan Spinner Subjek ---
        String[] subjects = {"Mathematic", "Science", "English"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subjects);
        spinnerSubject.setAdapter(adapter);

        // --- Ambil data sedia ada dari Intent dan paparkan dalam borang ---
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String lessonId = extras.getString("LESSON_ID"); // Simpan ID untuk rujukan semasa menyimpan

            // Paparkan data teks
            etLessonTitle.setText(extras.getString("LESSON_TITLE"));
            etYears.setText(extras.getString("LESSON_YEAR"));
            etDescription.setText(extras.getString("LESSON_DESCRIPTION"));

            // Tetapkan pilihan spinner berdasarkan subjek sedia ada
            String subject = extras.getString("LESSON_SUBJECT");
            if (subject != null) {
                int spinnerPosition = adapter.getPosition(subject);
                if (spinnerPosition >= 0) {
                    spinnerSubject.setSelection(spinnerPosition);
                }
            }

            // Paparkan imej sedia ada (menggunakan ID dari drawable)
            int imageResId = extras.getInt("LESSON_IMAGE_ID", 0);
            if (imageResId != 0) {
                ivImagePreview.setImageResource(imageResId);
                // Jika anda menyimpan URI, anda perlu menukarnya kepada URI sebenar di sini
                // imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + imageResId);
            }

            // Paparkan maklumat video sedia ada
            String existingVideoPath = extras.getString("LESSON_VIDEO_PATH");
            if (existingVideoPath != null && !existingVideoPath.isEmpty()) {
                videoUri = Uri.parse(existingVideoPath);
                tvSelectedVideo.setText("Current: " + videoUri.getLastPathSegment());
            }
        }

        // --- Logik untuk butang "Pick Image" ---
        btnUploadIllustration.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            pickImageLauncher.launch(intent);
        });

        // --- Logik untuk butang "Pick Video" ---
        btnPickVideo.setOnClickListener(v -> {
            Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
            pickIntent.setType("video/*");
            pickVideoLauncher.launch(pickIntent);
        });


        // --- Logik untuk butang "Save Changes" ---
        btnSave.setOnClickListener(v -> {
            // Ambil semua data yang telah dikemas kini dari borang
            String updatedTitle = etLessonTitle.getText().toString();
            String updatedSubject = spinnerSubject.getSelectedItem().toString();
            String updatedYear = etYears.getText().toString();
            String updatedDescription = etDescription.getText().toString();

            // Semak jika imej atau video telah dipilih
            if (imageUri == null && ivImagePreview.getDrawable() == null) {
                Toast.makeText(this, "Please pick a header image", Toast.LENGTH_SHORT).show();
                return;
            }
            if (videoUri == null) {
                Toast.makeText(this, "Please pick a lesson video", Toast.LENGTH_SHORT).show();
                return;
            }

            // DI SINI: Anda akan melaksanakan logik untuk mengemas kini data
            // dalam pangkalan data atau LessonRepository menggunakan lessonId
            // dan URI imej/video yang baru.
            // Contoh: LessonRepository.getInstance().updateLesson(lessonId, updatedTitle, ..., imageUri.toString(), videoUri.toString());

            Toast.makeText(adminEditLesson.this, "Lesson Updated: " + updatedTitle, Toast.LENGTH_SHORT).show();
            finish(); // Tutup aktiviti selepas menyimpan
        });
    }

    // Fungsi untuk butang kembali dalam XML
    public void goBack(View view) {
        finish();
    }

    // Fungsi untuk menu navigasi bawah dalam XML
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
