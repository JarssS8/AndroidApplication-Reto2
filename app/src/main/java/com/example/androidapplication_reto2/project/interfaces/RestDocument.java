package com.example.androidapplication_reto2.project.interfaces;

import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.beans.Rating;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestDocument {
    /**
     * Method who use the ejb to create a Document
     * @param document the document will be created
     */
    @POST
    public Call<Void> newDocument(@Body Document document) ;

    /**
     * Method who use the ejb to modify a document
     * @param document the document will be modified
     */
    @PUT("{id}")
    public Call<Void> modifyDocument(@Body Document document);

    /**
     * Method who use the ejb to delete a document
     * @param id of the document will be deleted
     */
    @DELETE("{id}")
    public Call<Void> deleteDocument(@Path("id") Long id) ;

    /**
     * Method who use the ejb to search all the documents
     * @return All the documents list
     */
    @GET
    public Call<List<Document>> findAllDocuments();

    /**
     * Method who use the ejb to search a document by his id
     * @param id the id to search by
     * @return the document with the specified id
     */
    @GET("{id}")
    public Call<Document> findDocument(@Path("id") Long id) ;
    /**
     * Method who use the ejb to search a document by various parameters
     * @param name the name to search by
     * @param category the category to search by
     * @param uploadDate the date to search by
     * @return A list of names of documents
     */
    @GET("{name}/{category}/{uploadDate}")
    public Call<List<Document>> findDocumentNameByParameters(
            @Path("name") String name,
            @Path("category") String category,
            @Path("uploadDate") Date uploadDate);


    /**
     * Method who use the ejb to search Rating of a document
     * @param id the id of the document
     */

    @GET("/ratings/{id}")
    public Call<Set<Rating>> findRatingsOfDocument(@Path("id") Long id);
}
