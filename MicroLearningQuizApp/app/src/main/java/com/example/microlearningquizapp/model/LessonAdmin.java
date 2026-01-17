//file:LessonAdmin
package com.example.microlearningquizapp.model;

import com.google.gson.annotations.SerializedName;

public class LessonAdmin {

    @SerializedName("id")
    private String id;

    @SerializedName("lesson_title")
    private String lesson_title;

    @SerializedName("subject")
    private String subject;

    @SerializedName("year")
    private String year;

    @SerializedName("description")
    private String description;

    @SerializedName("header_image")
    private String header_image;

    @SerializedName("lesson_video")
    private String lesson_video;

    public LessonAdmin(String id, String lesson_title, String subject, String year, String description,
                       String header_image, String lesson_video) {
        this.id = id;
        this.lesson_title = lesson_title;
        this.subject = subject;
        this.year = year;
        this.description = description;
        this.header_image = header_image;
        this.lesson_video = lesson_video;
    }

    // Getter & Setter
    public String getId() { return id; }
    public String getLesson_title() { return lesson_title; }
    public String getSubject() { return subject; }
    public String getYear() { return year; }
    public String getDescription() { return description; }
    public String getHeader_image() { return header_image; }
    public String getLesson_video() { return lesson_video; }

    public void setId(String id) { this.id = id; }
    public void setLesson_title(String lesson_title) { this.lesson_title = lesson_title; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setYear(String year) { this.year = year; }
    public void setDescription(String description) { this.description = description; }
    public void setHeader_image(String header_image) { this.header_image = header_image; }
    public void setLesson_video(String lesson_video) { this.lesson_video = lesson_video; }
}

