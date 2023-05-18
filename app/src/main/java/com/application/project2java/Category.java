package com.application.project2java;

public class Category {
    private String imageUri;
    private CategoryName category;
    private int frequency;

    public Category(String imageUri, CategoryName category, int frequency) {
        this.imageUri = imageUri;
        this.category = category;
        this.frequency = frequency;
    }

    public String getName() {
        return category.name();
    }

    public int getFrequency() {
        return frequency;
    }
}
