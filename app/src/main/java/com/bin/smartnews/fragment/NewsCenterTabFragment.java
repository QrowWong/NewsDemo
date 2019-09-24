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

import com.bin.smartnews.base.BaseFragment;

public class NewsCenterTabFragment extends BaseFragment {

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
}
