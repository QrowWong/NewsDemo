package com.bin.smartnews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bin.smartnews.R;
import com.bin.smartnews.adapter.GuideVPAdapter;
import com.bin.smartnews.sp.SPUtils;

import java.util.ArrayList;
import java.util.List;


public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "GuideActivity";
    private ViewPager vp;
    //向导图片
    private int[] imgs = new int[]{R.drawable.setting, R.drawable.setting_press, R.drawable.smartservice_press};
    private Button btStart;
    private LinearLayout containerGrayPoint;
    //小灰点之间的宽度
    private int width;
    private View redPoint;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        vp = findViewById(R.id.vp);
        btStart = findViewById(R.id.bt_start);
        containerGrayPoint = findViewById(R.id.container_gray_point);
        redPoint = findViewById(R.id.red_point);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initViewPager();
        initGrayPoint();
    }

    private void initGrayPoint() {
        for (int resId : imgs) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.point_gray_bg);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50,50);
            params.rightMargin = 20;//设置右边距
            containerGrayPoint.addView(view,params);
        }

    }

    private void initViewPager() {
        List<ImageView> views = new ArrayList<>();
        for (int resId:imgs){
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(resId);
            views.add(iv);
        }
        vp.setAdapter(new GuideVPAdapter(views));
        //设置页面的滑动监听
        vp.addOnPageChangeListener(this);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.saveBoolean(getApplicationContext(), "KEY_HAS_GUIDE",true);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     *
     * @position 当前滑动页面的下标
     * @positionOffset 页面的滑动比率
     * @positionOffsetPixels 页面滑动的实际像素
     *
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i(TAG,"position:"+position+",positionOffset:"+positionOffset
                +",positionOffsetPixels:"+positionOffsetPixels);

        if(width == 0){
            width = containerGrayPoint
                    .getChildAt(1)
                    .getLeft() - containerGrayPoint.getChildAt(0).getLeft();
            Log.i(TAG,"width:"+width);
        }
        //修改小红点与相对布局的左边距
        //根据当前的ViewPager移动的索引*小红点移动的距离 + 小红点移动距离 * 滑动百分比
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
        params.leftMargin = (int)(position*width + width*positionOffset);
        redPoint.setLayoutParams(params);

    }

    @Override
    public void onPageSelected(int position) {
        if (position == imgs.length - 1) {
            //显示按钮
            btStart.setVisibility(View.VISIBLE);
        } else {
            //隐藏按钮
            btStart.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
