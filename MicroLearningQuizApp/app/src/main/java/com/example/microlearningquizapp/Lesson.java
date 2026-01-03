// Fail: Lesson.java
package com.example.microlearningquizapp;

public class Lesson {

    // Semua medan yang diperlukan untuk satu pelajaran
    private String title;
    private String subject;
    private String year;
    private String description;
    private String content;
    private String videoPath;

    // Constructor kosong diperlukan untuk beberapa operasi seperti Firebase
    public Lesson() {
    }

    // Constructor utama untuk mencipta objek Lesson
    public Lesson(String title, String subject, String year, String description, String content, String videoPath) {
        this.title = title;
        this.subject = subject;
        this.year = year;
        this.description = description;
        this.content = content;
        this.videoPath = videoPath;
    }

    // Getter untuk setiap medan (diperlukan oleh Adapter)
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
