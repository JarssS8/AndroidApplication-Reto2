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
 * Extends of class User for users that access paying to our platform
 * @author Adrian
 */

public class Premium extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Boolean true if the autorenovation is active
     */
    
    private boolean autorenovation;
    /**
     * Timestamp with the date when the User starts being premium
     */
    
    private Date beginSub;
    /**
     * A long with the number of the user's credit card 
     */
    private Long cardNumber;
    /**
     * A int with the CVC of the user's credit card
     */
    private int cvc;
    /**
     * Timestamp with the date when the User should finish his premium period
     */
  
    private Timestamp endSub;
    /**
     * A int with the month expiration of the user's credit card
     */
    private int expirationMonth;
    /**
     * A int with the year expiration of the user's credit card
     */
    private int expirationYear;

    public boolean isAutorenovation() {
        return autorenovation;
    }

    public void setAutorenovation(boolean autorenovation) {
        this.autorenovation = autorenovation;
    }

  
    public Date getBeginSub() {
        return beginSub;
    }

    public void setBeginSub(Date beginSub) {
        this.beginSub = beginSub;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public Timestamp getEndSub() {
        return endSub;
    }

    public void setEndSub(Timestamp endSub) {
        this.endSub = endSub;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    /**
     * Return an int calculated from id for the User
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
     * @return a String of an User id
     */
    @Override
    public String toString() {
        return "serverapplication.entities.User[ id=" + getId() + " ]";
    }
}
