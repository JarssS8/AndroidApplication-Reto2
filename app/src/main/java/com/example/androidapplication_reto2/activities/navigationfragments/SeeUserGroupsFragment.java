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
public class SeeUserGroupsFragment extends Fragment {


    public SeeUserGroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_see_user_groups, container, false);
    }

}
