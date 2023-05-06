package com.application.project2java;

public class Item {
    private int cost;
    private int viewCount;
    private boolean isFavourite;
    private boolean isInCart;
    private String description;
    private String name;

    public Item(int cost, int viewCount, boolean isFavourite, boolean isInCart, String description, String name) {
        this.cost = cost;
        this.viewCount = viewCount;
        this.isFavourite = isFavourite;
        this.isInCart = isInCart;
        this.description = description;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public int getViewCount() {
        return viewCount;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public boolean isInCart() {
        return isInCart;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
