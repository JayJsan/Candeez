package com.application.project2java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;

import java.util.List;

public class DetailsActivity extends FragmentActivity {
    DataProvider dataProvider;
    ItemModel item;
    List<ItemModel> relatedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        dataProvider = App.getDataProvider();
        fetchItemDetails();
        setDetails();
        fetchRelatedItems();
        setupRelatedItemsRecyclerView();
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);
    }

    private void setupRelatedItemsRecyclerView() {
        RecyclerView relatedItemsRecyclerView = findViewById(R.id.related_items_recycler_view);
        ProductListAdapter relatedItemsAdapter = new ProductListAdapter(relatedItems);
        relatedItemsRecyclerView.setAdapter(relatedItemsAdapter);
        LinearLayoutManager productLayoutManager = new LinearLayoutManager(this);
        relatedItemsRecyclerView.setLayoutManager(productLayoutManager);
    }

    private void fetchItemDetails() {
        dataProvider.open();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        item = dataProvider.getItemWithName(name);
        dataProvider.close();

    }

    private void fetchRelatedItems() {
        dataProvider.open();
        relatedItems = dataProvider.getRelatedItems(item.getName(), CategoryName.valueOf(item.getCategory()));
        dataProvider.close();
    }

    private void setDetails() {
        if (item == null) return;
        TextView heading = this.findViewById(R.id.product_header);
        TextView title = this.findViewById(R.id.item_title);
        TextView details = this.findViewById(R.id.item_description);
        TextView price = this.findViewById(R.id.item_price);
        heading.setText(item.getName());
        title.setText(item.getName());
        details.setText(item.getDescription());
        price.setText("$" + item.getPrice());
    }

    public void goBack(View v) {
        this.finish();
    }

}