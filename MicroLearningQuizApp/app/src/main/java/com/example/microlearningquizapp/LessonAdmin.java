package com.example.microlearningquizapp;

public class LessonAdmin {

    public String title;
    public String subject;
    public String year;
    public String description;
    public String videoUrl;

    public LessonAdmin(String title, String subject, String year) {
        this.title = title;
        this.subject = subject;
        this.year = year;
        this.description = "";
        this.videoUrl = "";
    }
}
