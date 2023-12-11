package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quiz_app.databinding.ActivityResultsBinding;

public class ResultsActivity extends AppCompatActivity {
    ActivityResultsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_results);
        binding.txtAnswer.setText("Your score is: "
        + MainActivity.result
        +"/"+ MainActivity.totalQuestions);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(i) ;
            }
        });
    }
}