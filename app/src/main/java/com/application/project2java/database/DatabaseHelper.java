package com.application.project2java.database;

import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_CART_QUANTITY;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_CATEGORY;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_DESCRIPTION;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_ID;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_IMAGE_URIS;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_IS_FAVOURITE;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_NAME;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_PRICE;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_VIEW_COUNT;
import static com.application.project2java.constants.ItemContract.ItemTable.TABLE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.application.project2java.constants.ItemContract;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "candeez_data";
    private static final int DATABASE_VERSION = 1;
    private final String createTableQuery = "CREATE TABLE " + ItemContract.ItemTable.TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_CATEGORY + " TEXT, "
            + COLUMN_IMAGE_URIS + " TEXT, "
            + COLUMN_DESCRIPTION + " TEXT, "
            + COLUMN_PRICE + " REAL, "
            + COLUMN_IS_FAVOURITE + " SMALLINT, "
            + COLUMN_CART_QUANTITY + " SMALLINT, "
            + COLUMN_VIEW_COUNT + " SMALLINT)";
    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            String alterTableQuery = "ALTER TABLE " + TABLE_NAME
                    + " ADD COLUMN new_column TEXT";
            db.execSQL(alterTableQuery);
        }
    }

    public void copyDatabase() {
        try {
            getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            String outFileName = context.getDatabasePath(DATABASE_NAME).getPath();
            OutputStream outputStream = Files.newOutputStream(Paths.get(outFileName));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
