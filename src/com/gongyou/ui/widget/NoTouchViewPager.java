package com.gongyou.ui.widget;

import android.R;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoTouchViewPager extends ViewPager {

	public NoTouchViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoTouchViewPager(Context context) {
		super(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return true;
	}
}
