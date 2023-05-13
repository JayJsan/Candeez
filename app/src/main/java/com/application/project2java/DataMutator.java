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

    public long addData(String name, String description, int price, String imageUris, int viewCount, boolean isFavourite, int cartQty) {
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_NAME, name);
        values.put(ItemContract.ItemEntry.COLUMN_DESCRIPTION, description);
        values.put(ItemContract.ItemEntry.COLUMN_PRICE, price);
        values.put(ItemContract.ItemEntry.COLUMN_IMAGE_URIS, imageUris);
        values.put(ItemContract.ItemEntry.COLUMN_VIEW_COUNT, viewCount);
        values.put(ItemContract.ItemEntry.COLUMN_IS_FAVOURITE, isFavourite ? 1 : 0);
        values.put(ItemContract.ItemEntry.COLUMN_CART_QUANTITY, cartQty);

        return db.insert(ItemContract.ItemTable.TABLE_NAME, null, values);
    }

    public int updateItemCartStatus(String name, int cartQty) {
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_CART_QUANTITY, cartQty);

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
