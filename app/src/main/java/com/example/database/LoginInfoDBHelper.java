package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginInfoDBHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "login.db";
    public static final String TABLE_NAME="login_info";
    public static final int DB_VERSION = 1;
    public static LoginInfoDBHelper loginInfoDBHelper=null;

    public static LoginInfoDBHelper getInstance(Context context){
        if(loginInfoDBHelper!=null){
            loginInfoDBHelper=new LoginInfoDBHelper(context);
        }
        return loginInfoDBHelper;
    }

    public LoginInfoDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table  if not exists  " + TABLE_NAME + "(_id integer primary key autoincrement not null, phone integer not null,password varchar not null,isremember integer not null);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
