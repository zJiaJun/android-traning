package com.zjiajun.firstapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjiajun.firstapp.R;

/**
 * Created by zhujiajun
 * 15/7/8 17:58
 */
public class AnotherBottomFragment extends Fragment {

    public AnotherBottomFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_another_bottom,container,false);
    }
}
