package com.application.project2java;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2java.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryRecyclerView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryRecyclerView extends Fragment {

    protected CategoryAdapter adapter;
    protected RecyclerView recyclerView;
    private List<Category> categories = new ArrayList<>();

    private void setupCategories() {
        DataProvider dataProvider = new DataProvider(App.getAppContext());
        dataProvider.open();
        for(CategoryName categoryName: CategoryName.values()){
            int categoryFrequency = dataProvider.getCategoryItemFrequency(categoryName);
            categories.add(new Category("", categoryName, categoryFrequency));
        }

    }


    public static CategoryRecyclerView newInstance(String param1, String param2) {
        CategoryRecyclerView fragment = new CategoryRecyclerView();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setupCategories();
        View rootView = inflater.inflate(R.layout.fragment_category_recycler_view, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.category_recycler_view);
        adapter = new CategoryAdapter(categories);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        return rootView;

    }
}