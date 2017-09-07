package com.example.zhangyang.retrofitdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.zhangyang.retrofitdemo.R;

/**
 * Created by zhangyang on 2017/8/28.
 */

public class SubActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("SubActivity","onCreate");
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("SubActivity","onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("SubActivity","onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("SubActivity","onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("SubActivity","onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("SubActivity","onDestroy");

    }
}
