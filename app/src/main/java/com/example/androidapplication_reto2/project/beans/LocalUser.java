package com.example.androidapplication_reto2.project.beans;

import android.content.ContentValues;

import java.io.Serializable;

public class LocalUser implements Serializable {

    private int id;
    private String login;
    private String password;
    private int active;

    public LocalUser (){}

    public LocalUser(String login, String password, int active) {
        this.login = login;
        this.password = password;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", getLogin());
        contentValues.put("password", getPassword());
        contentValues.put("active", getActive());
        return contentValues;
    }
}
