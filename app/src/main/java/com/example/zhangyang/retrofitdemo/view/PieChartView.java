package com.example.zhangyang.retrofitdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangyang on 2017/11/13.
 */

public class PieChartView extends View {
    private Paint paint;
    private int color;
    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        RectF rectF=new RectF(100,100,400,400);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawColor(Color.LTGRAY);
        canvas.drawArc(rectF,0,70,true,paint);
        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF,70,100,true,paint);
        paint.setColor(color);
        canvas.drawArc(rectF,170,130,true,paint);
        paint.setColor(Color.parseColor("#f2933f"));
        canvas.drawArc(rectF,300,60,true,paint);
        Path path=new Path();
        path.addArc(rectF,225,315);
        paint.setColor(Color.BLACK);
        paint.setTextSize(36);
        canvas.drawTextOnPath("你好啊阿斯顿撒旦奥术大师",path,0,-10,paint);

    }
}
