package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityDifficultyBinding;
import com.example.myapplication.ui.model.CategoryModel;

public class DifficultyActivity extends AppCompatActivity {

    ActivityDifficultyBinding activityDifficultyBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDifficultyBinding = ActivityDifficultyBinding.inflate(getLayoutInflater());
        setContentView(activityDifficultyBinding.getRoot());
        Intent intent = getIntent();
        CategoryModel categoryModel = (CategoryModel) intent.getSerializableExtra("Category");
        activityDifficultyBinding.easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DifficultyActivity.this, QuizzActivity.class);
                intent1.putExtra("Category", categoryModel);
                intent1.putExtra("Level", "Easy");
                startActivity(intent1);
            }
        });
        activityDifficultyBinding.mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DifficultyActivity.this, QuizzActivity.class);
                intent2.putExtra("Category", categoryModel);
                intent2.putExtra("Level", "Medium");
                startActivity(intent2);
            }

        });
        activityDifficultyBinding.hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(DifficultyActivity.this, QuizzActivity.class);
                intent3.putExtra("Category", categoryModel);
                intent3.putExtra("Level", "Hard");
                startActivity(intent3);
            }
        });
            setSupportActionBar(activityDifficultyBinding.toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Choose Difficulty");
            activityDifficultyBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }
