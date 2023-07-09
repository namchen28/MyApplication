package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityFlashcardBinding;
import com.example.myapplication.ui.model.CategoryModel;
import com.example.myapplication.ui.model.FlashCardModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class FlashcardActivity extends AppCompatActivity {
    ActivityFlashcardBinding activityFlashcardBinding;
    boolean isBackVisible = false;
    ArrayList<FlashCardModel> arrayList ;
    FlashCardModel flashCardModel;
    int numberQS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFlashcardBinding = ActivityFlashcardBinding.inflate(getLayoutInflater());
        setContentView(activityFlashcardBinding.getRoot());
        setSupportActionBar(activityFlashcardBinding.toolbars);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Flash Card");
        activityFlashcardBinding.toolbars.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initQs();

        AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.front_card);
        AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.behind_card);
        activityFlashcardBinding.btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRightOut.setTarget(activityFlashcardBinding.imgFront);
                setLeftIn.setTarget(activityFlashcardBinding.txtBehind);
                setRightOut.start();
                setLeftIn.start();
                isBackVisible = false;
            }
        });
        activityFlashcardBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberQS<arrayList.size()-1){
                    numberQS++;
                    nextQS(numberQS);
                }else{
                    startActivity(new Intent(FlashcardActivity.this,FlashCardCompleteActivity.class));

                }

            }
        });



    }

    private void initQs() {
        arrayList = new ArrayList<>();
        Intent intent = getIntent();
        CategoryModel categoryModel = (CategoryModel) intent.getSerializableExtra("Category");
        activityFlashcardBinding.txtTopic.setText("Topic: "+categoryModel.getCategory());
        FirebaseFirestore.getInstance().collection("flashcard")
                .whereEqualTo("category",categoryModel.getCategory())

                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            String picture = documentSnapshot.getString("picture");
                            String pro = documentSnapshot.getString("pro");
                            String type = documentSnapshot.getString("type");
                            String word = documentSnapshot.getString("word");
                            arrayList.add(new FlashCardModel(picture,pro,type,word));
                        }
                        if(arrayList.size()>0){
                            activityFlashcardBinding.txtNumberQS.setText((numberQS+1)+"/"+arrayList.size());
                            flashCardModel = arrayList.get(0);
                            activityFlashcardBinding.progressQuestion.setMax(arrayList.size());
                            nextQS(0);
                        }


                    }
                });

    }

    private void nextQS(int numberQS) {
        Picasso.get().load(arrayList.get(numberQS).getPicture()).into(activityFlashcardBinding.imgFront);
        String totalBehind = arrayList.get(numberQS).getWord()+"\n"+arrayList.get(numberQS).getType()+"\n"+arrayList.get(numberQS).getPro();
        activityFlashcardBinding.txtBehind.setText(totalBehind);
        activityFlashcardBinding.txtNumberQS.setText((numberQS+1)+"/"+arrayList.size());
        activityFlashcardBinding.progressQuestion.setProgress(numberQS+1);
    }
}