package com.example.myapplication.ui.model;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private  String category;
    private  String icon;

    public  CategoryModel(String category,String icon){
        this. category = category;
        this.icon = icon;
    }

    public String getCategory() {
        return category;
    }

    public String getIcon() {
        return icon;
    }
}
