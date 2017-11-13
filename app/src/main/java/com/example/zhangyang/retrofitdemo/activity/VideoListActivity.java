package com.example.zhangyang.retrofitdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zhangyang.retrofitdemo.BaseActivity;
import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.adapter.VideoAdapter;
import com.example.zhangyang.retrofitdemo.bean.VideoInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import video.media.IjkVideoView;

/**
 * Created by zhangyang on 2017/11/9.
 */

public class VideoListActivity extends BaseActivity {
    private List<VideoInfo> list=new ArrayList<>();
    private RecyclerView recycleview;
    private VideoAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_videolist);

        for (int i=0;i<50;i++){
            list.add(new VideoInfo("title"+i,"http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4",
                    "username"+i, new Random().nextInt(10000)+""));
        }
        recycleview= (RecyclerView) findViewById(R.id.recycleview);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter=new VideoAdapter(this,list);
        recycleview.setLayoutManager(mLayoutManager);
        recycleview.setAdapter(adapter);
        recycleview.setItemAnimator(new DefaultItemAnimator());
        recycleview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recycleview.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstPos = mLayoutManager.findFirstVisibleItemPosition();
                RecyclerView.ViewHolder vh = recycleview.findViewHolderForLayoutPosition(firstPos);
                IjkVideoView ijk =(IjkVideoView) vh.itemView.findViewById(R.id.ijkVideo);
                //不滚动时
                if (newState==RecyclerView.SCROLL_STATE_IDLE){
                    ijk.start();
                }else if (newState==RecyclerView.SCROLL_STATE_DRAGGING){
                    ijk.pause();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
