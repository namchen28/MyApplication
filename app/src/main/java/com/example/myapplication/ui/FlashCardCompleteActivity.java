package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityFlashCardCompleteBinding;
import com.example.myapplication.databinding.ActivityQuizFinishBinding;


public class FlashCardCompleteActivity extends AppCompatActivity {

    ActivityFlashCardCompleteBinding activityFlashCardCompleteBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFlashCardCompleteBinding = ActivityFlashCardCompleteBinding.inflate(getLayoutInflater());
        setContentView(activityFlashCardCompleteBinding.getRoot());
        Intent intent = getIntent();
        String topic = intent.getStringExtra("Topic");
        activityFlashCardCompleteBinding.txtTopic.setText("Topic: "+topic);

        activityFlashCardCompleteBinding.btnTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( FlashCardCompleteActivity.this,TopicActivity.class));
                finish();
            }
        });
        activityFlashCardCompleteBinding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( FlashCardCompleteActivity.this,HomeActivity.class));
                finish();
            }
        });


    }
}