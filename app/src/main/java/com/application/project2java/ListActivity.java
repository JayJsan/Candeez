package com.application.project2java;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;

public class ListActivity extends FragmentActivity {

    //TODO REMOVE
    private Category[] categories = {
            new Category(CategoryName.CATEGORY1, 69),
            new Category(CategoryName.CATEGORY2, 69),
            new Category(CategoryName.CATEGORY3, 69),
            new Category(CategoryName.CATEGORY4, 69)};
    private FilterAdapter filterAdapter;
    private ProductListAdapter productListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        EditText searchArea = this.findViewById(R.id.search_area);
        searchArea.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });

        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);

        RecyclerView filterRecyclerView = this.findViewById(R.id.filter_recycler_view);
        filterAdapter = new FilterAdapter(categories);
        filterRecyclerView.setAdapter(filterAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        filterRecyclerView.setLayoutManager(layoutManager);

        RecyclerView productRecyclerView = this.findViewById(R.id.product_recycler_view);
        productListAdapter = new ProductListAdapter(categories);
        productRecyclerView.setAdapter(productListAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        productRecyclerView.setLayoutManager(productLayoutManager);

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}