package com.application.project2java;

public class Category {
    private final int imageId;
    private final CategoryName category;
    private final int frequency;

    public Category(int imageId, CategoryName category, int frequency) {
        this.imageId = imageId;
        this.category = category;
        this.frequency = frequency;
    }

    public CategoryName getCategory() {
        return category;
    }

    public String getDisplayName() {
        return category.name().replaceAll("_", " ");
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return category.name();
    }

    public int getFrequency() {
        return frequency;
    }
}
