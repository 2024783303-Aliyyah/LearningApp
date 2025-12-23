package com.example.microlearningquizapp;

public class Lesson {
    private String subject;
    private String title;
    private String year;
    private String description;
    private String content;
    private String videoUrl; // or Uri

    public Lesson(String subject, String title, String year, String description, String content, String videoUrl) {
        this.subject = subject;
        this.title = title;
        this.year = year;
        this.description = description;
        this.content = content;
        this.videoUrl = videoUrl;
    }

    public String getSubject() { return subject; }
    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getDescription() { return description; }
    public String getContent() { return content; }
    public String getVideoUrl() { return videoUrl; }
}
