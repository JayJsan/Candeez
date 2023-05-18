package com.application.project2java;

public final class ItemContract {
    private ItemContract() {
    }

    public static class ItemTable {
        public static final String TABLE_NAME = "items";
    }

    public static class ItemEntry {
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_IMAGE_URIS = "image_uris";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_VIEW_COUNT = "view_count";
        public static final String COLUMN_IS_FAVOURITE = "is_favourite";
        public static final String COLUMN_CART_QUANTITY = "cart_quantity";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
    }
}
