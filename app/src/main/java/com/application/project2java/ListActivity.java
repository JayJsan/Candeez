package com.application.project2java;

import android.os.Bundle;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);
        RecyclerView filterRecyclerView = this.findViewById(R.id.filter_recycler_view);
        filterAdapter = new FilterAdapter(categories);
        filterRecyclerView.setAdapter(filterAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        filterRecyclerView.setLayoutManager(layoutManager);

    }
}