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
import com.example.androidapplication_reto2.project.beans.lists.DocumentList;
import com.example.androidapplication_reto2.project.factories.DocumentFactory;
import com.example.androidapplication_reto2.project.interfaces.RestDocument;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDocumentsFragment extends Fragment implements View.OnClickListener{

    private EditText date, name,author;
    private ImageView imageButtonCalendar;
    private Button btSearchDocument;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_search_documents, container, false);

        date = root.findViewById(R.id.date);
        imageButtonCalendar = root.findViewById(R.id.imageButtonCalendar);
        btSearchDocument = root.findViewById(R.id.btSearchDocument);
        name = root.findViewById(R.id.txtFindDocName);
        author = root.findViewById(R.id.txtfindAuthorName);

        btSearchDocument.setOnClickListener(this);
        imageButtonCalendar.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSearchDocument:
                Date pickerDate=null;
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");
                    pickerDate= formatter.parse(date.getText().toString());
                }catch (Exception e){
                    pickerDate=new Date();
                }
                java.sql.Date utilDate = new java.sql.Date(pickerDate.getTime());
                RestDocument restDocument = DocumentFactory.getClient();
                Call<DocumentList> documentListCall = restDocument.findDocumentNameByParameters(name.getText().toString()+"",author.getText().toString()+"",utilDate);
                Toast.makeText(getContext(), "Buscando...", Toast.LENGTH_SHORT).show();
                documentListCall.enqueue(new Callback<DocumentList>() {
                    @Override
                    public void onResponse(Call<DocumentList> call, Response<DocumentList> response) {
                        switch (response.code()) {

                        }
                    }

                    @Override
                    public void onFailure(Call<DocumentList> call, Throwable t) {

                    }
                });


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
