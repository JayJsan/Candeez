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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<ItemModel> items;

    public CartAdapter(List<ItemModel> items) {
        this.items = items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        ItemModel item = items.get(position);
        String name = item.getName();

        holder.textViewPrice.setText("$" + item.getPrice());
        holder.textViewName.setText(name);
        holder.itemName = name;

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
        MaterialButton buttonRemoveItem;
        String itemName;
        int quantity;
        TextView textViewName;
        TextView textViewPrice;
        ShapeableImageView imageView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(v1 -> ListItemUtils.navigateToDetails(itemName));
            textViewName = v.findViewById(R.id.product_name);
            textViewPrice = v.findViewById(R.id.product_price);
            imageView = v.findViewById(R.id.image_cart_item);
            buttonRemoveItem = v.findViewById(R.id.button_remove_item);

            buttonRemoveItem.setOnClickListener(v1 -> {
                DataMutator dataMutator = App.getDataMutator();
                dataMutator.open();
                dataMutator.updateItemCartStatus(itemName, 0);
                dataMutator.close();
            });
        }

    }
}
