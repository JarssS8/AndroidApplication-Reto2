/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.androidapplication_reto2.project.beans;

import java.io.Serializable;


/**
 *
 * @author Gaizka Andres
 */
public class RatingId implements Serializable{
    
    private Long idDocument;
    private Long idUser;

    public void ratinId(Long idDocument,Long idUser){
        this.idDocument=idDocument;
        this.idUser=idUser;
    }
    
    public Long getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Long idDocument) {
        this.idDocument = idDocument;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    
    /**
     * Return an int calculated from id for the User
     * @return an int representating the instance of this entity
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocument != null ? idDocument.hashCode() : 0);
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }
    /**
     * Compares two instances of Users
     * @param object the other User instance to compare to
     * @return true if instances are equal
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RatingId)) {
            return false;
        }
        RatingId other = (RatingId) object;
        if ((this.idDocument == null && other.idDocument != null) || 
            (this.idDocument != null && !this.idDocument.equals(other.idDocument))) {
            return false;
        }
        if ((this.idUser == null && other.idUser != null) || 
            (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }
}
