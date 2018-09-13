package com.example.zhangyang.retrofitdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.zhangyang.retrofitdemo.BaseActivity;
import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.fragment.FragmentForTab;
import com.example.zhangyang.retrofitdemo.fragment.FragmentOne;
import com.example.zhangyang.retrofitdemo.ViewPageTransformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyang on 2018/4/2.
 */

public class TabActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private String[] titles = {"页面1", "页面2", "页面3","页面4", "页面5", "页面6","页面7", "页面8"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        initView();
        initData();
    }

    private void initView() {
        mTabLayout = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.viewpager);
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        Bundle bundle1 = new Bundle();
        bundle1.putString("Title", "第一个Fragment");
        bundle1.putInt("pager_num", 1);
        FragmentOne fg1 = FragmentOne.newInstance(bundle1);

        Bundle bundle2 = new Bundle();
        bundle2.putString("Title", "第二个Fragment");
        bundle2.putInt("pager_num", 2);
        Fragment fg2 = FragmentForTab.newInstance(bundle2);

        Bundle bundle3 = new Bundle();
        bundle3.putString("Title", "第三个Fragment");
        bundle3.putInt("pager_num", 3);
        Fragment fg3 = FragmentForTab.newInstance(bundle3);

        Bundle bundle4 = new Bundle();
        bundle4.putString("Title", "第四个Fragment");
        bundle4.putInt("pager_num", 4);
        Fragment fg4 = FragmentForTab.newInstance(bundle4);

        Bundle bundle5 = new Bundle();
        bundle5.putString("Title", "第五个Fragment");
        bundle5.putInt("pager_num", 5);
        Fragment fg5 = FragmentForTab.newInstance(bundle5);

        Bundle bundle6 = new Bundle();
        bundle6.putString("Title", "第六个Fragment");
        bundle6.putInt("pager_num", 6);
        Fragment fg6 = FragmentForTab.newInstance(bundle6);

        Bundle bundle7 = new Bundle();
        bundle7.putString("Title", "第七个Fragment");
        bundle7.putInt("pager_num", 7);
        Fragment fg7 = FragmentForTab.newInstance(bundle7);

        Bundle bundle8 = new Bundle();
        bundle8.putString("Title", "第八个Fragment");
        bundle8.putInt("pager_num", 8);
        Fragment fg8 = FragmentForTab.newInstance(bundle8);

        fragments.add(fg1);
        fragments.add(fg2);
        fragments.add(fg3);
        fragments.add(fg4);
        fragments.add(fg5);
        fragments.add(fg6);
        fragments.add(fg7);
        fragments.add(fg8);

        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments,titles));
        mViewPager.setOffscreenPageLimit(3);//设置缓存的页面数量
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());

        mTabLayout.setupWithViewPager(mViewPager);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        private String[] titles;
        public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, String[] titles) {
            super(fm);
            fragmentList = fragments;
            this.titles=titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
