package com.example.microlearningquizapp.remote;

import com.example.microlearningquizapp.model.ApiResponse;
import com.example.microlearningquizapp.model.LessonAdmin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LessonService {

    @FormUrlEncoded
    @POST("Lesson/addLesson")
    Call<ApiResponse> addLesson(
            @Field("admin_ID") String admin_ID,
            @Field("module_ID") String module_ID,
            @Field("lesson_title") String lesson_title,
            @Field("year") String year,
            @Field("description") String description,
            @Field("header_image") String header_image,
            @Field("lesson_video") String lesson_video
    );

    @GET("Lessons")
    Call<List<LessonAdmin>> getLessons();
}


