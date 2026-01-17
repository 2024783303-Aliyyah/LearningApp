// Fail: LessonAdapterAdmin.java
package com.example.microlearningquizapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.microlearningquizapp.model.LessonAdmin;

import java.util.ArrayList;

public class LessonAdapterAdmin extends RecyclerView.Adapter<LessonAdapterAdmin.LessonAdminViewHolder> {

    private Context context;
    private ArrayList<LessonAdmin> lessonList;

    public LessonAdapterAdmin(Context context, ArrayList<LessonAdmin> lessonList) {
        this.context = context;
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public LessonAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_lesson, parent, false);
        return new LessonAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdminViewHolder holder, int position) {
        LessonAdmin lesson = lessonList.get(position);
        holder.tvLessonTitle.setText(lesson.getLesson_title());
        holder.tvSubject.setText(lesson.getSubject());
        holder.tvYear.setText(lesson.getYear());

        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, adminEditLesson.class);
            intent.putExtra("LESSON_ID", lesson.getId());
            intent.putExtra("LESSON_TITLE", lesson.getLesson_title());
            intent.putExtra("LESSON_SUBJECT", lesson.getSubject());
            intent.putExtra("LESSON_YEAR", lesson.getYear());
            intent.putExtra("LESSON_DESCRIPTION", lesson.getDescription());
            intent.putExtra("LESSON_VIDEO_PATH", lesson.getLesson_video());
            intent.putExtra("LESSON_IMAGE_PATH", lesson.getHeader_image());
            context.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener(v -> {
            Toast.makeText(context, "Delete: " + lesson.getLesson_title(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    public static class LessonAdminViewHolder extends RecyclerView.ViewHolder {
        TextView tvLessonTitle, tvSubject, tvYear;
        Button btnEdit, btnDelete;

        public LessonAdminViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLessonTitle = itemView.findViewById(R.id.tvLessonTitle);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvYear = itemView.findViewById(R.id.tvYear);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
