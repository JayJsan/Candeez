package com.application.project2java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.project2java.R;

import java.util.Locale;

public class DetailsActivity extends FragmentActivity {
    DataProvider dataProvider;
    ItemModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        fetchItemDetails();
        setDetails();
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);
    }

    private void fetchItemDetails() {
        dataProvider = new DataProvider(App.getAppContext());
        dataProvider.open();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        item = dataProvider.getItemWithName(name);
        dataProvider.close();

    }

    private void setDetails() {
        if (item == null) return;
        TextView heading = this.findViewById(R.id.item_title);
        TextView details = this.findViewById(R.id.item_description);
        TextView price = this.findViewById(R.id.item_price);
        heading.setText(item.getName());
        details.setText(item.getDescription());
        price.setText("$" + item.getPrice());
    }

    public void goBack(View v) {
        this.finish();
    }

}