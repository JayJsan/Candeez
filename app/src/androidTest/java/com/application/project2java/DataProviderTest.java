package com.application.project2java;

import android.content.Context;
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

@RunWith(AndroidJUnit4.class)
public class DataProviderTest {
    private DataProvider dataProvider;
    private SQLiteDatabase database;

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();
        dataProvider = new DataProvider(context);
        dataProvider.open();
        database = dataProvider.db;
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
    public void testDeleteData() {
        //TODO add data

        int id = 1;

        dataProvider.deleteData(id);

        //TODO make assertions
    }
}
