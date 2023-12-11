package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.quiz_app.databinding.ActivityMainBinding;
import com.example.quiz_app.model.Questions;
import com.example.quiz_app.model.QuestionsList;
import com.example.quiz_app.viewModel.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    QuizViewModel quizViewModel;
    List<Questions> questionsList;
    static int result = 0;
    static int totalQuestions = 0;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionsList = new ArrayList<>();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        result = 0;
        totalQuestions = 0;

        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        quizViewModel.getQuestionsListLiveData().observe(this, new Observer<QuestionsList>() {
            @Override
            public void onChanged(QuestionsList questions) {
                questionsList = questions;
                Log.i("TAGY", "THE FIRST QUESTION: " + questions.get(0));
                binding.txtQuestion.setText("Question" +1+":"+questions.get(0).getQuestion());
                binding.radio1.setText(questions.get(0).getOption1());
                binding.radio2.setText(questions.get(0).getOption2());
                binding.radio3.setText(questions.get(0).getOption3());
                binding.radio4.setText(questions.get(0).getOption4());
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(binding.btnNext.getText().equals("Finish")){
                  Intent i1 = new Intent(MainActivity.this, ResultsActivity.class);
                  startActivity(i1);
                  finish();
              }

                int selectedOption = binding.radioGroup.getCheckedRadioButtonId();
                if(selectedOption != -1){
                    RadioButton radioButton = findViewById(selectedOption);
                    if((questionsList.size() - i) > 0){
                        totalQuestions = questionsList.size();
                        if(radioButton.getText().toString().equals(questionsList.get(i-1).getCorrectOption())){
                             result++;
                             binding.txtResult.setText("Correct Answer: " + result);
                        }
                        if(i == 0){
                            i++;
                        }
                        binding.txtQuestion.setText("Question" + (i+1) + ":" + questionsList.get(i).getQuestion());
                        binding.radio1.setText(questionsList.get(i).getOption1());
                        binding.radio2.setText(questionsList.get(i).getOption2());
                        binding.radio3.setText(questionsList.get(i).getOption3());
                        binding.radio4.setText(questionsList.get(i).getOption4());

                        if(i == (questionsList.size()-1)){
                            binding.btnNext.setText("Finish");
                        }
                        binding.radioGroup.clearCheck();
                        i++;
                    }else{
                        if(radioButton.getText().toString().equals(questionsList.get(i-1).getCorrectOption())){
                            result++;
                            binding.txtResult.setText("Correct Answer: " + result);
                        }else{

                        }

                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "YOU NEED TO MAKE A SELECTION", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}