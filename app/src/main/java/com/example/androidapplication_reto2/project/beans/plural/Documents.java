/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.androidapplication_reto2.project.beans.plural;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


/**
 * Entity class for Document.
 *
 * @author Gaizka Andrés
 */
@Root(name = "documents")
public class Documents implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Id to identificate the document
     */
    @Element(name = "id")
    private Long id;
    /**
     * The name of the document
     */
    @Element(name = "name")
    private String name;
    /**
     * The date when the document has been upload
     */
    @Element(name = "uploadDate")
    private String uploadDate;
    /**
     * The total rating of the document
     */
    @Element(name = "totalRating", required = false)
    private int totalRating;
    /**
     * The total of reviews the document has
     */
    @Element(name = "ratingCount", required = false)
    private int ratingCount;
    /**
     * The file itself
     */
    @ElementArray(name = "file", required = false)
    private Byte[] file;
    /**
     * The collection of rating the document has been given
     */
    @ElementList(name = "ratings", required = false, inline = true, empty = true, data = false)
    private Set<Ratings> ratings;

    /**
     * The author of the document
     */
    @Element(name = "user", required = false)
    private Users user;
    /**
     * The category of the document
     */
    @Element(name = "category", required = false)
    private Categories category;
    /**
     * The author group of the document
     */
    @Element(name = "group",required = false)
    private Groups group;

    public Documents() {
    }

    public Documents(Long id, String name, String author, String uploadDate, int totalRating, int ratingCount) {
        this.id = id;
        this.name = name;
        this.user = new Users();
        this.user.setLogin(author);
        this.uploadDate = uploadDate;
        this.totalRating = totalRating;
        this.ratingCount = ratingCount;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUploadDate() {
        Date resultado=null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");
            resultado= formatter.parse(uploadDate);
        }catch (Exception e){
            resultado=new Date();
        }
        return resultado;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate.toString();
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingAccount) {
        this.ratingCount = ratingAccount;
    }

    public Byte[] getFile() {
        return file;
    }

    public void setFile(Byte[] file) {
        this.file = file;
    }

    public Set<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Ratings> ratings) {
        this.ratings = ratings;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }


    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    /**
     * Return an int calculated from id for the User
     *
     * @return an int representating the instance of this entity
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
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
        if (!(object instanceof Documents)) {
            return false;
        }
        Documents other = (Documents) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
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
        return "serverapplication.entities.Document[ id=" + id + " ]";
    }


}
