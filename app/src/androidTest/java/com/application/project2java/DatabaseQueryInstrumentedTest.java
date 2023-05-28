package com.application.project2java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void testCategoryQuery() {
        dataMutator.addData(defaultItem);
        List<ItemModel> result = dataProvider.getCategoryItems(CategoryName.Gummies);
        assertEquals(1, result.size());
        System.out.println(result.get(0).toString());
        assertEquals("Gummies", result.get(0).getCategory());
    }

    @Test
    public void testMultipleCategoryQuery() {
        dataMutator.addData(defaultItem);
        dataMutator.addData(hardCandyItem);
        List<CategoryName> categoriesToQuery = new ArrayList<CategoryName>() {{
            add(CategoryName.Gummies);
            add(CategoryName.Hard_Candy);
            add(CategoryName.Chocolate);
        }};
        List<ItemModel> result = dataProvider.getItemsFromMultipleCategories(categoriesToQuery);
        assertEquals(result.size(), 2);
        dataMutator.addData(defaultItem);
        result = dataProvider.getItemsFromMultipleCategories(categoriesToQuery);
        assertEquals(result.size(), 3);

    }

    @Test
    public void testCategoryFrequencyQuery() {
        dataMutator.addData(defaultItem);
        dataMutator.addData(defaultItem);
        int result = dataProvider.getCategoryItemFrequency(CategoryName.Gummies);
        assertEquals(2, result);
        dataMutator.addData(hardCandyItem);
        result = dataProvider.getCategoryItemFrequency(CategoryName.Gummies);
        assertEquals(2, result);
        dataMutator.addData(defaultItem);
        result = dataProvider.getCategoryItemFrequency(CategoryName.Gummies);
        assertEquals(3, result);
    }

    @Test
    public void testMostViewedQuery() {
        for (int i = 0; i < 6; ++i) {
            dataMutator.addData(defaultItem);
        }
        List<ItemModel> result = dataProvider.getMostViewedItems();
        assertEquals(3, result.size());
    }

    @Test
    public void testGetItemByNameQuery() {
        dataMutator.addData(defaultItem);
        ItemModel result = dataProvider.getItemWithName("test");
        assertNotEquals(null, result);
        result = dataProvider.getItemWithName("fake");
        assertNull(result);
    }

    @Test
    public void testGetWithNameAndCategoryQuery() {
        dataMutator.addData(defaultItem);
        List<CategoryName> categories = new ArrayList<>();
        categories.add(CategoryName.Gummies);
        List<ItemModel> result = dataProvider.getItemsFromMultipleCategoriesWithName(categories, "te");
        for (ItemModel i : result) {
            System.out.println(i.toString());
        }
        assertEquals(1, result.size());
        categories.remove(0);

        result = dataProvider.getItemsFromMultipleCategoriesWithName(categories, "es");
        assertEquals(0, result.size());

    }


}
