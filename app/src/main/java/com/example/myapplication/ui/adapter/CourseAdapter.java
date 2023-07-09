package com.example.myapplication.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.CoursesVideoActivity;
import com.example.myapplication.ui.FlashcardActivity;
import com.example.myapplication.ui.interfaces.setOnItemClick;
import com.example.myapplication.ui.model.CategoryModel;
import com.example.myapplication.ui.model.CoursesModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHodler>
{
     private Context context;
     private ArrayList<CoursesModel> categoryModels;

    public CourseAdapter(Context context, ArrayList<CoursesModel> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
    }



    @NonNull
    @Override
    public CourseAdapter.ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.stream_video,parent,false);

        return new ViewHodler(convertView);

    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHodler holder, int position) {

        CoursesModel categoryModel = categoryModels.get(position);
        holder.txtName.setText(categoryModel.getName());
        holder.setOnItemClicks(new setOnItemClick() {
            @Override
            public void setOnItemClick(View view, int pos) {
                Intent intent = new Intent(context,CoursesVideoActivity.class);
                intent.putExtra("Video",categoryModel);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }



    public class ViewHodler extends RecyclerView.ViewHolder implements  View.OnClickListener {
        ImageView imgIcon;
        TextView txtName;
        setOnItemClick setOnItemClick;
        public ViewHodler(@NonNull View convertView) {
            super(convertView);
             imgIcon = convertView.findViewById(R.id.familyImage);
             txtName = convertView.findViewById(R.id.familyName);
             convertView.setOnClickListener(this);
        }
        public  void  setOnItemClicks(setOnItemClick setOnItemClick){
            this.setOnItemClick = setOnItemClick;
        }

        @Override
        public void onClick(View v) {
            setOnItemClick.setOnItemClick(v,getAdapterPosition());
        }
    }
}
