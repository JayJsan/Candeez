package com.application.project2java;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class DataProviderInstrumentedTest {
    private DataProvider dataProvider;
    private SQLiteDatabase database;

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        dataProvider = new DataProvider(context);
        dataProvider.open();
        database = dataProvider.db;
        dataProvider.addData("test", "testLorem", 69, "live", 69, false, 99);
    }

    @After
    public void teardown() {
        dataProvider.close();
    }

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
                        ItemContract.ItemEntry.COLUMN_IMAGE_URIS,
                        ItemContract.ItemEntry.COLUMN_DESCRIPTION,
                        ItemContract.ItemEntry.COLUMN_PRICE,
                        ItemContract.ItemEntry.COLUMN_IS_FAVOURITE,
                        ItemContract.ItemEntry.COLUMN_CART_QUANTITY,
                        ItemContract.ItemEntry.COLUMN_VIEW_COUNT
                };

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
    @Test
    public void testSearchData() {
        //TODO add data
        String query = "SELECT * FROM " + ItemContract.ItemTable.TABLE_NAME;
        String[] selectionArgs = null;

        List<ItemModel> dataList = dataProvider.searchData(query, selectionArgs);

        assertNotNull(dataList);
        assertTrue(dataList.size() > 0);
    }

    @Test
    public void testUpdateData() {
        //TODO add data
        int id = 1;
        String name = "John";
        int age = 30;

        dataProvider.updateData(id, name, age);

        //TODO make assertions
    }

    @Test
    public void testAddData() {
        dataProvider.addData("test", "testLorem", 69, "live", 69, false, 99);

    }

    @Test
    public void testDeleteData() {
        //TODO add data

        int id = 1;

        dataProvider.deleteData(id);

        //TODO make assertions
    }
}
