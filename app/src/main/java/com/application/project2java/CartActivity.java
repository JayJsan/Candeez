package com.application.project2java;

import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends FragmentActivity {

    private CartAdapter cartAdapter;
    private List<ItemModel> items;
    private DataProvider dataProvider;
    private DataMutator dataMutator;

    private TextView totalPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        totalPriceText = findViewById(R.id.total_price_text);
        dataProvider = App.getDataProvider();
        dataMutator = App.getDataMutator();
        dataMutator.addDatabaseWriteListener(this::updateData);
        setupCartRecyclerView();

        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);
    }

    private void updateData() {
        dataProvider.open();
        items = dataProvider.getCartItems();
        cartAdapter.setItems(items);
        dataProvider.close();
        totalPriceText.setText("Total: " + ListItemUtils.calculateTotal(items));

    }

    private void setupCartRecyclerView() {
        RecyclerView cartRecyclerView = findViewById(R.id.cart_recycler_view);
        cartAdapter = new CartAdapter(new ArrayList<>());
        updateData();
        cartRecyclerView.setAdapter(cartAdapter);
        LinearLayoutManager cartLayoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(cartLayoutManager);
    }
}