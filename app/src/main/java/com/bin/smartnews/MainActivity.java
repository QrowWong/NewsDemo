package com.bin.smartnews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private List<Fragment> fragments;
    private RadioButton rb_newscenter;
    private RadioButton rb_smartservice;
    private RadioButton rb_govaffairs;
    private RadioButton rb_setting;
    private RadioButton rb_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initVP();
    }

    //初始化控件
    private void initView() {
        vp = findViewById(R.id.vp);
        RadioGroup rg_tab = findViewById(R.id.rg_tab);
        //设置选择改变监听
        rg_tab.setOnCheckedChangeListener(this);
        rb_home =  findViewById(R.id.rb_home);
        rb_newscenter = findViewById(R.id.rb_newscenter);
        rb_smartservice =  findViewById(R.id.rb_smartservice);
        rb_govaffairs = findViewById(R.id.rb_govaffairs);
        rb_setting =  findViewById(R.id.rb_setting);
    }

    //初始化ViewPager
    private void initVP() {
        fragments = new ArrayList<>();
        fragments.add(new HomeTabFragment());
        fragments.add(new NewsCenterTabFragment());
        fragments.add(new SmartServiceTabFragment());
        fragments.add(new GovaffairsTabFragment());
        fragments.add(new SettingTabFragment());
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),fragments);
        //调用ViewPager 的 setAdapter方法，将Fragment和ViewPager进行绑定
        vp.setAdapter(adapter);
        //给ViewPager设置页面滑动改变监听
        vp.addOnPageChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int item = 0;
        switch (checkedId) {
            //根据相对应的ID赋值，用以切换相应界面
            case R.id.rb_home:
                item = 0;
                break;
            case R.id.rb_newscenter:
                item = 1;
                break;
            case R.id.rb_smartservice:
                item = 2;
                break;
            case R.id.rb_govaffairs:
                item = 3;
                break;
            case R.id.rb_setting:
                item = 4;
                break;
        }
            //ViewPager切换到对应的页面
            vp.setCurrentItem(item,false);//false 不需要Viewpager页面切换的时候有滑动的动画

        }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    //实现ViewPager滑动监听的回调方法，监听滑动事件，在onPageSelected方法中，
    //根据选中的index，切换底部的tab。

    /**
     * onPageScrolled()：当viewpager滑动时，回调该方法
     * onPageSelected()：当界面被选中时，回调该方法
     * onPageScrollStateChanged()：当ViewPager滑动状态发生变化时，回调该方法
     */
    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rb_home.setChecked(true);
                break;
            case 1:
                rb_newscenter.setChecked(true);
                break;
            case 2:
                rb_smartservice.setChecked(true);
                break;
            case 3:
                rb_govaffairs.setChecked(true);
                break;
            case 4:
                rb_setting.setChecked(true);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
