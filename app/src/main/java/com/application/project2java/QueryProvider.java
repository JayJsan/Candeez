package com.application.project2java;

public final class QueryProvider {
    public static final String BEST_SELLING_QUERY = "SELECT * FROM " + ItemContract.ItemTable.TABLE_NAME
            + " ORDER BY " + ItemContract.ItemEntry.COLUMN_CART_QUANTITY + " DESC"
            + " LIMIT 3";
    public static final String CART_ITEMS_QUERY = "SELECT * FROM " + ItemContract.ItemTable.TABLE_NAME + " WHERE "
            + ItemContract.ItemEntry.COLUMN_CART_QUANTITY + " > 0";
    public static final String FAVOURITE_ITEMS_QUERY = "SELECT * FROM " + ItemContract.ItemTable.TABLE_NAME + " WHERE "
            + ItemContract.ItemEntry.COLUMN_IS_FAVOURITE + " > 0";
    public static final String ALL_ITEMS_QUERY = "SELECT * FROM " + ItemContract.ItemTable.TABLE_NAME;
    public static final String CATEGORY_ITEM_QUERY = "";

    private QueryProvider() {
    }

    ;
}
