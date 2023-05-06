package com.application.project2java;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2java.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryRecyclerView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryRecyclerView extends Fragment {

    protected CategoryAdapter adapter;
    protected RecyclerView recyclerView;

    public CategoryRecyclerView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryRecyclerView.
     */
    // TODO: Rename and change types and number of parameters
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
        adapter = new CategoryAdapter();
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        return rootView;

    }
}