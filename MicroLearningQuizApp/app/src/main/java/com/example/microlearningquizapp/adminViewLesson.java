//File:adminViewLesson.java
package com.example.microlearningquizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.microlearningquizapp.model.LessonAdmin;
import com.example.microlearningquizapp.remote.LessonService;
import com.example.microlearningquizapp.remote.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adminViewLesson extends AppCompatActivity {

    RecyclerView rvLessons;
    Button btnAddLesson, btnPrint;

    ArrayList<LessonAdmin> lessonList;
    LessonAdapterAdmin adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_lesson);

        rvLessons = findViewById(R.id.rvLessons);
        btnAddLesson = findViewById(R.id.btnAddLesson);
        btnPrint = findViewById(R.id.btnPrint);

        lessonList = new ArrayList<>();
        adapter = new LessonAdapterAdmin(this, lessonList);
        rvLessons.setLayoutManager(new LinearLayoutManager(this));
        rvLessons.setAdapter(adapter);

        loadAllLessonsForAdmin();

        btnAddLesson.setOnClickListener(v -> {
            startActivity(new Intent(adminViewLesson.this, adminAddLesson.class));
        });

        btnPrint.setOnClickListener(v -> {
            PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
            String jobName = getString(R.string.app_name) + " Document";
            printManager.print(jobName, new LessonPrintAdapter(this, lessonList), null);
        });
    }

    private void loadAllLessonsForAdmin() {
        LessonService service = RetrofitClient.getRetrofitInstance().create(LessonService.class);
        service.getLessons().enqueue(new Callback<List<LessonAdmin>>() {
            @Override
            public void onResponse(Call<List<LessonAdmin>> call, Response<List<LessonAdmin>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lessonList.clear();
                    lessonList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(adminViewLesson.this, "Failed to load lessons", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LessonAdmin>> call, Throwable t) {
                Toast.makeText(adminViewLesson.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}



