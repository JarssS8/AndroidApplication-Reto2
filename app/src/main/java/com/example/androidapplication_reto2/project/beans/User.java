/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.androidapplication_reto2.project.beans;

import android.content.ContentValues;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * This class is an entity.
 *
 * @author aimar
 */

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The Id for the user.
     */
    private Long id;
    /**
     * The login value for the user.
     */
    private String login;
    /**
     * The email value for the user.
     */
    private String email;
    /**
     * The full name for the user.
     */
    private String fullName;
    /**
     * The status for the users account.
     */
    private Status status;
    /**
     * The privilege for the user.
     */
    private Privilege privilege;
    /**
     * The password value for the user.
     */
    private String password;
    /**
     * The date when the user last acceded to the applicacion.
     */
    private String lastAccess;
    /**
     * The date when the user last changed password.
     */
    private String lastPasswordChange;
    /**
     * A collection with all the ratings given by the user.
     */
    private Set<Rating> ratings;
    /**
     * A collection with all the documents uploaded by the user.
     */
    private Set<Document> documents;
    /**
     * A collection with all the groups for the user.
     */
    private Set<Group> groups;
    /**
     * A collection with the group the user administrates.
     */
    private Set<Group> adminGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastAccess() {
        Date resultado=null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");
            resultado= formatter.parse(lastAccess);
        }catch (Exception e){
            resultado=new Date();
        }
        return resultado;
    }

    public void setLastAccess(String lastAccess) {
        this.lastAccess = lastAccess.toString();
    }

    public Date getLastPasswordChange() {
        Date resultado=null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");
            resultado= formatter.parse(lastPasswordChange);
        }catch (Exception e){
            resultado=new Date();
        }
        return resultado;
    }

    public void setLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange.toString();
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    /**
     * @return the adminGroups
     */
    public Set<Group> getAdminGroups() {
        return adminGroups;
    }

    /**
     * @param adminGroups the adminGroups to set
     */
    public void setAdminGroups(Set<Group> adminGroups) {
        this.adminGroups = adminGroups;
    }

    public ContentValues toContentValues(){
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",login);
        contentValues.put("password",password);
        contentValues.put("active",1);
        return contentValues;
    }

}
