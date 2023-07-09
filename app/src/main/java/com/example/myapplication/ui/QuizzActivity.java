package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityQuizzBinding;
import com.example.myapplication.ui.model.CategoryModel;
import com.example.myapplication.ui.model.QuizModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class QuizzActivity extends AppCompatActivity {

    ActivityQuizzBinding activityQuizzBinding;
    ArrayList<QuizModel> arrayList= new ArrayList<>();
    int numberQS = 0;
    int correctAll = 0;
    CategoryModel categoryModel;
    String level="Easy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityQuizzBinding = ActivityQuizzBinding.inflate(getLayoutInflater());
        setContentView(activityQuizzBinding.getRoot());
        initQS();
        activityQuizzBinding.rA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(arrayList.get(numberQS).getCorrect().equalsIgnoreCase("A")){
                         correctAll++;
                         nextQS();
                     }else{
                         activityQuizzBinding.txtSelect.setText(arrayList.get(numberQS).getA());
                         activityQuizzBinding.rGroup.setVisibility(View.GONE);
                         activityQuizzBinding.lWrong.setVisibility(View.VISIBLE);
                     }

                 }
            }
        });
        activityQuizzBinding.rB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(arrayList.get(numberQS).getCorrect().equalsIgnoreCase("B")){
                         correctAll++;
                         nextQS();
                     }else{
                         activityQuizzBinding.txtSelect.setText(arrayList.get(numberQS).getB());
                         activityQuizzBinding.rGroup.setVisibility(View.GONE);
                         activityQuizzBinding.lWrong.setVisibility(View.VISIBLE);
                     }

                 }
            }
        });
        activityQuizzBinding.rC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(arrayList.get(numberQS).getCorrect().equalsIgnoreCase("C")){
                         correctAll++;
                         nextQS();
                     }else{
                         activityQuizzBinding.txtSelect.setText(arrayList.get(numberQS).getC());
                         activityQuizzBinding.rGroup.setVisibility(View.GONE);
                         activityQuizzBinding.lWrong.setVisibility(View.VISIBLE);
                     }

                 }
            }
        });
        activityQuizzBinding.rD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(arrayList.get(numberQS).getCorrect().equalsIgnoreCase("D")){
                         correctAll++;
                         nextQS();
                     }else{
                         activityQuizzBinding.txtSelect.setText(arrayList.get(numberQS).getD());
                         activityQuizzBinding.rGroup.setVisibility(View.GONE);
                         activityQuizzBinding.lWrong.setVisibility(View.VISIBLE);

                     }

                 }
            }
        });
        setSupportActionBar(activityQuizzBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quiz");
        activityQuizzBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activityQuizzBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityQuizzBinding.lWrong.setVisibility(View.GONE);
                activityQuizzBinding.rGroup.setVisibility(View.VISIBLE);
                nextQS();
            }
        });

    }

    private void initQS() {
        Intent intent = getIntent();
        categoryModel = (CategoryModel) intent.getSerializableExtra("Category");
         level = intent.getStringExtra("Level");
        FirebaseFirestore.getInstance().collection("Quiz")
                .document(level).collection(categoryModel.getCategory())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            String qs = documentSnapshot.getString("question");
                            String a = documentSnapshot.getString("a");
                            String b = documentSnapshot.getString("b");
                            String c = documentSnapshot.getString("c");
                            String d = documentSnapshot.getString("d");
                            String correct = documentSnapshot.getString("correct");
                            String details = documentSnapshot.getString("details");
                            arrayList.add(new QuizModel(qs,a,b,c,d,correct,false,details));
                        }
                        if(arrayList.size()>0){
                            activityQuizzBinding.txtQuestion.setText("Question: "+(numberQS+1)+"\n "+arrayList.get(0).getQs());
                            activityQuizzBinding.rA.setText(arrayList.get(0).getA());
                            activityQuizzBinding.rB.setText(arrayList.get(0).getB());
                            activityQuizzBinding.rC.setText(arrayList.get(0).getC());
                            activityQuizzBinding.rD.setText(arrayList.get(0).getD());
                            activityQuizzBinding.txtDetails.setText(arrayList.get(0).getDetails());
                            switch (arrayList.get(0).getCorrect()){
                                case  "A":activityQuizzBinding.txtCorrect.setText(arrayList.get(0).getA());break;
                                case  "B":activityQuizzBinding.txtCorrect.setText(arrayList.get(0).getB());break;
                                case  "C":activityQuizzBinding.txtCorrect.setText(arrayList.get(0).getC());break;
                                case  "D":activityQuizzBinding.txtCorrect.setText(arrayList.get(0).getD());break;
                            }


                        }

                    }
                });
    }
    void nextQS(){
        if(numberQS<arrayList.size()-1){
            numberQS++;
            activityQuizzBinding.txtQuestion.setText("Question: "+(numberQS+1)+"\n "+arrayList.get(numberQS).getQs());
            activityQuizzBinding.rA.setText(arrayList.get(numberQS).getA());
            activityQuizzBinding.rB.setText(arrayList.get(numberQS).getB());
            activityQuizzBinding.rC.setText(arrayList.get(numberQS).getC());
            activityQuizzBinding.rD.setText(arrayList.get(numberQS).getD());
            activityQuizzBinding.txtDetails.setText(arrayList.get(numberQS).getDetails());
            switch (arrayList.get(numberQS).getCorrect()){
                case  "A":activityQuizzBinding.txtCorrect.setText(arrayList.get(numberQS).getA());break;
                case  "B":activityQuizzBinding.txtCorrect.setText(arrayList.get(numberQS).getB());break;
                case  "C":activityQuizzBinding.txtCorrect.setText(arrayList.get(numberQS).getC());break;
                case  "D":activityQuizzBinding.txtCorrect.setText(arrayList.get(numberQS).getD());break;
            }
        }else{
            Intent intent = new Intent(QuizzActivity.this,QuizFinishActivity.class);
            intent.putExtra("Level",level);
            intent.putExtra("Topic",categoryModel.getCategory());
            intent.putExtra("Correct",correctAll);
            intent.putExtra("QS",arrayList.size());
            startActivity(intent);


        }


    }
}