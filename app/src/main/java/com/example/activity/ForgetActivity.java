package com.example.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.database.UserDBHelper;
import com.example.databinding.ActivityForgetBinding;
import com.example.utils.HashMapProxy;
import com.example.utils.SuperToast;

import java.util.Objects;

public class ForgetActivity extends BaseViewModelActivity<ActivityForgetBinding> {

    private String mobile;
    private String name;
    private String username;
    private String oldPassword;
    private String newPassword;
    private String newAgainPassword;

    @Override
    protected void initListens() {
        super.initListens();
        viewBinding.confirmChange.setOnClickListener((v) -> {
            confirmChange();
        });
    }

    private void confirmChange() {
        mobile = viewBinding.editTextPhone.getText().toString();
        name = viewBinding.editTextName.getText().toString();
        username = viewBinding.editTextTextPersonName.getText().toString();
        oldPassword = viewBinding.editTextOldPassword.getText().toString();
        newPassword = viewBinding.editTextNewPassword.getText().toString();
        newAgainPassword = viewBinding.editTextTextPassword.getText().toString();
        UserDBHelper userDBHelper = UserDBHelper.getInstance(this);
        SQLiteDatabase rdb = userDBHelper.getReadableDatabase();
        HashMapProxy<String, Runnable> checkUser = new HashMapProxy<>();
        Runnable runnable = checkUser
                .putObj(mobile, () -> SuperToast.show("手机号不能为空"))
                .putObj(name, () -> SuperToast.show("姓名不能为空"))
                .putObj(username, () -> SuperToast.show("用户名不能为空"))
                .putObj(oldPassword, () -> SuperToast.show("密码不能为空"))
                .putObj(newPassword, () -> SuperToast.show("新密码不能为空"))
                .putObj(newAgainPassword, () -> SuperToast.show("密码不能为空"))
                .get("");
        if (runnable != null) {
            runnable.run();
        } else {
            if (newPassword.equals(newAgainPassword)) {
                if (rdb.isOpen()) {
                    Cursor cursor = rdb.rawQuery("select * from " + UserDBHelper.TABLE_NAME + " where phone = ? ", new String[]{mobile});
                    Cursor cursor2 = rdb.rawQuery("select * from " + UserDBHelper.TABLE_NAME + " where phone = ? and password=?", new String[]{mobile, oldPassword});
                    while (cursor2.moveToNext()) {
                        while (cursor.moveToNext()) {
                            if (cursor.getCount() > 0) {
                                SQLiteDatabase wdb = userDBHelper.getWritableDatabase();
                                if (wdb.isOpen()) {
                                    wdb.execSQL("update " + UserDBHelper.TABLE_NAME + " set name=? ,username= ? ,password=?", new Object[]{name, username, newPassword});
                                    wdb.close();
                                    SuperToast.show("修改成功");
                                    cursor.close();
                                }
                            } else {
                                SuperToast.show("该用户不存在");
                            }
                        }
                        SuperToast.show("输入的旧密码不正确");
                    }
                }
            } else {
                SuperToast.show("两次密码输入不正确");
            }
        }
    }

    @Override
    protected void initDatum() {
        super.initDatum();

    }

    @Override
    protected void initViews() {
        super.initViews();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("返回登录");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
