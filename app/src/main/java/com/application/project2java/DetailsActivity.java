package com.application.project2java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.project2java.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class DetailsActivity extends FragmentActivity {
    private DataProvider dataProvider;
    private DataMutator dataMutator;
    private ItemModel item;
    private boolean isInCart;
    private ViewPager imageViewPager;
    private MaterialButton favouriteButton;
    private MaterialButton cartButton;
    private boolean isFavourite;
    private List<ItemModel> relatedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        dataProvider = App.getDataProvider();
        dataMutator = App.getDataMutator();
        fetchItemDetails();
        setDetails();
        setupFavouriteButton();
        setupCartButton();
        fetchRelatedItems();
        setupRelatedItemsRecyclerView();
        updateFavouriteButtonAppearance();
        updateCartButtonAppearance();
        imageViewPager = findViewById(R.id.image_view_pager);
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getSupportFragmentManager(), item.getImageUris());
        imageViewPager.setAdapter(imagePagerAdapter);

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
        isFavourite = item.isFavourite();
        isInCart = item.getCartQuantity() > 0;

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

    private void setupCartButton() {
        cartButton = findViewById(R.id.add_to_cart_button);
        cartButton.setOnClickListener(l -> {
            isInCart = !isInCart;
            dataMutator.open();
            if (isInCart) dataMutator.updateItemCartStatus(item.getName(), 1);
            else dataMutator.updateItemCartStatus(item.getName(), 0);
            dataMutator.close();
            updateCartButtonAppearance();
        });
    }

    private void setupFavouriteButton() {
        favouriteButton = findViewById(R.id.favourite_button);
        favouriteButton.setOnClickListener(l -> {
            isFavourite = !isFavourite;
            dataMutator.open();
            dataMutator.updateItemFavouriteStatus(item.getName(), isFavourite);
            dataMutator.close();
            updateFavouriteButtonAppearance();
        });
    }

    private void updateFavouriteButtonAppearance() {
        if (isFavourite) {
            favouriteButton.setIconResource(R.drawable.baseline_favorite_24);
            favouriteButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_primary));
            favouriteButton.setTextColor(ResourceUtils.getColorStateList(R.color.md_theme_light_onPrimary));
        } else {
            favouriteButton.setIconResource(R.drawable.baseline_favorite_border_24);
            favouriteButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_primaryContainer));
            favouriteButton.setTextColor(ResourceUtils.getColorStateList(R.color.md_theme_light_onTertiaryContainer));
        }
    }

    private void updateCartButtonAppearance() {
        if (isInCart) {
            cartButton.setIconResource(R.drawable.baseline_remove_shopping_cart_24);
            cartButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_tertiary));
            cartButton.setIconTintResource(R.color.md_theme_light_onPrimary);
        } else {
            cartButton.setIconResource(R.drawable.baseline_add_shopping_cart_24);
            cartButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_tertiaryContainer));
            cartButton.setIconTintResource(R.color.md_theme_light_tertiary);
        }
    }

    public void goBack(View v) {
        this.finish();
    }

}