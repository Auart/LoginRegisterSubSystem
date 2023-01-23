package com.example.activity;

import android.content.Intent;
import com.example.databinding.ActivitySplashBinding;
public class SplashActivity extends BaseViewModelActivity<ActivitySplashBinding> {

    @Override
    protected void initListens() {
        super.initListens();
        viewBinding.title.postDelayed(this::next,3000);
    }

    private void next() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
