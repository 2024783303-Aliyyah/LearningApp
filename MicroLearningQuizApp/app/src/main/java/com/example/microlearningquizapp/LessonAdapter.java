// Fail: LessonAdapter.java
package com.example.microlearningquizapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private Context context;
    private ArrayList<Lesson> lessonList;
    private boolean isForAdmin;

    public LessonAdapter(Context context, ArrayList<Lesson> lessonList, boolean isForAdmin) {
        this.context = context;
        this.lessonList = lessonList;
        this.isForAdmin = isForAdmin;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Gunakan layout item yang sesuai untuk pelajar (cth: lesson_item.xml)
        View view = LayoutInflater.from(context).inflate(R.layout.item_lessons, parent, false);
        return new LessonViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson lesson = lessonList.get(position);
        holder.tvLessonTitle.setText(lesson.getTitle());

        if(isForAdmin)
        {
            holder.tvLessonSubject.setVisibility(View.VISIBLE);
            holder.tvLessonSubject.setText(lesson.getSubject() + " - " + lesson.getYear());
        }
        else{
            holder.tvLessonSubject.setVisibility(View.GONE);
        }

        // Tetapkan OnClickListener untuk setiap item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, lessonActivity.class);
            // Hantar semua data pelajaran ke lessonActivity
            intent.putExtra("title", lesson.getTitle());
            intent.putExtra("subject", lesson.getSubject());
            intent.putExtra("year", lesson.getYear());
            intent.putExtra("description", lesson.getDescription());
            intent.putExtra("content", lesson.getContent());
            intent.putExtra("video", lesson.getVideoPath());
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    // ViewHolder untuk memegang view bagi setiap item
    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView tvLessonTitle, tvLessonSubject;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            // Pautkan view dari lesson_item.xml
            tvLessonTitle = itemView.findViewById(R.id.tvLessonTitle);
            tvLessonSubject = itemView.findViewById(R.id.tvLessonSubject);
        }
    }
}
