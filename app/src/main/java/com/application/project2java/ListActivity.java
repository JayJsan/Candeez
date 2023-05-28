package com.application.project2java;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
    private FilterField filterField = FilterField.FILTER_ALPHABETICALLY;
    private FilterDirection filterDirection = FilterDirection.ASCENDING;
    private Comparator<ItemModel> comparator = FilterUtils.getComparator(filterField, filterDirection);
    private FloatingActionButton buttonSortName;
    private FloatingActionButton buttonSortBestSelling;
    private FloatingActionButton buttonSortMostViewed;
    private FloatingActionButton buttonSortPrice;
    private Dialog dialog;

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
        if (intent.hasExtra("filter")) {
            filterField = FilterField.valueOf(intent.getStringExtra("filter"));
            filterDirection = FilterDirection.DESCENDING;
        }

        noItemsFound = findViewById(R.id.no_search_results);
        noItemsFound.setVisibility(View.GONE);

        resultCount = findViewById(R.id.results_returned);

        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);


        setUpSearchBar();
        setupFilterRecyclerView();
        setupProductRecyclerView();
        setupFilterDialog();
        updateData();
    }

    private void updateResultCount(int count) {
        resultCount.setText(count + " Results Returned");
    }


    private void setupFilterDialog() {
        MaterialButton filterButton = findViewById(R.id.button_filter);
        filterButton.setOnClickListener(l -> openFilters());
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.filter_bottom_drawer);


        MaterialButton closeDialogButton = dialog.findViewById(R.id.button_close_dialog);
        buttonSortName = dialog.findViewById(R.id.button_sort_name);
        buttonSortBestSelling = dialog.findViewById(R.id.button_sort_best_selling);
        buttonSortPrice = dialog.findViewById(R.id.button_sort_price);
        buttonSortMostViewed = dialog.findViewById(R.id.button_sort_most_viewed);

        closeDialogButton.setOnClickListener(l -> dialog.dismiss());

        setupFilterButton(buttonSortName, FilterField.FILTER_ALPHABETICALLY);
        setupFilterButton(buttonSortMostViewed, FilterField.FILTER_BY_VIEWS);
        setupFilterButton(buttonSortPrice, FilterField.FILTER_BY_PRICE);
        setupFilterButton(buttonSortBestSelling, FilterField.FILTER_BEST_SELLING);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        updateButtons();
    }

    private void setupFilterRecyclerView() {
        RecyclerView filterRecyclerView = findViewById(R.id.filter_recycler_view);
        filterAdapter = new FilterAdapter(categories, selectedCategories, this::updateFilters);
        filterRecyclerView.setAdapter(filterAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        filterRecyclerView.setLayoutManager(layoutManager);
    }

    private void openFilters() {

        dialog.show();
    }

    private void updateButtons() {
        switch (filterField) {
            case FILTER_ALPHABETICALLY:
                setButtonSelected(buttonSortName);
                setButtonsDisabled(buttonSortPrice, buttonSortBestSelling, buttonSortMostViewed);
                break;
            case FILTER_BEST_SELLING:
                setButtonSelected(buttonSortBestSelling);
                setButtonsDisabled(buttonSortPrice, buttonSortName, buttonSortMostViewed);
                break;
            case FILTER_BY_VIEWS:
                setButtonSelected(buttonSortMostViewed);
                setButtonsDisabled(buttonSortPrice, buttonSortBestSelling, buttonSortName);
                break;
            case FILTER_BY_PRICE:
                setButtonSelected(buttonSortPrice);
                setButtonsDisabled(buttonSortName, buttonSortBestSelling, buttonSortMostViewed);
                break;
            default:
                break;
        }
    }

    private void setButtonSelected(FloatingActionButton button) {
        if (filterDirection == FilterDirection.ASCENDING)
            FilterUtils.setAscendingButtonAppearance(button);
        else FilterUtils.setDescendingButtonAppearance(button);
    }

    private void setButtonsDisabled(FloatingActionButton... buttons) {
        for (FloatingActionButton button : buttons) {
            FilterUtils.setButtonDisabled(button);
        }
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

    private void displayDefaultItems() {
        noItemsFound.setVisibility(View.GONE);
        productRecyclerView.setVisibility(View.VISIBLE);
        productListAdapter.setItems(items);
        updateResultCount(allItemCount);
    }

    private void displayNonEmptyFilteredItems() {
        noItemsFound.setVisibility(View.GONE);
        productRecyclerView.setVisibility(View.VISIBLE);
        productListAdapter.setItems(filteredItems);
        updateResultCount(filteredItems.size());
    }

    private boolean isDefaultSettings() {
        return selectedCategories.isEmpty() && searchQuery.trim().equals("");
    }

    private void updateData() {
        if (isDefaultSettings()) {
            displayDefaultItems();
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
            FilterUtils.applyFilters(filteredItems, comparator);
            displayNonEmptyFilteredItems();
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
        FilterUtils.applyFilters(items, comparator);
        dataProvider.close();
        productRecyclerView = this.findViewById(R.id.product_recycler_view);
        productListAdapter = new ProductListAdapter(items);
        allItemCount = items.size();
        updateResultCount(allItemCount);
        productRecyclerView.setAdapter(productListAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        productRecyclerView.setLayoutManager(productLayoutManager);

    }

    private void setupFilterButton(FloatingActionButton floatingActionButton, FilterField filterField) {
        floatingActionButton.setOnClickListener(l -> {
            if (this.filterField != filterField) {
                filterDirection = FilterDirection.ASCENDING;
            } else {
                if (filterDirection == FilterDirection.ASCENDING)
                    filterDirection = FilterDirection.DESCENDING;
                else filterDirection = FilterDirection.ASCENDING;
            }
            this.filterField = filterField;
            updateButtons();
            comparator = FilterUtils.getComparator(filterField, filterDirection);
            if (isDefaultSettings()) {
                FilterUtils.applyFilters(items, comparator);
                displayDefaultItems();

                return;
            }
            if (!filteredItems.isEmpty()) {
                FilterUtils.applyFilters(filteredItems, comparator);
                displayNonEmptyFilteredItems();
            }

        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}