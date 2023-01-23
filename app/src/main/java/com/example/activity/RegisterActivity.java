package com.example.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.R;
import com.example.database.UserDBHelper;
import com.example.databinding.ActivityResgisterBinding;
import com.example.utils.HashMapProxy;
import com.example.utils.SuperToast;
import java.util.Objects;

public class RegisterActivity extends BaseViewModelActivity<ActivityResgisterBinding> implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    private String phone;
    private String name;
    private String username;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
    }

    @Override
    protected void initListens() {
        super.initListens();
        viewBinding.btnRegister.setOnClickListener(this);
        viewBinding.goLogin.setOnClickListener((v) -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }

    private final HashMapProxy<Integer, Runnable> onClickMap = new HashMapProxy<>();

    @Override
    public void onClick(View v) {
        Objects.requireNonNull(onClickMap.putObj(R.id.btn_register, this::register).get(v.getId())).run();
    }

    private void register() {
        phone = viewBinding.editTextPhone.getText().toString();
        name = viewBinding.editTextName.getText().toString();
        username = viewBinding.editTextTextPersonName.getText().toString();
        password = viewBinding.editTextTextPassword.getText().toString();
        UserDBHelper userDBHelper = UserDBHelper.getInstance(this);
        HashMapProxy<String, Runnable> checkUser = new HashMapProxy<>();
        //消除大量 if else
        Runnable runnable = checkUser
                .putObj(phone, () -> SuperToast.show("手机号不能为空"))
                .putObj(name, () -> SuperToast.show("姓名不能为空"))
                .putObj(username, () -> SuperToast.show("用户名不能为空"))
                .putObj(password, () -> SuperToast.show("密码不能为空"))
                .get("");
        if (runnable != null) {
            runnable.run();
        } else {
            SQLiteDatabase db = userDBHelper.getWritableDatabase();
            if (db.isOpen()) {
                db.execSQL("insert into " + UserDBHelper.TABLE_NAME + "(phone,name,username,password) values (?,?,?,?)", new Object[]{phone, name, username, password});
                db.close();
                SuperToast.show("注册成功");
                startActivity(new Intent(this, LoginActivity.class));
            }
        }
    }
}
