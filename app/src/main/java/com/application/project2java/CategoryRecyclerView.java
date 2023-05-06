package com.application.project2java;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2java.R;

import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryRecyclerView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryRecyclerView extends Fragment {

    protected CategoryAdapter adapter;
    protected RecyclerView recyclerView;
    private Category[] categories = {
            new Category("Category1", 69),
            new Category("Category2", 69),
            new Category("Category3", 69),
            new Category("Category4", 69),
    };
    public CategoryRecyclerView() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
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