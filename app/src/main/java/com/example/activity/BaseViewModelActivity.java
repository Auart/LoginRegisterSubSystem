package com.example.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.example.utils.ViewBindingUtil;

public class BaseViewModelActivity<T extends ViewBinding> extends BaseLogicActivity {

    protected T viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ViewBindingUtil.getViewBinding(getLayoutInflater(), getClass());
        setContentView(viewBinding.getRoot());
    }
}
