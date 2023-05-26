package com.application.project2java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project2java.R;

import java.util.List;

public class CartActivity extends FragmentActivity {

    private CartAdapter cartAdapter;
    private List<ItemModel> items;
    private DataProvider dataProvider;
    private DataMutator dataMutator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView cartRecyclerView = findViewById(R.id.cart_recycler_view);
        dataProvider = App.getDataProvider();
        dataProvider.open();
        items = dataProvider.getAllItems();
        dataProvider.close();
        cartAdapter = new CartAdapter(items);
        cartRecyclerView.setAdapter(cartAdapter);
        LinearLayoutManager cartLayoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(cartLayoutManager);


        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);
    }
}