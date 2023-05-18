package com.application.project2java;

import android.content.Intent;

import androidx.fragment.app.FragmentActivity;

import com.example.project2java.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class BottomNavigationUtils {
    private static final HashMap<Integer, Class<?>> destinationMap = new HashMap<>();

    static {
        destinationMap.put(R.id.home, MainActivity.class);
        destinationMap.put(R.id.search, ListActivity.class);
        destinationMap.put(R.id.favourites, FavouritesActivity.class);
        destinationMap.put(R.id.cart, CartActivity.class);
    }

    public static void setupBottomNavigationView(FragmentActivity activity) {
        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Class<?> destinationClass = getDestinationClass(activity, item.getItemId());
            if (destinationClass != null) {
                navigateToDestination(activity, destinationClass, item.getItemId());
                return true;
            }
            return false;
        });
    }

    private static Class<?> getDestinationClass(FragmentActivity activity, int itemId) {
        Class<?> destinationClass = destinationMap.get(itemId);
        if (destinationClass != null && !activity.getClass().equals(destinationClass)) {
            return destinationClass;
        }
        return null;
    }

    private static void navigateToDestination(FragmentActivity activity, Class<?> destinationClass, int itemId) {
        Intent intent = new Intent(activity, destinationClass);
        activity.startActivity(intent);
    }

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
        }else {
            selectedItemIndex = 0;
        }
        bottomNavigationView.getMenu().getItem(selectedItemIndex).setChecked(true);
    }
}
