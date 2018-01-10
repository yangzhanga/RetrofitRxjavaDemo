package com.example.zhangyang.retrofitdemo.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.zhangyang.retrofitdemo.BaseActivity;
import com.example.zhangyang.retrofitdemo.Http.HttpManager;
import com.example.zhangyang.retrofitdemo.Http.HttpResponse;
import com.example.zhangyang.retrofitdemo.Http.RxSchedulers;
import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.adapter.MyRecycleAdapter;
import com.example.zhangyang.retrofitdemo.api.HomeApi;
import com.example.zhangyang.retrofitdemo.bean.Home;
import com.example.zhangyang.retrofitdemo.observer.HttpObserver;

import java.util.List;

/**
 * Created by zhangyang on 2018/1/4.
 */

public class ScrollToolbarActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private MyRecycleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Home> listData;
    private int page=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolltoolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyRecycleAdapter(ScrollToolbarActivity.this, listData);
        mAdapter.openLoadAnimation();//开启加载动画
        mAdapter.isFirstOnly(false);//是否只显示一次动画
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        initData();
    }

    private void initData() {
        getData(1);
    }

    private void getData(int p) {

        HttpManager.getInstance()
                .createService(HomeApi.class)
                .getAndroidData(p)
                .compose(RxSchedulers.<HttpResponse<List<Home>>>compose())
                .subscribe(new HttpObserver<List<Home>>(ScrollToolbarActivity.this, true) {
                    @Override
                    protected void onFailed(Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(List<Home> list) {
                        if (page == 1) {
                            mAdapter.updateData(list);
                        } else {
                            mAdapter.addData(list);
                        }
                    }

                });
    }
}
