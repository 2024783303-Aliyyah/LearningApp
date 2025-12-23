package com.example.microlearningquizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LessonAdapter
        extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    Context context;
    ArrayList<Lesson> lessonList;

    public LessonAdapter(Context context, ArrayList<Lesson> lessonList) {
        this.context = context;
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_lessons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder, int position) {

        Lesson lesson = lessonList.get(position);
        holder.tvLessonName.setText(lesson.getName());
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvLessonName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLessonName = itemView.findViewById(R.id.item_lessons);
        }
    }
}

