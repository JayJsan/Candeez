package com.application.project2java;

public class Category {
    private String name;
    private int frequency;

    public Category(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }
}
