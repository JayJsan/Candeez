package com.application.project2java;

import static com.application.project2java.ItemContract.ItemEntry.COLUMN_CART_QUANTITY;
import static com.application.project2java.ItemContract.ItemEntry.COLUMN_CATEGORY;
import static com.application.project2java.ItemContract.ItemEntry.COLUMN_IS_FAVOURITE;
import static com.application.project2java.ItemContract.ItemEntry.COLUMN_VIEW_COUNT;
import static com.application.project2java.ItemContract.ItemTable.TABLE_NAME;

public final class QueryProvider {
    public static final String BEST_SELLING_HOME_SCREEN_QUERY = "SELECT * FROM " + TABLE_NAME
            + " ORDER BY " + COLUMN_CART_QUANTITY + " DESC"
            + " LIMIT 3";
    public static final String MOST_VIEWED_HOME_SCREEN_QUERY = "SELECT * FROM " + TABLE_NAME
            + " ORDER BY " + COLUMN_VIEW_COUNT + " DESC"
            + " LIMIT 3";
    public static final String CART_ITEMS_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE "
            + COLUMN_CART_QUANTITY + " > 0";
    public static final String FAVOURITE_ITEMS_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE "
            + COLUMN_IS_FAVOURITE + " > 0";
    public static final String ALL_ITEMS_QUERY = "SELECT * FROM " + TABLE_NAME;
    public static final String CATEGORY_ITEM_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY + " IN (?)";
    public static final String CATEGORY_ITEM_FREQUENCY_QUERY = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY + " IN (?)";

    private QueryProvider() {
    }

    ;
}
