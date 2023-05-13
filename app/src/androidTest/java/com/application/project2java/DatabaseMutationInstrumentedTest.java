package com.application.project2java;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DatabaseMutationInstrumentedTest extends DatabaseInstrumentedTest{

    @Test
    public void testUpdateCart() {
        dataMutator.addData(mockData);
        int updated = dataMutator.updateItemCartStatus("test", 99);
        System.out.println(Integer.toString(updated) + " rows updated.");
        assertTrue(updated > 0);

    }
    @Test
    public void testAddData() {
        long row = dataMutator.addData(mockData);
        System.out.println(row);
        assertNotEquals(-1, row);
    }
}
