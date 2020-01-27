package com.example.androidapplication_reto2.project.activities.navigationfragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.beans.lists.DocumentList;
import com.example.androidapplication_reto2.project.factories.DocumentFactory;
import com.example.androidapplication_reto2.project.interfaces.RestDocument;
import com.example.androidapplication_reto2.project.recyclers.MainRecyclerView;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindDocumentsFragment extends Fragment {

    private View root;
    private RecyclerView recyclerView;
    private MainRecyclerView mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static DocumentList documentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_find_documents, container, false);

        layoutManager=new LinearLayoutManager(getContext());
        recyclerView= root.findViewById(R.id.recyclerViewDocumentsUser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        //mAdapter=new MainRecyclerView(response.body());
        recyclerView.setAdapter(mAdapter);

        return root;
    }

    public static void setDocumentList(DocumentList documentList) {
        FindDocumentsFragment.documentList = documentList;
    }
}
