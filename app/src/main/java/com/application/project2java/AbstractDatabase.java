package com.application.project2java;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractDatabase {

    SQLiteDatabase db;
    private final DatabaseHelper dbHelper;

    public AbstractDatabase(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

}
