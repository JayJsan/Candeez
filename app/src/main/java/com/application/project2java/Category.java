package com.application.project2java;

public class Category {
    private CategoryName category = CategoryName.CATEGORY1;
    private int frequency;

    public Category(CategoryName category, int frequency) {
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
