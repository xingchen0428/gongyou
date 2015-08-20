package com.gongyou.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gongyou.R;

public class SettingInfoActivity extends Activity {

	private TextView tv_user_info;
	private EditText et_user_info;
	private ImageView iv_title_left;
	private TextView tv_title;
	private TextView tv_title_right;
	private String mTitle;
	private String mType;
	private String mInfo;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		
		Intent intent = getIntent();
		mTitle = intent.getStringExtra("title");
		mType = intent.getStringExtra("type");
		mInfo = intent.getStringExtra("info");
		
		initView();
		initListener();
		initData();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		toggleInputState(true);
	}
	
	private void initView() {
		tv_user_info = (TextView)this.findViewById(R.id.tv_user_info);
		et_user_info = (EditText)this.findViewById(R.id.et_user_info);
		iv_title_left = (ImageView)this.findViewById(R.id.iv_title_left);
		iv_title_left.setVisibility(View.VISIBLE);
		tv_title = (TextView)this.findViewById(R.id.tv_title);
		tv_title_right = (TextView)this.findViewById(R.id.tv_title_right);
		
		tv_title_right.setVisibility(View.VISIBLE);
	}

	private void initListener() {
		iv_title_left.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				toggleInputState(false);
				finish();
			}
		});
		
		tv_title_right.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//TODO 上传数据到服务器
				
				toggleInputState(false);
				String info = et_user_info.getText().toString();
				finish();
			}
		});
	}

	private void initData() {
		iv_title_left.setImageResource(R.drawable.rightmenu);
		tv_title_right.setText("完成");
		tv_title.setText(mTitle);
		tv_user_info.setText(mType);
		et_user_info.setText(mInfo);
	}
	
	public void toggleInputState(boolean isShow){
		final InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE); 
//		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		if(isShow){
			//显示输入法
			mHandler.postDelayed(new Runnable(){
				@Override
				public void run() {
					imm.showSoftInput(et_user_info, 0);
				}
			}, 300);
		}else{
			//隐藏输入法
			imm.hideSoftInputFromWindow(et_user_info.getWindowToken(), 0);
		}
	}
}
