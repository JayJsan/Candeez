package com.application.project2java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DatabaseMutationInstrumentedTest extends DatabaseInstrumentedTest {

    @Test
    public void testUpdateCart() {
        dataMutator.addData(defaultItem);
        int updated = dataMutator.updateItemCartStatus("test", 99);
        System.out.println(updated + " rows updated.");
        assertTrue(updated > 0);

    }

    @Test
    public void testAddData() {
        long row = dataMutator.addData(defaultItem);
        System.out.println(row);
        assertNotEquals(-1, row);
    }

    @Test
    public void testUpdateViewCount() {
        dataMutator.addData(defaultItem);
        dataMutator.updateItemViewCount("test", 20);
        ItemModel result = dataProvider.getItemWithName("test");
        assertEquals(21, result.getViewCount());
    }
}
