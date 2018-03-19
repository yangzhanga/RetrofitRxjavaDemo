package com.example.zhangyang.retrofitdemo.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.example.zhangyang.retrofitdemo.BaseActivity;
import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.view.PayView;
import com.example.zhangyang.retrofitdemo.view.PieChartView;

/**
 * Created by zhangyang on 2017/11/13.
 */

public class MyViewActivity extends BaseActivity {
    private PieChartView pieChartView;
    private PayView payView;
    private Button btSuccess, btFail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_myview);

        pieChartView = findViewById(R.id.pieChartView);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(pieChartView, "color", 0xffff0000, 0xff00ff00);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        // 在这里使用 ObjectAnimator.setEvaluator() 来设置 ArgbEvaluator，修复闪烁问题
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.setDuration(4000);
        objectAnimator.start();

        payView = findViewById(R.id.payView);
        btSuccess = findViewById(R.id.btSuccess);
        btFail = findViewById(R.id.btFail);
        btSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payView.setType(PayView.TYPE_RIGHT);
                payView.requestLayout();
            }
        });
        btFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payView.setType(PayView.TYPE_WRONG);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
