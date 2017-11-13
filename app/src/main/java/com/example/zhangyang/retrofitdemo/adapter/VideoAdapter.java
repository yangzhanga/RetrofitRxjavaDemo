package com.example.zhangyang.retrofitdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.bean.VideoInfo;

import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import video.media.AndroidMediaController;
import video.media.IjkVideoView;

/**
 * Created by zhangyang on 2017/11/9.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHoder> implements View.OnClickListener,View.OnLongClickListener{
    private Context context;
    private List<VideoInfo> list;
    private MyRecycleAdapter.OnRecyclerViewItemClickListener recyclerViewItemClickListener;


    public VideoAdapter(Context context, List<VideoInfo> list) {
        this.context = context;
        this.list = list;
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent,false);
        MyViewHoder myViewHoder=new MyViewHoder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return myViewHoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.titleTv.setText(list.get(position).getTitle());
        holder.countTv.setText("播放"+list.get(position).getPlaycount()+"次");
        holder.nameTv.setText(list.get(position).getName());
        holder.ijkVideoView.setVideoPath(list.get(position).getUrl());
//使用默认的控制界面，进度条快进等等
//        holder.ijkVideoView.setMediaController(new AndroidMediaController(context,false));
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public void onClick(View v) {
        if (recyclerViewItemClickListener!=null){
            recyclerViewItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }
    @Override
    public boolean onLongClick(View v) {
        if (recyclerViewItemClickListener!=null){
            recyclerViewItemClickListener.onItemLongClick(v, (Integer) v.getTag());
        }
        return false;
    }

    static class MyViewHoder  extends RecyclerView.ViewHolder{
        private TextView titleTv,nameTv,countTv;
        private IjkVideoView ijkVideoView;
        public MyViewHoder(View itemView) {
            super(itemView);
            ijkVideoView= (IjkVideoView) itemView.findViewById(R.id.ijkVideo);
            titleTv= (TextView) itemView.findViewById(R.id.tvTitle);
            nameTv= (TextView) itemView.findViewById(R.id.tvUsername);
            countTv=(TextView) itemView.findViewById(R.id.tvPlayCount);
        }
    }

    public void setRecyclerViewItemClickListener(MyRecycleAdapter.OnRecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }
}
