package com.application.project2java;

import android.content.Intent;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.example.project2java.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class BottomNavigationUtils {
    private static final HashMap<Integer, Class<?>> destinationMap = new HashMap<>();

    static {
        destinationMap.put(R.id.home, MainActivity.class);
        destinationMap.put(R.id.search, ListActivity.class);
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
        intent.putExtra("selectedItemId", itemId);
        activity.startActivity(intent);
    }
    public static void setCurrentItem(FragmentActivity activity) {

        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.bottom_navigation);
        if (bottomNavigationView == null) return;
        int selectedItemId = activity.getIntent().getIntExtra("selectedItemId", R.id.home);
        int selectedItemIndex;
        if (selectedItemId == R.id.home){
            selectedItemIndex = 0;
        } else if (selectedItemId == R.id.search){
            selectedItemIndex = 1;
        } else {
            selectedItemIndex = 0;
        }
        bottomNavigationView.getMenu().getItem(selectedItemIndex).setChecked(true);
    }
}
