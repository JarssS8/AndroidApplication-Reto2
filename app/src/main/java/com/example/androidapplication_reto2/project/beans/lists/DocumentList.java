package com.example.androidapplication_reto2.project.beans.lists;

import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.beans.User;

import java.util.ArrayList;
import java.util.Set;

public class DocumentList {
    private Set<Document> documents;

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}
