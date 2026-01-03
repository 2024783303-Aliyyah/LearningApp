// Fail: LessonAdapterAdmin.java
package com.example.microlearningquizapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class LessonAdapterAdmin extends RecyclerView.Adapter<LessonAdapterAdmin.LessonAdminViewHolder> {

    private Context context;
    private ArrayList<LessonAdmin> lessonAdminList;

    public LessonAdapterAdmin(Context context, ArrayList<LessonAdmin> lessonAdminList) {
        this.context = context;
        this.lessonAdminList = lessonAdminList;
    }

    @NonNull
    @Override
    public LessonAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Gunakan layout item yang direka khas untuk admin (cth: lesson_item_admin.xml)
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_lesson,parent, false);
        return new LessonAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdminViewHolder holder, int position) {
        LessonAdmin lesson = lessonAdminList.get(position);
        holder.tvLessonTitle.setText(lesson.getTitle());
        holder.tvLessonSubject.setText(lesson.getSubject() + " - " + lesson.getYear());

        // Logik untuk butang Edit
        holder.btnEdit.setOnClickListener(v -> {
            // Cipta Intent untuk membuka adminEditLesson.class
            Intent intent = new Intent(context, adminEditLesson.class);
            // Hantar ID pelajaran yang unik untuk memberitahu aktiviti mana yang perlu diedit
            intent.putExtra("LESSON_ID", lesson.getLessonId());
            context.startActivity(intent);
        });

        // Logik untuk butang Delete
        holder.btnDelete.setOnClickListener(v -> {
            // Tambah logik untuk memadam pelajaran di sini
            // Contoh: Papar dialog pengesahan sebelum memadam
            String lessonIdToDelete = lesson.getLessonId();
            // Anda akan panggil fungsi pangkalan data di sini untuk memadam pelajaran dengan ID ini
            Toast.makeText(context, "Delete lesson: " + lesson.getTitle(), Toast.LENGTH_SHORT).show();
            // Selepas berjaya padam, kemas kini senarai dan panggil notifyDataSetChanged()
        });
    }

    @Override
    public int getItemCount() {
        return lessonAdminList.size();
    }

    // ViewHolder untuk item admin
    public static class LessonAdminViewHolder extends RecyclerView.ViewHolder {
        TextView tvLessonTitle, tvLessonSubject;
        Button btnEdit, btnDelete;

        public LessonAdminViewHolder(@NonNull View itemView) {
            super(itemView);
            // Pautkan view dari lesson_item_admin.xml
            tvLessonTitle = itemView.findViewById(R.id.tvLessonTitle);
            tvLessonSubject = itemView.findViewById(R.id.tvSubject);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
