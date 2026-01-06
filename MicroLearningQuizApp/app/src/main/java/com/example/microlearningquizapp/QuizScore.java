// Fail: QuizScore.java
//nak simpan skor untuk setiap quiz
package com.example.microlearningquizapp;

public class QuizScore {
    private String quizName;
    private int score;
    private int totalQuestions;

    // Constructor
    public QuizScore(String quizName, int score, int totalQuestions) {
        this.quizName = quizName;
        this.score = score;
        this.totalQuestions = totalQuestions;
    }

    // Getters
    public String getQuizName() { return quizName; }
    public int getScore() { return score; }
    public int getTotalQuestions() { return totalQuestions; }
}
