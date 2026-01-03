// Fail: LessonAdmin.java
package com.example.microlearningquizapp;

public class LessonAdmin {

    private String lessonId; // ID unik untuk setiap pelajaran
    private String title;
    private String subject;
    private String year;
    private String description;
    private String content;
    private String videoPath;

    public LessonAdmin() {
        // Constructor kosong
    }

    // Constructor utama untuk Admin
    public LessonAdmin(String lessonId, String title, String subject, String year, String description, String content, String videoPath) {
        this.lessonId = lessonId;
        this.title = title;
        this.subject = subject;
        this.year = year;
        this.description = description;
        this.content = content;
        this.videoPath = videoPath;
    }

    // Getter untuk semua medan
    public String getLessonId() {
        return lessonId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getVideoPath() {
        return videoPath;
    }
}
