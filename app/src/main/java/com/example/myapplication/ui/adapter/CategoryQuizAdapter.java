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
import com.example.myapplication.ui.DifficultyActivity;
import com.example.myapplication.ui.FlashcardActivity;
import com.example.myapplication.ui.interfaces.setOnItemClick;
import com.example.myapplication.ui.model.CategoryModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryQuizAdapter extends RecyclerView.Adapter<CategoryQuizAdapter.ViewHolder>
{
     private Context context;
     private ArrayList<CategoryModel> categoryModels;

    public CategoryQuizAdapter(Context context, ArrayList<CategoryModel> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
    }



    @NonNull
    @Override
    public CategoryQuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.stream_category,parent,false);

        return new ViewHolder(convertView);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryQuizAdapter.ViewHolder holder, int position) {

        CategoryModel categoryModel = categoryModels.get(position);
        holder.txtName.setText(categoryModel.getCategory());
        Picasso.get().load(categoryModel.getIcon().trim()).into(holder.imgIcon);
        holder.setOnItemClicks(new setOnItemClick() {
            @Override
            public void setOnItemClick(View view, int pos) {
                Intent intent = new Intent(context, DifficultyActivity.class);
                intent.putExtra("Category",categoryModel);
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



    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        ImageView imgIcon;
        TextView txtName;
        setOnItemClick setOnItemClick;
        public ViewHolder(@NonNull View convertView) {
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
