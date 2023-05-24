package com.application.project2java;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractDatabase {

    private DatabaseHelper dbHelper;
    SQLiteDatabase db;

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
