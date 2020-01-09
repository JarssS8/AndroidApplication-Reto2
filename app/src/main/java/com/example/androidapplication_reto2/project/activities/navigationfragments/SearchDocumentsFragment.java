package com.example.androidapplication_reto2.project.activities.navigationfragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.androidapplication_reto2.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDocumentsFragment extends Fragment implements View.OnClickListener{

    private EditText date;
    private ImageView imageButtonCalendar;
    private Button btSearchDocument;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_search_documents, container, false);

        date = root.findViewById(R.id.date);
        imageButtonCalendar = root.findViewById(R.id.imageButtonCalendar);
        btSearchDocument = root.findViewById(R.id.btSearchDocument);

        btSearchDocument.setOnClickListener(this);
        imageButtonCalendar.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSearchDocument:
                //Todo
                Toast.makeText(getContext(), "Buscando...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButtonCalendar:
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
        }
    }
}
