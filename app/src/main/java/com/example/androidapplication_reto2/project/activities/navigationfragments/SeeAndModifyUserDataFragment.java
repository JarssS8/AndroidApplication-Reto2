package com.example.androidapplication_reto2.project.activities.navigationfragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.activities.MainFragmentsController;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeeAndModifyUserDataFragment extends Fragment {

    private TextView lbLogin, lbFullName, lbPrivilege, lbEmail;

    //Todo ADD CREDIT CARD POP UP
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_see_and_modify_user_data, container, false);

        setHasOptionsMenu(true);

        lbLogin = root.findViewById(R.id.lbUserNameUserData2);
        lbFullName = root.findViewById(R.id.lbFullNameUserData2);
        lbEmail = root.findViewById(R.id.lbEmailUserData2);
        lbPrivilege = root.findViewById(R.id.lbPrivilegeUserData2);



        return root;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.user_data_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btEditUserData:
                Toast.makeText(getContext(), "Hey", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
