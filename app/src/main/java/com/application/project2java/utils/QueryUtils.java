package com.application.project2java.utils;


import com.application.project2java.constants.CategoryName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * QueryUtils is a helper class that provides methods to modify elements in the database.
 */
public final class QueryUtils {
    /**
     * Converts the list of categories selected to an array of strings.
     * @param categories The list of categories selected.
     * @return An array of string of the categories selected.
     */
    public static String[] joinCategories(List<CategoryName> categories) {
        List<String> categoryStrings = new ArrayList<>();
        for (CategoryName category : categories) {
            categoryStrings.add(category.toString());
        }
        return categoryStrings.toArray(new String[categories.size()]);
    }

    /**
     * Formats the query passed in.
     * @param argCount The amount of arguments.
     * @param query The query to be formatted.
     * @return The formatted query.
     */
    public static String formatQueryWithArray(int argCount, String query) {
        return query.replace(",,", String.join(",", Collections.nCopies(argCount, "?")));
    }
}
