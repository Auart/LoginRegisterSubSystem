package com.example.fragment;


import android.os.Bundle;

import com.example.databinding.FragmentHomeBinding;


public class HomeFragment extends BaseViewModelFragment <FragmentHomeBinding> {
    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        viewBinding.webView.loadUrl("https://www.chzc.edu.cn/xxx/xwdt/tpxw.htm");
    }
}
