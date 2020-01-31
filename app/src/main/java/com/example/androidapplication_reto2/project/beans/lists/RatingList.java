package com.example.androidapplication_reto2.project.beans.lists;

import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.beans.Rating;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.Set;

@Root(name = "ratings")
public class RatingList {
    @ElementList(name = "rating", inline = true)
    private Set<Rating> ratings;

    public Set<Rating> getDocuments() {
        return ratings;
    }

    public void setDocuments(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
