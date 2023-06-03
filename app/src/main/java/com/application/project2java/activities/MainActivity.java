package com.application.project2java.activities;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.project2java.App;
import com.application.project2java.adapters.CategoryAdapter;
import com.application.project2java.adapters.CompactListAdapter;
import com.application.project2java.constants.CategoryName;
import com.application.project2java.constants.FilterField;
import com.application.project2java.database.DataMutator;
import com.application.project2java.database.DataProvider;
import com.application.project2java.models.CategoryModel;
import com.application.project2java.models.ItemModel;
import com.application.project2java.utils.BottomNavigationUtils;
import com.application.project2java.utils.ListItemUtils;
import com.example.project2java.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity is the main homepage of Candeez. It displays the categories, best selling items,
 * most viewed items, and a search bar.
 */
public class MainActivity extends FragmentActivity {

    private final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    DataProvider dataProvider;
    DataMutator dataMutator;
    CompactListAdapter bestSellingAdapter;
    CompactListAdapter mostViewedAdapter;
    List<ItemModel> bestSellingItems;
    List<ItemModel> mostViewedItems;
    List<CategoryModel> categories;
    private int width;
    private boolean isGrid = false;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);
        getScreenSize();
        Log.d("DEBUG", width + "");
        gridLayoutManager = new GridLayoutManager(this, width / 170);
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

    //https://stackoverflow.com/questions/1016896/how-to-get-screen-dimensions-as-pixels-in-android
    private void getScreenSize() {
        float density = App.getAppContext().getResources().getDisplayMetrics().density;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = (int) ((int) size.x / density);
    }

    private void setupButtons() {
        MaterialButton seeAllCategories = findViewById(R.id.see_all_categories_button);
        MaterialButton seeBestSelling = findViewById(R.id.see_all_best_selling_button);
        MaterialButton seeMostViewed = findViewById(R.id.see_all_most_viewed_button);
        MaterialButton changeLayout = findViewById(R.id.change_layout_button);
        seeAllCategories.setOnClickListener(l -> {
            ListItemUtils.navigateToList((CategoryName) null);
        });
        seeBestSelling.setOnClickListener(l -> {
            ListItemUtils.navigateToList(FilterField.FILTER_BEST_SELLING);
        });
        seeMostViewed.setOnClickListener(l -> {
            ListItemUtils.navigateToList(FilterField.FILTER_BY_VIEWS);
        });
        changeLayout.setOnClickListener(l -> {
            if (isGrid) {
                changeLayout.setIconResource(R.drawable.baseline_grid_view_24);
                setCategoriesToCompact();
            } else {
                changeLayout.setIconResource(R.drawable.baseline_view_column_24);
                setCategoriesToGrid();
            }
            isGrid = !isGrid;

        });

    }


    private void setupCategories() {
        categories = new ArrayList<>();
        DataProvider dataProvider = App.getDataProvider();
        dataProvider.open();
        for (CategoryName categoryName : CategoryName.values()) {
            int categoryFrequency = dataProvider.getCategoryItemFrequency(categoryName);
            categories.add(new CategoryModel(categoryName.bannerId, categoryName, categoryFrequency));
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
        categoryRecyclerView = findViewById(R.id.category_recycler_view);
        CategoryAdapter adapter = new CategoryAdapter(categories, ListItemUtils::navigateToList);
        categoryRecyclerView.setAdapter(adapter);
        setCategoriesToCompact();
    }

    private void setCategoriesToCompact() {
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setCategoriesToGrid() {
        categoryRecyclerView.setLayoutManager(gridLayoutManager);
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
        intent.putExtra("wants_search", Byte.MAX_VALUE);
        this.startActivity(intent);
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}