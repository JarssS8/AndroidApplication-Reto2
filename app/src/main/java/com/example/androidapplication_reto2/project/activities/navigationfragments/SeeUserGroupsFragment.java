package com.example.androidapplication_reto2.project.activities.navigationfragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.beans.Category;
import com.example.androidapplication_reto2.project.beans.plural.Groups;
import com.example.androidapplication_reto2.project.recyclers.MainRecyclerView;

import java.util.ArrayList;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeeUserGroupsFragment extends Fragment {

    private static Set<Groups> groups;
    private View root;
    private Spinner spinnerUserGroups;
    private TextView groupData;
    Context mContext;

    private RecyclerView recyclerView;
    private MainRecyclerView mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_see_user_groups, container, false);
        mContext=getContext();


        //spinner
        spinnerUserGroups = root.findViewById(R.id.spinnerUserGroups);
        ArrayList<String> groupsNames = new ArrayList<String>();
        groupsNames.add("Seleccionar un grupo: ");
        for (Groups auxGroup : groups) {
            groupsNames.add(auxGroup.getName());
        }

        ArrayAdapter<String> groupsNameAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, groupsNames){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        groupsNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUserGroups.setAdapter(groupsNameAdapter);

        spinnerUserGroups.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    layoutManager=new LinearLayoutManager(getContext());
                    recyclerView= root.findViewById(R.id.recyclerViewDocumentsUser);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);
                    mAdapter=new MainRecyclerView(groups.stream().filter((group) -> group.getName().equalsIgnoreCase(selectedItemText)).findFirst().get().getDocuments());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }


    public static void setGroups(Set<Groups> groups) {
        SeeUserGroupsFragment.groups = groups;
    }
}
