package com.gongyou.ui.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gongyou.R;
import com.gongyou.ui.widget.PullToRefreshLayout;

public class WorkOrderActivity extends Activity implements PullToRefreshLayout.OnRefreshListener{

	private ImageView iv_title_left;
	private TextView tv_title;
	private ListView lv_work_order;
	private PullToRefreshLayout refreshLayout;
	private RotateAnimation loadingAnimation;
	private ArrayList orderList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		initView();
		initListener();
		//TODO 在initData()方法中处理数据获取
		initData();
	}

	private void initView() {
		iv_title_left = (ImageView)this.findViewById(R.id.iv_title_left);
		tv_title = (TextView)this.findViewById(R.id.tv_title);
		
		lv_work_order = (ListView) this.findViewById(R.id.content_view_order);
		lv_work_order.setDivider(null);
		refreshLayout = (PullToRefreshLayout) this.findViewById(R.id.refresh_view_order);
		refreshLayout.setOnRefreshListener(this);
		
		loadingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotating);
		// 添加匀速转动动画
		LinearInterpolator lir = new LinearInterpolator();
		loadingAnimation.setInterpolator(lir);
	}

	private void initListener() {
		lv_work_order.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
			}
		});
		
		iv_title_left.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void initData() {
		iv_title_left.setImageResource(R.drawable.rightmenu);
		iv_title_left.setVisibility(View.VISIBLE);
		tv_title.setText("维修状态");
		
		if(orderList==null){
			orderList = new ArrayList();
		}
		
		//lv_work_order.setAdapter(new MyBaseAdapter());
	}

	@Override
	public void onRefresh() {
		// TODO 下拉刷新，获取最新网络数据
		
	}
	
	class MyBaseAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return orderList.size();
		}

		@Override
		public Object getItem(int position) {
			return orderList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if(convertView==null){
				holder = new ViewHolder();
				
				View view = View.inflate(WorkOrderActivity.this, R.layout.item_search_result, null);
				
				holder.ivState = (ImageView)view.findViewById(R.id.iv_search_state);
				holder.tvNumber = (TextView)view.findViewById(R.id.tv_search_priority);
				holder.tvAddress = (TextView)view.findViewById(R.id.tv_search_address);
				holder.tvTime = (TextView)view.findViewById(R.id.tv_search_time);
				holder.tvDes = (TextView)view.findViewById(R.id.tv_search_des);
				holder.tvState = (TextView)view.findViewById(R.id.tv_search_state);
				
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder)convertView.getTag();
			}
			
			//TODO 从集合获取数据，填充的过程
			
			return convertView;
		}
	}
	
	class ViewHolder{
		public ImageView ivState;
		public TextView tvNumber;
		public TextView tvAddress;
		public TextView tvTime;
		public TextView tvDes;
		public TextView tvState;
	}
}
