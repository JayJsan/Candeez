package com.application.project2java;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;

import java.util.List;

public class FavouritesActivity extends FragmentActivity {
    DataProvider dataProvider;
    DataMutator dataMutator;
    List<ItemModel> favouriteItems;
    CompactListAdapter favouritesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);

        dataProvider = App.getDataProvider();
        dataMutator = App.getDataMutator();

        dataMutator.addDatabaseWriteListener(this::updateItems);
        dataProvider.open();
        setupFavouritesRecyclerView();
        dataProvider.close();
    }

    private void updateItems() {
        dataProvider.open();
        favouriteItems = dataProvider.getFavouriteItems();
        dataProvider.close();
        favouritesAdapter.setItems(favouriteItems);

    }

    private void setupFavouritesRecyclerView() {
        RecyclerView favouritesRecyclerView = this.findViewById(R.id.recycler_view_favourites);
        favouriteItems = dataProvider.getFavouriteItems();
        favouritesAdapter = new CompactListAdapter(favouriteItems);
        favouritesRecyclerView.setAdapter(favouritesAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        favouritesRecyclerView.setLayoutManager(productLayoutManager);
    }
}