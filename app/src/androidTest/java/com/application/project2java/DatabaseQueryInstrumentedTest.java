package com.application.project2java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.List;

public class DatabaseQueryInstrumentedTest extends DatabaseInstrumentedTest {
    @Test
    public void testAllItemsQuery() {
        super.dataMutator.addData(defaultItem);
        List<ItemModel> dataList = dataProvider.getAllItems();

        assertNotNull(dataList.toString(), dataList);
        assertTrue(dataList.toString(), dataList.size() > 0);
        System.out.println(dataList.toString());
    }


    @Test
    public void testFavouritesQuery() {
        dataMutator.addData(defaultItem);
        dataMutator.updateItemFavouriteStatus("test", true);
        List<ItemModel> result = dataProvider.getFavouriteItems();
        for (ItemModel item : result) {
            System.out.println(item.toString());
            assertTrue(item.isFavourite());
        }
        dataMutator.updateItemFavouriteStatus("test", false);
        result = dataProvider.getFavouriteItems();
        System.out.println("Items in favourites after removing: " + result.size());
        assertEquals(0, result.size());


    }

    @Test
    public void testBestSellingQuery() {
        final int itemsToAdd = 5;
        for (int i = 0; i < itemsToAdd; ++i) {
            dataMutator.addData(defaultItem);
        }
        List<ItemModel> allItems = dataProvider.getAllItems();
        assertEquals(itemsToAdd, allItems.size());
        List<ItemModel> result = dataProvider.getBestSellingItems();
        assertEquals(3, result.size());
    }

    @Test
    public void testCartQuery() {
        dataMutator.addData(defaultItem);
        List<ItemModel> result = dataProvider.getCartItems();
        assertEquals(0, result.size());
        dataMutator.addData(itemInCart);
        result = dataProvider.getCartItems();
        assertEquals(1, result.size());

    }


}
