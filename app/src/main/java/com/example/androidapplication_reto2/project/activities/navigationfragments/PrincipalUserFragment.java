package com.example.androidapplication_reto2.project.activities.navigationfragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.activities.LoginActivity;
import com.example.androidapplication_reto2.project.activities.MainFragmentsController;
import com.example.androidapplication_reto2.project.beans.lists.DocumentList;
import com.example.androidapplication_reto2.project.factories.DocumentFactory;
import com.example.androidapplication_reto2.project.interfaces.RestDocument;
import com.example.androidapplication_reto2.project.recyclers.MainRecyclerView;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass that is going to be the pricipal window of all my fragments.
 */
public class PrincipalUserFragment extends Fragment {

    private RecyclerView recyclerView;
    private MainRecyclerView mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    /**
     * Creation of the principal fragment, is going to be the first fragment showed when the user log in.
     * Contains a list with all documents in the application.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the view inflated for this fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_principal_user, container, false);
        setHasOptionsMenu(true);

        //Load all documents
        RestDocument restDocument = DocumentFactory.getClient();
        Call<DocumentList> documentsUser = restDocument.findAllDocuments();
        documentsUser.enqueue(new Callback<DocumentList>() {
            @Override
            public void onResponse(Call<DocumentList> call, Response<DocumentList> response) {
                Log.d("PRINCIPAL", "YUJUUU");
                switch (response.code()) {
                    case 200:
                        if (response.isSuccessful()) {
                            layoutManager = new LinearLayoutManager(getContext());
                            recyclerView = root.findViewById(R.id.recyclerViewDocumentsUser);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(layoutManager);
                            mAdapter = new MainRecyclerView(response.body());
                            recyclerView.setAdapter(mAdapter);
                        }
                        break;
                    case 404:
                        Snackbar.make(getView(), getString(R.string.doc_not_found), Snackbar.LENGTH_SHORT).show();
                        break;
                    default:
                        Snackbar.make(getView(), getString(R.string.server_error), Snackbar.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<DocumentList> call, Throwable t) {
                Log.d("PRINCIPAL", "NOPE");
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getString(R.string.client_error)).show();
            }
        });

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
        inflater.inflate(R.menu.log_out, menu);
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
            case R.id.btLogOut:
                logOutMessage();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Alert dialog when the user clicks on log out button
     */
    public void logOutMessage() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        MediaPlayer.create(getContext(), R.raw.byebye).start();
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.logout)).setPositiveButton(getString(R.string.yes), dialogClickListener)
                .setNegativeButton(getString(R.string.no), dialogClickListener).show();
    }

}
