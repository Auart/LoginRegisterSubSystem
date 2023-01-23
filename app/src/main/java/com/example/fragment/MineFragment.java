package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.activity.LoginActivity;
import com.example.databinding.FragmentMineBinding;
public class MineFragment extends BaseViewModelFragment<FragmentMineBinding> {
    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initDatum() {
        super.initDatum();
    }

    @Override
    protected void initListens() {
        super.initListens();
        viewBinding.logout.setOnClickListener(this::logout);
    }

    private void logout(View view) {
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().onBackPressed();
    }
}



