package com.application.project2java;

import com.application.project2java.ItemContract.ItemEntry.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataMutator {
    private DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public DataMutator(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addData(ItemModel item) {
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_NAME, item.getName());
        values.put(ItemContract.ItemEntry.COLUMN_DESCRIPTION, item.getDescription());
        values.put(ItemContract.ItemEntry.COLUMN_PRICE, item.getPrice());
        values.put(ItemContract.ItemEntry.COLUMN_CATEGORY, item.getCategory());
        values.put(ItemContract.ItemEntry.COLUMN_IMAGE_URIS, item.getImageUris().toString());
        values.put(ItemContract.ItemEntry.COLUMN_VIEW_COUNT, item.getViewCount());
        values.put(ItemContract.ItemEntry.COLUMN_IS_FAVOURITE, item.isFavourite() ? 1 : 0);
        values.put(ItemContract.ItemEntry.COLUMN_CART_QUANTITY, item.getCartQuantity());

        return db.insert(ItemContract.ItemTable.TABLE_NAME, null, values);
    }

    public int updateItemCartStatus(String name, int cartQty) {
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_CART_QUANTITY, cartQty);
        return updateAtName(name, values);

    }

    public int updateItemFavouriteStatus(String name, boolean isFavourite) {
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_IS_FAVOURITE, isFavourite ? 1 : 0);
        return updateAtName(name, values);
    }

    private int updateAtName(String name, ContentValues values) {
        String whereClause = ItemContract.ItemEntry.COLUMN_NAME + " = ?";
        String[] whereArgs = {name};
        return db.update(ItemContract.ItemTable.TABLE_NAME, values, whereClause, whereArgs);
    }


    public void deleteData(int id) {
        String whereClause = ItemContract.ItemEntry.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db.delete(ItemContract.ItemTable.TABLE_NAME, whereClause, whereArgs);
    }
}
