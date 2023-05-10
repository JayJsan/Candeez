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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private Category[] categories;

    public CategoryAdapter(Category[] categories){
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
        Category category = categories[position];
        String name = category.getName();
        String qty = String.valueOf(category.getFrequency());
        holder.textViewName.setText(name);
        holder.textViewQty.setText(qty + " Items");
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }
}
