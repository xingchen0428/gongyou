package com.gongyou.ui.view;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gongyou.R;
import com.gongyou.base.BaseResource;
import com.gongyou.domain.LoginBean;
import com.gongyou.domain.WorkInfoBean;
import com.gongyou.ui.activity.HomeActivity;
import com.gongyou.ui.widget.SlidingGroupView;

public class SlidingLeftView extends View {

	private Context mContext;
	private View slidingmenu_left;
	private TextView tv_user_number;
	private TextView tv_user_position;
	private ListView lv_work_info;
	private ArrayList<WorkInfoBean> mWorkList = new ArrayList<WorkInfoBean>();

	public SlidingLeftView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initView();
		initData();
	}

	public SlidingLeftView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
		initData();
	}

	public SlidingLeftView(Context context) {
		super(context);
		mContext = context;
		initView();
		initData();
	}

	private void initView() {
		slidingmenu_left = View.inflate(mContext, R.layout.slidingmenu_left,
				null);

		tv_user_number = (TextView) slidingmenu_left
				.findViewById(R.id.tv_user_number);
		tv_user_position = (TextView) slidingmenu_left
				.findViewById(R.id.tv_user_position);
		lv_work_info = (ListView) slidingmenu_left
				.findViewById(R.id.lv_work_info);
		lv_work_info.setDivider(null);
	}

	private void initData() {
		BaseResource resource = new BaseResource();
		
		LoginBean bean = (LoginBean)(((HomeActivity)mContext).getIntent().getSerializableExtra("LoginBean"));
		tv_user_number.setText("工号："+bean.staffCode);
		tv_user_position.setText("职位："+bean.typeDisplay);
		mWorkList  = resource.getResourceMenu(bean.type);;
		lv_work_info.setAdapter(new MyBaseAdapter());
	}

	public View getView() {
		return slidingmenu_left;
	}

	public void addItemView(View child) {
		lv_work_info.addView(child);
	}

	public void setMenuItemListener(OnItemClickListener listener) {
		lv_work_info.setOnItemClickListener(listener);
	}

	class MyBaseAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mWorkList.size();
		}

		@Override
		public WorkInfoBean getItem(int position) {
			return mWorkList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = new SlidingGroupView(mContext);
			}

			SlidingGroupView sliding = (SlidingGroupView) convertView;
			WorkInfoBean item = getItem(position);

			sliding.setUserInfo(item.des, item.resId);
			return sliding.getView();
		}

	}
}
