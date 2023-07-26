package com.demo.sharing;

import static android.provider.Telephony.Carriers.PASSWORD;
import static android.provider.Telephony.Mms.Part.NAME;
import static android.provider.Telephony.ThreadsColumns.DATE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class sqlite extends SQLiteOpenHelper {
    private static final String dbname = "login";
    private static final String dbtable = "users";


    String createtable = ("create table users(username TEXT primary key , email TEXT UNIQUE, password TEXT)");
    public sqlite(Context context) {
        super(context, dbname, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

db.execSQL("drop table if exists users");

    }

    public Boolean insertdata(String username, String passwords , String email ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", passwords);
        contentValues.put("email", email);
        long result = db.insert(dbtable, null, contentValues);
        if( result == -1) return false;
        else return true ;


    }

    public Boolean viewdata(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username= ? and password = ?", new String[]{username,password});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;


    }
    public Boolean checkusername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username= ? ", new String[]{username});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;


    }
    public Boolean checkemail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email= ? ", new String[]{email});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;


    }
    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM users"); //delete all rows in a table
        db.close();
    }
    public void update( String password , String email){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        db.update("users",contentValues, "email=?", new String[]{email});
        db.close();


    }
}