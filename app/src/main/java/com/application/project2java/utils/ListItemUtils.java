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

/**
 * ListItemUtils is a helper class that provides methods to modify elements in the ListActivity or
 * navigate the user to the right activity.
 */
public class ListItemUtils {
    /**
     * Updates the favourite button appearance to the correct state.
     * @param isFavourite The favourite state of an item.
     * @param buttonFavouriteItem The favourite button to be updated.
     */
    public static void updateFavouriteButtonAppearance(boolean isFavourite, MaterialButton buttonFavouriteItem) {
        if (isFavourite) {
            buttonFavouriteItem.setIconTintResource(R.color.md_theme_light_tertiary);
            buttonFavouriteItem.setIconResource(R.drawable.baseline_favorite_24);
        } else {
            buttonFavouriteItem.setIconTintResource(R.color.md_theme_light_primary);
            buttonFavouriteItem.setIconResource(R.drawable.baseline_favorite_border_24);
        }
    }
    /**
     * Updates the cart button appearance to the correct state.
     * @param isInCart The cart state of the item.
     * @param buttonAddCart The cart button to be updated
     */
    public static void updateCartButtonAppearance(boolean isInCart, MaterialButton buttonAddCart) {

        if (isInCart) {
            buttonAddCart.setIconTintResource(R.color.md_theme_light_tertiary);
            buttonAddCart.setIconResource(R.drawable.baseline_remove_shopping_cart_24);
        } else {
            buttonAddCart.setIconTintResource(R.color.md_theme_light_primary);
            buttonAddCart.setIconResource(R.drawable.baseline_add_shopping_cart_24);
        }
    }

    /**
     * Updates the isInCart state of the specified item in the database.
     * @param isInCart The cart state of the item.
     * @param name The name of the item to be updated.
     */
    public static void updateCartStatus(boolean isInCart, String name) {
        DataMutator dataMutator = App.getDataMutator();
        dataMutator.open();
        if (isInCart) dataMutator.updateItemCartStatus(name, 1);
        else dataMutator.updateItemCartStatus(name, 0);
        dataMutator.close();
    }

    /**
     * Navigates the user to the DetailsActivity with the correct item information based on the
     * item the user has selected.
     * @param name The name of the item the user has selected.
     * @param viewCount The total amount of times the item has been viewed.
     */
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

    /**
     * Navigates to the ListActivity with the category selected applied to the list of items.
     * @param category The category selected.
     */
    public static void navigateToList(CategoryName category) {
        Intent intent = new Intent(App.getAppContext(), ListActivity.class);
        if (category != null) intent.putExtra("category", category.toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        App.getAppContext().startActivity(intent);

    }
    /**
     * Navigates to the ListActivity with the filter selected applied to the list of items.
     * @param filterField The filter selected.
     */
    public static void navigateToList(FilterField filterField) {
        Intent intent = new Intent(App.getAppContext(), ListActivity.class);
        intent.putExtra("filter", filterField.toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        App.getAppContext().startActivity(intent);

    }

    /**
     * Calculates the sum cost of all items in the cart.
     * @param items The items in the cart.
     * @return The sum cost of the items in the cart.
     */
    public static String calculateTotal(List<ItemModel> items) {
        DecimalFormat df = new DecimalFormat("0.00");
        float sum = 0;
        for (ItemModel item : items) {
            sum += item.getPrice() * item.getCartQuantity();
        }
        return "$" + df.format(sum);
    }
}
