package com.example.androidapplication_reto2.project.activities.navigationfragments;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.androidapplication_reto2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubscriptionFragment extends Fragment implements View.OnClickListener{

    private View root;
    private ImageView btSelectDate;
    private EditText txtCardNumber, txtCVC, txtExpirationDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_subscription, container, false);
        setHasOptionsMenu(true);

        btSelectDate = root.findViewById(R.id.imageButtonChooseDateCreditCard);
        btSelectDate.setOnClickListener(this);
        txtCardNumber = root.findViewById(R.id.txtNumberCreditCard);
        txtCVC = root.findViewById(R.id.txtCVC);
        txtExpirationDate = root.findViewById(R.id.txtChooseDateCreditCard);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonChooseDateCreditCard:
                DatePickerDialog datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtExpirationDate.setText(month+1+"/"+(year+"").substring(3,5));
                    }
                }, 1998, 3, 3);
                datePicker.findViewById(getResources().getIdentifier("day","id","android")).setVisibility(View.GONE);
                datePicker.show();
                break;
        }
    }
}
