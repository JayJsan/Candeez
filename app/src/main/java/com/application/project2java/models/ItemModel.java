package com.application.project2java.models;

import androidx.annotation.NonNull;

import com.application.project2java.constants.CategoryName;

import java.util.List;

public class ItemModel {
    private final List<String> imageUris;
    private final float price;
    private final int viewCount;
    private final CategoryName category;
    private final boolean isFavourite;
    private final int cartQuantity;
    private final String description;
    private final String name;
    private int id;

    public ItemModel(String name, String description, float price, CategoryName category, List<String> imageUris, int viewCount, boolean isFavourite, int cartQuantity) {
        this.price = price;
        this.viewCount = viewCount;
        this.isFavourite = isFavourite;
        this.category = category;
        this.cartQuantity = cartQuantity;
        this.description = description;
        this.name = name;
        this.imageUris = imageUris;
    }

    public float getPrice() {
        return price;
    }

    public int getViewCount() {
        return viewCount;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }

    public List<String> getImageUris() {
        return imageUris;
    }

    public String getCategory() {
        return category.toString();
    }

    public String getCategory(boolean isDisplay) {
        return category.toString().replaceAll("_", " ");
    }

    @NonNull
    @Override
    public String toString() {
        String sb = "Name: " +
                getName() +
                " Description: " +
                getDescription() +
                "\nPrice: " +
                getPrice() +
                "\nCategory: " +
                getCategory() +
                "\nView Count: " +
                getViewCount() +
                "\nFavourite?: " +
                isFavourite() +
                "\nQuantity in cart: " +
                getCartQuantity() +
                "\nImage URIs: " +
                getImageUris();
        return sb;
    }
}
