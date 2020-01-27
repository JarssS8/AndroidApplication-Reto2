package com.example.androidapplication_reto2.project.interfaces;

import com.example.androidapplication_reto2.project.beans.Admin;
import com.example.androidapplication_reto2.project.beans.Free;
import com.example.androidapplication_reto2.project.beans.Premium;
import com.example.androidapplication_reto2.project.beans.User;

import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestUser {
    @POST
    public Call<Void> createUser(@Body User user) ;

    @PUT("modifyFree/")
    public Call<Void> modifyUserData(@Body Free free);

    @PUT("modifyPremium/")
    public Call<Void> modifyUserData(@Body Premium premium);

    @PUT("modifyAdmin/")
    public Call<Void> modifyUserData(@Body Admin admin);

    @DELETE("deleteByLogin/{login}")
    public Call<Void> deleteUserByLogin(@Path("login") String login);

    @DELETE("deleteById/{id}")
    public Call<Void> deleteUserById(@Path("id") Long id) ;

    @GET("logIn/{login}/{password}")
    public Call<ResponseBody> logIn(@Path("login") String login, @Path("password") String password);

    @GET("id/{id}")
    public Call<Object> findUserById(@Path("id") Long id);

    @GET("{login}")
    public Call<Object> findUserByLogin(@Path("login") String login);

    @GET(".")
    public Call<Set<User>> findAllUsers() ;

    @PUT("goFree/")
    public Call<Void> modifyUserToFree(@Body User user) ;

    @PUT("goPremium/")
    public Call<Void> modifyUserToPremium(@Body Premium premium) ;

    @PUT("goAdmin/")
    public Call<Void> modifyUserToAdmin(@Body User user) ;
}
