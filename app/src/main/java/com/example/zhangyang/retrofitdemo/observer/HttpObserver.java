package com.example.zhangyang.retrofitdemo.observer;


import android.content.Context;
import android.util.Log;

import com.example.zhangyang.retrofitdemo.Http.HttpResponse;
import com.example.zhangyang.retrofitdemo.dialog.QaTextProgressDialog;


import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhangyang on 2017/9/5.
 */

public abstract class HttpObserver<T> implements Observer<HttpResponse<T>> {
    private QaTextProgressDialog progressDialog;
    private Disposable  mDisposable;
    public HttpObserver(Context context,boolean isShow) {
        showProgressDialog(context,isShow);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mDisposable=  d;
    }

    @Override
    public void onNext(@NonNull HttpResponse<T> httpResponse) {

        if (!httpResponse.isError()){
            Log.e("success","success");

            onSuccess(httpResponse.results);
        }else {
            onFailed(new Throwable("error="+httpResponse.isError()));
        }

    }

    @Override
    public void onError(@NonNull Throwable e) {

        onFailed(e);
        dismissProgressDialog();
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    protected abstract void onFailed(Throwable throwable);


    protected abstract void onSuccess(T data);
    private void showProgressDialog(Context context,boolean isShow) {

        if (!isShow)
            return;

        if (progressDialog == null) {

            progressDialog = new QaTextProgressDialog(context);
            progressDialog.setContentText("正在加载中。。。");
        }

        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void dismissProgressDialog() {

        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }


    public void unSubscribe(){
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
