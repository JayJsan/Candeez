package com.application.project2java;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

@RunWith(AndroidJUnit4.class)
public class DatabaseInstrumentedTest {
    DataProvider dataProvider;
    DataMutator dataMutator;
    SQLiteDatabase database;

    ItemModel defaultItem = new ItemModel("test", "testLorem", 69, CategoryName.Gummies, Arrays.asList("test"), 69, false, 0);
    //CartQty > 0
    ItemModel itemInCart = new ItemModel("test", "testLorem", 69, CategoryName.Gummies, Arrays.asList("test"), 69, false, 1);
    ItemModel hardCandyItem = new ItemModel("test", "testLorem", 69, CategoryName.Hard_Candy, Arrays.asList("test"), 69, false, 1);

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
    public void testDeleteData() {
        //TODO add data

        int id = 1;

        dataMutator.deleteData(id);
        dataMutator.close();

        //TODO make assertions
    }
}
