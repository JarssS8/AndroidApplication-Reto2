/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.androidapplication_reto2.project.beans;

import android.content.ContentValues;

import com.example.androidapplication_reto2.project.beans.plural.Documents;
import com.example.androidapplication_reto2.project.beans.plural.Groups;
import com.example.androidapplication_reto2.project.beans.plural.Ratings;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * This class is an entity.
 *
 * @author aimar
 */
@Root(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The Id for the user.
     */
    @Element(name = "id")
    private Long id;
    /**
     * The login value for the user.
     */
    @Element(name = "login")
    private String login;
    /**
     * The email value for the user.
     */
    @Element(name = "email",required = false)
    private String email;
    /**
     * The full name for the user.
     */
    @Element(name = "fullName",required = false)
    private String fullName;
    /**
     * The status for the users account.
     */
    @Element(name = "status")
    private Status status;
    /**
     The privilege for the user.
     */
    @Element(name = "privilege")
    private Privilege privilege;
    /**
     * The password value for the user.
     */
    @Element(name = "password")
    private String password;
    /**
     * The profile picture for the user.
     */
    @ElementArray(name = "profilePicture",required = false)
    private Byte[] profilePicture;
    /**
     * The date when the user last acceded to the applicacion.
     */
    @Element(name = "lastAccess",required = false)
    private String lastAccess;
    /**
     * The date when the user last changed password.
     */
    @Element(name = "lastPasswordChange",required = false)
    private String lastPasswordChange;
    /**
     * A collection with all the ratings given by the user.
     */
    @ElementList(name = "ratings",required = false,inline = true)
    private Set<Ratings> ratings;
    /**
     * A collection with all the documents uploaded by the user.
     */
    @ElementList(name = "documents",required = false,inline = true)
    private Set<Documents> documents;
    /**
     * A collection with all the groups for the user.
     */
    @ElementList(name = "groups",required = false,inline = true)
    private Set<Groups> groups;
    /**
     * A collection with the group the user administrates.
     */
    @ElementList(name="adminGroups",required = false, inline = true)
    private Set<Groups> adminGroups;

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

    public Byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Byte[] profilePicture) {
        this.profilePicture = profilePicture;
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

    public Set<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Ratings> ratings) {
        this.ratings = ratings;
    }

    public Set<Documents> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Documents> documents) {
        this.documents = documents;
    }

    public Set<Groups> getGroups() {
        return groups;
    }

    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }

    /**
     * @return the adminGroups
     */
    public Set<Groups> getAdminGroups() {
        return adminGroups;
    }

    /**
     * @param adminGroups the adminGroups to set
     */
    public void setAdminGroups(Set<Groups> adminGroups) {
        this.adminGroups = adminGroups;
    }



}
