package com.application.project2java;

public class ItemModel {
    private int id;
    private String[] imageUris;
    private int price;
    private int viewCount;
    private boolean isFavourite;
    private int cartQuantity;
    private String description;
    private String name;

    public ItemModel(String name, String description, int price, String[] imageUris, int viewCount, boolean isFavourite, int cartQuantity ) {
        this.price = price;
        this.viewCount = viewCount;
        this.isFavourite = isFavourite;
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
}
