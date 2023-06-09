package com.application.project2java.database;

import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_CART_QUANTITY;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_CATEGORY;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_DESCRIPTION;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_ID;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_IMAGE_URIS;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_IS_FAVOURITE;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_NAME;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_PRICE;
import static com.application.project2java.constants.ItemContract.ItemEntry.COLUMN_VIEW_COUNT;

import android.content.Context;
import android.database.Cursor;

import com.application.project2java.constants.CategoryName;
import com.application.project2java.constants.QueryProvider;
import com.application.project2java.models.ItemModel;
import com.application.project2java.utils.QueryUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The DataProvider class provides methods that returns specific data from the database.
 */
public class DataProvider extends AbstractDatabase {

    public DataProvider(Context context) {
        super(context);
    }

    public List<ItemModel> searchData(String query, String[] selectionArgs) {
        List<ItemModel> dataList = new ArrayList<>();

        // To use, have ? in the query so you can fill it in with selectionArgs
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String imageUris = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URIS));
                imageUris = imageUris.replaceAll(" ", "");
                List<String> imageUriArray = Arrays.asList(imageUris.split(","));
                CategoryName category = CategoryName.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                boolean isFavourite = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVOURITE)) == 1;
                int cartQty = cursor.getInt(cursor.getColumnIndex(COLUMN_CART_QUANTITY));
                int viewCount = cursor.getInt(cursor.getColumnIndex(COLUMN_VIEW_COUNT));
                float price = cursor.getFloat(cursor.getColumnIndex(COLUMN_PRICE));
                //TODO add data to the list of data
                ItemModel item = new ItemModel(name, description, price, category, imageUriArray, viewCount, isFavourite, cartQty);
                dataList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return dataList;
    }

    public int countData(String query, String[] selectionArgs) {
        Cursor cursor = db.rawQuery(query, selectionArgs);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public List<ItemModel> getAllItems() {
        return searchData(QueryProvider.ALL_ITEMS_QUERY, null);
    }

    public List<ItemModel> getFavouriteItems() {
        return searchData(QueryProvider.FAVOURITE_ITEMS_QUERY, null);
    }

    public List<ItemModel> getBestSellingItems() {
        return searchData(QueryProvider.BEST_SELLING_HOME_SCREEN_QUERY, null);
    }

    public List<ItemModel> getMostViewedItems() {
        return searchData(QueryProvider.MOST_VIEWED_HOME_SCREEN_QUERY, null);
    }

    public List<ItemModel> getCartItems() {
        return searchData(QueryProvider.CART_ITEMS_QUERY, null);
    }

    public ItemModel getItemWithName(String name) {
        List<ItemModel> result = searchData(QueryProvider.ITEM_BY_NAME_QUERY, new String[]{name});
        if (result.size() > 0)
            return result.get(0);
        else return null;
    }


    public List<ItemModel> getCategoryItems(CategoryName category) {
        return searchData(QueryProvider.CATEGORY_ITEM_QUERY.replaceAll(",,", "?"), new String[]{category.toString()});
    }

    public List<ItemModel> getRelatedItems(String name, CategoryName category) {
        return searchData(QueryProvider.CATEGORY_ITEM_QUERY.replaceAll(",,", "?") + " AND NOT " + COLUMN_NAME + " = ? LIMIT 5", new String[]{category.toString(), name});
    }

    public List<ItemModel> getItemsFromMultipleCategories(List<CategoryName> categories) {
        String query = QueryUtils.formatQueryWithArray(categories.size(), QueryProvider.CATEGORY_ITEM_QUERY);
        String[] args = QueryUtils.joinCategories(categories);
        return searchData(query, args);
    }

    public List<ItemModel> getItemsFromMultipleCategoriesWithName(List<CategoryName> categories, String name) {
        String query;
        String[] args;
        String nameClause = name != "" ? name + "%" : "%";
        if (categories.size() > 0) {
            String[] categoryQueryArgs = QueryUtils.joinCategories(categories);

            List<String> temp = new ArrayList<>(Arrays.asList(categoryQueryArgs));
            temp.add(nameClause);
            args = temp.toArray(temp.toArray(new String[0]));
            query = QueryUtils.formatQueryWithArray(categories.size(), QueryProvider.ITEMS_LIKE_NAME_IN_CATEGORIES_QUERY);
        } else {
            query = QueryProvider.ITEMS_LIKE_NAME_QUERY;
            args = new String[]{nameClause};
        }
        return searchData(query, args);

    }

    public int getCategoryItemFrequency(CategoryName category) {
        return countData(QueryProvider.CATEGORY_ITEM_FREQUENCY_QUERY, new String[]{category.toString()});
    }


}

