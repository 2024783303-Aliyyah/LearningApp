// Fail: AdminScoreAdapter.java
package com.example.microlearningquizapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdminScoreAdapter extends RecyclerView.Adapter<AdminScoreAdapter.StudentScoreViewHolder> {

    private Context context;
    private List<StudentOverallScore> studentScores;

    public AdminScoreAdapter(Context context, List<StudentOverallScore> studentScores) {
        this.context = context;
        this.studentScores = studentScores;
    }

    @NonNull
    @Override
    public StudentScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student_overall_card, parent, false);
        return new StudentScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentScoreViewHolder holder, int position) {
        StudentOverallScore currentStudent = studentScores.get(position);
        holder.tvStudentUsername.setText("Username: " + currentStudent.getStudentUsername());

        // Kosongkan container sebelum menambah view baru
        holder.subjectsContainer.removeAllViews();

        // Loop melalui setiap subjek yang ada skornya
        for (SubjectScore subjectScore : currentStudent.getSubjectScores()) {
            // Cipta view untuk setiap subjek
            View subjectView = LayoutInflater.from(context).inflate(R.layout.item_subject_score, holder.subjectsContainer, false);

            TextView tvSubjectName = subjectView.findViewById(R.id.tvSubjectName);
            LinearLayout quizzesContainer = subjectView.findViewById(R.id.quizzes_container);
            tvSubjectName.setText(subjectScore.getSubjectName());

            // Loop melalui setiap kuiz dalam subjek ini
            for (QuizScore quizScore : subjectScore.getQuizScores()) {
                TextView quizScoreView = new TextView(context);
                String scoreText = quizScore.getQuizName() + ": " + quizScore.getScore() + "/" + quizScore.getTotalQuestions();
                quizScoreView.setText(scoreText);
                quizScoreView.setPadding(40, 4, 0, 4); // Indent sikit
                quizzesContainer.addView(quizScoreView);
            }

            // Tambah view subjek ke dalam container utama
            holder.subjectsContainer.addView(subjectView);
        }
    }

    @Override
    public int getItemCount() {
        return studentScores.size();
    }

    public static class StudentScoreViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentUsername;
        LinearLayout subjectsContainer;

        public StudentScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentUsername = itemView.findViewById(R.id.tvStudentUsername);
            subjectsContainer = itemView.findViewById(R.id.subjects_container);
        }
    }
}
