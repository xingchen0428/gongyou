package com.gongyou.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gongyou.R;
import com.gongyou.domain.LoginBean;
import com.gongyou.net.LoginNet;
import com.gongyou.net.LoginNet.LoginCallback;

public class LoginActivity extends Activity implements OnClickListener{
	private EditText et_username;
	private EditText et_password;
	private Button btn_login;
	private Button btn_register;
	private String userName;
	private String passWord;
	LoginNet loginNet ;
	private LinearLayout ll_root;
	private int screenHeigth;
	private int screenWidth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initWidget();
		initListener();
		initScreenSize();
		loginNet = new LoginNet();
		initRootSize();
	}

	private void initRootSize() {
		LayoutParams params = ll_root.getLayoutParams();
		params.width = screenWidth;
		params.height = screenHeigth;
		ll_root.setLayoutParams(params);
	}

	private void initScreenSize() {
		WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
		screenHeigth = wm.getDefaultDisplay().getHeight();
		screenWidth = wm.getDefaultDisplay().getWidth();
	}

	private void initListener() {
		btn_login.setOnClickListener(this);
		btn_register.setOnClickListener(this);
	}

	private void initWidget() {
		ll_root = (LinearLayout)findViewById(R.id.ll_root);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_register = (Button)findViewById(R.id.btn_register);
	}

	@Override
	public void onClick(View v) {
		if(v==btn_login){//登陆
			if(getContent()){
				login(userName,passWord);
			}
			Log.e("test", "login"+" "+passWord);
			return;
		}
		if(v==btn_register){//注册
			Log.e("test", "register");
			//startActivity(new Intent(LoginActivity.this, TestActivity.class));
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			return;
		}
	}
	

	private void login(String userName,String passWord){
		loginNet.userLoginIn(userName,passWord, new LoginCallback() {
			@Override
			public void loginSuccess(LoginBean loginBean) {
				Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
				intent.putExtra("LoginBean", loginBean);
				startActivity(intent);
				finish();
				Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void loginFailure(String successInfo) {
				Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void connectFailure() {
				Toast.makeText(LoginActivity.this, "请检查您的网络连接！", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	/**
	 * 获取账号和密码
	 */
	private boolean getContent() {
		userName = et_username.getText().toString().trim();
		passWord = et_password.getText().toString().trim();
		if(TextUtils.isEmpty(userName)){
			Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
			et_username.requestFocus();
			return false;
		}
		if(TextUtils.isEmpty(passWord)){
			Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
			et_username.requestFocus();
			return false;
		}
		return true;
	}
	
}
