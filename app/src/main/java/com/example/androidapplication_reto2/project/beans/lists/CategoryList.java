package com.example.androidapplication_reto2.project.beans.lists;

import com.example.androidapplication_reto2.project.beans.Category;
import com.example.androidapplication_reto2.project.beans.User;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
@Root(name = "categories")
public class CategoryList {
    @ElementList(name = "category", inline=true)
    private ArrayList<Category> categories;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
