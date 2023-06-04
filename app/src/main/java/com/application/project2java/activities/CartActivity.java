package com.application.project2java.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.project2java.App;
import com.application.project2java.adapters.CartAdapter;
import com.application.project2java.database.DataMutator;
import com.application.project2java.database.DataProvider;
import com.application.project2java.models.ItemModel;
import com.application.project2java.utils.BottomNavigationUtils;
import com.application.project2java.utils.ListItemUtils;
import com.example.project2java.R;

import java.util.ArrayList;
import java.util.List;

/**
 * CartActivity displays the list of items that have been added to a cart.
 */
public class CartActivity extends FragmentActivity {
    private RecyclerView cartRecyclerView;
    private View noCartResults;
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
        updateViews();

        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);
    }

    private void updateData() {
        dataProvider.open();
        items = dataProvider.getCartItems();
        cartAdapter.setItems(items);
        dataProvider.close();
        updateViews();
        totalPriceText.setText("Total: " + ListItemUtils.calculateTotal(items));

    }

    private void updateViews() {
        if (items.isEmpty()) {
            noCartResults.setVisibility(View.VISIBLE);
            cartRecyclerView.setVisibility(View.GONE);
        } else {
            noCartResults.setVisibility(View.GONE);
            cartRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void setupCartRecyclerView() {
        noCartResults = findViewById(R.id.no_cart_results);
        cartRecyclerView = findViewById(R.id.cart_recycler_view);
        cartAdapter = new CartAdapter(new ArrayList<>());
        updateData();
        cartRecyclerView.setAdapter(cartAdapter);
        LinearLayoutManager cartLayoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(cartLayoutManager);
    }
}