package com.application.project2java;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);

        setupBestSellingRecyclerView();
        setupMostViewedRecyclerView();
    }

    private void setupBestSellingRecyclerView() {
        RecyclerView bestSellingRecyclerView = this.findViewById(R.id.best_selling_recycler_view);
        //TODO use real data
        CompactListAdapter compactListAdapter = new CompactListAdapter(new ArrayList<>());
        bestSellingRecyclerView.setAdapter(compactListAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        bestSellingRecyclerView.setLayoutManager(productLayoutManager);
    }

    private void setupMostViewedRecyclerView(){
        RecyclerView mostViewedRecyclerView = this.findViewById(R.id.most_viewed_recycler_view);
        //TODO use real data
        CompactListAdapter compactListAdapter = new CompactListAdapter(new ArrayList<>());
        mostViewedRecyclerView.setAdapter(compactListAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        mostViewedRecyclerView.setLayoutManager(productLayoutManager);
    }
}