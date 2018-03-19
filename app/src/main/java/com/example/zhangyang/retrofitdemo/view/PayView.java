package com.example.zhangyang.retrofitdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.zhangyang.retrofitdemo.R;

/**
 * Created by zhangyang on 2018/3/13.
 * <p>
 * 在绘画路径时需要用到PathMeasure, PathMeasure主要用来测量path,通过它,我们可以得到路径上特定的点的坐标等等,此外还需要两个动画对象,用来控制路径的动画。
 * <p>
 * nextContour()：移动到下一个轮廓
 * <p>
 * setPath(Path path , boolean isClosed)：给当前PathMeasure对象设置Path
 * <p>
 * getLength()	该方法用于获得path路径的长度
 * <p>
 * getSegment(float startD,float stopD,Path dst,boolean startWithMoveTo)：调用这个方法，我们可以获取到指定范围内的一段轮廓，存入到dst参数中。
 * 所以，这个方法传入的参数分别为长度起始值、结束值、装这一段路径的Path对象、是否MoveTo。
 * 另外，这个方法返回值为Boolean类型，如果getLength为0的话，返回false，或者startD > stopD，同样返回false。
 */

public class PayView extends View implements ValueAnimator.AnimatorUpdateListener {
    private Context mContext;
    private Paint paint;

    private Path circlepath1;
    private Path pathSuccess;
    private Path pathFail1;
    private Path pathFail2;

    private Path dst1;
    private Path dst2;
    private Path dst3;
    private Path dst4;
    private PathMeasure mPathMeasure;
    private int type;
    public static final int TYPE_Loading = 0;
    public static final int TYPE_RIGHT = 1;
    public static final int TYPE_WRONG = 2;
    /**
     * 动画
     */
    private ValueAnimator mCircleAnimator;
    private ValueAnimator mRightAnimator;
    private ValueAnimator mWrong1Animator;
    private ValueAnimator mWrong2Animator;
    private int mColor;
    private float mWidth;

    private float mCirclePercent, mRightPercent, mWrongPercent1, mWrongPercent2;
    private float startAngle, minAngle, sweepAngle, currentAngle;

