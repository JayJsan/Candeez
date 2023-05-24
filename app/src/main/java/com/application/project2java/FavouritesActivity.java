package com.application.project2java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project2java.R;

import java.util.List;

public class FavouritesActivity extends FragmentActivity {
    DataProvider dataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);

        dataProvider = App.getDataProvider();
        dataProvider.open();
        setupFavouritesRecyclerView();
        dataProvider.close();
    }

    private void setupFavouritesRecyclerView() {
        RecyclerView favouritesRecyclerView = this.findViewById(R.id.recycler_view_favourites);
        List<ItemModel> items = dataProvider.getFavouriteItems();
        CompactListAdapter compactListAdapter = new CompactListAdapter(items);
        favouritesRecyclerView.setAdapter(compactListAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        favouritesRecyclerView.setLayoutManager(productLayoutManager);
    }
}