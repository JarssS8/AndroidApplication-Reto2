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
        Cursor auxUser = getReadableDatabase().query(TABLE_NAME_USER, null, null,
                null, null, null, null);
        ParseData(auxUser);
        if (localUser!=null) {
            sqLiteDatabase.update(TABLE_NAME_USER, user.toContentValues(), "_id = 1", null);
        } else {
            sqLiteDatabase.insert(TABLE_NAME_USER, null, user.toContentValues());
        }

    }

    public LocalUser getUser() {
        Cursor auxUser = getReadableDatabase().query(TABLE_NAME_USER, null, null,
                null, null, null, null);
        ParseData(auxUser);
        return localUser;
    }

    private void ParseData(Cursor usersLocal) {
        if (usersLocal.moveToFirst())
            localUser = new LocalUser(
                    usersLocal.getString(usersLocal.getColumnIndex("login")),
                    usersLocal.getString(usersLocal.getColumnIndex("password")),
                    usersLocal.getInt(usersLocal.getColumnIndex("active"))
            );
    }
}
