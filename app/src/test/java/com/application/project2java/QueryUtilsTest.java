package com.application.project2java;

import static com.application.project2java.ItemContract.ItemEntry.COLUMN_CATEGORY;
import static com.application.project2java.ItemContract.ItemTable.TABLE_NAME;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QueryUtilsTest {

    @Test
    public void testJoinCategories() {
        List<CategoryName> categories = new ArrayList<>();
        categories.add(CategoryName.Gummies);
        categories.add(CategoryName.Hard_Candy);
        String[] result = QueryUtils.joinCategories(categories);
        assertArrayEquals(new String[]{"Gummies", "Hard_Candy"}, result);
    }

    @Test
    public void testFormatArrayQuery(){
       String result = QueryUtils.formatQueryWithArray(4,QueryProvider.CATEGORY_ITEM_QUERY);
       assertEquals("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY + " IN (?,?,?,?)", result);

    }
}