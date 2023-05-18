package com.application.project2java;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2java.R;

import java.util.List;

public class CompactListAdapter extends RecyclerView.Adapter<CompactListAdapter.ViewHolder> {

    private List<ItemModel> items;

    public CompactListAdapter(List<ItemModel> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View v) {
            super(v);
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
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
