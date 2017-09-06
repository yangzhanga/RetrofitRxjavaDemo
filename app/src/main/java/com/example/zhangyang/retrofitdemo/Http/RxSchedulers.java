package com.example.zhangyang.retrofitdemo.Http;

import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import com.example.zhangyang.retrofitdemo.MyApplication;
import com.example.zhangyang.retrofitdemo.utils.NetUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhangyang on 2017/9/5.
 * 进行线程的切换
 * 可以通过它将一种类型的Observable转换成另一种类型的Observable
 *
 *
 * subscribeOn()改变调用它之前代码的线程  当使用了多个subscribeOn()的时候，只有第一个 subscribeOn() 起作用。
 * observeOn()改变调用它之后代码的线程
 *
 * 默认情况下， doOnSubscribe() 执行在 subscribe() 回调的线程；
 * 而如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程。
 */

public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> compose() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> observable) {

                return observable.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                Log.e("THREAD",""+ Thread.currentThread().getName());
                                if (!NetUtils.isNetworkAvailable(MyApplication.getContext())) {
                                    Toast.makeText(MyApplication.getContext(), "网络连接错误", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

    }
}
