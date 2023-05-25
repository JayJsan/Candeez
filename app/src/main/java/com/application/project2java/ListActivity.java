package com.application.project2java;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends FragmentActivity {

    private final List<CategoryName> categories = new ArrayList<>();
    private List<CategoryName> selectedCategories = new ArrayList<>();
    private DataProvider dataProvider;
    private DataMutator dataMutator;
    private List<ItemModel> items;
    private List<ItemModel> filteredItems;
    private FilterAdapter filterAdapter;
    private ProductListAdapter productListAdapter;
    private boolean isSearching;

    private void setupCategories() {
        categories.addAll(Arrays.asList(CategoryName.values()));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupCategories();
        setContentView(R.layout.activity_list);
        dataProvider = App.getDataProvider();

        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);

        setUpSearchBar();
        setupFilterRecyclerView();
        setupProductRecyclerView();
    }

    private void setupFilterRecyclerView() {
        RecyclerView filterRecyclerView = this.findViewById(R.id.filter_recycler_view);
        filterAdapter = new FilterAdapter(categories, this::updateFilters);
        filterRecyclerView.setAdapter(filterAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        filterRecyclerView.setLayoutManager(layoutManager);
    }

    private void updateFilters(CategoryName categoryName, boolean isSelected) {

        if (isSelected)
            selectedCategories.add(categoryName);
        else
            selectedCategories.remove(categoryName);

        if (!selectedCategories.isEmpty()) {
            dataProvider.open();
            filteredItems = dataProvider.getItemsFromMultipleCategories(selectedCategories);
            dataProvider.close();
            productListAdapter.setItems(filteredItems);
        } else {
            productListAdapter.setItems(items);
        }


    }

    private void setUpSearchBar() {
        EditText searchArea = this.findViewById(R.id.edit_search_area);
        searchArea.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        View rootView = findViewById(android.R.id.content);

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private int mPreviousHeight = rootView.getHeight();

            @Override
            public void onGlobalLayout() {
                int newHeight = rootView.getHeight();
                if (newHeight != mPreviousHeight) {
                    boolean isKeyboardOpen = newHeight < mPreviousHeight;
                    if (isKeyboardOpen) {
                        growSearch();
                    } else {
                        shrinkSearch();
                    }
                    mPreviousHeight = newHeight;
                }
            }
        });
    }

    private void setupProductRecyclerView() {
        dataProvider.open();
        items = dataProvider.getAllItems();
        dataProvider.close();
        RecyclerView productRecyclerView = this.findViewById(R.id.product_recycler_view);
        productListAdapter = new ProductListAdapter(items);
        productRecyclerView.setAdapter(productListAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        productRecyclerView.setLayoutManager(productLayoutManager);

    }

    private void shrinkSearch() {
        LinearLayout topSection = this.findViewById(R.id.search_and_filters);
        ObjectAnimator animation = ObjectAnimator.ofFloat(topSection, "translationY", -140f);
        animation.setDuration(200);
        animation.start();
    }

    private void growSearch() {
        LinearLayout topSection = this.findViewById(R.id.search_and_filters);
        ObjectAnimator animation = ObjectAnimator.ofFloat(topSection, "translationY", 0f);
        animation.setDuration(200);
        animation.start();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}