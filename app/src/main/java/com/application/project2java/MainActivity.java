package com.application.project2java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    DataProvider dataProvider;
    DataMutator dataMutator;
    CompactListAdapter bestSellingAdapter;
    CompactListAdapter mostViewedAdapter;
    List<ItemModel> bestSellingItems;
    List<ItemModel> mostViewedItems;
    List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);

        dataMutator = App.getDataMutator();
        dataMutator.addDatabaseWriteListener(this::updateItems);
        dataProvider = App.getDataProvider();
        setup();
    }

    private void setup() {
        dataProvider.open();
        setupBestSellingRecyclerView();
        setupMostViewedRecyclerView();
        setupCategoryRecyclerView();
        setupButtons();
        dataProvider.close();
    }

    private void setupButtons() {
        MaterialButton seeAllCategories = findViewById(R.id.see_all_categories_button);
        MaterialButton seeBestSelling = findViewById(R.id.see_all_best_selling_button);
        MaterialButton seeMostViewed = findViewById(R.id.see_all_most_viewed_button);
        seeAllCategories.setOnClickListener(l -> {
            ListItemUtils.navigateToList((CategoryName) null);
        });
        seeBestSelling.setOnClickListener(l -> {
            ListItemUtils.navigateToList(FilterField.FILTER_BEST_SELLING);
        });
        seeMostViewed.setOnClickListener(l -> {
            ListItemUtils.navigateToList(FilterField.FILTER_BY_VIEWS);
        });

    }


    private void setupCategories() {
        categories = new ArrayList<>();
        DataProvider dataProvider = App.getDataProvider();
        dataProvider.open();
        for (CategoryName categoryName : CategoryName.values()) {
            int categoryFrequency = dataProvider.getCategoryItemFrequency(categoryName);
            categories.add(new Category(categoryName.bannerId, categoryName, categoryFrequency));
        }
        dataProvider.close();

    }

    private void updateItems() {
        dataProvider.open();
        bestSellingItems = dataProvider.getBestSellingItems();
        mostViewedItems = dataProvider.getMostViewedItems();
        dataProvider.close();
        bestSellingAdapter.setItems(bestSellingItems);
        mostViewedAdapter.setItems(mostViewedItems);
    }

    private void setupCategoryRecyclerView() {
        setupCategories();
        RecyclerView recyclerView = findViewById(R.id.category_recycler_view);
        CategoryAdapter adapter = new CategoryAdapter(categories, ListItemUtils::navigateToList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setupBestSellingRecyclerView() {
        RecyclerView bestSellingRecyclerView = this.findViewById(R.id.best_selling_recycler_view);
        bestSellingItems = dataProvider.getBestSellingItems();
        bestSellingAdapter = new CompactListAdapter(bestSellingItems);
        bestSellingRecyclerView.setAdapter(bestSellingAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        bestSellingRecyclerView.setLayoutManager(productLayoutManager);
    }

    private void setupMostViewedRecyclerView() {
        RecyclerView mostViewedRecyclerView = this.findViewById(R.id.most_viewed_recycler_view);
        mostViewedItems = dataProvider.getMostViewedItems();
        mostViewedAdapter = new CompactListAdapter(mostViewedItems);
        mostViewedRecyclerView.setAdapter(mostViewedAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        mostViewedRecyclerView.setLayoutManager(productLayoutManager);
    }

    public void goToSearch(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        this.startActivity(intent);
    }
}