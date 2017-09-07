package com.example.zhangyang.retrofitdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangyang.retrofitdemo.BaseActivity;
import com.example.zhangyang.retrofitdemo.Http.HttpManager;
import com.example.zhangyang.retrofitdemo.Http.HttpResponse;
import com.example.zhangyang.retrofitdemo.Http.RxSchedulers;
import com.example.zhangyang.retrofitdemo.api.HomeApi;
import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.bean.Home;
import com.example.zhangyang.retrofitdemo.observer.HttpObserver;

import java.util.List;

import io.reactivex.Observable;


public class MainActivity extends BaseActivity {
    private Button getBt,interruptBt;
    private RecyclerView recyclerView;
    HttpObserver<List<Home>> observer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "onCreate");

        getBt= (Button) findViewById(R.id.getBt);
        interruptBt= (Button) findViewById(R.id.interruptBt);
        recyclerView= (RecyclerView) findViewById(R.id.recycleview);



        getBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();



            }
        });

        interruptBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (observer!=null)
                observer.unSubscribe();
            }
        });

    }

    private void getData() {

        Observable<HttpResponse<List<Home>>> observable = HttpManager.getInstance()
                .createService(HomeApi.class)
                .getAndroidData(1);
        observer= new HttpObserver<List<Home>>(MainActivity.this, false) {
            @Override
            protected void onFailed(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<Home> list) {
                Log.e("data",list.get(0).getDesc());
                Log.e("THREAD1", "" + Thread.currentThread().getName());
            }

        };
        observable.compose(RxSchedulers.<HttpResponse<List<Home>>>compose())
                .subscribe(observer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity", "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity", "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", "onDestroy");


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainActivity", "onRestart");

    }
}
