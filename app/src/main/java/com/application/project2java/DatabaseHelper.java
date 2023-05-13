package com.application.project2java;

import static com.application.project2java.ItemContract.ItemEntry.COLUMN_CART_QUANTITY;
import static com.application.project2java.ItemContract.ItemEntry.COLUMN_DESCRIPTION;
import static com.application.project2java.ItemContract.ItemEntry.COLUMN_ID;
import static com.application.project2java.ItemContract.ItemEntry.COLUMN_IS_FAVOURITE;
import static com.application.project2java.ItemContract.ItemEntry.COLUMN_NAME;
import static com.application.project2java.ItemContract.ItemEntry.COLUMN_PRICE;
import static com.application.project2java.ItemContract.ItemEntry.COLUMN_VIEW_COUNT;
import static com.application.project2java.ItemContract.ItemTable.TABLE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "itemdatabase.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + ItemContract.ItemTable.TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_PRICE + " SMALLINT, "
                + COLUMN_IS_FAVOURITE + " BOOLEAN, "
                + COLUMN_CART_QUANTITY + " SMALLINT, "
                + COLUMN_VIEW_COUNT + " SMALLINT)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            String alterTableQuery = "ALTER TABLE " + TABLE_NAME
                    + " ADD COLUMN new_column TEXT";
            db.execSQL(alterTableQuery);
        }
    }
}
