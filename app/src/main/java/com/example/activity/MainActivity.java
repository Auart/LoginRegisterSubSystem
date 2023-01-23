package com.example.activity;

import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.R;
import com.example.adapter.NavBottomAdapter;
import com.example.databinding.ActivityMainBinding;
import com.example.fragment.HomeFragment;
import com.example.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseViewModelActivity<ActivityMainBinding> {

    private com.example.databinding.BottomNavLayoutBinding bottomNavLayoutBinding;

    @Override
    protected void initListens() {
        super.initListens();

        bottomNavLayoutBinding.navHome.setOnClickListener((view) -> viewBinding.viewpager.setCurrentItem(0));
        bottomNavLayoutBinding.navMine.setOnClickListener((view) -> viewBinding.viewpager.setCurrentItem(1));


        //viewPageChange
        viewBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pageSelected(0);
    }

    private void resetColor() {
        bottomNavLayoutBinding.ivHome.setSelected(false);
        bottomNavLayoutBinding.ivMine.setSelected(false);
        bottomNavLayoutBinding.tvHome.setTextColor(getResources().getColor(R.color.black));
        bottomNavLayoutBinding.tvMine.setTextColor(getResources().getColor(R.color.black));
    }

    private void pageSelected(int position) {
        resetColor();
        @SuppressLint("ResourceAsColor") Runnable[] runnable = {
                () -> {
                    bottomNavLayoutBinding.ivHome.setSelected(true);
                    bottomNavLayoutBinding.tvHome.setTextColor(getResources().getColor(R.color.colorPrimary));
                },
                () -> {
                    bottomNavLayoutBinding.ivMine.setSelected(true);
                    bottomNavLayoutBinding.tvMine.setTextColor(getResources().getColor(R.color.colorPrimary));
                },

        };
        runnable[position].run();
    }


    @Override
    protected void initDatum() {
        super.initDatum();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());

        //获取底导航适配器
        NavBottomAdapter bottomNavigateAdapter = new NavBottomAdapter(getSupportFragmentManager(), fragmentList);
        //将适配器添加到viewPager中
        viewBinding.viewpager.setAdapter(bottomNavigateAdapter);

    }


    @Override
    protected void initViews() {
        super.initViews();
        bottomNavLayoutBinding = viewBinding.bottomNavLayout;
    }
}