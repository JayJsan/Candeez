package com.application.project2java;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;

import java.util.List;

public class MainActivity extends FragmentActivity {

    DataProvider dataProvider;
    DataMutator dataMutator;
    CompactListAdapter bestSellingAdapter;
    CompactListAdapter mostViewedAdapter;
    List<ItemModel> bestSellingItems;
    List<ItemModel> mostViewedItems;

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
}