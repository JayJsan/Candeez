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
        String name = item.getName();

        holder.textViewPrice.setText("$" + item.getPrice());
        holder.textViewName.setText(name);
        holder.textViewDescription.setText(item.getDescription());
        holder.itemName = name;
        holder.viewCount = item.getViewCount();

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
        int viewCount;
        String itemName;
        TextView textViewName;
        TextView textViewDescription;
        TextView textViewPrice;
        ShapeableImageView imageView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(v1 -> ListItemUtils.navigateToDetails(itemName, viewCount));
            textViewName = v.findViewById(R.id.product_name);
            textViewDescription = v.findViewById(R.id.product_description);
            textViewPrice = v.findViewById(R.id.product_price);
            imageView = v.findViewById(R.id.product_image);
        }

    }
}
