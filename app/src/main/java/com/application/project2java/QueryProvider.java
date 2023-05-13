package com.application.project2java;

public final class QueryProvider {
    public static final String BEST_SELLING_QUERY = "";
    public static final String CART_ITEMS_QUERY = "";
    public static final String FAVOURITE_ITEMS_QUERY = "SELECT * FROM " + ItemContract.ItemTable.TABLE_NAME + " WHERE "
            + ItemContract.ItemEntry.COLUMN_IS_FAVOURITE + " > 0";
    public static final String ALL_ITEMS_QUERY = "";
    public static final String CATEGORY_ITEM_QUERY = "";

    private QueryProvider() {
    }

    ;
}
