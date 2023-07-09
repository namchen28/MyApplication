package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityQuizFinishBinding;

public class QuizFinishActivity extends AppCompatActivity {
    ActivityQuizFinishBinding activityQuizFinishBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityQuizFinishBinding = ActivityQuizFinishBinding.inflate(getLayoutInflater());
        setContentView(activityQuizFinishBinding.getRoot());
        Intent intent = getIntent();
        String topic = intent.getStringExtra("Topic");
        int correct = intent.getIntExtra("Correct",0);
        int Qs = intent.getIntExtra("QS",0);
        String level = intent.getStringExtra("Level");
        activityQuizFinishBinding.txtCorrect.setText(correct+"/"+Qs);
        activityQuizFinishBinding.txtTopic.setText("Topic: "+topic);
        activityQuizFinishBinding.txtLevel.setText("Difficulty: "+level);

        activityQuizFinishBinding.btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( QuizFinishActivity.this,TopicQuizActivity.class));
                finish();
            }
        });
        activityQuizFinishBinding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( QuizFinishActivity.this,HomeActivity.class));
                finish();
            }
        });


    }
}