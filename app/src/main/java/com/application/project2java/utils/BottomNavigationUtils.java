package com.application.project2java.utils;

import android.content.Intent;

import androidx.fragment.app.FragmentActivity;

import com.application.project2java.activities.CartActivity;
import com.application.project2java.activities.DetailsActivity;
import com.application.project2java.activities.FavouritesActivity;
import com.application.project2java.activities.ListActivity;
import com.application.project2java.activities.MainActivity;
import com.example.project2java.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

/**
 * BottomNavigationUtils is a helper class that provides helper methods to make sure the right
 * activity clicked from the respective icon is showing.
 */
public class BottomNavigationUtils {
    private static final HashMap<Integer, Class<?>> destinationMap = new HashMap<>();

    static {
        destinationMap.put(R.id.home, MainActivity.class);
        destinationMap.put(R.id.search, ListActivity.class);
        destinationMap.put(R.id.favourites, FavouritesActivity.class);
        destinationMap.put(R.id.cart, CartActivity.class);
    }

    /**
     * This sets up the bottom navigation view in the current activity
     * @param activity The current activity displayed.
     */
    public static void setupBottomNavigationView(FragmentActivity activity) {
        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.bottom_navigation);

        // Makes sure the icon clicked navigates the user to the correct activity the icon
        // represents.
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Class<?> destinationClass = getDestinationClass(activity, item.getItemId());
            if (destinationClass != null) {
                navigateToDestination(activity, destinationClass, item.getItemId());
                return true;
            }
            return false;
        });
    }

    /**
     * Returns the respective class activity according to the item id.
     * @param activity the current activity displayed.
     * @param itemId the itemID of the desired class activity.
     * @return The class activity based on the item ID.
     */
    private static Class<?> getDestinationClass(FragmentActivity activity, int itemId) {
        Class<?> destinationClass = destinationMap.get(itemId);
        if (destinationClass != null && !activity.getClass().equals(destinationClass)) {
            return destinationClass;
        }
        return null;
    }

    /**
     * Displays the new activity based on the given destination class.
     * @param activity the current activity.
     * @param destinationClass the new activity class.
     * @param itemId the item id of the activity.
     */
    private static void navigateToDestination(FragmentActivity activity, Class<?> destinationClass, int itemId) {
        Intent intent = new Intent(activity, destinationClass);
        activity.startActivity(intent);
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        activity.finish();
    }

    /**
     * Sets the highlighted icon to be the icon that represents the current activity shown.
     * @param activity the current activity.
     */
    public static void setCurrentItem(FragmentActivity activity) {
        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.bottom_navigation);

        if (bottomNavigationView == null) return;
        int selectedItemIndex;
        if (activity instanceof MainActivity) {
            selectedItemIndex = 0;
        } else if (activity instanceof ListActivity || activity instanceof DetailsActivity) {
            selectedItemIndex = 1;
        } else if (activity instanceof FavouritesActivity) {
            selectedItemIndex = 2;
        } else if (activity instanceof CartActivity) {
            selectedItemIndex = 3;
        } else {
            selectedItemIndex = 0;
        }
        bottomNavigationView.getMenu().getItem(selectedItemIndex).setChecked(true);
    }
}
