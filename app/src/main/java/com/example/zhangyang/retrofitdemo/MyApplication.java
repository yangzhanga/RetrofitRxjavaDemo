package com.example.zhangyang.retrofitdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangyang on 2017/9/5.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}

