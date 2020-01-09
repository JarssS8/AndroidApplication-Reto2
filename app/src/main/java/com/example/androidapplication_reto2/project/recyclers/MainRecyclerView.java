package com.example.androidapplication_reto2.project.recyclers;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainRecyclerView extends RecyclerView.Adapter<MainRecyclerView.MyHolder> {

    @NonNull
    @Override
    public MainRecyclerView.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerView.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
