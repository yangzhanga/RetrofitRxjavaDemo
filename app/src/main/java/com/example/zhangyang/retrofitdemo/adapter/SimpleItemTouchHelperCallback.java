package com.example.zhangyang.retrofitdemo.adapter;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangyang on 2018/1/10.
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private ItemTouchHelperAdapter mAdapter;

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    /**
     * 该方法用于返回可以滑动的方向，比如说允许从右到左侧滑，允许上下拖动等。
     * 我们一般使用makeMovementFlags(int,int)或makeFlag(int, int)来构造我们的返回值
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int moveFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlag= ItemTouchHelper.LEFT;
        return makeMovementFlags(moveFlag,swipeFlag);
    }

    /**
     * 当用户拖动一个Item进行上下移动从旧的位置到新的位置的时候会调用该方法，
     * 在该方法内，我们可以调用Adapter的notifyItemMoved方法来交换两个ViewHolder的位置，
     * 最后返回true，表示被拖动的ViewHolder已经移动到了目的位置。
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * 当用户左右滑动Item达到删除条件时，会调用该方法，一般手指触摸滑动的距离达到RecyclerView宽度的一半时，
     * 再松开手指，此时该Item会继续向原先滑动方向滑过去并且调用onSwiped方法进行删除，否则会反向滑回原来的位置。
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDissmiss(viewHolder.getAdapterPosition());
    }

    /**
     *  先于item绘制，绘制在item之下，被item覆盖
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState 拖拽还是侧滑，对应ACTION_STATE_DRAG和ACTION_STATE_SWIPE
     * @param isCurrentlyActive true表示这个Item正在被用户所控制，false则表示它仅仅是在回到原本状态的动画过程当中
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //如果是侧滑
        int x=getSlideLimitation(viewHolder);
        if (actionState==ItemTouchHelper.ACTION_STATE_SWIPE){
            //如果dx小于等于删除方块的宽度，把方块滑出
            if (Math.abs(dX)<=x){
                viewHolder.itemView.scrollTo(-(int)dX,0);
            }

        }else {
            //拖拽状态下不做改变，需要调用父类的方法
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

    }

    /**
     * 获取删除方块的宽度
     */
    private int getSlideLimitation(RecyclerView.ViewHolder viewHolder) {
        ViewGroup itemView = (ViewGroup) viewHolder.itemView;
        return itemView.getChildAt(1).getLayoutParams().width;
    }

    /**
     * 恢复item状态
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        //重置改变，防止由于复用而导致的显示问题
        viewHolder.itemView.setScrollX(0);
    }

    /**
     * 绘制在item之上
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
