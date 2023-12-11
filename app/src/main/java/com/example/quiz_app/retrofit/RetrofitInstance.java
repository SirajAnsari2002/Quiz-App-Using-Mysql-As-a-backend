package com.example.quiz_app.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    String baseURL = "http://192.168.0.120:80/quiz/";

    public Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(baseURL).
                addConverterFactory(GsonConverterFactory.create()).build();
    }
}
