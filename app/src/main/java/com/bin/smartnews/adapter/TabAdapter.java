package com.bin.smartnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

//通过FragmentPagerAdapter来创建Fragment，并提供给ViewPager进行控制
public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public TabAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    //获取对应位置的Fragment
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    //页面的数量
    @Override
    public int getCount() {
        return fragments.size();
    }
}
