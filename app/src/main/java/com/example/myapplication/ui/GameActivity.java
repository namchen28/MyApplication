package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityGameBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding activityGameBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGameBinding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(activityGameBinding.getRoot());
        setSupportActionBar(activityGameBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Let's play");
        activityGameBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        activityGameBinding.bottomNavigation.setSelectedItemId(R.id.bottom_game);

        activityGameBinding.bottomNavigation.setOnItemSelectedListener(this::onNavigationItemSelected);
        activityGameBinding.rFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( GameActivity.this,TopicActivity.class));
            }
        });
        activityGameBinding.r2Quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( GameActivity.this,TopicQuizActivity.class));
            }
        });

    }

    private boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.bottom_home) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else if (itemId == R.id.bottom_course) {
            startActivity(new Intent(getApplicationContext(), CourseActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else if (itemId == R.id.bottom_account) {
            startActivity(new Intent(getApplicationContext(), AccountActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else if (itemId == R.id.bottom_tool) {
            startActivity(new Intent(getApplicationContext(), ToolActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else return itemId == R.id.bottom_game;
    }
}