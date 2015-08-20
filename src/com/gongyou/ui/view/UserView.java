package com.gongyou.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gongyou.R;
import com.gongyou.base.BaseNet.EntityCallback;
import com.gongyou.base.BaseNet.EntityrResult;
import com.gongyou.domain.LoginBean;
import com.gongyou.net.LoginNet;
import com.gongyou.ui.activity.LoginActivity;
import com.gongyou.ui.activity.SettingInfoActivity;
import com.gongyou.ui.widget.UserGroupView;

public class UserView extends View {

	private View userView;
	private Context mContext;
	private UserGroupView ugv_user_call;
	private UserGroupView ugv_user_address;
	private UserGroupView ugv_user_function;
	private UserGroupView ugv_user_logout;
	private Intent intent;
	private TextView tv_user_name;
	private TextView tv_user_number;
	private TextView tv_user_type;
	public UserView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initView();
		initListener();
		initData();
	}

	public UserView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
		initListener();
		initData();
	}

	public UserView(Context context) {
		super(context);
		mContext = context;
		initView();
		initListener();
		initData();
	}

	private void initView() {
		userView = View.inflate(mContext, R.layout.view_user, null);
		
		ugv_user_call = (UserGroupView)userView.findViewById(R.id.ugv_user_call);
		ugv_user_address = (UserGroupView)userView.findViewById(R.id.ugv_user_address);
		ugv_user_function = (UserGroupView)userView.findViewById(R.id.ugv_user_function);
		ugv_user_logout = (UserGroupView)userView.findViewById(R.id.ugv_user_logout);
		
		tv_user_name = (TextView)userView.findViewById(R.id.tv_user_name);
		tv_user_number = (TextView)userView.findViewById(R.id.tv_user_number);
		tv_user_type = (TextView)userView.findViewById(R.id.tv_user_type);
	}
	
	public void setUserInfo(String name, String number, String type){
		tv_user_name.setText(name);
		tv_user_number.setText(number);
		tv_user_type.setText(type);
	}

	private void initListener() {
		ugv_user_call.setMyGroupClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				startInfoActivty(ugv_user_call.getInfoTitle(), ugv_user_call.getInfoType(), ugv_user_call.getUserInfo());
			}
		});
		
		ugv_user_address.setMyGroupClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				startInfoActivty(ugv_user_address.getInfoTitle(), ugv_user_address.getInfoType(), ugv_user_address.getUserInfo());
			}
		});
		
		ugv_user_function.setMyGroupClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				startInfoActivty(ugv_user_function.getInfoTitle(), ugv_user_function.getInfoType(), ugv_user_function.getUserInfo());
			}
		});
		
		ugv_user_logout.setMyGroupClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				new LoginNet().userLogout(new EntityCallback(){

					@Override
					public void connectCallback(EntityrResult entityrResult) {
						mContext.startActivity(new Intent(mContext,LoginActivity.class));
						((Activity)mContext).finish();
						Toast.makeText(mContext, "已退出登陆", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	}
	
	private void initData() {
		intent = ((Activity)mContext).getIntent();
		LoginBean loginBean = (LoginBean)intent.getSerializableExtra("LoginBean");
		setUserInfo("姓名："+loginBean.name,"工号："+loginBean.staffCode,"级别："+loginBean.typeDisplay);
		ugv_user_call.setUserInfo("联系方式 :"+loginBean.phone, R.drawable.iv_user_call);
		ugv_user_address.setUserInfo("当前位置 :"+loginBean.address, R.drawable.iv_user_address);
		ugv_user_function.setUserInfo("业务介绍 :"+loginBean.description, R.drawable.iv_user_function);
		ugv_user_logout.setUserInfo("退出登录", R.drawable.iv_user_logout);
	}

	public View getView(){
		return userView;
	}
	
	public void startInfoActivty(String title, String type, String info){
		Intent intent = new Intent(mContext,SettingInfoActivity.class);
		intent.putExtra("title", title);
		intent.putExtra("type", type);
		intent.putExtra("info", info);
		mContext.startActivity(intent);
	}
}
