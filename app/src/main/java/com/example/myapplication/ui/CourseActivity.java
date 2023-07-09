package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCourseBinding;
import com.example.myapplication.ui.adapter.CourseAdapter;
import com.example.myapplication.ui.model.CoursesModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {
    ActivityCourseBinding activityCourseBinding;
    ArrayList<CoursesModel> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCourseBinding = ActivityCourseBinding.inflate(getLayoutInflater());
        setContentView(activityCourseBinding.getRoot());

        activityCourseBinding.bottomNavigation.setSelectedItemId(R.id.bottom_course);

        activityCourseBinding.bottomNavigation.setOnItemSelectedListener(this::onNavigationItemSelected);
        FirebaseFirestore.getInstance().collection("Courses")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot d : queryDocumentSnapshots){
                            String video = d.getString("video");
                            String name = d.getString("name");
                            arrayList.add(new CoursesModel(video,name));

                        }
                        CourseAdapter courseAdapter = new CourseAdapter(CourseActivity.this,arrayList);
                        activityCourseBinding.rcvCourses.setLayoutManager(new LinearLayoutManager(CourseActivity.this));
                        activityCourseBinding.rcvCourses.setAdapter(courseAdapter);


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
        } else if (itemId == R.id.bottom_account) {
            startActivity(new Intent(getApplicationContext(), AccountActivity.class));
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
        } else return itemId == R.id.bottom_course;
    }
}