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
    private int imageResourceId; // <-- 1. MEDAN BARU DITAMBAH (untuk ID imej)

    // Constructor kosong diperlukan untuk beberapa operasi seperti Firebase
    public Lesson() {
    }

    // 2. CONSTRUCTOR UTAMA DIKEMAS KINI untuk menerima ID imej
    public Lesson(String title, String subject, String year, String description, String content, String videoPath, int imageResourceId) {
        this.title = title;
        this.subject = subject;
        this.year = year;
        this.description = description;
        this.content = content;
        this.videoPath = videoPath;
        this.imageResourceId = imageResourceId; // <-- Simpan ID imej
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

    // 3. GETTER BARU DITAMBAH untuk mendapatkan ID imej
    public int getImageResourceId() {
        return imageResourceId;
    }
}
