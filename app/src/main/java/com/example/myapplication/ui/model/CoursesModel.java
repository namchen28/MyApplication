package com.example.myapplication.ui.model;

import java.io.Serializable;

public class CoursesModel  implements Serializable {
    private  String video;
    private String name;

    public CoursesModel(String video, String name) {
        this.video = video;
        this.name = name;
    }

    public CoursesModel() {

    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
