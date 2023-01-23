package com.example;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.UserDBHelper;
import com.example.utils.SuperToast;

public class MyApplication extends Application {

    private static MyApplication appContext;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this;
        SuperToast.init(appContext);
        initBaseData();

    }

    private void initBaseData() {
        UserDBHelper userDBHelper = UserDBHelper.getInstance(appContext);
        SQLiteDatabase db = userDBHelper.getWritableDatabase();
        if(db.isOpen()){
            db.execSQL("insert into "+UserDBHelper.TABLE_NAME+"(phone ,name ,username ,password) values (2022,'zhangjinxin','taibai',2022)");
        }
    }
    public static MyApplication getInstance(){
        return appContext;
    }
}
