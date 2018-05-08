package com.example.zhangyang.retrofitdemo.RvSticky;

import android.view.View;

/**
 *
 *  获取吸附View相关的信息
 *
 * Created by zhangyang on 2018/5/8.
 */

public interface StickyView {

    /**
     * 是否是吸附view
     * @param view
     * @return
     */
    boolean isStickyView(View view);

    /**
     * 得到吸附view的itemType
     * @return
     */
    int getStickViewType();
}
