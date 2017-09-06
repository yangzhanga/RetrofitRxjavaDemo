package com.example.zhangyang.retrofitdemo.Http;

import retrofit2.Retrofit;

/**
 * Created by zhangyang on 2017/9/4.
 *
 * 单例获取HttpManager
 */

public class HttpManager {
    private Retrofit Retrofitclient;
    private volatile static HttpManager mInstance = null;

    private HttpManager() {

        Retrofitclient = RetrofitClient.getClient();
    }

    public static HttpManager getInstance() {

        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {

                    mInstance = new HttpManager();
                }

            }
        }

        return mInstance;
    }

    public <T> T createService(Class<T> service) {


        return Retrofitclient.create(service);
    }
}
