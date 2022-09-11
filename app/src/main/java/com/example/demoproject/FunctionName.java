package com.example.demoproject;

public class FunctionName {
    private String functionName;
    private int imageResource;

    public FunctionName(String functionName, int imageResource) {
        this.functionName = functionName;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
