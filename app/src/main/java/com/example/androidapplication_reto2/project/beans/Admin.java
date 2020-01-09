/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.androidapplication_reto2.project.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



/**
 * Extends of class User for users that are going to control the platform
 *
 * @author Adrian
 */
public class Admin extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * A timestamp with the date of one user become admin
     */
    private Date adminDate;

    public Date getAdminDate() {
        return adminDate;
    }

    public void setAdminDate(Date adminDate) {
        this.adminDate = adminDate;
    }

    /**
     * Return an int calculated from id for the User
     *
     * @return an int representating the instance of this entity
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    /**
     * Compares two instances of Users
     *
     * @param object the other User instance to compare to
     * @return true if instances are equal
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    /**
     * Obtains a String representation including id value and classes full Name
     *
     * @return a String of an User id
     */
    @Override
    public String toString() {
        return "serverapplication.entities.User[ id=" + getId() + " ]";
    }
}
