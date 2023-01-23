package com.example.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initViews();

        initDatum();

        initListens();
    }

    protected void initListens() {


    }

    protected void initDatum() {

    }

    protected void initViews() {
    }


}
