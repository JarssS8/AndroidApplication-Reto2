package com.example.androidapplication_reto2.project.recyclers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.util.Log;
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
import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.beans.lists.DocumentList;

import java.util.ArrayList;
import java.util.Set;

public class MainRecyclerView extends RecyclerView.Adapter<MainRecyclerView.MyHolder> {

    private ArrayList<Document> documents= new ArrayList<>();

    public MainRecyclerView (DocumentList documents){
        this.documents.addAll(documents.getDocuments());
    }

    @NonNull
    @Override
    public MainRecyclerView.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_document_cell,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerView.MyHolder holder, int position) {
        holder.lbDocName.setText(documents.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView lbDocName;
        private ImageView btEdit, btRemove;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            lbDocName = itemView.findViewById(R.id.lbDocName);
            btEdit = itemView.findViewById(R.id.imageButtonDocEdit);
            btRemove = itemView.findViewById(R.id.imageButtonDocRemove);

            btEdit.setOnClickListener(this);
            btEdit.setClickable(true);
            btRemove.setOnClickListener(this);
            btRemove.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_data_document);
                }
            });
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageButtonDocEdit:
                    Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_edit_document);
                    break;
                case R.id.imageButtonDocRemove:
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder();
                    builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                    break;
            }
        }
    }
}
