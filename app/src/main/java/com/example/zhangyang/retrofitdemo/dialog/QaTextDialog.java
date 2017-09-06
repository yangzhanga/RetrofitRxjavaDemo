package com.example.zhangyang.retrofitdemo.dialog;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zhangyang.retrofitdemo.R;


/**
 * 文本对话框，不带标题栏，只带一个显示的文本，可设置文本左边的icon。
 * @author yhb
 *
 */
public class QaTextDialog extends QaBaseDialog{

	private String mContentText = "";
	private int mContentTextLeftDrawableResId;
	
	public QaTextDialog(Context context) {
		
		super(context);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_qa_text);
	}
	
	@Override
	protected void initContentView() {
		
		TextView tv = (TextView) findViewById(R.id.tvText);
		tv.setText(mContentText);
		if(mContentTextLeftDrawableResId > 0){
			tv.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(mContentTextLeftDrawableResId), 
												   		null, null, null);
		}
	}
	
	public void setContentTextLeftDrawable(int resId){
		
		mContentTextLeftDrawableResId = resId;
	}
	
	public void setContentText(int rid){
	
		setContentText(getContext().getString(rid));
	}	

	public void setContentText(String text){
		if (text!=null){
			mContentText = text;
		}

	}
	
	public String getContentText(){
	
		return mContentText;
	}
}
