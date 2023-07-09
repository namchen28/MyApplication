package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityTopicQuizBinding;
import com.example.myapplication.ui.adapter.CategoryAdapter;
import com.example.myapplication.ui.adapter.CategoryQuizAdapter;
import com.example.myapplication.ui.model.CategoryModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TopicQuizActivity extends AppCompatActivity {
ActivityTopicQuizBinding activityTopicBinding;
    ArrayList<CategoryModel> arrayList = new ArrayList<>();
    CategoryQuizAdapter categoryAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTopicBinding = ActivityTopicQuizBinding.inflate(getLayoutInflater());
        setContentView(activityTopicBinding.getRoot());
        setSupportActionBar(activityTopicBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Topic Quiz");
        activityTopicBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FirebaseFirestore.getInstance().collection("Topic")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            String category = documentSnapshot.getString("category");
                            String icon = documentSnapshot.getString("icon");
                            arrayList.add(new CategoryModel(category,icon));
                        }
                        categoryAdapter = new CategoryQuizAdapter(TopicQuizActivity.this,arrayList);
                        activityTopicBinding.gvTopic.setLayoutManager(new GridLayoutManager(TopicQuizActivity.this,2));
                        activityTopicBinding.gvTopic.setAdapter(categoryAdapter);
                    }
                });

    }
}