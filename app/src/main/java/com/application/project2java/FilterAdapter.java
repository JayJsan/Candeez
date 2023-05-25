package com.application.project2java;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    private final List<CategoryName> categories;

    public FilterAdapter(List<CategoryName> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_filter_button, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryName category = categories.get(position);
        String name = category.toString();
        holder.categoryName = category;
        holder.getFilterButton().setText(name);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialButton filterButton;
        CategoryName categoryName;

        boolean isSelected;

        public ViewHolder(View v) {
            super(v);
            filterButton = v.findViewById(R.id.filter_button);
            v.setOnClickListener(v1 -> {
                isSelected = !isSelected;
                if (isSelected){
                    filterButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_primary));
                    filterButton.setTextColor(ResourceUtils.getColorStateList(R.color.md_theme_light_onPrimary));
                } else {
                    filterButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_primaryContainer));
                    filterButton.setTextColor(ResourceUtils.getColorStateList(R.color.md_theme_light_onTertiaryContainer));
                }
            });
        }

        public MaterialButton getFilterButton() {
            return filterButton;
        }
    }


}
