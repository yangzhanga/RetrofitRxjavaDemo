package com.example.zhangyang.retrofitdemo.ViewPageTransformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zhangyang on 2018/4/2.
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MAX_SCALE = 1.0f;
    private static final float MIN_SCALE = 0.8f;

    /**
     * 你千万不要把 position 理解成了 ViewPager 页面的下标，一定要看仔细，这个 position 可是 float 类型，下标怎么可能是浮点型呢！
     * 从 doc 注释来看，当前选中的 item 的 position 永远是 0 ，被选中 item 的前一个为 -1，被选中 item 的后一个为 1。
     *
     * 在用户滑动界面的时候，position 是动态变化的，下面以左滑为例（以向左为正方向）:
     * 选中 item 的 position：从 0 渐至 -1 - offset (pageMargin / pageWidth)
     * 前一个 item 的 position：从 -1 渐至 -1 - offset (pageMargin / pageWidth)
     * 前两个 item 的 position：从 -2 渐至 -2 - offset (pageMargin / pageWidth)，再往前就以此类推
     * 后一个 item 的 position：从 1 + offset (pageMargin / pageWidth) 渐至 0，再往后就以此类推
     *
     * @param page
     * @param position
     */
    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) {
            float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);
            page.setScaleX(scaleFactor);

//                if (position>0){
//                    page.setTranslationX(-scaleFactor*2);
//                }else if (position < 0){
//                    page.setTranslationX(scaleFactor*2);
//                }
            page.setScaleY(scaleFactor);

        } else {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }
}
