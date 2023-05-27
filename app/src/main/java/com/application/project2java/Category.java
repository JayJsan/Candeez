package com.application.project2java;

public class Category {
    private final String imageUri;
    private final CategoryName category;
    private final int frequency;

    public Category(String imageUri, CategoryName category, int frequency) {
        this.imageUri = imageUri;
        this.category = category;
        this.frequency = frequency;
    }

    public CategoryName getCategory() {
        return category;
    }

    public String getName() {
        return category.name();
    }

    public int getFrequency() {
        return frequency;
    }
}
