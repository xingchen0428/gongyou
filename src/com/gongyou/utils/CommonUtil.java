package com.gongyou.utils;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.gongyou.global.BaseApplication;

/**
 * 常用方法封装
 * @author Administrator
 *
 */
public class CommonUtil {
	/**
	 * 在主线程中执行runnable
	 * @param runnable
	 */
	public static void runOnUIThread(Runnable runnable){
		BaseApplication.getMainHandler().post(runnable);
	}
	
	
	/**
	 * 获取字符串资源
	 * @param id
	 * @return
	 */
	public static String getString(int id){
		return BaseApplication.getContext().getResources().getString(id);
	}
	
	
	/**
	 * 获取图片资源
	 * @param id
	 * @return
	 */
	public static Drawable getDrawable(int id){
		return BaseApplication.getContext().getResources().getDrawable(id);
	}
	
	/**
	 * 获取字符串数组资源
	 * @param id
	 * @return
	 */
	public static String[] getStringArray(int id){
		return 	BaseApplication.getContext().getResources().getStringArray(id);
	}
	
	/**
	 * 将view从父view中移除
	 * @param view
	 */
	public static void removeSelfFromParent(View view){
		if(view!=null){
			ViewParent parent = view.getParent();
			if(parent instanceof ViewGroup){
				ViewGroup group = (ViewGroup) parent;
				group.removeView(view);
			}
		}
	}
	
	public static int getScreenWidth(Activity activity){
		return activity.getWindowManager().getDefaultDisplay().getWidth();
	}
	
	public static int getScrreenHeight(Activity activity){
		return activity.getWindowManager().getDefaultDisplay().getHeight();
	}
}
