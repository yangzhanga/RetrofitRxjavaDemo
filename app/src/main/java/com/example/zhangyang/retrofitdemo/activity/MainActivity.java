package com.example.zhangyang.retrofitdemo.activity;

import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangyang.retrofitdemo.Http.HttpManager;
import com.example.zhangyang.retrofitdemo.Http.HttpResponse;
import com.example.zhangyang.retrofitdemo.Http.RxSchedulers;
import com.example.zhangyang.retrofitdemo.api.HttpRequest;
import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.bean.Home;
import com.example.zhangyang.retrofitdemo.observer.HttpObserver;

import java.util.List;




public class MainActivity extends AppCompatActivity {
    private Button getBt,postBt;
    private TextView tv;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "onCreate");

        getBt= (Button) findViewById(R.id.getBt);
        postBt= (Button) findViewById(R.id.postBt);
        tv= (TextView) findViewById(R.id.tv);
        img= (ImageView) findViewById(R.id.img);

        getBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance()
                        .createService(HttpRequest.class)
                        .getAndroidData(1)
                        .compose(RxSchedulers.<HttpResponse<List<Home>>>compose())
                        .subscribe(new HttpObserver<List<Home>>() {
                            @Override
                            protected void onFailed(Throwable throwable) {

                            }

                            @Override
                            public void onSuccess(List<Home> list) {
                                tv.setText(list.get(0).getDesc());
                                Log.e("THREAD1",""+ Thread.currentThread().getName());
                            }

                        });


            }
        });


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
