package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCoursesVideoBinding;
import com.example.myapplication.ui.model.CoursesModel;

public class CoursesVideoActivity extends AppCompatActivity {
    ActivityCoursesVideoBinding activityCoursesVideoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCoursesVideoBinding = ActivityCoursesVideoBinding.inflate(getLayoutInflater());
        setContentView(activityCoursesVideoBinding.getRoot());
        setSupportActionBar(activityCoursesVideoBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityCoursesVideoBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        CoursesModel coursesModel = (CoursesModel) intent.getSerializableExtra("Video");

        getSupportActionBar().setTitle(coursesModel.getName());
        activityCoursesVideoBinding.wv.loadUrl(coursesModel.getVideo());
        activityCoursesVideoBinding.wv.setWebViewClient(new WebViewClient());
        activityCoursesVideoBinding.wv.getSettings().setJavaScriptEnabled(true);



    }
}