package com.bin.smartnews.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bin.smartnews.activity.MainActivity;
import com.bin.smartnews.base.BaseFragment;
import com.bin.smartnews.base.BaseLoadNetDataOperator;
import com.bin.smartnews.bean.NewsCenterBean;
import com.bin.smartnews.utils.Constant;
import com.bin.smartnews.utils.DebugLog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class NewsCenterTabFragment extends BaseFragment implements BaseLoadNetDataOperator {
    private static final String TAG = "NewsCenterFragment";
    private NewsCenterBean newsCenterBean;
    @Override
    public void initTitle() {
        setIbMenuDisplayState(true);
        setIbPicTypeDisplayState(false);
        setTitle("新闻中心");
    }

    @Override
    public View createContent() {
        return null;
    }


    @Override
    public void loadNetData() {
        final String url = Constant.NEWSCENTER_URL;
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "获取新闻中心数据失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugLog.i(TAG, response);
                        //把response  == json 转换成对应的数据模型
                        processData(response);

                    }
                });
    }
    //把Json格式的字符串转换成对应的模型对象
    public void processData(String json){
        Gson gson = new Gson();
        newsCenterBean = gson.fromJson(json, NewsCenterBean.class);
        //把数据传递给MainActivity
        ((MainActivity)getActivity()).setNewsCenterMenuBeanList(newsCenterBean.data);
    }

}
