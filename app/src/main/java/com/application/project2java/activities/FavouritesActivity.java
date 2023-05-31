package com.application.project2java.activities;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.project2java.App;
import com.application.project2java.adapters.CompactListAdapter;
import com.application.project2java.database.DataMutator;
import com.application.project2java.database.DataProvider;
import com.application.project2java.models.ItemModel;
import com.application.project2java.utils.BottomNavigationUtils;
import com.example.project2java.R;

import java.util.List;

public class FavouritesActivity extends FragmentActivity {
    DataProvider dataProvider;
    DataMutator dataMutator;
    List<ItemModel> favouriteItems;
    CompactListAdapter favouritesAdapter;
    private RecyclerView favouritesRecyclerView;
    private View noFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);
        setup();
        updateViews();
    }

    private void setup() {
        noFavourites = findViewById(R.id.no_favourites);

        dataProvider = App.getDataProvider();
        dataMutator = App.getDataMutator();

        dataMutator.addDatabaseWriteListener(this::updateItems);
        dataProvider.open();
        setupFavouritesRecyclerView();
        dataProvider.close();
    }

    private void updateViews() {
        if (favouriteItems.isEmpty()) {
            noFavourites.setVisibility(View.VISIBLE);
            favouritesRecyclerView.setVisibility(View.GONE);
        } else {
            noFavourites.setVisibility(View.GONE);
            favouritesRecyclerView.setVisibility(View.VISIBLE);
        }
    }


    private void updateItems() {
        dataProvider.open();
        favouriteItems = dataProvider.getFavouriteItems();
        dataProvider.close();
        updateViews();

        favouritesAdapter.setItems(favouriteItems);
    }

    private void setupFavouritesRecyclerView() {
        favouritesRecyclerView = this.findViewById(R.id.recycler_view_favourites);
        favouriteItems = dataProvider.getFavouriteItems();
        favouritesAdapter = new CompactListAdapter(favouriteItems);
        favouritesRecyclerView.setAdapter(favouritesAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        favouritesRecyclerView.setLayoutManager(productLayoutManager);
    }
}