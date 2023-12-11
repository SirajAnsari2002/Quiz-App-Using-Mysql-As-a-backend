package com.example.quiz_app.retrofit;

import com.example.quiz_app.model.QuestionsList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface QuestionsAPI {

    @GET("questionsapi.php")
    Call<QuestionsList> getQuestions();

}
