package com.application.project2java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;

public class DatabaseNonNullInstrumentedTest extends DatabaseInstrumentedTest {

    @Test
    public void testDataProviderNotNull() {
        assertNotNull(dataProvider);
    }

    @Test
    public void testDatabaseNotNull() {
        assertNotNull(database);
    }

    @Test
    public void testTableColumns() {
        Cursor cursor = database.rawQuery("PRAGMA table_info(" + ItemContract.ItemTable.TABLE_NAME + ")", null);
        if (cursor != null) {
            try {
                int columnNameIndex = cursor.getColumnIndex("name");

                String[] expectedColumns = {
                        ItemContract.ItemEntry.COLUMN_ID,
                        ItemContract.ItemEntry.COLUMN_NAME,
                        ItemContract.ItemEntry.COLUMN_CATEGORY,
                        ItemContract.ItemEntry.COLUMN_IMAGE_URIS,
                        ItemContract.ItemEntry.COLUMN_DESCRIPTION,
                        ItemContract.ItemEntry.COLUMN_PRICE,
                        ItemContract.ItemEntry.COLUMN_IS_FAVOURITE,
                        ItemContract.ItemEntry.COLUMN_CART_QUANTITY,
                        ItemContract.ItemEntry.COLUMN_VIEW_COUNT};

                for (String expectedColumn : expectedColumns) {
                    boolean columnExists = false;

                    if (cursor.moveToFirst()) {
                        do {
                            String columnName = cursor.getString(columnNameIndex);

                            if (columnName.equals(expectedColumn)) {
                                columnExists = true;
                                break;
                            }
                        } while (cursor.moveToNext());
                    }

                    assertTrue("Column does not exist: " + expectedColumn, columnExists);
                }
            } finally {
                cursor.close();
            }
        } else {
            // Cursor is null, test failed
            fail("Failed to retrieve table schema");
        }
    }
}
