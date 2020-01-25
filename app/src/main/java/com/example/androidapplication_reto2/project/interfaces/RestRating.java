package com.example.androidapplication_reto2.project.interfaces;

import com.example.androidapplication_reto2.project.beans.Rating;

import java.util.List;

import okhttp3.MediaType;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestRating {
    @POST(".")
    public Call<Void> newDocumentRating(@Body Rating rating);

    @GET(".")
    public Call<List<Rating>> findAllRatings();

    @PUT("{id}")
    public Call<Void> updateRating(@Body Rating rating);

    @DELETE("{id}")
    public Call<Void> deleteRating(@Path("id")Long id) ;
}
