package com.example.zhangyang.retrofitdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.activity.TabActivity;

import java.util.zip.Inflater;

/**
 * Created by zhangyang on 2018/4/2.
 */

public class FragmentOne extends Fragment {

    private Bundle arg;
    private ViewPager mViewpager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arg = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        mViewpager=view.findViewById(R.id.vp);
        VpAdapter vpAdapter=new VpAdapter(getActivity());
        mViewpager.setAdapter(vpAdapter);
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

        return view;
    }


    public static FragmentOne newInstance(Bundle args) {
        FragmentOne fragment = new FragmentOne();
        fragment.setArguments(args);
        return fragment;
    }

    public class VpAdapter extends PagerAdapter {
        private Context context;

        public VpAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_text, null);
            TextView tv = view.findViewById(R.id.tv);

            tv.setText("fragment中嵌套的viewpager"+position);
            container.addView(view);
            return view;

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {  //销毁页卡
            container.removeView((View) object);
        }

    }
}
