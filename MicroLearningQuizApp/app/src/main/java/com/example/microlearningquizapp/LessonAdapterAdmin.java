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

import java.util.ArrayList;

// Adapter ini direka khas untuk paparan Admin
public class LessonAdapterAdmin extends RecyclerView.Adapter<LessonAdapterAdmin.LessonAdminViewHolder> {

    private Context context;
    private ArrayList<LessonAdmin> lessonList; // Menggunakan model data LessonAdmin

    public LessonAdapterAdmin(Context context, ArrayList<LessonAdmin> lessonList) {
        this.context = context;
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public LessonAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Menggunakan layout item_admin_lesson.xml
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_lesson, parent, false);
        return new LessonAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdminViewHolder holder, int position) {
        // Dapatkan data pelajaran untuk item semasa
        LessonAdmin lesson = lessonList.get(position);

        // Paparkan data ke dalam TextView
        holder.tvLessonTitle.setText(lesson.getTitle());
        holder.tvSubject.setText(lesson.getSubject());
        holder.tvYear.setText(lesson.getYear());

        // --- Logik untuk Butang ---

        // Logik untuk butang Edit
        holder.btnEdit.setOnClickListener(v -> {
            // Cipta Intent untuk membuka aktiviti edit (cth: adminEditLesson.class)
            Intent intent = new Intent(context, adminEditLesson.class);
            // Hantar ID pelajaran yang unik supaya aktiviti edit tahu pelajaran mana yang perlu dimuatkan
            intent.putExtra("LESSON_ID", lesson.getLessonId());
            context.startActivity(intent);
        });

        // Logik untuk butang Delete
        holder.btnDelete.setOnClickListener(v -> {
            // DI SINI: Tambah logik untuk memadam pelajaran
            // Contoh mudah: Papar Toast untuk pengesahan
            String lessonTitle = lesson.getTitle();
            Toast.makeText(context, "Delete: " + lessonTitle, Toast.LENGTH_SHORT).show();

            // Dalam aplikasi sebenar, anda akan:
            // 1. Papar dialog pengesahan (AlertDialog).
            // 2. Jika "Ya", panggil fungsi untuk memadam data dari pangkalan data (Firebase/SQLite).
            // 3. Jika berjaya padam dari pangkalan data:
            //    lessonList.remove(position);
            //    notifyItemRemoved(position);
            //    notifyItemRangeChanged(position, lessonList.size());
        });
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    // ViewHolder yang memegang view untuk setiap item admin
    public static class LessonAdminViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi semua view dari item_admin_lesson.xml
        TextView tvLessonTitle, tvSubject, tvYear;
        Button btnEdit, btnDelete;

        public LessonAdminViewHolder(@NonNull View itemView) {
            super(itemView);
            // Pautkan view dari ID dalam XML
            tvLessonTitle = itemView.findViewById(R.id.tvLessonTitle);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvYear = itemView.findViewById(R.id.tvYear);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
