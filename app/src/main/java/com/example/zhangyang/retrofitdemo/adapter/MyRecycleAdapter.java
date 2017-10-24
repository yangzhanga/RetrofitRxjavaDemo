package com.example.zhangyang.retrofitdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.bean.Home;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyang on 2017/9/8.
 */

public class MyRecycleAdapter extends RecyclerView.Adapter implements View.OnClickListener,View.OnLongClickListener{
    private Context context;
    private List<Home> list;
    private OnRecyclerViewItemClickListener recyclerViewItemClickListener;

    public MyRecycleAdapter(Context context, List<Home> list) {
        this.context = context;
        this.list = list;
    }

    public List<Home> getList() {
        return list;
    }

    public void setList(List<Home> list) {
        this.list = list;
    }

    public void updateData(List<Home> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void addData(List<Home> newList) {
        if(list == null) {
            list = new ArrayList<>();
        }
        list.addAll(newList);
        notifyDataSetChanged();
    }
    /**
     * 添加新的Item
     */
    public void addNewItem(int position,Home home) {
        if(list == null) {
            list = new ArrayList<>();
        }
        list.add(position, home);
        notifyItemInserted(position);//仅仅是起到界面动画的效果，实际上并没有进行数据与界面的重新绑定 点击对应的position会不对
        notifyItemRangeChanged(position,list.size()-position);//通知重新绑定某一范围内的的数据与界面
    }

    /**
     * 删除Item
     */
    public void deleteItem(int position) {
        if(list == null || list.isEmpty()) {
            return;
        }
        list.remove(position);
        notifyItemRemoved(position);
        if(position!=list.size()){
            notifyItemRangeRemoved(position,list.size()-position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item, parent, false);
        MyViewHoder myViewHoder=new MyViewHoder(view);

        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return myViewHoder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHoder viewHoder=(MyViewHoder)holder;
        viewHoder.titleTv.setText(list.get(position).getDesc());
        viewHoder.nameTv.setText(list.get(position).getWho());
        viewHoder.timeTv.setText(list.get(position).getPublishedAt().replace("T"," ").replace("Z"," "));
        if (list.get(position).getImages()!=null&&list.get(position).getImages().get(0)!=null){
            viewHoder.img.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(list.get(position).getImages().get(0))
                    .centerCrop()
                    .crossFade()
                    .into(viewHoder.img);
        }else {
            viewHoder.img.setVisibility(View.GONE);
        }
        viewHoder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
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
    private TextView titleTv,nameTv,timeTv;
        private ImageView img;
        public MyViewHoder(View itemView) {
            super(itemView);

            titleTv= (TextView) itemView.findViewById(R.id.titleTv);
            nameTv= (TextView) itemView.findViewById(R.id.nameTv);
            timeTv=(TextView) itemView.findViewById(R.id.timeTv);
            img= (ImageView) itemView.findViewById(R.id.img);
        }
    }





    public void setRecyclerViewItemClickListener(OnRecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    //自定义监听事件
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
}
