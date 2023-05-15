package com.application.project2java;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class QueryUtils {
    public static String[] joinCategories(List<CategoryName> categories) {
        List<String> categoryStrings = new ArrayList<>();
        for (CategoryName category : categories) {
            categoryStrings.add(category.toString());
        }
        return categoryStrings.toArray(new String[categories.size()]);
    }

    public static String formatQueryWithArray(int argCount, String query) {
        return query.replace("?", String.join(",", Collections.nCopies(argCount, "?")));
    }
}
