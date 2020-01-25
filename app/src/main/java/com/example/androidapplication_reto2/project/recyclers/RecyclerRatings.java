package com.example.androidapplication_reto2.project.recyclers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.beans.Rating;
import com.example.androidapplication_reto2.project.beans.plural.Ratings;

import java.util.ArrayList;
import java.util.Set;


public class RecyclerRatings extends RecyclerView.Adapter<RecyclerRatings.MyHolder> {
    private ArrayList<Ratings> ratingsThisDocument = new ArrayList<>();

    public RecyclerRatings(Set<Ratings> ratings){
        ratingsThisDocument.addAll(ratings);
    }

    @NonNull
    @Override
    public RecyclerRatings.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_rating_cell, parent, false);
        return new RecyclerRatings.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerRatings.MyHolder holder, int position) {
        holder.actualRating = ratingsThisDocument.get(position);
        holder.docName.setText(ratingsThisDocument.get(position).getReview());
        holder.docRate.setText(ratingsThisDocument.get(position).getRating()+"");
    }

    @Override
    public int getItemCount() {
        return ratingsThisDocument.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView docName,docRate;
        private Ratings actualRating;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            docName=itemView.findViewById(R.id.lbCommentRatingCell);
            docRate=itemView.findViewById(R.id.lbRateRatingCell);
        }
    }
}
