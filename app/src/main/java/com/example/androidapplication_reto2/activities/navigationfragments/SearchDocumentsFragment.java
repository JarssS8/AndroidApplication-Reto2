package com.example.androidapplication_reto2.activities.navigationfragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidapplication_reto2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDocumentsFragment extends Fragment {


    public SearchDocumentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_documents, container, false);
    }

}
