package com.application.project2java.constants;

import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_CART_QUANTITY;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_CATEGORY;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_IS_FAVOURITE;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_NAME;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_VIEW_COUNT;
import static com.application.project2java.constants.ItemContract.ItemTable.TABLE_NAME;

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
    public static final String CATEGORY_ITEM_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY + " IN (,,)";
    public static final String CATEGORY_ITEM_FREQUENCY_QUERY = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY + " IN (?)";
    public static final String ITEM_BY_NAME_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = ? LIMIT 1";
    public static final String ITEMS_LIKE_NAME_IN_CATEGORIES_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY + " IN (,,) AND " + COLUMN_NAME + " LIKE ?";
    public static final String ITEMS_LIKE_NAME_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " LIKE ?";

    private QueryProvider() {
    }

}
