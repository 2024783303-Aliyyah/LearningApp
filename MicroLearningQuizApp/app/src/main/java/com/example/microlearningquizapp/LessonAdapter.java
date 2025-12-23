package com.example.microlearningquizapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private List<Lesson> lessonList;
    private Context context;

    public LessonAdapter(Context context, List<Lesson> lessonList) {
        this.context = context;
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lessons, parent, false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson lesson = lessonList.get(position);
        holder.title.setText(lesson.getTitle());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, lessonActivity.class);
            intent.putExtra("subject", lesson.getSubject());
            intent.putExtra("title", lesson.getTitle());
            intent.putExtra("year", lesson.getYear());
            intent.putExtra("description", lesson.getDescription());
            intent.putExtra("content", lesson.getContent());
            intent.putExtra("video", lesson.getVideoUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
        }
    }
}
