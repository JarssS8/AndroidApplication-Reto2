package com.example.androidapplication_reto2.project.activities.navigationfragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.example.androidapplication_reto2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalUserFragment extends Fragment implements View.OnClickListener{

    private static final int PERMISSIONS_REQUEST_CODE = 0;
    private static final int FILE_PICKER_REQUEST_CODE = 1 ;
    private FloatingActionButton floatingAddDocument;
    private EditText newDocNameUpload;
    private TextView lbDocUploadPath;



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
                Log.i("SignUp", "User click on help button.Creating a layout inflater for the popUp view");
                LayoutInflater layoutInflater = (LayoutInflater) getContext().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                Log.i("SignUp", "Usign the inflator inflating the layout");
                View popUpView = layoutInflater.inflate(R.layout.pop_up_upload_document, null);
                Log.i("SignUp", "Defining pop up componentes");
                final PopupWindow popupWindow = new PopupWindow(popUpView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,true);
                //FindViewById From the popUp
                Button btUploadDocument = popUpView.findViewById(R.id.btUploadDocument);
                newDocNameUpload = popUpView.findViewById(R.id.txtUploadDocName);
                Spinner spinnerCategories = popUpView.findViewById(R.id.spinnerCategoriesUploadDocument);
                ImageView imageButtonFindDocument= popUpView.findViewById(R.id.imageButtonFindFile);
                lbDocUploadPath = popUpView.findViewById(R.id.lbPathDocument);


                imageButtonFindDocument.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkPermisionAndOpenFilePicker();
                    }
                });


                btUploadDocument.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                Log.i("SignUp", "Showing the pop up");
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                break;
        }
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
                .withFilterDirectories(true)
                //.withFilter(Pattern.compile(".*\\.pdf$"))
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
            String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            if (path != null) {
                Log.d("Path (fragment): ", path);
                lbDocUploadPath.setText(path);

            }
        }
    }
}
