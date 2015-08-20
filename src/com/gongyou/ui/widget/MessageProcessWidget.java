package com.gongyou.ui.widget;

import java.util.ArrayList;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gongyou.R;

public class MessageProcessWidget extends LinearLayout {

	private View view;
	private ArrayList<TextView> arrTextViews;
	private ArrayList<ImageView> arrayImageViews;
	private LinearLayout ll_content;

	public MessageProcessWidget(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
		initWidget();
	}

	public MessageProcessWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		initWidget();
	}

	public MessageProcessWidget(Context context) {
		super(context);
		initView(context);
		initWidget();
	}
	
	private void initWidget(){
		new Thread(){
			public void run() {
				arrTextViews = new ArrayList<TextView>();
				arrTextViews.add(0, (TextView)view.findViewById(R.id.tv_process0));
				arrTextViews.add(1, (TextView)view.findViewById(R.id.tv_process1));
				arrTextViews.add(2, (TextView)view.findViewById(R.id.tv_process2));
				arrTextViews.add(3, (TextView)view.findViewById(R.id.tv_process3));
				arrTextViews.add(4, (TextView)view.findViewById(R.id.tv_process4));
				arrTextViews.add(5, (TextView)view.findViewById(R.id.tv_process5));
				arrTextViews.add(6, (TextView)view.findViewById(R.id.tv_process6));
				arrTextViews.add(7, (TextView)view.findViewById(R.id.tv_process7));
				arrayImageViews = new ArrayList<ImageView>();
				
				arrayImageViews.add(0,(ImageView)view.findViewById(R.id.iv_process1));
				arrayImageViews.add(1,(ImageView)view.findViewById(R.id.iv_process2));
				arrayImageViews.add(2,(ImageView)view.findViewById(R.id.iv_process3));
				arrayImageViews.add(3,(ImageView)view.findViewById(R.id.iv_process4));
				arrayImageViews.add(4,(ImageView)view.findViewById(R.id.iv_process5));
				arrayImageViews.add(5,(ImageView)view.findViewById(R.id.iv_process6));
				arrayImageViews.add(6,(ImageView)view.findViewById(R.id.iv_process7));
				
				//ll_content = (LinearLayout)view.findViewById(R.id.ll_content_process);
			};
		}.start();
		
	}
	
	private void initView(Context context){
		view = View.inflate(context, R.layout.widget_message_process, this);
	}
	
	public void setText(int position,String text){
		if(position==0){
			arrTextViews.get(position).setText(text);
			arrTextViews.get(position).setVisibility(VISIBLE);
		}else{
			arrTextViews.get(position).setText(text);
			arrTextViews.get(position).setVisibility(VISIBLE);
			arrayImageViews.get(position-1).setVisibility(VISIBLE);
		}
	}
	

}
