package com.example.androidapplication_reto2.project.retrofitcalls;

import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.beans.Rating;
import com.example.androidapplication_reto2.project.interfaces.RestDocument;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class DocumentCalls {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080/ServerApplication-Reto2/webresources/document")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();

    private final RestDocument restDocument =retrofit.create(RestDocument.class);

    public void newDocument( Document document) {
        Call<Void> newDocumentCall = restDocument.newDocument(document);
        newDocumentCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void modifyDocument( Document document){
        Call<Void> modifyDocumentCall = restDocument.modifyDocument(document);
        modifyDocumentCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void deleteDocument( Long id) {
        Call<Void> deleteDocumentCall = restDocument.deleteDocument(id);
        deleteDocumentCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public Set<Document> findAllDocuments(){
        Set<Document>[] documents = new Set[]{new HashSet<>()};
        Call<Set<Document>> findAllDocumentsCall = restDocument.findAllDocuments();
        findAllDocumentsCall.enqueue(new Callback<Set<Document>>() {
            @Override
            public void onResponse(Call<Set<Document>> call, Response<Set<Document>> response) {
                documents[0] = response.body();
            }

            @Override
            public void onFailure(Call<Set<Document>> call, Throwable t) {

            }
        });
        return documents[0];
    }

    public Document findDocument(Long id) {
        final Document[] document = {new Document()};
        Call<Document> findDocumentCall = restDocument.findDocument(id);
        findDocumentCall.enqueue(new Callback<Document>() {
            @Override
            public void onResponse(Call<Document> call, Response<Document> response) {
                document[0] = response.body();
            }

            @Override
            public void onFailure(Call<Document> call, Throwable t) {

            }
        });
        return document[0];
    }

    public Set<Document> findDocumentNameByParameters(String name, String category, Date uploadDate){
        Set<Document>[] documents = new Set[]{new HashSet<>()};
        Call<Set<Document>> findDocumentNameByParameterCall = restDocument.findDocumentNameByParameters(name,category,uploadDate);
        findDocumentNameByParameterCall.enqueue(new Callback<Set<Document>>() {
            @Override
            public void onResponse(Call<Set<Document>> call, Response<Set<Document>> response) {
                documents[0] = response.body();
            }

            @Override
            public void onFailure(Call<Set<Document>> call, Throwable t) {

            }
        });
        return documents[0];
    }

    public Set<Rating> findRatingsOfDocument(Long id){
        Set<Rating>[] documents = new Set[]{new HashSet<>()};
        Call<Set<Rating>> findAllRatingsOfDocumentCall = restDocument.findRatingsOfDocument(id);
        findAllRatingsOfDocumentCall.enqueue(new Callback<Set<Rating>>() {
            @Override
            public void onResponse(Call<Set<Rating>> call, Response<Set<Rating>> response) {
                documents[0] = response.body();
            }

            @Override
            public void onFailure(Call<Set<Rating>> call, Throwable t) {

            }
        });
        return documents[0];
    }
}
