package com.example.androidapplication_reto2.project.activities.navigationfragments;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.activities.LoginActivity;
import com.example.androidapplication_reto2.project.activities.MainFragmentsController;
import com.example.androidapplication_reto2.project.beans.Category;
import com.example.androidapplication_reto2.project.beans.User;
import com.example.androidapplication_reto2.project.beans.lists.CategoryList;
import com.example.androidapplication_reto2.project.beans.lists.DocumentList;
import com.example.androidapplication_reto2.project.factories.CategoryFactory;
import com.example.androidapplication_reto2.project.factories.DocumentFactory;
import com.example.androidapplication_reto2.project.interfaces.RestCategory;
import com.example.androidapplication_reto2.project.interfaces.RestDocument;
import com.example.androidapplication_reto2.project.recyclers.MainRecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalUserFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_CODE = 0;
    private static final int FILE_PICKER_REQUEST_CODE = 1;
    private FloatingActionButton floatingAddDocument;
    private EditText newDocNameUpload;
    private TextView lbDocUploadPath, userData;
    private ImageView imageButtonFindDocument, imageButtonShowDocument;
    private String path = "", docName;
    private int spinnerId;
    private static File document;
    private PopupWindow popupWindow;
    private User user;
    private Spinner spinner;
    private RecyclerView recyclerView;
    private MainRecyclerView mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_principal_user, container, false);
        mContext=getContext();
        userData = root.findViewById(R.id.lbUserData);
        setHasOptionsMenu(true);

        user = MainFragmentsController.getUser();

        RestDocument restDocument = DocumentFactory.getClient();
        Call<DocumentList> documentsUser = restDocument.findAllDocuments();
        documentsUser.enqueue(new Callback<DocumentList>() {
            @Override
            public void onResponse(Call<DocumentList> call, Response<DocumentList> response) {
                Log.d("PRINCIPAL", "YUJUUU");
                switch (response.code()) {
                    case 200:
                        if (response.isSuccessful()) {
                            layoutManager=new LinearLayoutManager(getContext());
                            recyclerView= root.findViewById(R.id.recyclerViewDocumentsUser);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(layoutManager);
                            mAdapter=new MainRecyclerView(response.body());
                            recyclerView.setAdapter(mAdapter);
                        }
                        break;
                }

            }

            @Override
            public void onFailure(Call<DocumentList> call, Throwable t) {
                Log.d("PRINCIPAL", "NOPE");
            }
        });
        if (user != null)
            userData.setText("Bienvenido " + user.getFullName() + ".\nEsta es tu ventana principal.");
        else
            Toast.makeText(getContext(), "No found user", Toast.LENGTH_SHORT).show();

        return root;
    }

    private void checkPermisionAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                Toast.makeText(getContext(), "Algo fue mal panita", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            openFilePicker();
        }
    }

    private void openFilePicker() {
        new MaterialFilePicker()
                .withSupportFragment(PrincipalUserFragment.this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withHiddenFiles(true)
                .withFilter(Pattern.compile(".*\\.pdf$"))
                .withTitle("Select PDF file")
                .start();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    Toast.makeText(getContext(), "Algo fue mal panita", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            if (path != null) {
                Log.d("Path (fragment): ", path);
                lbDocUploadPath.setText(path);

            }
        }
    }

    public static File getDocument() {
        return document;
    }

    public static void removeDialog(String docNameRemove, DialogInterface.OnClickListener dialogClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("You are going to delete the document "+docNameRemove+".Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.log_out, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btLogOut:
                logOutMessage();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logOutMessage (){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        MediaPlayer.create(getContext(),R.raw.byebye).start();
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure that want log out?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

}
