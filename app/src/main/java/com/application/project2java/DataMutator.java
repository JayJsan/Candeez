package com.application.project2java;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DataMutator extends AbstractDatabase {

    private final List<DatabaseWriteListener> writeListeners = new ArrayList<>();

    public DataMutator(Context context) {
        super(context);
    }

    public void addDatabaseWriteListener(DatabaseWriteListener listener) {
        writeListeners.add(listener);
    }

    public void removeDatabaseWriteListener(DatabaseWriteListener listener) {
        writeListeners.remove(listener);
    }

    private void notifyDatabaseWrite() {
        for (DatabaseWriteListener listener : writeListeners) {
            listener.onDatabaseWrite();
        }
    }

    public long addData(ItemModel item) {
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_NAME, item.getName());
        values.put(ItemContract.ItemEntry.COLUMN_DESCRIPTION, item.getDescription());
        values.put(ItemContract.ItemEntry.COLUMN_PRICE, item.getPrice());
        values.put(ItemContract.ItemEntry.COLUMN_CATEGORY, item.getCategory());
        values.put(ItemContract.ItemEntry.COLUMN_IMAGE_URIS, item.getImageUris().toString());
        values.put(ItemContract.ItemEntry.COLUMN_VIEW_COUNT, item.getViewCount());
        values.put(ItemContract.ItemEntry.COLUMN_IS_FAVOURITE, item.isFavourite() ? 1 : 0);
        values.put(ItemContract.ItemEntry.COLUMN_CART_QUANTITY, item.getCartQuantity());

        return db.insert(ItemContract.ItemTable.TABLE_NAME, null, values);
    }

    public int updateItemCartStatus(String name, int cartQty) {
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_CART_QUANTITY, cartQty);
        notifyDatabaseWrite();
        return updateAtName(name, values);

    }

    public int updateItemViewCount(String name, int newViewCount) {
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_VIEW_COUNT, newViewCount);
        notifyDatabaseWrite();
        return updateAtName(name, values);
    }

    public int updateItemFavouriteStatus(String name, boolean isFavourite) {
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_IS_FAVOURITE, isFavourite ? 1 : 0);
        return updateAtName(name, values);
    }

    private int updateAtName(String name, ContentValues values) {
        String whereClause = ItemContract.ItemEntry.COLUMN_NAME + " = ?";
        String[] whereArgs = {name};
        int result = db.update(ItemContract.ItemTable.TABLE_NAME, values, whereClause, whereArgs);
        notifyDatabaseWrite();
        return result;
    }


    public void deleteData(int id) {
        String whereClause = ItemContract.ItemEntry.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        db.delete(ItemContract.ItemTable.TABLE_NAME, whereClause, whereArgs);
    }
}
