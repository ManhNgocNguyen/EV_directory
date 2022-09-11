package com.example.demoproject;

public class EnglishTense {
    private String tenseName;
    private String tenseStructure;

    public EnglishTense(String tenseName, String tenseStructure) {
        this.tenseName = tenseName;
        this.tenseStructure = tenseStructure;
    }

    public String getTenseName() {
        return tenseName;
    }

    public void setTenseName(String tenseName) {
        this.tenseName = tenseName;
    }

    public String getTenseStructure() {
        return tenseStructure;
    }

    public void setTenseStructure(String tenseStructure) {
        this.tenseStructure = tenseStructure;
    }
}
