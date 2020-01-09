package com.example.androidapplication_reto2.project.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.androidapplication_reto2.project.beans.User;

public class SQLiteManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "User.db";
    private static final String TABLE_NAME_USER = "user";
    private SQLiteDatabase sqLiteDatabase = getWritableDatabase();

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_USER + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL, active BOOLEAN )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUser(User user){
        Cursor auxUser=getReadableDatabase().query(TABLE_NAME_USER,null,null,
                null,null,null,null);
        if (auxUser!=null){
            sqLiteDatabase.update(TABLE_NAME_USER,user.toContentValues(),"_id = 1",null);
        } else {
            sqLiteDatabase.insert(TABLE_NAME_USER,null,user.toContentValues());
        }

    }

    public Cursor getUser(){
        return getReadableDatabase().query(TABLE_NAME_USER,null,null,
                null,null,null,null);
    }
}
