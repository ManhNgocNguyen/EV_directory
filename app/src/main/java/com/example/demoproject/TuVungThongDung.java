package com.example.demoproject;

public class TuVungThongDung {
    private String English;
    private String Vietnamese;

    public TuVungThongDung(String english, String vietnamese) {
        English = english;
        Vietnamese = vietnamese;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getVietnamese() {
        return Vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        Vietnamese = vietnamese;
    }
}
