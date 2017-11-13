package com.example.zhangyang.retrofitdemo;

import android.app.Application;
import android.content.Context;
import android.net.http.HttpResponseCache;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhangyang on 2017/9/5.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

        File cacheDir = new File(getApplicationContext().getCacheDir(), "http");
        try {
            HttpResponseCache.install(cacheDir, 1024 * 1024 * 128);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return context;
    }
}

