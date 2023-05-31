package com.application.project2java.utils;

import android.content.Intent;

import com.application.project2java.App;
import com.application.project2java.activities.DetailsActivity;
import com.application.project2java.activities.ListActivity;
import com.application.project2java.constants.CategoryName;
import com.application.project2java.constants.FilterField;
import com.application.project2java.database.DataMutator;
import com.application.project2java.models.ItemModel;
import com.example.project2java.R;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
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

    public static void updateCartButtonAppearance(boolean isInCart, MaterialButton buttonAddCart) {

        if (isInCart) {
            buttonAddCart.setIconTintResource(R.color.md_theme_light_tertiary);
            buttonAddCart.setIconResource(R.drawable.baseline_remove_shopping_cart_24);
        } else {
            buttonAddCart.setIconTintResource(R.color.md_theme_light_primary);
            buttonAddCart.setIconResource(R.drawable.baseline_add_shopping_cart_24);
        }
    }

    public static void updateCartStatus(boolean isInCart, String name) {
        DataMutator dataMutator = App.getDataMutator();
        dataMutator.open();
        if (isInCart) dataMutator.updateItemCartStatus(name, 1);
        else dataMutator.updateItemCartStatus(name, 0);
        dataMutator.close();
    }

    public static void navigateToDetails(String name, int viewCount) {
        Intent intent = new Intent(App.getAppContext(), DetailsActivity.class);
        DataMutator dataMutator = App.getDataMutator();
        dataMutator.open();
        dataMutator.updateItemViewCount(name, viewCount);
        dataMutator.close();
        intent.putExtra("name", name);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        App.getAppContext().startActivity(intent);
    }


    public static void navigateToList(CategoryName category) {
        Intent intent = new Intent(App.getAppContext(), ListActivity.class);
        if (category != null) intent.putExtra("category", category.toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        App.getAppContext().startActivity(intent);

    }

    public static void navigateToList(FilterField filterField) {
        Intent intent = new Intent(App.getAppContext(), ListActivity.class);
        intent.putExtra("filter", filterField.toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        App.getAppContext().startActivity(intent);

    }

    public static String calculateTotal(List<ItemModel> items) {
        DecimalFormat df = new DecimalFormat("0.00");
        float sum = 0;
        for (ItemModel item : items) {
            sum += item.getPrice() * item.getCartQuantity();
        }
        return "$" + df.format(sum);
    }
}
