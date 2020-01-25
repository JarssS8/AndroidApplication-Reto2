/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.androidapplication_reto2.project.beans;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Entity class for rating. 
 * @author Gaizka Andrés
 */
@Root(name = "rating")
public class Rating implements Serializable{
    private static final long serialVersionUID=1L;
    /**
     * Id to indentificate the rating
     */
    @Element(name = "id", required = false)
    private RatingId id;
    /**
     * The rating given to the document
     */
    @Element(name = "rating", required = false)
    private int rating;
    /**
     * The rating given to the document
     */
    @Element(name = "review", required = false)
    private String review;
    /**
     * The date the review has been done
     */
    @Element(name = "ratingDate", required = false)
    private String ratingDate;
    /**
     * The document were the rating has been done
     */
    @Element(name = "document", required = false)
    private Document document;
    /**
     * The user who rates the document
     */
    @Element(name = "user", required = false)
    private User user;

    public RatingId getId() {
        return id;
    }

    public void setId(RatingId id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getRatingDate() {
        Date resultado=null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");
            resultado= formatter.parse(ratingDate);
        }catch (Exception e){
            resultado=new Date();
        }
        return resultado;
    }

    public void setRatingDate(Date ratingDate) {
        this.ratingDate = ratingDate.toString();
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
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
        return "serverapplication.entities.Rating[ id=" + getId() + " ]";
    }

    
}
