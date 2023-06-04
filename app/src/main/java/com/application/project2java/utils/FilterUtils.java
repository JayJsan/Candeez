package com.application.project2java.utils;

import com.application.project2java.constants.FilterDirection;
import com.application.project2java.constants.FilterField;
import com.application.project2java.models.ItemModel;
import com.example.project2java.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Comparator;
import java.util.List;

/**
 * FiltersUtils is a helper class for the filter component used in ListActivity.
 * The methods provided updates the list and appearance accordingly.
 */
public class FilterUtils {
    /**
     * Sorts the list according to the comparator passed in.
     * @param list The list of current items in the activity.
     * @param comparator The item used comparator to sort the list.
     */
    public static void applyFilters(List<ItemModel> list, Comparator<ItemModel> comparator) {
        list.sort(comparator);
    }

    /**
     * Returns the respective item comparator according to the filter field and direction.
     * @param filterField The filter field selected.
     * @param filterDirection The filter direction selected.
     * @return Returns the chosen comparator.
     */
    public static Comparator<ItemModel> getComparator(FilterField filterField, FilterDirection filterDirection) {
        Comparator<ItemModel> comparator;
        switch (filterField) {
            case FILTER_BEST_SELLING:
                comparator = Comparator.comparing(ItemModel::getCartQuantity);
                break;
            case FILTER_BY_VIEWS:
                comparator = Comparator.comparing(ItemModel::getViewCount);
                break;
            case FILTER_BY_PRICE:
                comparator = Comparator.comparing(ItemModel::getPrice);
                break;
            case FILTER_ALPHABETICALLY:
            default:
                comparator = Comparator.comparing(ItemModel::getName);
                break;
        }
        if (filterDirection == FilterDirection.DESCENDING) comparator = comparator.reversed();

        return comparator;
    }

    /**
     * Disables the button passed in.
     * @param floatingActionButton The button to be modified.
     */
    public static void setButtonDisabled(FloatingActionButton floatingActionButton) {
        floatingActionButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_primaryFixed));
        floatingActionButton.setImageTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_primary));
    }

    /**
     * Sets the Ascending button appearance.
     * @param floatingActionButton The button to be modified.
     */
    public static void setAscendingButtonAppearance(FloatingActionButton floatingActionButton) {
        floatingActionButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_primary));
        floatingActionButton.setImageTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_onPrimary));
    }
    /**
     * Sets the Descending button appearance.
     * @param floatingActionButton The button to be modified.
     */
    public static void setDescendingButtonAppearance(FloatingActionButton floatingActionButton) {
        floatingActionButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_tertiary));
        floatingActionButton.setImageTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_onPrimary));
    }

}
