// Fail: StudentOverallScore.java
//score untuk semua subject bagi satu student
package com.example.microlearningquizapp;

import java.util.ArrayList;
import java.util.List;

public class StudentOverallScore {
    private String studentUsername;
    private List<SubjectScore> subjectScores;

    // Constructor
    public StudentOverallScore(String studentUsername) {
        this.studentUsername = studentUsername;
        this.subjectScores = new ArrayList<>();
    }

    // Method to add a subject's scores
    public void addSubjectScore(SubjectScore subjectScore) {
        this.subjectScores.add(subjectScore);
    }

    // Getters
    public String getStudentUsername() { return studentUsername; }
    public List<SubjectScore> getSubjectScores() { return subjectScores; }
}
