package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityEditAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.C;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditAccountActivity extends AppCompatActivity {
    ActivityEditAccountBinding activityEditAccountBinding;
    String idUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditAccountBinding = ActivityEditAccountBinding.inflate(getLayoutInflater());
        setContentView(activityEditAccountBinding.getRoot());
        setSupportActionBar(activityEditAccountBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit account");
        activityEditAccountBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FirebaseFirestore.getInstance().collection("User")
                .whereEqualTo("Email", FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.size() > 0) {
                            DocumentSnapshot d = queryDocumentSnapshots.getDocuments().get(0);
                            String date = d.getString("DateOfBirth");
                            String fullname = d.getString("Name");
                            if (date.length() > 0) {
                                activityEditAccountBinding.txtDate.setText(date);
                            }
                            idUser = d.getId();
                            activityEditAccountBinding.txtFullName.setText(fullname);

                        }

                    }
                });

        activityEditAccountBinding.lUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdateFullName();
            }
        });
        activityEditAccountBinding.lDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int date = calendar.get(Calendar.DATE);
                DatePickerDialog dialog = new DatePickerDialog(EditAccountActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat=  new SimpleDateFormat("dd/MM/yyyy");
                        calendar.set(year,month,dayOfMonth);
                        Map<String,Object> map= new HashMap<>();
                        map.put("DateOfBirth",simpleDateFormat.format(calendar.getTime()));
                        FirebaseFirestore.getInstance().collection("User")
                                        .document(idUser)
                                                .update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(EditAccountActivity.this, "sucess", Toast.LENGTH_SHORT).show();

                                            activityEditAccountBinding.txtDate.setText(simpleDateFormat.format(calendar.getTime()));
                                        }
                                    }
                                });

                    }
                },year,month,date);
                dialog.show();
            }
        });
        activityEditAccountBinding.lResetPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(EditAccountActivity.this, "Please go to gmail ... reset password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        activityEditAccountBinding.txtEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

    }

    private void showDialogUpdateFullName() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_fullname);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        EditText editUserName = dialog.findViewById(R.id.username);
        editUserName.setText(activityEditAccountBinding.txtFullName.getText());
        dialog.findViewById(R.id.imgCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUserName.getText().toString().trim();
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("Name",username);
                if(username.length()>0){
                    FirebaseFirestore.getInstance().collection("User")
                            .document(idUser).update(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(EditAccountActivity.this, "sucess", Toast.LENGTH_SHORT).show();
                                        dialog.cancel();
                                        activityEditAccountBinding.txtFullName.setText(username);
                                    }
                                }
                            });
                }else{
                    Toast.makeText(EditAccountActivity.this, "Please dien thong tin username ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}