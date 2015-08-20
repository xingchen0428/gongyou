package com.gongyou.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.gongyou.R;
import com.gongyou.domain.LoginBean;
import com.gongyou.net.LoginNet;
import com.gongyou.net.LoginNet.LoginCallback;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        new Handler().postDelayed(new Runnable(){
        	@Override
        	public void run() {
        		new LoginNet().userLoginIn("t5","1", new LoginCallback() {
        			@Override
        			public void loginSuccess(LoginBean loginBean) {
        				Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
        				intent.putExtra("LoginBean", loginBean);
        				startActivity(intent);
        				finish();
        				Toast.makeText(SplashActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
        			}
        			
        			@Override
        			public void loginFailure(String successInfo) {
        				Toast.makeText(SplashActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
        			}
        			
        			@Override
        			public void connectFailure() {
        				Toast.makeText(SplashActivity.this, "请检查您的网络连接！", Toast.LENGTH_SHORT).show();
        			}
        		});
        	}
        }, 2000);
    }
}
