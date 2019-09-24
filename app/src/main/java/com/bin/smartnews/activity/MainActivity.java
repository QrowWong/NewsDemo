package com.bin.smartnews.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bin.smartnews.base.BaseFragment;
import com.bin.smartnews.base.BaseLoadNetDataOperator;
import com.bin.smartnews.bean.NewsCenterBean;
import com.bin.smartnews.fragment.GovaffairsTabFragment;
import com.bin.smartnews.fragment.HomeTabFragment;
import com.bin.smartnews.fragment.NewsCenterTabFragment;
import com.bin.smartnews.R;
import com.bin.smartnews.fragment.SettingTabFragment;
import com.bin.smartnews.fragment.SmartServiceTabFragment;
import com.bin.smartnews.adapter.TabAdapter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private static final String TAG = "MainActivity";
    private ViewPager vp;
    private List<Fragment> fragments;
    private RadioButton rb_newscenter;
    private RadioButton rb_smartservice;
    private RadioButton rb_govaffairs;
    private RadioButton rb_setting;
    private RadioButton rb_home;
    private SlidingMenu slidingMenu;

    //侧滑菜单的数据
    private List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList;

    public void setNewsCenterMenuBeanList(List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList) {
        this.newsCenterMenuBeanList = newsCenterMenuBeanList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initVP();
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        //创建侧滑菜单
        slidingMenu = new SlidingMenu(this);
        //设置菜单从左边滑出
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置全屏是否可以滑出菜单
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        //设置侧滑菜单的宽度
        slidingMenu.setBehindWidth(250);
        //把侧滑菜单附加在Activity里面
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //设置侧滑菜单的布局
        slidingMenu.setMenu(R.layout.activity_main_menu);
    }
    public SlidingMenu getSlidingMenu(){
        return slidingMenu;
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
    //点击底部tab切换到对应的Fragment
    //radioGroup添加选中的监听，并记录选中的radiobutton的索引
    //然后，将记录的索引，传递给ViewPager，让ViewPager进行切换到对应Fragment界面
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int item = 0;
        switch (checkedId) {
            //根据相对应的ID赋值，用以切换相应界面
            case R.id.rb_home:
                item = 0;
                //设置无法划出侧滑菜单
                slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_NONE);
                break;
            case R.id.rb_newscenter:
                item = 1;
                slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_smartservice:
                item = 2;
                slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_govaffairs:
                item = 3;
                slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_setting:
                slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_NONE);
                item = 4;
                break;
        }
            //ViewPager切换到对应的页面
            vp.setCurrentItem(item,false);//false 不需要Viewpager页面切换的时候有滑动的动画
        //加载网络数据的入口
        BaseFragment baseFragment = (BaseFragment) fragments.get(item);
        if(baseFragment instanceof BaseLoadNetDataOperator){
            ((BaseLoadNetDataOperator) baseFragment).loadNetData();
        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    //滑动切换下方tab跟着切换
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
