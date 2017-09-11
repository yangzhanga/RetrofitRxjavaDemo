package com.example.zhangyang.retrofitdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public void updateData(List<Home> list) {
        this.list = list;
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
        notifyItemInserted(position);
        notifyItemRangeChanged(position,list.size()-position);
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

        ((MyViewHoder)holder).titleTv.setText(list.get(position).getDesc());
        ((MyViewHoder)holder).itemView.setTag(position);
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
    private TextView titleTv;
        public MyViewHoder(View itemView) {
            super(itemView);

            titleTv= (TextView) itemView.findViewById(R.id.titleTv);
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
