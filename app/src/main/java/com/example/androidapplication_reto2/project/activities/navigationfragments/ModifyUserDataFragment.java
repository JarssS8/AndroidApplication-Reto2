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
import android.widget.EditText;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.beans.User;
import com.example.androidapplication_reto2.project.retrofitcalls.UserCalls;

public class ModifyUserDataFragment extends Fragment {

    private User user;
    private EditText txtFullName, txtPassword, txtRepeatPassword, txtEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modify_user_data, container, false);

        setHasOptionsMenu(true);

        UserCalls userCalls = new UserCalls();

        txtFullName = root.findViewById(R.id.txtFullNameModifyUserData);
        txtPassword = root.findViewById(R.id.txtPasswordModifyUserData);
        txtRepeatPassword = root.findViewById(R.id.txtDocumentNameEditDocument);
        txtEmail = root.findViewById(R.id.txtEmailModifyUserData);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btSave:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
