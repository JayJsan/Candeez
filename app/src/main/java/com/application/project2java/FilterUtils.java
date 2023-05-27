package com.application.project2java;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Comparator;
import java.util.List;

public class FilterUtils {
    public static void applyFilters(List<ItemModel> list, Comparator<ItemModel> comparator) {
        list.sort(comparator);
    }

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



}
