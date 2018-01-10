package com.example.zhangyang.retrofitdemo.adapter;

/**
 * Created by zhangyang on 2018/1/10.
 */

public interface ItemTouchHelperAdapter {
    //数据交换
    void onItemMove(int fromPosition, int toPosition);

    //数据删除
    void onItemDissmiss(int position);
}

