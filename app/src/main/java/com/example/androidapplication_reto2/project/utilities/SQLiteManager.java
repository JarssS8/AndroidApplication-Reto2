package com.example.androidapplication_reto2.project.utilities;

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

    /**
     * Constructor for our local database
     * @param context Context from where is the user interacting with this class
     */
    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Action executed always just when database is created
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_USER + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT NOT NULL, password TEXT NOT NULL, active NUMBER )");

    }

    /**
     * Action executed always just when database is updated (version)
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Insert or update the user of our local database, insert if there is no user and update if we have one
     * @param user Data for save the user data
     */
    public void insertUser(LocalUser user) {
        if(findUser()==null) {
            sqLiteDatabase.insert(TABLE_NAME_USER, null, user.toContentValues());
        } else {
            sqLiteDatabase.update(TABLE_NAME_USER,user.toContentValues(),"_id = 1",null);
        }

    }

    /**
     * Return one user
     * @return the only one user on our local data base
     */
    public LocalUser getUser() {
        return findUser();
    }

    /**
     * Find the user that is in out database
     * @return the user from local database
     */
    public LocalUser findUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        LocalUser user = null;

        Cursor cursor = db.query(TABLE_NAME_USER, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            user = new LocalUser(cursor.getString(cursor.getColumnIndex("login")), cursor.getString(cursor.getColumnIndex("password")), cursor.getInt(cursor.getColumnIndex("active")));
        }
        return user;
    }

    /**
     * Change the user status when he wont remember the login
     */
    public void changeToNoRemember(){
        LocalUser user = null;
        user = findUser();
        if (user!=null) {
            if(user.getActive()==1){
                user.setActive(0);
                sqLiteDatabase.update(TABLE_NAME_USER,user.toContentValues(),"_id = 1",null);
            }
        }
    }

}