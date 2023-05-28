package com.application.project2java;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private List<ItemModel> items;

    public ProductListAdapter(List<ItemModel> items) {
        this.items = items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        ItemModel item = items.get(position);
        holder.item = item;
        holder.setup();
        holder.updateFavouriteButton();
        holder.updateCartButton();


        ResourceUtils.getImageBitmapAsync(item.getImageUris().get(0), new ResourceUtils.BitmapCallback() {

            @Override
            public void onBitmapLoaded(Bitmap bitmap) {
                holder.imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e) {
                holder.imageView.setImageResource(R.drawable.baseline_image_not_supported_24);
                DrawableCompat.setTint(
                        DrawableCompat.wrap(holder.imageView.getDrawable()),
                        ContextCompat.getColor(App.getAppContext(), R.color.md_theme_light_primaryContainer)
                );
                Log.d("DEBUG", e.getLocalizedMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemModel item;
        private int viewCount;
        private String itemName;
        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewPrice;
        private TextView textViewCategory;
        private TextView textViewViews;
        private ShapeableImageView imageView;
        private MaterialButton buttonFavouriteItem;
        private MaterialButton buttonAddCart;
        private boolean isFavourite;
        private boolean isInCart;
        private DataMutator dataMutator;

        public ViewHolder(View v) {
            super(v);
            dataMutator = App.getDataMutator();
            v.setOnClickListener(v1 -> goToItemDetails());
            textViewName = v.findViewById(R.id.product_name);
            textViewDescription = v.findViewById(R.id.product_description);
            textViewPrice = v.findViewById(R.id.product_price);
            imageView = v.findViewById(R.id.product_image);
            textViewCategory = v.findViewById(R.id.product_category);
            textViewViews = v.findViewById(R.id.product_views);

            MaterialButton buttonItemNavigation = v.findViewById(R.id.button_see_item);
            buttonItemNavigation.setOnClickListener(v1 -> goToItemDetails());

            buttonFavouriteItem = v.findViewById(R.id.button_add_to_favourites);
            buttonFavouriteItem.setOnClickListener(v1 -> {
                // change favourites id to the opposite of current state
                // i.e. ---> isFavourite = 1 => isFavourite = 0
                //      ---> isFavourite = 0 => isFavourite = 1
                dataMutator.open();
                dataMutator.updateItemFavouriteStatus(itemName, !isFavourite);
                dataMutator.close();
            });
            buttonAddCart = v.findViewById(R.id.button_add_to_cart);
            buttonAddCart.setOnClickListener(v1 -> {
                isInCart = !isInCart;
                dataMutator.open();
                ListItemUtils.updateCartStatus(isInCart, itemName);
                dataMutator.close();
            });
        }

        public void setup() {

            String name = item.getName();

            textViewPrice.setText("$" + item.getPrice());
            textViewName.setText(name);
            textViewDescription.setText(item.getDescription());
            itemName = name;
            viewCount = item.getViewCount();
            textViewCategory.setText(item.getCategory(true));
            textViewViews.setText(item.getViewCount() + " Views");
            isFavourite = item.isFavourite();
            isInCart = item.getCartQuantity() > 0;
        }

        private void goToItemDetails() {
            ListItemUtils.navigateToDetails(itemName, viewCount);
        }

        public void updateFavouriteButton() {
            ListItemUtils.updateFavouriteButtonAppearance(isFavourite, buttonFavouriteItem);
        }

        public void updateCartButton() {
            ListItemUtils.updateCartButtonAppearance(isInCart, buttonAddCart);
        }

    }
}
