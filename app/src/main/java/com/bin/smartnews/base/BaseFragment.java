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
import com.bin.smartnews.activity.MainActivity;

public abstract class BaseFragment extends Fragment {

    private ImageButton ibMenu;
    private ImageView ibPicType;
    private TextView tvTitle;
    private FrameLayout containers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //实例化一般是由类创建的对象，在构造一个实例的时候需要在内存中开辟空间，
        // 即   Student   s = new Student();
        //初始化   实例化的基础上，并且对 对象中的值进行赋一下初始值
        //此处用inflate方法自己实例化一个view
        View view = inflater.inflate(R.layout.fragment_tab_base, container, false);
        //在Fragment中需要使用getView().find...但是已经实例化过view所以直接用
        ibMenu = view.findViewById(R.id.ib_menu);
        ibPicType = view.findViewById(R.id.ib_pic_type);
        tvTitle = view.findViewById(R.id.tv_title);
        containers = view.findViewById(R.id.container);
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
        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //对于侧滑菜单进行切换
                //目标：获取SlidingMenu -->MainActivity
                //错误: slidingMenu 在 MainActivity 中是 private 访问控制
            //((MainActivity)getActivity()).slidingMenu.toggle();
                ((MainActivity)getActivity()).getSlidingMenu().toggle();
            }
        });
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
