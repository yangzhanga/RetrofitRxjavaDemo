package com.example.zhangyang.retrofitdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangyang.retrofitdemo.R;

/**
 * Created by zhangyang on 2018/4/2.
 */

public class FragmentForTab extends Fragment {

    private Bundle arg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arg = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, null);
        TextView tv = view.findViewById(R.id.tv);
        int page = arg.getInt("pager_num");

        if (page == 1) {
            view.setBackgroundResource(R.color.colorAccent);
        } else if (page == 2) {
            view.setBackgroundResource(R.color.colorGreen);
        } else if (page == 3) {
            view.setBackgroundResource(R.color.colorYellow);
        } else if (page == 4) {
            view.setBackgroundResource(R.color.colorPrimary);
        } else if (page == 5) {
            view.setBackgroundResource(R.color.colorAccent);
        } else if (page == 6) {
            view.setBackgroundResource(R.color.colorGreen);
        } else if (page == 7) {
            view.setBackgroundResource(R.color.colorYellow);
        } else if (page == 8) {
            view.setBackgroundResource(R.color.colorPrimary);
        }

        tv.setText(arg.getString("Title"));
        return view;
    }


    public static FragmentForTab newInstance(Bundle args) {
        FragmentForTab fragment = new FragmentForTab();
        fragment.setArguments(args);
        return fragment;
    }
}
