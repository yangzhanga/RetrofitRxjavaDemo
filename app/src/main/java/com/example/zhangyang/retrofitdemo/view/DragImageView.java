package com.example.zhangyang.retrofitdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by zhangyang on 2017/10/25.
 */

public class DragImageView extends android.support.v7.widget.AppCompatImageView {
    private int lastX, lastY;

    public DragImageView(Context context) {
        super(context);
        bringToFront();
    }

    public DragImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bringToFront();

    }

    public DragImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bringToFront();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("DragImageView","onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e("DragImageView","onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }
    //调用invalidate   只会执行onDraw方法
    //调用requestLayout  则会执行onMeasure ，onLayout，onDraw方法，将绘制流程再走一遍
    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("DragImageView","onDraw");
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //getRawX 和getX的区别   getRawX获取的是相对于屏幕左上角的坐标，而getX获取的是相对于父控件的坐标
        int startX = (int) event.getRawX();
        int startY = (int) event.getRawY();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                int dx = startX - lastX;
                int dy = startY - lastY;

                int left = getLeft() + dx;
                int right = getRight() + dx;
                int top = getTop() + dy;
                int bottom = getBottom() + dy;

                //判断移动是否超出屏幕
                if (left < 0) {
                    left = 0;
                    right = getWidth() + left;
                }
                if (top < 0) {
                    top = 0;
                    bottom = getHeight() + top;
                }
                if (right > screenWidth) {
                    right = screenWidth;
                    left = right - getWidth();
                }
                if (bottom > screenHeight) {
                    bottom = screenHeight;
                    top = bottom - getHeight();
                }

                layout(left, top, right, bottom);

                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        lastX = startX;
        lastY = startY;

        return true;//如果返回true,从手指接触屏幕到手指离开屏幕，将不会触发点击事件
    }
}
