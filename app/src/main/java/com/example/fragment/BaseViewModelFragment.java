package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.example.utils.ViewBindingUtil;

public abstract class BaseViewModelFragment<T extends ViewBinding> extends BaseFragment{
    protected  T viewBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding= ViewBindingUtil.getViewBinding(getLayoutInflater(),getClass());

    }

    @Override
    public View getLayoutInflater(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return viewBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewBinding=null;
    }
}
