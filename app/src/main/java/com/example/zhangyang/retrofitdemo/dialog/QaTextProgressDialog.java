package com.example.zhangyang.retrofitdemo.dialog;

import android.content.Context;
import android.os.Bundle;

import com.example.zhangyang.retrofitdemo.R;


/**
 * 不确定进度的对话框，左边是loading，右边是文本显示
 *
 *
 */
public class QaTextProgressDialog extends QaTextDialog{

	private QaLoadingView mLoadingView;

	public QaTextProgressDialog(Context context) {

		super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		//super.onCreate(savedInstanceState);//不调用父类的onCreate
		setContentView(R.layout.dialog_qa_text_progress);//该布局内已经有和父类相同textid的文本控件
	}

	@Override
	protected void initContentView() {

		super.initContentView();//让父类去初始化text

		mLoadingView = (QaLoadingView) findViewById(R.id.qlvLoading);
	}

	@Override
	protected void onStart() {

		super.onStart();
		mLoadingView.startLoadingAnim(0);
	}

	@Override
	protected void onStop() {

		super.onStop();
		mLoadingView.stopLoadingAnim();
	}
}
