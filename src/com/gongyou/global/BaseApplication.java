package com.gongyou.global;

import com.gongyou.utils.SharedPreUtil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

public class BaseApplication extends Application{
	private static Context mContext;
	private static Handler mainHandler;
	private static SharedPreferences sp;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mContext = this;
		mainHandler = new Handler();
		sp = mContext.getSharedPreferences(SharedPreUtil.FILE_NAME, Context.MODE_PRIVATE);
	}
	
	/**
	 * 获取全局上下文对象
	 * @return
	 */
	public static Context getContext(){
		return mContext;
	}
	
	/**
	 * 获取主线程的Handler
	 * @return
	 */
	public static Handler getMainHandler(){
		return mainHandler;
	}
	
	public static SharedPreferences getSp(){
		return sp;
	}
}
