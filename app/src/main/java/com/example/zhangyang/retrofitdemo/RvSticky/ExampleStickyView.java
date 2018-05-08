package com.example.zhangyang.retrofitdemo.RvSticky;

import android.view.View;

/**
 * Created by zhangyang on 2018/5/8.
 */

public class ExampleStickyView implements StickyView {

    @Override
    public boolean isStickyView(View view) {
        if (view.getTag()==null){
            return false;
        }
        return (boolean) view.getTag();
    }

    @Override
    public int getStickViewType() {
        return 0;
    }
}
