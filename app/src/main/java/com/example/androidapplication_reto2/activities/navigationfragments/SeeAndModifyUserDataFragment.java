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
public class SeeAndModifyUserDataFragment extends Fragment {


    public SeeAndModifyUserDataFragment() {
        // Required empty public constructor
    }

    //ADD CREDIT CARD POP UP
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_see_and_modify_user_data, container, false);
        return root;
    }

}
