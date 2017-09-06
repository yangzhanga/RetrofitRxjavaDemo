package com.example.zhangyang.retrofitdemo.api;

import com.example.zhangyang.retrofitdemo.Http.HttpResponse;
import com.example.zhangyang.retrofitdemo.bean.Home;
import com.example.zhangyang.retrofitdemo.bean.Plan;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zhangyang on 2017/8/17.
 */

public interface HttpRequest {

    @GET("users/{user}/repos")
    Observable<String> getUser(@Path("user") String user);

    @GET("qyer/search/hot_history?track_device_info=virgo&v=1&types=hotel_city%2Cplace%2Czk&lon=116.435329&track_deviceid=867389021584965&track_os=Android6.0.1&client_id=qyer_android&track_app_channel=qyer&client_secret=9fcaae8aefc4f9ac4915&debug=yes&app_installtime=1504247329614&track_app_version=7.11.0&lat=39.941863")
    Observable<HttpResponse<Plan>> getData();


    @GET("data/all/20/{page}")
    Observable<HttpResponse<List<Home>>> getAndroidData(@Path("page") int page);
}
