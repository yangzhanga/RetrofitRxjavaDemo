package com.example.zhangyang.retrofitdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhangyang.retrofitdemo.BaseActivity;
import com.example.zhangyang.retrofitdemo.Http.HttpManager;
import com.example.zhangyang.retrofitdemo.Http.HttpResponse;
import com.example.zhangyang.retrofitdemo.Http.RxSchedulers;
import com.example.zhangyang.retrofitdemo.adapter.MyRecycleAdapter;
import com.example.zhangyang.retrofitdemo.api.HomeApi;
import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.bean.Home;
import com.example.zhangyang.retrofitdemo.observer.HttpObserver;

import java.util.List;

import io.reactivex.Observable;


public class MainActivity extends BaseActivity {
    private Button getBt, interruptBt, addBt, deleteBt;
    private RecyclerView recyclerView;
    private MyRecycleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Home> list;

    HttpObserver<List<Home>> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "onCreate");

        getBt = (Button) findViewById(R.id.getBt);
        interruptBt = (Button) findViewById(R.id.interruptBt);
        addBt = (Button) findViewById(R.id.addBt);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyRecycleAdapter(MainActivity.this, list);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mAdapter.setRecyclerViewItemClickListener(new MyRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "长按删除", Toast.LENGTH_SHORT).show();
                mAdapter.deleteItem(position);

            }
        });

        getBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();

            }
        });

        interruptBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (observer != null)
                    observer.unSubscribe();
            }
        });

        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 0;
                mAdapter.addNewItem(pos, new Home("新添加的"));
                recyclerView.smoothScrollToPosition(pos);

            }
        });


    }

    private void getData() {

        Observable<HttpResponse<List<Home>>> observable = HttpManager.getInstance()
                .createService(HomeApi.class)
                .getAndroidData(1);
        observer = new HttpObserver<List<Home>>(MainActivity.this, false) {
            @Override
            protected void onFailed(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<Home> list) {
                Log.e("list", list.size() + "");
                mAdapter.updateData(list);
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
