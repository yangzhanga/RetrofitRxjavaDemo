package com.example.zhangyang.retrofitdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhangyang.retrofitdemo.BaseActivity;
import com.example.zhangyang.retrofitdemo.Http.HttpManager;
import com.example.zhangyang.retrofitdemo.Http.HttpResponse;
import com.example.zhangyang.retrofitdemo.Http.RxSchedulers;
import com.example.zhangyang.retrofitdemo.adapter.MyRecycleAdapter;
import com.example.zhangyang.retrofitdemo.adapter.SimpleItemTouchHelperCallback;
import com.example.zhangyang.retrofitdemo.api.HomeApi;
import com.example.zhangyang.retrofitdemo.R;
import com.example.zhangyang.retrofitdemo.bean.Home;
import com.example.zhangyang.retrofitdemo.observer.HttpObserver;
import com.example.zhangyang.retrofitdemo.view.DragImageView;

import java.util.List;

import io.reactivex.Observable;


/**
 * @author zhangyang
 */
public class MainActivity extends BaseActivity {
    private static final int COUNT_NUM = 40;
    private Button getBt, interruptBt, addBt;
    private RecyclerView recyclerView;
    private MyRecycleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Home> listData;
    private int page=1;
    HttpObserver<List<Home>> observer;
    private ImageView toTop;
    private DragImageView dragImage;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "onCreate");

        initView();
        initData();

    }


    private void initView() {
        getBt = (Button) findViewById(R.id.getBt);
        interruptBt = (Button) findViewById(R.id.interruptBt);
        addBt = (Button) findViewById(R.id.addBt);
        toTop= (ImageView) findViewById(R.id.toTop);
        dragImage= (DragImageView) findViewById(R.id.dragImage);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyRecycleAdapter(MainActivity.this, listData);
        mAdapter.openLoadAnimation();//开启加载动画
        mAdapter.isFirstOnly(false);//是否只显示一次动画

        //给recycleview添加交换位置和左滑删除功能
        SimpleItemTouchHelperCallback callback=new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                page = 1;
                getData(page);
            }
        });

        mAdapter.setRecyclerViewItemClickListener(new MyRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mAdapter.getList()!=null&&mAdapter.getList().get(position).getUrl() != null&&!"".equals(mAdapter.getList().get(position).getUrl())) {
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    intent.putExtra("url", mAdapter.getList().get(position).getUrl());
                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;
            LinearLayoutManager manager =(LinearLayoutManager) recyclerView.getLayoutManager();

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //不滚动时
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    //获取最后一个完全显示的ItemPosition
                    int lastPos = manager.findLastCompletelyVisibleItemPosition();
                    int itemCount=manager.getItemCount();

                    // 判断是否滚动到底部，
                    if ((lastPos == itemCount - 1) && isSlidingToLast) {
                        page++;
                        getData(page);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //向下滑动
                if (dy>0){
                    isSlidingToLast=true;
                }else {
                    isSlidingToLast=false;
                }
                int lastPos = manager.findLastCompletelyVisibleItemPosition();

                if (lastPos == mAdapter.getItemCount() - 1) {
                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    }
                }
                if (lastPos>COUNT_NUM){
                    toTop.setVisibility(View.VISIBLE);
                }else {
                    toTop.setVisibility(View.GONE);
                }
            }
        });
        getBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                getData(page);

            }
        });

        interruptBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (observer != null) {
                    observer.unSubscribe();
                }
            }
        });

        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 0;
                mAdapter.addNewItem(pos, new Home("新添加的","","",null));
                recyclerView.smoothScrollToPosition(pos);

            }
        });

        toTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }
    private void initData() {
        getData(1);
    }

    private void getData(int p) {
//这种格式是为了 可以终止网络请求
//        Observable<HttpResponse<List<Home>>> observable = HttpManager.getInstance()
//                .createService(HomeApi.class)
//                .getAndroidData(p);
//        observer = new HttpObserver<List<Home>>(MainActivity.this, true) {
//            @Override
//            protected void onFailed(Throwable throwable) {
//                swipeRefreshLayout.setRefreshing(false);
//                page--;
//            }
//
//            @Override
//            public void onSuccess(List<Home> list) {
//                swipeRefreshLayout.setRefreshing(false);
//                Log.e("page",page+"");
//                if (page==1){
//                    mAdapter.updateData(list);
//                }else {
//                    mAdapter.addData(list);
//                }
//            }
//
//        };
//        observable.compose(RxSchedulers.<HttpResponse<List<Home>>>compose())
//                .subscribe(observer);


        HttpManager.getInstance()
                .createService(HomeApi.class)
                .getAndroidData(p)
                .compose(RxSchedulers.<HttpResponse<List<Home>>>compose())
                .subscribe(new HttpObserver<List<Home>>(MainActivity.this, true) {
                    @Override
                    protected void onFailed(Throwable throwable) {
                        swipeRefreshLayout.setRefreshing(false);
                        page--;
                    }

                    @Override
                    public void onSuccess(List<Home> list) {
                        swipeRefreshLayout.setRefreshing(false);
                        Log.e("page", page + "");
                        if (page == 1) {
                            mAdapter.updateData(list);
                        } else {
                            mAdapter.addData(list);
                        }
                    }

                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.action_svga:
            Intent intent=new Intent(MainActivity.this,SVGAActivity.class);
            startActivity(intent);
                break;
            case R.id.action_video:
                Intent intentVideo=new Intent(MainActivity.this,VideoListActivity.class);
                startActivity(intentVideo);
                break;

            case R.id.action_view:
                Intent intentView=new Intent(MainActivity.this,MyViewActivity.class);
                startActivity(intentView);
                break;
            case R.id.action_scroll_view:
                Intent intentscrollView=new Intent(MainActivity.this,ScrollingActivity.class);
                startActivity(intentscrollView);
                break;
            case R.id.action_scroll_view2:
                Intent intentscrollView2=new Intent(MainActivity.this,ScrollToolbarActivity.class);
                startActivity(intentscrollView2);
                break;
            case R.id.action_tab:
                Intent intentTab=new Intent(MainActivity.this,TabActivity.class);
                startActivity(intentTab);
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
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
