package com.gongyou.ui.widget;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gongyou.R;

public class MessagePicWidget extends LinearLayout {
	private ArrayList<TextView> arrTextView;
	private ArrayList<ImageView> arrImageViews;
	private View view;
	private TextView tv_title;
	public MessagePicWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
		initWidget();
	}

	public MessagePicWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		initWidget();
	}

	public MessagePicWidget(Context context) {
		super(context);
		initView(context);
		initWidget();
	}

	private void initView(Context context){
		view = View.inflate(context, R.layout.widget_message_pic, this);
	}
	
	private void initWidget(){
		new Thread(){
			public void run() {
				tv_title = (TextView)view.findViewById(R.id.tv_title);
				arrTextView = new ArrayList<TextView>();
				arrTextView.add(0, (TextView)view.findViewById(R.id.tv_message0)); 
				arrTextView.add(1, (TextView)view.findViewById(R.id.tv_message1));
				arrTextView.add(2, (TextView)view.findViewById(R.id.tv_message2));
				arrTextView.add(3, (TextView)view.findViewById(R.id.tv_message3));
				arrImageViews = new ArrayList<ImageView>();
				arrImageViews.add(0,(ImageView)view.findViewById(R.id.iv_pic0));
				arrImageViews.add(1,(ImageView)view.findViewById(R.id.iv_pic1));
				arrImageViews.add(2,(ImageView)view.findViewById(R.id.iv_pic2));
			};
		}.start();
	}
	
	/**
	 * 
	 * @param position position<=2
	 * @param bitmap
	 */
	public void setImage(int position,Bitmap bitmap){
		arrImageViews.get(position).setImageBitmap(bitmap);
		arrImageViews.get(position).setVisibility(VISIBLE);
	}
	
	/**
	 * 
	 * @param position position<=3
	 * @param text
	 */
	public void setText(int position,String text){
		arrTextView.get(position).setText(text);
		arrTextView.get(position).setVisibility(VISIBLE);
	}
	
	public View getView(){
		return view;
	}
	
	public void setTitle(String text){
		tv_title.setText(text);
	}
}
