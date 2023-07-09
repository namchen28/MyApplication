package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());

        setContentView(activityHomeBinding.getRoot());

        activityHomeBinding.bottomNavigation.setSelectedItemId(R.id.bottom_home);

        activityHomeBinding.bottomNavigation.setOnItemSelectedListener(this::onNavigationItemSelected);
        activityHomeBinding.buttonDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,TopicQuizActivity.class));
            }
        });
        activityHomeBinding.flashcardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,TopicActivity.class));
            }
        });
        activityHomeBinding.quizzImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,TopicQuizActivity.class));
            }
        });
        activityHomeBinding.dictionaryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,DictionaryActivity.class));
            }
        });
        activityHomeBinding.translateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,TranslateActivity.class));
            }
        });

    }

    private boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.bottom_account) {
            startActivity(new Intent(getApplicationContext(), AccountActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else if (itemId == R.id.bottom_course) {
            startActivity(new Intent(getApplicationContext(), CourseActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else if (itemId == R.id.bottom_game) {
            startActivity(new Intent(getApplicationContext(), GameActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else if (itemId == R.id.bottom_tool) {
            startActivity(new Intent(getApplicationContext(), ToolActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else return itemId == R.id.bottom_home;
    }
}