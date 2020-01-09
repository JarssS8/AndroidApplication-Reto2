package com.example.androidapplication_reto2.project.activities.navigationfragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapplication_reto2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalUserFragment extends Fragment implements View.OnClickListener{

    private FloatingActionButton floatingAddDocument;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_principal_user, container, false);

        floatingAddDocument=root.findViewById(R.id.floatingActionButton);

        floatingAddDocument.setOnClickListener(this);

        return root;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingActionButton:
                Toast.makeText(getContext(), "Bobo haz el pop up boooobooooo", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
