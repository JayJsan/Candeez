package com.application.project2java;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    private DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public DataProvider(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<ItemModel> searchData(String query, String[] selectionArgs) {
        List<ItemModel> dataList = new ArrayList<>();

        // To use, have ? in the query so you can fill it in with selectionArgs
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME));
                String imageUris = cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_IMAGE_URIS));
                String[] imageUriArray = imageUris.split(",");
                String description = cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_DESCRIPTION));
                boolean isFavourite = cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_IS_FAVOURITE)) == 1;
                int cartQty = cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_CART_QUANTITY));
                int viewCount = cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_VIEW_COUNT));
                int price = cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_PRICE));
                //TODO add data to the list of data
                ItemModel item = new ItemModel(name, description, price, imageUriArray, viewCount, isFavourite, cartQty);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return dataList;
    }

    public void updateData(int id, String name, int age) {
        ContentValues values = new ContentValues();

        String whereClause = "";
        String[] whereArgs = {};

        db.update(ItemContract.ItemTable.TABLE_NAME, values, whereClause, whereArgs);
    }

    public void deleteData(int id) {
        String whereClause = ItemContract.ItemEntry.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db.delete(ItemContract.ItemTable.TABLE_NAME, whereClause, whereArgs);
    }
}

