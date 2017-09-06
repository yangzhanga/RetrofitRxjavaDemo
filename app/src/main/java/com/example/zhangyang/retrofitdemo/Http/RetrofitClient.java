package com.example.zhangyang.retrofitdemo.Http;

import com.example.zhangyang.retrofitdemo.MyApplication;
import com.example.zhangyang.retrofitdemo.cons.Constants;
import com.example.zhangyang.retrofitdemo.utils.FileUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangyang on 2017/9/4.
 */

public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {

//        File httpCacheDirectory = new File(FileUtils.getCacheDir(MyApplication.getContext()), "OkHttpCache");

        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder
//                .addInterceptor(new Interceptor() {//添加通用的header
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//
//                        Request.Builder builder = chain.request().newBuilder();
//                        builder.addHeader("","");
//                        return chain.proceed(builder.build());
//                    }
//                })
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {//添加拦截器
                    @Override
                    public void log(String message) {

                    }
                }).setLevel(HttpLoggingInterceptor.Level.BASIC))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS);
//                .cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024));


        return httpClientBuilder.build();

    }
}
