package com.example.zhangyang.retrofitdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.zhangyang.retrofitdemo.R;

/**
 * Created by zhangyang on 2017/8/28.
 */

public class SubActivity extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("SubActivity","onCreate");
        setContentView(R.layout.activity_webview);
        webView= (WebView) findViewById(R.id.webview);

        String url=getIntent().getStringExtra("url");
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);//开启javascript
        setting.setDomStorageEnabled(true);//开启DOM
        setting.setDefaultTextEncodingName("utf-8");//设置字符编码
        //设置web页面
        setting.setAllowFileAccess(true);//设置支持文件流
        setting.setSupportZoom(true);// 支持缩放
        //隐藏原生的缩放控件
        setting.setDisplayZoomControls(false);
        setting.setBuiltInZoomControls(true);// 支持缩放
        setting.setUseWideViewPort(true);// 调整到适合webview大小
        setting.setLoadWithOverviewMode(true);// 调整到适合webview大小
        setting.setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        setting.setRenderPriority(WebSettings.RenderPriority.HIGH);


        setting.setAppCacheEnabled(true);//开启缓存机制
        webView.loadUrl(url);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("SubActivity","onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("SubActivity","onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("SubActivity","onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("SubActivity","onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("SubActivity","onDestroy");

    }
}
