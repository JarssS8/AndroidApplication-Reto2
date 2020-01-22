package com.example.androidapplication_reto2.project.retrofitcalls;

import com.example.androidapplication_reto2.project.beans.Admin;
import com.example.androidapplication_reto2.project.beans.Free;
import com.example.androidapplication_reto2.project.beans.Premium;
import com.example.androidapplication_reto2.project.beans.User;
import com.example.androidapplication_reto2.project.interfaces.RestGroup;
import com.example.androidapplication_reto2.project.interfaces.RestUser;

import java.util.HashSet;
import java.util.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class UserCalls {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.21.154:8080/ServerApplication-Reto2/webresources/user/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();

    private final RestUser restUser =retrofit.create(RestUser.class);

    public void createUser( User user) {
        Call<Void> createUserCall = restUser.createUser(user);
        createUserCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void modifyUserData( Free free){
        Call<Void> modifyUserDataFreeCall = restUser.modifyUserData(free);
        modifyUserDataFreeCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void modifyUserData( Premium premium){
        Call<Void> modifyUserDataPremiumCall = restUser.modifyUserData(premium);
        modifyUserDataPremiumCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void modifyUserData( Admin admin){
        Call<Void> modifyUserDataAdminCall = restUser.modifyUserData(admin);
        modifyUserDataAdminCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void deleteUserByLogin( String login){
        Call<Void> deleteUserByLoginCall = restUser.deleteUserByLogin(login);
        deleteUserByLoginCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void deleteUserById( Long id) {
        Call<Void> deleteUserByIdCall = restUser.deleteUserById(id);
        deleteUserByIdCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

  
    public Object findUserById( Long id){
        Call<Object> findUserByIdCall = restUser.findUserById(id);
        //Todo
        return null;
    }

    public Object findUserByLogin( String login){
        Call<Object> findUserByLoginCall = restUser.findUserByLogin(login);
       //Todo
        return null;
    }

    public Set<User> findAllUsers() {
        final Set<User>[] users = new Set[]{new HashSet<>()};
        Call<Set<User>> findAllUsersCall = restUser.findAllUsers();
        findAllUsersCall.enqueue(new Callback<Set<User>>() {
            @Override
            public void onResponse(Call<Set<User>> call, Response<Set<User>> response) {
                users[0] = response.body();
            }

            @Override
            public void onFailure(Call<Set<User>> call, Throwable t) {

            }
        });
        return users[0];
    }

    public void modifyUserToFree( User user) {
        Call<Void> modifyUserToFreeCall = restUser.modifyUserToFree(user);
        modifyUserToFreeCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void modifyUserToPremium( Premium premium) {
        Call<Void> modifyUserToPremiumCall = restUser.modifyUserToPremium(premium);
        modifyUserToPremiumCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void modifyUserToAdmin( User user) {
        Call<Void> modifyUserToAdminCall = restUser.modifyUserToAdmin(user);
        modifyUserToAdminCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
