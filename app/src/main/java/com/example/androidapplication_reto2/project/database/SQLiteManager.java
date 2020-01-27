package com.example.androidapplication_reto2.project.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.androidapplication_reto2.project.beans.LocalUser;


public class SQLiteManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "User.db";
    private static final String TABLE_NAME_USER = "user";
    private LocalUser localUser = null;
    private SQLiteDatabase sqLiteDatabase = getWritableDatabase();

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_USER + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT NOT NULL, password TEXT NOT NULL, active NUMBER )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUser(LocalUser user) {
        if(findUser()==null) {
            sqLiteDatabase.insert(TABLE_NAME_USER, null, user.toContentValues());
        } else {
            sqLiteDatabase.update(TABLE_NAME_USER,user.toContentValues(),"_id = 1",null);
        }

    }

    public LocalUser getUser() {
        return findUser();
    }

    public LocalUser findUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        LocalUser user = null;

        Cursor cursor = db.query(TABLE_NAME_USER, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            user = new LocalUser(cursor.getString(cursor.getColumnIndex("login")), cursor.getString(cursor.getColumnIndex("password")), cursor.getInt(cursor.getColumnIndex("active")));
        }
        return user;
    }

}