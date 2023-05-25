package com.application.project2java;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    private final List<CategoryName> categories;
    private final OnSelectListener onSelectListener;

    public FilterAdapter(List<CategoryName> categories, OnSelectListener onSelectListener) {
        this.categories = categories;
        this.onSelectListener = onSelectListener;

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
        holder.filterButton.setOnClickListener(v -> {
            holder.isSelected = !holder.isSelected;
            if (holder.isSelected) {
                holder.filterButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_primary));
                holder.filterButton.setTextColor(ResourceUtils.getColorStateList(R.color.md_theme_light_onPrimary));
            } else {
                holder.filterButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_primaryContainer));
                holder.filterButton.setTextColor(ResourceUtils.getColorStateList(R.color.md_theme_light_onTertiaryContainer));
            }
            onSelectListener.onSelect(holder.categoryName, holder.isSelected);
        });

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
        }

        public MaterialButton getFilterButton() {
            return filterButton;
        }
    }

    public static interface OnSelectListener {
        void onSelect(CategoryName categoryName, boolean isSelected);
    }


}
