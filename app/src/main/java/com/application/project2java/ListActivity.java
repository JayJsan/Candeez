package com.application.project2java;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends FragmentActivity {

    private final List<CategoryName> categories = new ArrayList<>();
    private final List<CategoryName> selectedCategories = new ArrayList<>();
    private int allItemCount;
    private Handler handler;
    private DataProvider dataProvider;
    private List<ItemModel> items;
    private List<ItemModel> filteredItems;
    private FilterAdapter filterAdapter;
    private ProductListAdapter productListAdapter;
    private RecyclerView productRecyclerView;
    private CoordinatorLayout outerScrollView;
    private TextView resultCount;
    private View noItemsFound;
    private String searchQuery = "";

    private void setupCategories() {
        categories.addAll(Arrays.asList(CategoryName.values()));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupCategories();
        setContentView(R.layout.activity_list);
        dataProvider = App.getDataProvider();


        Intent intent = getIntent();

        if (intent.hasExtra("category")) {
            CategoryName preset = CategoryName.valueOf(intent.getStringExtra("category"));
            selectedCategories.add(preset);
        }

        noItemsFound = findViewById(R.id.no_search_results);
        noItemsFound.setVisibility(View.GONE);

        resultCount = findViewById(R.id.results_returned);

        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);

        setUpSearchBar();
        setupFilterRecyclerView();
        setupProductRecyclerView();
        updateData();
    }

    private void updateResultCount(int count) {
        resultCount.setText(count + " Results Returned");
    }

    private void setupFilterRecyclerView() {
        RecyclerView filterRecyclerView = this.findViewById(R.id.filter_recycler_view);
        filterAdapter = new FilterAdapter(categories, selectedCategories, this::updateFilters);
        filterRecyclerView.setAdapter(filterAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        filterRecyclerView.setLayoutManager(layoutManager);
    }

    private void enterFocusMode() {
        //outerScrollView.post(() -> outerScrollView.fullScroll(ScrollView.FOCUS_DOWN));
    }

    private void updateFilters(CategoryName categoryName, boolean isSelected) {
        enterFocusMode();

        if (isSelected)
            selectedCategories.add(categoryName);
        else
            selectedCategories.remove(categoryName);

        updateData();
    }

    private void updateData() {
        if (selectedCategories.isEmpty() && searchQuery.trim().equals("")) {
            noItemsFound.setVisibility(View.GONE);
            productRecyclerView.setVisibility(View.VISIBLE);
            productListAdapter.setItems(items);
            updateResultCount(allItemCount);
            return;
        }

        dataProvider.open();
        filteredItems = dataProvider.getItemsFromMultipleCategoriesWithName(selectedCategories, searchQuery);
        dataProvider.close();
        if (filteredItems.isEmpty()) {
            noItemsFound.setVisibility(View.VISIBLE);
            productRecyclerView.setVisibility(View.GONE);
            updateResultCount(0);
        } else {
            noItemsFound.setVisibility(View.GONE);
            productRecyclerView.setVisibility(View.VISIBLE);
            productListAdapter.setItems(filteredItems);
            updateResultCount(filteredItems.size());
        }

    }


    private void setUpSearchBar() {
        EditText searchArea = this.findViewById(R.id.search_area);
        searchArea.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });

        handler = new Handler();

        searchArea.addTextChangedListener(new TextWatcher() {
            private Runnable runnable;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacks(runnable);
                runnable = () -> {
                    searchQuery = searchArea.getText().toString();
                    updateData();
                    Log.d("DEBUG", searchQuery);
                };
                handler.postDelayed(runnable, 300);
            }
        });

    }

    private void setupProductRecyclerView() {
        dataProvider.open();
        items = dataProvider.getAllItems();
        dataProvider.close();
        productRecyclerView = this.findViewById(R.id.product_recycler_view);
        productListAdapter = new ProductListAdapter(items);
        allItemCount = items.size();
        updateResultCount(allItemCount);
        productRecyclerView.setAdapter(productListAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        productRecyclerView.setLayoutManager(productLayoutManager);

    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}