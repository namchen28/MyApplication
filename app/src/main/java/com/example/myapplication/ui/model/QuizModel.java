package com.example.myapplication.ui.model;

public class QuizModel {
    private  String qs;
    private  String a;
    private  String b;
    private  String c;
    private  String d;
    private  String correct;
    private  String details;
    private  boolean isCorrect;

    public QuizModel(String qs, String a, String b, String c, String d, String correct,boolean isCorrect,String details) {
        this.qs = qs;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correct = correct;
        this.isCorrect= isCorrect;
        this.details=details;
    }

    public String getDetails() {
        return details;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getQs() {
        return qs;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public String getCorrect() {
        return correct;
    }
}
