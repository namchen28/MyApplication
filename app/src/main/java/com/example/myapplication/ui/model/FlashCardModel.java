package com.example.myapplication.ui.model;

public class FlashCardModel {
    private  String picture;
    private  String pro;
    private  String type;
    private  String word;

    public FlashCardModel(String picture, String pro, String type, String word) {
        this.picture = picture;
        this.pro = pro;
        this.type = type;
        this.word = word;
    }

    public String getPicture() {
        return picture;
    }

    public String getPro() {
        return pro;
    }

    public String getType() {
        return type;
    }

    public String getWord() {
        return word;
    }
}
