package com.gongyou.ui.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gongyou.R;
import com.gongyou.base.BaseResource;
import com.gongyou.domain.LoginBean;
import com.gongyou.domain.WorkInfoBean;
import com.gongyou.ui.activity.DeviceActivity;
import com.gongyou.ui.activity.HomeActivity;
import com.gongyou.ui.activity.UserRepairsActivity;
import com.gongyou.ui.activity.WorkOrderActivity;

public class FunctionView extends View {

	private View functionView;
	private GridView gv_view_function;
	private Context mContext;
	private ArrayList<WorkInfoBean> resourceGrid;
	private LoginBean loginBean;

	public FunctionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public FunctionView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FunctionView(Context context) {
		super(context);
		mContext = context;
		initView();
		initListener();
		initData();
	}
	
	private void initView() {
		functionView = View.inflate(mContext, R.layout.view_function, null);
		
		gv_view_function = (GridView) functionView
				.findViewById(R.id.gv_view_function);
	}

	private void initListener() {
		gv_view_function.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				gridSelecter(position);
			}
		});
	}
	
	public void gridSelecter(int position){
		String des = resourceGrid.get(position).des;
		if(position==0 && "0".equals(loginBean.type)){
			mContext.startActivity(new Intent(mContext,UserRepairsActivity.class));
		}else if("预防性维保".equals(des) || "设备维护".equals(des)){
			Intent intent = new Intent(mContext,DeviceActivity.class);
			intent.putExtra("title", des);
			mContext.startActivity(intent);
		}else{
			//TODO 跳到ListView展示条目的页面
			Intent intent = new Intent(mContext,WorkOrderActivity.class);
			intent.putExtra("LoginBean", loginBean);
			intent.putExtra("WorkInfoBean", resourceGrid.get(position));
			
			mContext.startActivity(intent);
		}
	}

	private void initData() {
		loginBean = (LoginBean)(((HomeActivity)mContext).getIntent().getSerializableExtra("LoginBean"));
		BaseResource resource = new BaseResource();
		resourceGrid = resource.getResourceGrid(loginBean.type);
		gv_view_function.setAdapter(new MyBaseAdapter());
	}


	public View getView() {
		return functionView;
	}
	
	class MyBaseAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return resourceGrid.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView = View.inflate(mContext, R.layout.item_function, null);
			}
			
			ImageView iv_gvitem = (ImageView)convertView.findViewById(R.id.iv_gvitem);
			TextView tv_gvitem = (TextView)convertView.findViewById(R.id.tv_gvitem);
			
			iv_gvitem.setImageResource(resourceGrid.get(position).resId);
			tv_gvitem.setText(resourceGrid.get(position).des);
			
			return convertView;
		}
	}
}
