package com.application.project2java.adapters;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.project2java.constants.CategoryName;
import com.application.project2java.models.CategoryModel;
import com.example.project2java.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final List<CategoryModel> categories;

    private final OnSelectListener onSelectListener;

    public CategoryAdapter(List<CategoryModel> categories, OnSelectListener onSelectListener) {
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
        Animation animation = AnimationUtils.loadAnimation(holder.getCardView().getContext(), R.anim.slide_in_up);
        CategoryModel categoryModel = categories.get(position);
        String name = categoryModel.getDisplayName();
        String qty = String.valueOf(categoryModel.getFrequency());
        holder.textViewName.setText(name);
        holder.textViewQty.setText(qty + " Items");
        holder.cardView.setOnClickListener(l -> {
            onSelectListener.onSelect(categoryModel.getCategory());
        });
        holder.imageView.setImageResource(categoryModel.getImageId());
        holder.getCardView().startAnimation(animation);
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
        ShapeableImageView imageView;
        TextView textViewName;
        TextView textViewQty;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(v1 -> Log.d(TAG, "Element " + getAdapterPosition() + " clicked."));
            cardView = v.findViewById(R.id.category_list_item);
            textViewName = v.findViewById(R.id.category_name);
            textViewQty = v.findViewById(R.id.category_qty);
            imageView = v.findViewById(R.id.category_banner_image);

        }

        public CardView getCardView() {
            return cardView;
        }
    }
}