    public PayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context, attrs);
    }

    public PayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        circlepath1 = new Path();
        pathSuccess = new Path();
        pathFail1 = new Path();
        pathFail2 = new Path();

        dst1 = new Path();
        dst2 = new Path();
        dst3 = new Path();
        dst4 = new Path();
        mPathMeasure = new PathMeasure();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PayView);
        mColor = ta.getColor(R.styleable.PayView_paycolor, Color.GREEN);
        mWidth = ta.getDimension(R.styleable.PayView_paywidth, 5);
        ta.recycle();

        paint.setColor(mColor);
        paint.setStrokeWidth(mWidth);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        mCircleAnimator = ValueAnimator.ofFloat(0, 1);
        mCircleAnimator.setDuration(1000);

        mRightAnimator = ValueAnimator.ofFloat(0, 1);
        mRightAnimator.setDuration(500);
        mRightAnimator.addUpdateListener(this);

        mWrong1Animator = ValueAnimator.ofFloat(0, 1);
        mWrong1Animator.setDuration(300);
        mWrong1Animator.addUpdateListener(this);
        mWrong2Animator = ValueAnimator.ofFloat(0, 1);
        mWrong2Animator.setDuration(300);
        mWrong2Animator.addUpdateListener(this);

        mCircleAnimator.addUpdateListener(this);
        mRightAnimator.addUpdateListener(this);
        mWrong1Animator.addUpdateListener(this);
        mWrong2Animator.addUpdateListener(this);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("onMeasure","onMeasure");
        setMeasuredDimension(dp2px(100), dp2px(100));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("onLayout","onLayout");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.e("onDraw","onDraw");

        if (type == TYPE_Loading) {
            paint.setColor(Color.BLUE);

            if (startAngle == minAngle) {
                sweepAngle += 6;
            }

            if (sweepAngle >= 300 || startAngle > minAngle) {
                startAngle += 6;
                if (sweepAngle > 20) {
                    sweepAngle -= 6;
                }
            }
            if (startAngle > minAngle + 300) {
                startAngle %= 360;
                minAngle = startAngle;
                sweepAngle = 20;
            }
            canvas.rotate(currentAngle += 4, getWidth() / 2, getHeight() / 2);
            canvas.drawArc(new RectF(0, 0, getWidth(), getHeight()), startAngle, sweepAngle, false, paint);
            invalidate();
        } else {
            if (type == TYPE_RIGHT) {
                paint.setColor(Color.BLUE);
            } else if (type == TYPE_WRONG) {
                paint.setColor(Color.RED);
            }

            //先画圆
            circlepath1.addCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - mWidth / 2, Path.Direction.CW);
            mPathMeasure.setPath(circlepath1, false);
            mPathMeasure.getSegment(0, mCirclePercent * mPathMeasure.getLength(), dst1, true);
            canvas.drawPath(dst1, paint);

            if (type == TYPE_RIGHT) {//正确
                pathSuccess.moveTo(getWidth() * 0.25f, getHeight() * 0.4f);
                pathSuccess.lineTo(getWidth() * 0.5f, getHeight() * 0.8f);
                pathSuccess.lineTo(getWidth() * 0.8f, getHeight() * 0.3f);

                if (mCirclePercent == 1) {//如果圆画完，开始画对号
                    mPathMeasure.nextContour();
                    mPathMeasure.setPath(pathSuccess, false);
                    mPathMeasure.getSegment(0, mRightPercent * mPathMeasure.getLength(), dst2, true);
                    canvas.drawPath(dst2, paint);
                }

            } else if (type == TYPE_WRONG) {//错误
                pathFail1.moveTo(getWidth() * 0.7f, getHeight() * 0.3f);
                pathFail1.lineTo(getWidth() * 0.3f, getHeight() * 0.7f);

                pathFail2.moveTo(getWidth() * 0.3f, getHeight() * 0.3f);
                pathFail2.lineTo(getWidth() * 0.7f, getHeight() * 0.7f);

                if (mCirclePercent == 1) {//如果圆画完，开始画×的第一个斜
                    mPathMeasure.nextContour();
                    mPathMeasure.setPath(pathFail1, false);
                    mPathMeasure.getSegment(0, mWrongPercent1 * mPathMeasure.getLength(), dst3, true);
                    canvas.drawPath(dst3, paint);
                }
                if (mWrongPercent1 == 1) {//第一个斜杠画完，画第二个斜杠
                    mPathMeasure.nextContour();
                    mPathMeasure.setPath(pathFail2, false);
                    mPathMeasure.getSegment(0, mWrongPercent2 * mPathMeasure.getLength(), dst4, true);
                    canvas.drawPath(dst4, paint);
                }
            }
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if (animation.equals(mCircleAnimator)) {
            mCirclePercent = (float) animation.getAnimatedValue();
            invalidate();

            if (mCirclePercent == 1) {
                if (type == TYPE_RIGHT) {
                    mRightAnimator.start();
                } else {
                    mWrong1Animator.start();
                }
            }
        } else if (animation.equals(mRightAnimator)) {
            mRightPercent = (float) animation.getAnimatedValue();
            invalidate();
        } else if (animation.equals(mWrong1Animator)) {
            mWrongPercent1 = (float) animation.getAnimatedValue();
            invalidate();

            if (mWrongPercent1 == 1) {
                mWrong2Animator.start();
            }
        } else if (animation.equals(mWrong2Animator)) {
            mWrongPercent2 = (float) animation.getAnimatedValue();
            invalidate();
        }
    }

    private int dp2px(int dp) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (scale * dp + 0.5f);
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public float getStrokeWidth() {
        return mWidth;
    }

    public void setStrokeWidth(float mWidth) {
        this.mWidth = mWidth;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        mCircleAnimator.start();
    }
}
