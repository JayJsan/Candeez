package com.application.project2java;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    DataProvider dataProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);

        dataProvider = new DataProvider(App.getAppContext());
        dataProvider.open();
        setupBestSellingRecyclerView();
        setupMostViewedRecyclerView();
        dataProvider.close();
    }

    private void setupBestSellingRecyclerView() {
        RecyclerView bestSellingRecyclerView = this.findViewById(R.id.best_selling_recycler_view);
        List<ItemModel> items = dataProvider.getBestSellingItems();
        CompactListAdapter compactListAdapter = new CompactListAdapter(items);
        bestSellingRecyclerView.setAdapter(compactListAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        bestSellingRecyclerView.setLayoutManager(productLayoutManager);
    }

    private void setupMostViewedRecyclerView(){
        RecyclerView mostViewedRecyclerView = this.findViewById(R.id.most_viewed_recycler_view);
        List<ItemModel> items = dataProvider.getMostViewedItems();
        CompactListAdapter compactListAdapter = new CompactListAdapter(items);
        mostViewedRecyclerView.setAdapter(compactListAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        mostViewedRecyclerView.setLayoutManager(productLayoutManager);
    }
}