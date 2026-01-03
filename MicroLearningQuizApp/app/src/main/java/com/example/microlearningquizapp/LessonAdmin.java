
package com.example.microlearningquizapp;

public class LessonAdmin {

    private String lessonId;
    private String title;
    private String subject;
    private String year;
    private String description;
    private String content;
    private String videoPath;
    private int imageResourceId; // Menggunakan int untuk imej dari drawable

    public LessonAdmin() {
        // Constructor kosong
    }

    // Constructor utama
    public LessonAdmin(String lessonId, String title, String subject, String year, String description, String content, String videoPath, int imageResourceId) {
        this.lessonId = lessonId;
        this.title = title;
        this.subject = subject;
        this.year = year;
        this.description = description;
        this.content = content;
        this.videoPath = videoPath;
        this.imageResourceId = imageResourceId;
    }

    // Getter untuk semua medan
    public String getLessonId() { return lessonId; }
    public String getTitle() { return title; }
    public String getSubject() { return subject; }
    public String getYear() { return year; }
    public String getDescription() { return description; }
    public String getContent() { return content; }
    public String getVideoPath() { return videoPath; }
    public int getImageResourceId() { return imageResourceId; }
}
