package com.application.project2java;

import com.example.project2java.R;

public enum CategoryName {
    Gummies(R.drawable.category_banner_gummies),
    Hard_Candy(R.drawable.category_banner_hard_candy),
    Chocolate(R.drawable.category_banner_chocolate),
    Licorice(R.drawable.category_banner_licorice),
    Sour_Candy(R.drawable.category_banner_sour_candy),
    Marshmallow(R.drawable.category_banner_marshmallow),
    Nougat(R.drawable.category_banner_nougat),
    Mints(R.drawable.category_banner_hard_candy),
    Jelly_Beans(R.drawable.category_banner_jelly_bean),
    Lollipops(R.drawable.category_banner_lolipop),
    Bubble_Gum(R.drawable.category_banner_bubble_gum),
    Savoury(R.drawable.category_banner_savoury);

    public final int bannerId;

    private CategoryName(int id) {
        bannerId = id;
    }
}
