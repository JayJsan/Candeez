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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class DataProviderInstrumentedTest {
    private DataProvider dataProvider;
    private DataMutator dataMutator;
    private SQLiteDatabase database;

    private ItemModel mockData = new ItemModel("test", "testLorem", 69, Arrays.asList("test"), 69, false, 99);

    private void deleteAllRows() {
        database.delete(ItemContract.ItemTable.TABLE_NAME, null, null);
    }

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        dataMutator = new DataMutator(context);
        dataProvider = new DataProvider(context);
        dataMutator.open();
        dataProvider.open();
        database = dataProvider.db;
    }

    @After
    public void teardown() {
        deleteAllRows();
        dataMutator.close();
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

                String[] expectedColumns = {ItemContract.ItemEntry.COLUMN_ID, ItemContract.ItemEntry.COLUMN_NAME, ItemContract.ItemEntry.COLUMN_IMAGE_URIS, ItemContract.ItemEntry.COLUMN_DESCRIPTION, ItemContract.ItemEntry.COLUMN_PRICE, ItemContract.ItemEntry.COLUMN_IS_FAVOURITE, ItemContract.ItemEntry.COLUMN_CART_QUANTITY, ItemContract.ItemEntry.COLUMN_VIEW_COUNT};

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
        dataMutator.addData(mockData);
        List<ItemModel> dataList = dataProvider.getAllItems();

        assertNotNull(dataList.toString(), dataList);
        assertTrue(dataList.toString(), dataList.size() > 0);
        System.out.println(dataList.toString());
    }

    @Test
    public void testAddData() {
        long row = dataMutator.addData(mockData);
        System.out.println(row);
        assertNotEquals(-1, row);
    }

    @Test
    public void testFavouritesQuery() {
        dataMutator.addData(mockData);
        dataMutator.updateItemFavouriteStatus("test", true);
        List<ItemModel> result = dataProvider.getFavouriteItems();
        for(ItemModel item : result){
            System.out.println(item.toString());
            assertTrue(item.isFavourite());
        }

    }

    @Test
    public void testUpdateCart() {
        dataMutator.addData(mockData);
        int updated = dataMutator.updateItemCartStatus("test", 99);
        System.out.println(Integer.toString(updated) + " rows updated.");
        assertTrue(updated > 0);

    }

    @Test
    public void testDeleteData() {
        //TODO add data

        int id = 1;

        dataMutator.deleteData(id);
        dataMutator.close();

        //TODO make assertions
    }
}
