package com.application.project2java;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.application.project2java.DatabaseHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DataProvider(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<Item> getAllData() {
        List<Item> dataList = new ArrayList<>();

        Cursor cursor = db.query(
                ItemContract.ItemTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_AGE));
                //TODO add data to the list of data

            } while (cursor.moveToNext());
        }

        cursor.close();

        return dataList;
    }

    public void updateData(int id, String name, int age) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_AGE, age);

        String whereClause = DatabaseHelper.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db.update(DatabaseHelper.TABLE_NAME, values, whereClause, whereArgs);
    }

    public void deleteData(int id) {
        String whereClause = DatabaseHelper.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db.delete(DatabaseHelper.TABLE_NAME, whereClause, whereArgs);
    }
}

