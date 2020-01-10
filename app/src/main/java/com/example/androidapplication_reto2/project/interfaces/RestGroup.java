package com.example.androidapplication_reto2.project.interfaces;

import com.example.androidapplication_reto2.project.beans.Group;
import com.example.androidapplication_reto2.project.beans.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestGroup {
    @POST
    public Call<Void> createGroup(@Body Group group);

    @PUT
    public Call<Void> modifyGroup(@Body Group group);

    @PUT("groupName/{groupName}/password/{password}")
    public Call<Void> joinGroup(@Path("groupName") String groupName, @Path("password") String password, @Body User user);

    @PUT("id/{id}")
    public Call<Void> leaveGroup(@Path("id") Long id,@Body User user);

    @GET
    public Call<List<Group>> findGroups();

    //Todo regañar a diego
    @GET("user")
    public Call<List<Group>> findAllGroups(String login) ;

    //Todo Probablemente tampoco funcione
    @DELETE
    public Call<Void> deleteGroup (Long id);
}
