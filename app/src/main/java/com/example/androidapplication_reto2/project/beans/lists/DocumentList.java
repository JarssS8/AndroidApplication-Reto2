package com.example.androidapplication_reto2.project.beans.lists;

import com.example.androidapplication_reto2.project.beans.Document;
import com.example.androidapplication_reto2.project.beans.User;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.Set;
@Root(name = "documents")
public class DocumentList {
    @ElementList(name = "document", inline = true)
    private Set<Document> documents;

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}
