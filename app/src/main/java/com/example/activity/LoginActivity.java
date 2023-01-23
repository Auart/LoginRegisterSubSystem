package com.example.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.example.MyApplication;
import com.example.database.LoginInfoDBHelper;
import com.example.database.UserDBHelper;
import com.example.databinding.ActivityLoginBinding;
import com.example.utils.SuperToast;

public class LoginActivity extends BaseViewModelActivity<ActivityLoginBinding> {
    private String username;
    private String password;
    private MyApplication application;
    private boolean isChecked;
    private String pwd;
    private boolean isRemember;
    private LoginInfoDBHelper loginInfoDBHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = MyApplication.getInstance();
    }

    @Override
    protected void initListens() {
        super.initListens();
        viewBinding.goRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
        viewBinding.btnLogin.setOnClickListener(v -> login());
        viewBinding.forget.setOnClickListener(v -> startActivity(new Intent(this, ForgetActivity.class)));
        loginInfoDBHelper = LoginInfoDBHelper.getInstance(this);
        viewBinding.editTextTextPersonName.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                SQLiteDatabase rdb = loginInfoDBHelper.getReadableDatabase();
                if (rdb.isOpen()) {
                  String username = viewBinding.editTextTextPersonName.getText().toString();
                    Cursor cursor = rdb.rawQuery("select * from " + loginInfoDBHelper.TABLE_NAME + " where phone = ?;", new String[]{username});
                    if (cursor.moveToNext()) {
                        pwd = cursor.getString(cursor.getColumnIndex("password"));
                        isRemember = cursor.getInt(cursor.getColumnIndex("isremember")) == 1;
                    }
                    viewBinding.editTextTextPassword.setText(pwd);
                    viewBinding.rememberPassword.setChecked(isRemember);
                    rdb.close();
                    cursor.close();
                }
            }
        });
    }


    @Override
    protected void initDatum() {
        super.initDatum();
    }

    private void login() {
        username = viewBinding.editTextTextPersonName.getText().toString();
        password = viewBinding.editTextTextPassword.getText().toString();
        UserDBHelper userDBHelper = UserDBHelper.getInstance(this);
        SQLiteDatabase rdb = userDBHelper.getReadableDatabase();
        if (rdb.isOpen()) {
            Cursor cursor = rdb.rawQuery("select * from " + UserDBHelper.TABLE_NAME + " where username=? and password =? ;", new String[]{username, password});
            Runnable runnable = cursor.moveToNext() ?
                    () -> {
                        isChecked = viewBinding.rememberPassword.isChecked();
                        if (isChecked) {
                            SQLiteDatabase rdb2 = loginInfoDBHelper.getWritableDatabase();
                            if (rdb2.isOpen()) {
                                rdb2.execSQL("insert into " + loginInfoDBHelper.TABLE_NAME + "(phone,password,isremember) values (?,?,?)", new Object[]{username, password, 1});
                                rdb2.close();
                            }
                        }
                        SuperToast.show("登录成功");
                        startActivity(new Intent(this, MainActivity.class));
                    }
                    : () -> SuperToast.show("登录失败");
            runnable.run();
            rdb.close();
            cursor.close();
        }
    }
}
