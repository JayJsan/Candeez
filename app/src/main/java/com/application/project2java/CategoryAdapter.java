package com.application.project2java;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private List<Category> categories;

    private void setupCategories() {
        DataProvider dataProvider = new DataProvider(App.getAppContext());
        dataProvider.open();
        for(CategoryName categoryName: CategoryName.values()){
            int categoryFrequency = dataProvider.getCategoryItemFrequency(categoryName);
            categories.add(new Category(categoryName, categoryFrequency));
        }

    }
    public CategoryAdapter(List<Category> categories){
        this.categories = categories;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        TextView textViewName;
        TextView textViewQty;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            cardView = (CardView) v.findViewById(R.id.category_list_item);
            textViewName = v.findViewById(R.id.category_name);
            textViewQty = v.findViewById(R.id.category_qty);
        }

        public CardView getCardView() {
            return cardView;
        }
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
        String name = category.getName();
        String qty = String.valueOf(category.getFrequency());
        holder.textViewName.setText(name);
        holder.textViewQty.setText(qty + " Items");
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
