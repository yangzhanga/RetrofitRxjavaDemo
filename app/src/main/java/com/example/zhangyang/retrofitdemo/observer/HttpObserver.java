package com.example.zhangyang.retrofitdemo.observer;


import android.util.Log;

import com.example.zhangyang.retrofitdemo.Http.HttpResponse;


import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhangyang on 2017/9/5.
 */

public abstract class HttpObserver<T> implements Observer<HttpResponse<T>> {


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull HttpResponse<T> httpResponse) {

        if (!httpResponse.error){
            Log.e("success","success");

            onSuccess(httpResponse.results);
        }else {
            onFailed(new Throwable("error="+httpResponse.error));
        }

    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.e("onError",e.getMessage());
        onFailed(e);
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onFailed(Throwable throwable);


    protected abstract void onSuccess(T data);

}
