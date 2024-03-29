package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityAccountBinding;
import com.example.myapplication.databinding.ActivityToolBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends AppCompatActivity {

    ActivityAccountBinding activityAccountBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAccountBinding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(activityAccountBinding.getRoot());
        setSupportActionBar(activityAccountBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Account");
        activityAccountBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        activityAccountBinding.bottomNavigation.setSelectedItemId(R.id.bottom_account);

        activityAccountBinding.bottomNavigation.setOnItemSelectedListener(this::onNavigationItemSelected);
        findViewById(R.id.editAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this,EditAccountActivity.class));
            }
        });
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AccountActivity.this,LoginActivity.class));
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
        } else if (itemId == R.id.bottom_tool) {
            startActivity(new Intent(getApplicationContext(), ToolActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            return true;
        } else return itemId == R.id.bottom_account;
    }
}