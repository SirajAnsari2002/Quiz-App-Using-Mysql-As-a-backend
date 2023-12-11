package com.example.quiz_app.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quiz_app.model.QuestionsList;
import com.example.quiz_app.retrofit.QuestionsAPI;
import com.example.quiz_app.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {
    private QuestionsAPI questionsAPI;

    public QuizRepository() {
        questionsAPI = new RetrofitInstance().
                getRetrofitInstance().create(QuestionsAPI.class);
    }
    public LiveData<QuestionsList> getQuestionsFromAPI(){
        MutableLiveData<QuestionsList> data = new MutableLiveData<>();
        QuestionsList list;
        Call<QuestionsList> response = questionsAPI.getQuestions();
        response.enqueue(new Callback<QuestionsList>() {
            @Override
            public void onResponse(Call<QuestionsList> call, Response<QuestionsList> response) {
                QuestionsList list = response.body();
                data.setValue(list);
                Log.i("TAGY",""+data.getValue());

            }

            @Override
            public void onFailure(Call<QuestionsList> call, Throwable t) {

            }
        });
        /*
        if(response != null){
            list = (QuestionsList) response.body();
            data.postValue(list);
            Log.i("TAGY",""+data.getValue());
        }
        */


        return data;
    }
}
