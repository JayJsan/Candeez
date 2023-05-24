package com.application.project2java;

import android.content.Intent;
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

public class CompactListAdapter extends RecyclerView.Adapter<CompactListAdapter.ViewHolder> {

    private List<ItemModel> items;

    public CompactListAdapter(List<ItemModel> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DataMutator dataMutator = new DataMutator(App.getAppContext());
        TextView textViewName;
        TextView textViewPrice;
        ShapeableImageView imageView;
        MaterialButton buttonViewItem;
        MaterialButton buttonFavouriteItem;
        String itemName;
        boolean isFavourite;

        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.compact_item_image);
            textViewName = v.findViewById(R.id.compact_item_name);
            textViewPrice = v.findViewById(R.id.compact_item_price);
            v.setOnClickListener(v1 -> {
                ListItemUtils.navigateToDetails(itemName);
            });
            buttonViewItem = v.findViewById(R.id.compact_button_view_item);
            buttonViewItem.setOnClickListener(v1 -> {
                ListItemUtils.navigateToDetails(itemName);
            });

            buttonFavouriteItem = v.findViewById(R.id.compact_favourite_button);
            buttonFavouriteItem.setOnClickListener(v1 -> {
                // change favourites id to the opposite of current state
                // i.e. ---> isFavourite = 1 => isFavourite = 0
                //      ---> isFavourite = 0 => isFavourite = 1
                isFavourite = !isFavourite;
                dataMutator.open();
                dataMutator.updateItemFavouriteStatus(itemName, isFavourite);
                dataMutator.close();
                ListItemUtils.updateFavouriteButtonAppearance(isFavourite, buttonFavouriteItem);
            });
        }


    }

    @NonNull
    @Override
    public CompactListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.compact_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel item = items.get(position);
        String name = item.getName();
        boolean isFavourite = item.isFavourite();
        holder.textViewName.setText(name);
        holder.textViewPrice.setText("$" + item.getPrice());
        holder.itemName = name;
        holder.isFavourite = isFavourite;
        ListItemUtils.updateFavouriteButtonAppearance(isFavourite, holder.buttonFavouriteItem);

        Log.d("DEBUG", item.getImageUris().toString());
        ImageUtils.getImageBitmapAsync(item.getImageUris().get(0), new ImageUtils.BitmapCallback() {
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
}
