package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityToolBinding;

public class ToolActivity extends AppCompatActivity {

    ActivityToolBinding activityToolBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityToolBinding = ActivityToolBinding.inflate(getLayoutInflater());
        setContentView(activityToolBinding.getRoot());


        activityToolBinding.bottomNavigation.setSelectedItemId(R.id.bottom_tool);

        activityToolBinding.bottomNavigation.setOnItemSelectedListener(this::onNavigationItemSelected);

        activityToolBinding.dictionaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ToolActivity.this, DictionaryActivity.class));
            }
        });
        activityToolBinding.translateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ToolActivity.this, TranslateActivity.class));
            }
        });
        activityToolBinding.rTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ToolActivity.this, StoriesActivity.class));
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
        } else if (itemId == R.id.bottom_game) {
            startActivity(new Intent(getApplicationContext(), GameActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else if (itemId == R.id.bottom_account) {
            startActivity(new Intent(getApplicationContext(), AccountActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else return itemId == R.id.bottom_tool;
    }
}