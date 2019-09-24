package com.bin.smartnews.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bin.smartnews.R;

public abstract class BaseFragment extends Fragment {

    private ImageButton ibMenu;
    private ImageView ibPicType;
    private TextView tvTitle;
    private FrameLayout containers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_base, container, false);
        //在Fragment中需要使用
        ibMenu = getView().findViewById(R.id.ib_menu);
        ibPicType = getView().findViewById(R.id.ib_pic_type);
        tvTitle = getView().findViewById(R.id.tv_title);
        containers = getView().findViewById(R.id.container);
        return view;
    }

    /**
     * onCreate是指创建该fragment，类似于Activity.onCreate，你可以在其中初始化除了view之外的东西。
     * onCreateView是创建该fragment对应的视图，你必须在这里创建自己的视图并返回给调用者。
     * onViewCreated在onCreateView执行完后立即执行
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitle();
    }

    //初始化标题   让每个子类去进行实现
    public abstract void initTitle();
    //设置Menu的显示状态
    public void setIbMenuDisplayState(boolean isShow){
        ibMenu.setVisibility(isShow?View.VISIBLE:View.GONE);
    }
    //设置PicType的显示状态
    public void setIbPicTypeDisplayState(boolean isShow){
        ibPicType.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //设置标题内容
    public void setTitle(String title){
        tvTitle.setText(title);
    }

    //创建内容
    public abstract View createContent();

    //向容器里面添加内容
    public void addView(View view){
        //清空原来的内容
        containers.removeAllViews();
        //添加内容
        containers.addView(view);
    }


}
