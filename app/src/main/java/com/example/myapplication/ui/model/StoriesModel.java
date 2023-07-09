package com.example.myapplication.ui.model;

import java.io.Serializable;

public class StoriesModel implements Serializable {
    private String url;
    private String name;
    private String picture;

    public StoriesModel(String url, String name, String picture) {
        this.url = url;
        this.name = name;
        this.picture = picture;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
}
