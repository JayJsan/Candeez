package com.application.project2java;

import androidx.annotation.NonNull;

import java.util.List;

public class ItemModel {
    private int id;
    private List<String> imageUris;
    private int price;
    private int viewCount;
    private CategoryName category;
    private boolean isFavourite;
    private int cartQuantity;
    private String description;
    private String name;

    public ItemModel(String name, String description, int price, CategoryName category, List<String> imageUris, int viewCount, boolean isFavourite, int cartQuantity) {
        this.price = price;
        this.viewCount = viewCount;
        this.isFavourite = isFavourite;
        this.category = category;
        this.cartQuantity = cartQuantity;
        this.description = description;
        this.name = name;
        this.imageUris = imageUris;
    }

    public int getPrice() {
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

    public String getCategory(){
        return category.toString();
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("Name: ")
                .append(getName())
                .append(" Description: ")
                .append(getDescription())
                .append("\nPrice: ")
                .append(getPrice())
                .append("\nCategory: ")
                .append(getCategory())
                .append("\nView Count: ")
                .append(getViewCount())
                .append("\nFavourite?: ")
                .append(isFavourite())
                .append("\nQuantity in cart: ")
                .append(getCartQuantity())
                .append("\nImage URIs: ")
                .append(getImageUris());
        return sb.toString();
    }
}
