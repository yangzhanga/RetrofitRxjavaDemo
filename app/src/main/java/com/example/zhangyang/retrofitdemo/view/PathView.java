package com.example.zhangyang.retrofitdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangyang on 2017/11/13.
 */

public class PathView extends View {
    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        Path path = new Path();

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        paint.setAntiAlias(true);
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(30);
        paint2.setAntiAlias(true);

        paint.setStrokeJoin(Paint.Join.MITER);
        path.moveTo(100, 100);
        path.rLineTo(200, 0);
        path.rLineTo(-100, 100);
        canvas.drawPath(path, paint);

        paint2.setStrokeJoin(Paint.Join.ROUND);
        path.moveTo(600, 100);
        path.rLineTo(200, 0);
        path.rLineTo(-100, 100);
        canvas.drawPath(path, paint2);

        paint.setStrokeJoin(Paint.Join.BEVEL);
        path.moveTo(350, 100);
        path.rLineTo(200, 0);
        path.rLineTo(-100, 100);
        canvas.drawPath(path, paint);



        path(canvas);




    }

    /**
     * 画折线图
     */
    private void path(Canvas canvas) {
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        Path path=new Path();

        path.moveTo(100,500);
        path.rLineTo(100,-200);
        path.rLineTo(100,100);
        path.rLineTo(100,-100);
        path.rLineTo(100,300);
        path.rLineTo(100,-200);

        Path pathDraw=new Path();
        pathDraw.addCircle(0,0,10, Path.Direction.CW);
        PathEffect pathEffect = new PathDashPathEffect(pathDraw,30,0,PathDashPathEffect.Style.TRANSLATE);

        paint.setPathEffect(pathEffect);
        canvas.drawPath(path,paint);

        Paint paint1=new Paint();
        paint1.setShadowLayer(2,0,0,Color.RED);
        paint1.setColor(Color.BLACK);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setTextSize(40);
//        paint1.setUnderlineText(true);//下划线
        paint1.setStrikeThruText(true);//删除线
        canvas.drawText("折线图",300,700,paint1);

    }
}
