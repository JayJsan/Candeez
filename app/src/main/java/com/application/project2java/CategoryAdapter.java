package com.application.project2java;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final List<Category> categories;

    private final OnSelectListener onSelectListener;

    public CategoryAdapter(List<Category> categories, OnSelectListener onSelectListener) {
        this.categories = categories;
        this.onSelectListener = onSelectListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);
        String name = category.getDisplayName();
        String qty = String.valueOf(category.getFrequency());
        holder.textViewName.setText(name);
        holder.textViewQty.setText(qty + " Items");
        holder.cardView.setOnClickListener(l -> {
            onSelectListener.onSelect(category.getCategory());
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public interface OnSelectListener {
        void onSelect(CategoryName categoryName);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        TextView textViewName;
        TextView textViewQty;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(v1 -> Log.d(TAG, "Element " + getAdapterPosition() + " clicked."));
            cardView = v.findViewById(R.id.category_list_item);
            textViewName = v.findViewById(R.id.category_name);
            textViewQty = v.findViewById(R.id.category_qty);

        }

        public CardView getCardView() {
            return cardView;
        }
    }
}
