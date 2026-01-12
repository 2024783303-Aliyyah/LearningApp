// Fail: SubjectScore.java
//nak simpan semua score bagi satu subject
package com.example.microlearningquizapp;

import java.util.ArrayList;
import java.util.List;

public class SubjectScore {
    private String subjectName;
    private List<QuizScore> quizScores;

    // Constructor
    public SubjectScore(String subjectName) {
        this.subjectName = subjectName;
        this.quizScores = new ArrayList<>();
    }

    // Method to add a quiz score
    public void addQuizScore(QuizScore quizScore) {
        this.quizScores.add(quizScore);
    }

    // Getters
    public String getSubjectName() { return subjectName; }
    public List<QuizScore> getQuizScores() { return quizScores; }
}
