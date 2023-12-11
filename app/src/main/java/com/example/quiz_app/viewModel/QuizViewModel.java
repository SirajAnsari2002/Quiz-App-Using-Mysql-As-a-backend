package com.example.quiz_app.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quiz_app.model.QuestionsList;
import com.example.quiz_app.repository.QuizRepository;

public class QuizViewModel extends ViewModel {

    QuizRepository repository = new QuizRepository();

    LiveData<QuestionsList> questionsListLiveData;

    public QuizViewModel() {
        questionsListLiveData = repository.getQuestionsFromAPI();
    }

    public LiveData<QuestionsList> getQuestionsListLiveData(){
        return questionsListLiveData;
    }



}
