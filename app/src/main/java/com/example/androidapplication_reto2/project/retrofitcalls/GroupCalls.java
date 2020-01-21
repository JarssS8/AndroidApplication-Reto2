package com.example.androidapplication_reto2.project.retrofitcalls;

import com.example.androidapplication_reto2.project.beans.Category;
import com.example.androidapplication_reto2.project.beans.Group;
import com.example.androidapplication_reto2.project.beans.User;
import com.example.androidapplication_reto2.project.interfaces.RestCategory;
import com.example.androidapplication_reto2.project.interfaces.RestGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class GroupCalls {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.21.154:8080/ServerApplication-Reto2/webresources/group/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();

    private final RestGroup restGroup =retrofit.create(RestGroup.class);

    public void createGroup(Group group){
        Call<Void> createGroupCall = restGroup.createGroup(group);
        createGroupCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void modifyGroup( Group group){
        Call<Void> modifyGroupCall = restGroup.modifyGroup(group);
        modifyGroupCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    public void joinGroup( String groupName, String password, User user){
        Call<Void> joinGroupCall = restGroup.joinGroup(groupName,password,user);
        joinGroupCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void leaveGroup( Long id, User user){
        Call<Void> leaveGroupCall = restGroup.leaveGroup(id,user);
        leaveGroupCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public Set<Group> findGroups(){
        Set<Group>[] groups = new Set[]{new HashSet<>()};
        Call<Set<Group>> findGroupsCall = restGroup.findGroups();
        findGroupsCall.enqueue(new Callback<Set<Group>>() {
            @Override
            public void onResponse(Call<Set<Group>> call, Response<Set<Group>> response) {
                groups[0] = response.body();
            }

            @Override
            public void onFailure(Call<Set<Group>> call, Throwable t) {

            }
        });
        return groups[0];
    }

    public Set<Group> findAllGroups(String login) {
        Set<Group>[] groups = new Set[]{new HashSet<>()};
        Call<Set<Group>> findGroupsCall = restGroup.findGroups();
        findGroupsCall.enqueue(new Callback<Set<Group>>() {
            @Override
            public void onResponse(Call<Set<Group>> call, Response<Set<Group>> response) {
                groups[0] = response.body();
            }

            @Override
            public void onFailure(Call<Set<Group>> call, Throwable t) {

            }
        });
        return groups[0];
    }

    public void deleteGroup (Long id){
        Call<Void> deleteGroupCall = restGroup.deleteGroup(id);
        deleteGroupCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
