package com.example.androidapplication_reto2.project.activities.navigationfragments;


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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.beans.Rating;
import com.example.androidapplication_reto2.project.factories.RatingFactory;
import com.example.androidapplication_reto2.project.factories.UserFactory;
import com.example.androidapplication_reto2.project.interfaces.RestRating;
import com.example.androidapplication_reto2.project.interfaces.RestUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentDataFragment extends Fragment implements View.OnClickListener{

    private View root;
    private static Document documentData;
    private TextView docName, docData;
    private Spinner spinnerRate;
    private ImageView imageButtonSendReview;
    private EditText commentReview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_document_data, container, false);

        docName = root.findViewById(R.id.lbDocNameDocumentData);
        docData = root.findViewById(R.id.lbDocumentData);
        imageButtonSendReview = root.findViewById(R.id.imageButtonSendRatingDocData);
        commentReview = root.findViewById(R.id.txtCommentDocumentData);
        spinnerRate = root.findViewById(R.id.spinnerRateDocumentData);

        setHasOptionsMenu(true);

        //spinner
        ArrayAdapter<String> rateValuesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, new String[]{"0","1","2","3","4","5"});
        rateValuesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRate.setAdapter(rateValuesAdapter);

        //data
        docName.setText(documentData.getName());
        docData.setText("Author: TODO"+"\nCategory: "+documentData.getCategory()+"\nUpload date: "+documentData.getUploadDate());

        //listeners
        imageButtonSendReview.setOnClickListener(this);

        return root;
    }

    public static void setRatings(Document document) {
        DocumentDataFragment.documentData = document;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonSendRatingDocData:
                Toast.makeText(getContext(), "HEY", Toast.LENGTH_SHORT).show();
                Rating auxRating = new Rating();
                auxRating.setReview(commentReview.getText().toString());
                auxRating.setRating(Integer.parseInt((String)spinnerRate.getSelectedItem()));
                auxRating.setDocument(documentData);
                RestRating restRating = RatingFactory.getClient();
                Call<Void> callSendReview = restRating.newDocumentRating(auxRating);
                callSendReview.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("Document Data", "RESPONSE");
                        switch (response.code()) {
                            case 204:
                                Log.d("Document Data", "204 a tope");
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("Document Data", "fALLO");
                        Log.d("Document Data", t.getMessage());
                    }
                });
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        RestUser restUser = UserFactory.getClient();

        inflater.inflate(R.menu.delete_document,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btDeleteDoc:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
