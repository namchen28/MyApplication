package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityStoriesBinding;
import com.example.myapplication.ui.adapter.StoriesAdapter;
import com.example.myapplication.ui.model.StoriesModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StoriesActivity extends AppCompatActivity {
    ActivityStoriesBinding activityStoriesBinding;
    ArrayList<StoriesModel> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStoriesBinding = ActivityStoriesBinding.inflate(getLayoutInflater());
        setContentView(activityStoriesBinding.getRoot());
        setSupportActionBar(activityStoriesBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Stories");
        activityStoriesBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FirebaseFirestore.getInstance().collection("Stories")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot d : queryDocumentSnapshots){
                            String url = d.getString("url");
                            String name = d.getString("name");
                            String picture = d.getString("picture");
                            arrayList.add(new StoriesModel(url,name,picture));

                        }
                         StoriesAdapter courseAdapter = new StoriesAdapter(StoriesActivity.this,arrayList);
                         activityStoriesBinding.rcvStories.setLayoutManager(new LinearLayoutManager(StoriesActivity.this));
                         activityStoriesBinding.rcvStories.setAdapter(courseAdapter);


                    }
                });
    }

}