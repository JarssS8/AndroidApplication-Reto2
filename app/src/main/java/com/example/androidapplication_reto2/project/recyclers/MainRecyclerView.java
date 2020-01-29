package com.example.androidapplication_reto2.project.recyclers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.activities.navigationfragments.DocumentDataFragment;
import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.beans.lists.DocumentList;
import com.example.androidapplication_reto2.project.beans.plural.Documents;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRecyclerView extends RecyclerView.Adapter<MainRecyclerView.MyHolder> {

    private ArrayList<Document> documents = new ArrayList<>();


    public MainRecyclerView(DocumentList documents) {
        this.documents.addAll(documents.getDocuments());
    }

    public MainRecyclerView(Set<Documents> documents) {
       // this.documents.addAll(documents);
    }

    @NonNull
    @Override
    public MainRecyclerView.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_document_cell, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerView.MyHolder holder, int position) {
        holder.auxDocument = documents.get(position);
        holder.lbDocName.setText(documents.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }


    public void deleteItem(int index) {
        documents.remove(index);
        notifyItemRemoved(index);
    }



    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView lbDocName;
        private ImageView btEdit, btRemove;
        private Document auxDocument;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            lbDocName = itemView.findViewById(R.id.lbDocName);
            btEdit = itemView.findViewById(R.id.imageButtonDocEdit);
            btRemove = itemView.findViewById(R.id.imageButtonDocRemove);

            btEdit.setOnClickListener(this);
            btEdit.setClickable(true);
            btRemove.setOnClickListener(this);
            btRemove.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                default:
                    DocumentDataFragment.setRatings(documents.get(getAdapterPosition()));
                    Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_data_document);
                    break;
            }


        }

    }
}
