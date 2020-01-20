package com.example.androidapplication_reto2.project.activities.navigationfragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
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
import androidx.navigation.Navigation;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.beans.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalUserFragment extends Fragment implements View.OnClickListener {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_principal_user, container, false);

        floatingAddDocument = root.findViewById(R.id.floatingActionButton);
        userData = root.findViewById(R.id.lbUserData);

        floatingAddDocument.setOnClickListener(this);
        userData.setText("Bienvenido "+user.getFullName()+".\nEsta es tu ventana principal.");
        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingActionButton:
                LayoutInflater layoutInflater = (LayoutInflater) getContext().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popUpView = layoutInflater.inflate(R.layout.pop_up_upload_document, null);
                popupWindow = new PopupWindow(popUpView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
                //FindViewById From the popUp
                Button btUploadDocument = popUpView.findViewById(R.id.btUploadDocument);
                newDocNameUpload = popUpView.findViewById(R.id.txtUploadDocName);
                Spinner spinnerCategories = popUpView.findViewById(R.id.spinnerCategoriesUploadDocument);
                imageButtonFindDocument = popUpView.findViewById(R.id.imageButtonFindFile);
                lbDocUploadPath = popUpView.findViewById(R.id.lbPathDocument);
                imageButtonShowDocument = popUpView.findViewById(R.id.imageButtonShowDocument);
                if (!path.equalsIgnoreCase("")) {
                    if (docName != null)
                        newDocNameUpload.setText(docName);
                    spinnerCategories.setId(spinnerId);
                    lbDocUploadPath.setText(path);

                }

                imageButtonFindDocument.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkPermisionAndOpenFilePicker();
                    }
                });

                imageButtonShowDocument.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        document = new File(lbDocUploadPath.getText().toString());
                        if (lbDocUploadPath.getText().toString().equalsIgnoreCase(path)) {
                            docName = newDocNameUpload.getText().toString();
                            spinnerId = spinnerCategories.getId();
                            Navigation.findNavController(getView()).navigate(R.id.action_nav_home_to_nav_view_document);
                            popupWindow.dismiss();
                        } else {
                            Snackbar.make(getView(),"Select one pdf before", Snackbar.LENGTH_SHORT).show();
                        }
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
}
