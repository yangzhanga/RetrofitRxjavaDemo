package com.example.zhangyang.retrofitdemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhangyang.retrofitdemo.BaseActivity;
import com.example.zhangyang.retrofitdemo.R;

/**
 * Created by zhangyang on 2018/4/19.
 * <p>
 * BottomSheet 在布局文件xml中的使用,BottomSheet需要配合CoordinatorLayout控件
 * app:behavior_hideable="true" hideable是当我们拖拽下拉的时候，bottom sheet是否能全部隐藏。
 * app:behavior_peekHeight="300dp"  表示弹出后显示的高度为300dp。
 * app:layout_behavior="@string/bottom_sheet_behavior" 表示这是一个bottom sheet。
 * <p>
 * BottomSheetDialog不用被CoordinatorLayout包裹，但是还是推荐实用推荐的滑动控件NestedScrollView
 *
 * BottomSheetDialogFragment不需要依赖CoordingLayout,与activity_test布局无关
 */

public class BottomSheetActivity extends BaseActivity implements ItemListDialogFragment.Listener {
    private Button buttonSheet, buttonSheetDialog, buttonFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        buttonSheet = findViewById(R.id.button0);
        buttonSheetDialog = findViewById(R.id.button1);
        buttonFragment = findViewById(R.id.button2);


        View bottomSheet = findViewById(R.id.design_bottom_sheet);
        final BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //这里是bottomSheet状态的改变
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //这里是拖拽中的回调，根据slideOffset可以做一些动画

            }
        });

        buttonSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet(behavior);
            }
        });

        buttonSheetDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(BottomSheetActivity.this);
                View view = getLayoutInflater().inflate(R.layout.bottomsheetdialog, null);
                mBottomSheetDialog.setContentView(view);
                mBottomSheetDialog.show();
            }
        });

        buttonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemListDialogFragment.newInstance(100).show(getSupportFragmentManager(), "tag");
            }
        });

    }

    private void showBottomSheet(BottomSheetBehavior<View> behavior) {
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(BottomSheetActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
    }
}
