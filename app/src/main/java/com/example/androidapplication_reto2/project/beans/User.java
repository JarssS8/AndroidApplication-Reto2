/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.androidapplication_reto2.project.beans;

import android.content.ContentValues;

import java.io.Serializable;
import java.sql.Timestamp;
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
    private Date lastAccess;
    /**
     * The date when the user last changed password.
     */
    private Date lastPasswordChange;
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
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "serverapplication.entities.User[ id=" + id + " ]";
    }

    public ContentValues toContentValues(){
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",login);
        contentValues.put("password",password);
        contentValues.put("active",1);
        return contentValues;
    }

}
