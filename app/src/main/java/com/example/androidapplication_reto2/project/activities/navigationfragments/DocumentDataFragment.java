package com.example.androidapplication_reto2.project.activities.navigationfragments;


import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.activities.LoginActivity;
import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.factories.DocumentFactory;
import com.example.androidapplication_reto2.project.factories.UserFactory;
import com.example.androidapplication_reto2.project.interfaces.RestDocument;
import com.example.androidapplication_reto2.project.interfaces.RestUser;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentDataFragment extends Fragment{

    private View root;
    private static Document documentData;
    private TextView docName, docData;

    /**
     * Isolated fragment that is going to show the data of one document and if the user is admin, lets him delete the document
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the view inflated for this fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_document_data, container, false);

        docName = root.findViewById(R.id.lbDocNameDocumentData);
        docData = root.findViewById(R.id.lbDocumentData);

        String login = LoginActivity.getLogin();
        RestUser restUser = UserFactory.getClientText();
        Call<String> callPrivilege = restUser.findPrivilegeOfUserByLogin(login);
        callPrivilege.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()) {
                    case 200:
                        Log.d("PRIVILEGE",response.body());
                        if (response.body().equalsIgnoreCase("admin")) {
                            setHasOptionsMenu(true);
                        }
                        break;
                    case 404:
                        Snackbar.make(getView(), getString(R.string.email_not_found), Snackbar.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getString(R.string.client_error)).show();
            }
        });

        //Document data
        docName.setText(documentData.getName());
        docData.setText("Author: Gaizka"+"\nCategory: "+documentData.getCategory().getName()+"\nUpload date: "+documentData.getUploadDate());

        return root;
    }

    /**
     * Creates the menu selected on the App Bar Main
     *
     * @param menu     items that want be created on AppBarMenu
     * @param inflater component for inflate the menu
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        RestUser restUser = UserFactory.getClient();

        inflater.inflate(R.menu.delete_document,menu);
    }

    /**
     * Select items from my menu
     *
             * @param item the item that user is interacting with
     * @return true if the item was selected
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btDeleteDoc:
                RestDocument restDocument = DocumentFactory.getClient();
                Call<Void> callRemoveDoc = restDocument.deleteDocument(documentData.getId());
                callRemoveDoc.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        switch (response.code()) {
                            case 204:
                                Snackbar.make(getView(), getString(R.string.delete_doc), Snackbar.LENGTH_SHORT).show();
                                Navigation.findNavController(getView()).navigate(R.id.action_nav_data_document_to_nav_home);
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Document getDocumentData() {
        return documentData;
    }

    public static void setDocumentData(Document documentData) {
        DocumentDataFragment.documentData = documentData;
    }
}
