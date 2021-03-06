/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.androidapplication_reto2.project.beans.plural;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.Set;

/**
 * Class category, with the different categories for our application. Every
 * document must have a category
 *
 * @author Adrian
 */
@Root(name = "categories")
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * A long with the identifier of the category
     */
    @Element(name = "id")
    private Long id;
    /**
     * A String with the name of the category
     */
    @Element(name = "name")
    private String name;
    /**
     * A collection with the documents of this category
     */
    @ElementList(name = "documents", inline = true, required = false)
    private Set<Documents> documents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public Set<Documents> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Documents> documents) {
        this.documents = documents;
    }

    /**
     * Return an int calculated from id for the Category
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
     * Compares two instances of Category
     *
     * @param object the other Category instance to compare to
     * @return true if instances are equal
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
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
        return "serverapplication.entities.Category[ id=" + id + " ]";
    }

}
