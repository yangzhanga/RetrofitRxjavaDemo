package com.example.zhangyang.retrofitdemo.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchUIUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.bean.Home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhangyang on 2017/9/8.
 */

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHoder> implements ItemTouchHelperAdapter{
    private Context context;
    private List<Home> list;
    private OnRecyclerViewItemClickListener recyclerViewItemClickListener;
    private boolean mOpenAnimationEnable = false;
    private boolean mFirstOnlyEnable = true;
    private int mDuration = 300;
    private int mLastPosition = -1;
    private Interpolator mInterpolator = new LinearInterpolator();

    public MyRecycleAdapter(Context context, List<Home> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 开启加载动画
     */
    public void openLoadAnimation() {
        this.mOpenAnimationEnable = true;
    }

    /**
     * true  每一个item只显示一遍动画
     * @param firstOnly
     */
    public void isFirstOnly(boolean firstOnly) {
        this.mFirstOnlyEnable = firstOnly;
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
        //之所以不用notifyDataSetChanged(); 是避免加载更多时  当前屏幕的item重复执行动画
        notifyItemRangeInserted(list.size()-newList.size(),newList.size());
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
     *  移动
     * @param fromPosition
     * @param toPosition
     */
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if(list == null || list.isEmpty()) {
            return;
        }
        Collections.swap(list,fromPosition,toPosition);
        notifyItemMoved(fromPosition, toPosition);
        notifyItemChanged(fromPosition);
        notifyItemChanged(toPosition);

    }

    /**
     * 删除
     * @param position
     */
    @Override
    public void onItemDissmiss(int position) {
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
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item, parent, false);
        MyViewHoder myViewHoder=new MyViewHoder(view,recyclerViewItemClickListener);

        return myViewHoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        addAnimation(holder);

        holder.titleTv.setText(list.get(position).getDesc());
        holder.nameTv.setText(list.get(position).getWho());
        holder.timeTv.setText(list.get(position).getPublishedAt().replace("T"," ").replace("Z"," "));
        if (list.get(position).getImages()!=null&&list.get(position).getImages().get(0)!=null){
            holder.img.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(list.get(position).getImages().get(0))
                    .centerCrop()
                    .crossFade()
                    .into(holder.img);
        }else {
            holder.img.setVisibility(View.GONE);
        }
//        if (position==3 ||position==6) {
//            holder.itemView.setTag(true);
//        }else {
//            holder.itemView.setTag(false);
//        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }



    /**
     * 当item进入到屏幕时调用
     * 和onBindViewHolder大致相同，在onBindViewHolder之后调用
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(MyViewHoder holder) {
        super.onViewAttachedToWindow(holder);
    }

    /**
     * 显示RecyclerView时调用一次
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 添加动画
     * @param holder
     */
    private void addAnimation(RecyclerView.ViewHolder holder) {
        if (mOpenAnimationEnable) {
            if (!mFirstOnlyEnable || holder.getLayoutPosition() > mLastPosition) {
                /**
                 * 可以随意组合
                 */
                //从右往左
//                Animator[] animations =new Animator[]{
//                        ObjectAnimator.ofFloat(holder.itemView, "translationX", holder.itemView.getRootView().getWidth(), 0)
//                 };
                //从左往右
//                Animator[] animations =new Animator[]{
//                        ObjectAnimator.ofFloat(holder.itemView, "translationX", -holder.itemView.getRootView().getWidth(), 0)
//                };

                //缩放
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(holder.itemView, "scaleX", .5f, 1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(holder.itemView, "scaleY", .5f, 1f);
                Animator[] animations =new Animator[]{scaleX,scaleY};
                //透明到显示
//                Animator[] animations =new Animator[]{
//                       ObjectAnimator.ofFloat(holder.itemView, "alpha", 0f, 1f)
//                };
//
                for (Animator anim : animations) {
                    startAnim(anim, holder.getLayoutPosition());
                }
                mLastPosition = holder.getLayoutPosition();
            }
        }
    }

    private void startAnim(Animator anim, int index) {
        anim.setDuration(mDuration).start();
        anim.setInterpolator(mInterpolator);
    }

    static class MyViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView titleTv, nameTv, timeTv;
        private ImageView img;
        private OnRecyclerViewItemClickListener mRecyclerViewItemClickListener;

        public MyViewHoder(View itemView,OnRecyclerViewItemClickListener recyclerViewItemClickListener) {
            super(itemView);

            mRecyclerViewItemClickListener=recyclerViewItemClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnClickListener(this);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            nameTv = (TextView) itemView.findViewById(R.id.nameTv);
            timeTv = (TextView) itemView.findViewById(R.id.timeTv);
            img = (ImageView) itemView.findViewById(R.id.img);

        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewItemClickListener!=null){
                mRecyclerViewItemClickListener.onItemClick(v,getLayoutPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(mRecyclerViewItemClickListener != null){
                mRecyclerViewItemClickListener.onItemLongClick(v,getLayoutPosition());
            }
            return true;
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
