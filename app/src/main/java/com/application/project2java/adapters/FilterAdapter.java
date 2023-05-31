package com.application.project2java.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.project2java.constants.CategoryName;
import com.application.project2java.utils.ResourceUtils;
import com.example.project2java.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    private final List<CategoryName> categories;
    private final List<CategoryName> presets;
    private final OnSelectListener onSelectListener;

    public FilterAdapter(List<CategoryName> categories, List<CategoryName> presets, OnSelectListener onSelectListener) {
        this.categories = categories;
        this.onSelectListener = onSelectListener;
        this.presets = presets;

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
        Animation animation = AnimationUtils.loadAnimation(holder.getFilterButton().getContext(), R.anim.slide_in_up);
        CategoryName category = categories.get(position);
        String name = category.toString();
        holder.categoryName = category;
        holder.getFilterButton().setText(name.replaceAll("_", " "));
        holder.isSelected = presets.contains(category);
        holder.updateButtonAppearance();
        holder.filterButton.setOnClickListener(v -> {
            holder.isSelected = !holder.isSelected;
            holder.updateButtonAppearance();
            onSelectListener.onSelect(holder.categoryName, holder.isSelected);
        });
        holder.getFilterButton().startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public interface OnSelectListener {
        void onSelect(CategoryName categoryName, boolean isSelected);
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

        public void updateButtonAppearance() {
            if (isSelected) {
                filterButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_primary));
                filterButton.setTextColor(ResourceUtils.getColorStateList(R.color.md_theme_light_onPrimary));
            } else {
                filterButton.setBackgroundTintList(ResourceUtils.getColorStateList(R.color.md_theme_light_primaryContainer));
                filterButton.setTextColor(ResourceUtils.getColorStateList(R.color.md_theme_light_onTertiaryContainer));
            }
        }

    }


}
