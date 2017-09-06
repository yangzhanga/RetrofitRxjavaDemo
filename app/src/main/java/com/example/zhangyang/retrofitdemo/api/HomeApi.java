package com.example.zhangyang.retrofitdemo.api;

import com.example.zhangyang.retrofitdemo.Http.HttpResponse;
import com.example.zhangyang.retrofitdemo.bean.Home;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhangyang on 2017/9/6.
 */

public interface HomeApi {

    @GET("data/all/20/{page}")
    Observable<HttpResponse<List<Home>>> getAndroidData(@Path("page") int page);
}
