package com.application.project2java;

import android.content.Intent;

import com.example.project2java.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ListItemUtils {
    public static void updateFavouriteButtonAppearance(boolean isFavourite, MaterialButton buttonFavouriteItem) {
        if (isFavourite) {
            buttonFavouriteItem.setIconTintResource(R.color.md_theme_light_tertiary);
            buttonFavouriteItem.setIconResource(R.drawable.baseline_favorite_24);
        } else {
            buttonFavouriteItem.setIconTintResource(R.color.md_theme_light_primary);
            buttonFavouriteItem.setIconResource(R.drawable.baseline_favorite_border_24);
        }
    }

    public static void navigateToDetails(String name) {
        Intent intent = new Intent(App.getAppContext(), DetailsActivity.class);
        intent.putExtra("name", name);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        App.getAppContext().startActivity(intent);
    }

    public static void navigateToList(CategoryName category) {
        Intent intent = new Intent(App.getAppContext(), ListActivity.class);
        intent.putExtra("category", category.toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        App.getAppContext().startActivity(intent);

    }

    public static String calculateTotal(List<ItemModel> items) {
        int sum = 0;
        for (ItemModel item : items) {
            sum += item.getPrice() * item.getCartQuantity();
        }
        return "$" + sum;
    }
}
