package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class UserDBHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "user.db";
    public static final String TABLE_NAME="user_info";
    public static final int DB_VERSION = 1;
    public static UserDBHelper userDBHelper=null;

    private UserDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //利用单例模式获取数据帮助器的唯一实例
    public static UserDBHelper getInstance(Context context){
        if(userDBHelper==null){
            userDBHelper= new UserDBHelper(context);
        }
        return  userDBHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table  if not exists  " + TABLE_NAME + "(_id integer primary key autoincrement not null, phone integer not null,name varchar not null,username varchar not null,password varchar not null);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
