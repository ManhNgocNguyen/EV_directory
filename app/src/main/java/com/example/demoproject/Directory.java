package com.example.demoproject;

public class Directory {
    private int id;
    private String vietnamese;
    private String english;
    private String kind;

    public Directory(int id, String english, String vietnamese, String kind) {
        this.vietnamese = vietnamese;
        this.english = english;
        this.id = id;
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
