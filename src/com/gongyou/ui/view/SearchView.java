package com.gongyou.ui.view;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gongyou.R;
import com.gongyou.base.BaseResource;
import com.gongyou.base.BaseNet.BaseCallback;
import com.gongyou.domain.CasesBean;
import com.gongyou.domain.MessageBean;
import com.gongyou.domain.CasesBean.Records;
import com.gongyou.net.OrderManager;
import com.gongyou.ui.activity.HomeActivity;
import com.gongyou.ui.widget.PullToRefreshLayout;

public class SearchView extends View implements PullToRefreshLayout.OnRefreshListener{

	private View searchView;
	private Context mContext;
	private EditText et_search;
	private ImageView iv_search_click;
	private List<Records> mWorkList;
	private AbsListView lv_search_result;
	private RotateAnimation loadingAnimation;
	private PullToRefreshLayout refreshLayout;
	private HashMap<String,Integer> resourceState;
	private MyBaseAdapter baseAdapter;
	public SearchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		searchView = View.inflate(context, R.layout.view_search, null);
	}

	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		searchView = View.inflate(context, R.layout.view_search, null);
	}

	public SearchView(Context context) {
		super(context);
		mContext = context;
		initView();
		initListener();
		initData();
	}

	private void initView() {
		searchView = View.inflate(mContext, R.layout.view_search, null);
		
		initListView();
		
		et_search = (EditText)searchView.findViewById(R.id.et_search);
		iv_search_click = (ImageView)searchView.findViewById(R.id.iv_search_click);
	}
	
	public void initListView(){
		lv_search_result = (AbsListView) searchView.findViewById(R.id.content_view);
		((ListView) lv_search_result).setDivider(null);
		refreshLayout = (PullToRefreshLayout) searchView.findViewById(R.id.refresh_view);
		refreshLayout.setOnRefreshListener(this);
		
		View headView = View.inflate(mContext, R.layout.search_head, null);
		((ListView) lv_search_result).addHeaderView(headView, null, false);
		
		loadingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(mContext,
				R.anim.rotating);
		// 添加匀速转动动画
		LinearInterpolator lir = new LinearInterpolator();
		loadingAnimation.setInterpolator(lir);
	}

	private void initListener() {
		iv_search_click.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		lv_search_result.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Toast.makeText(mContext, "checked..."+position, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void initData(){
		resourceState = BaseResource.getResourceState();
	}
	
	public void setData(CasesBean bean){
		mWorkList = bean.records;
		if(baseAdapter==null){
			baseAdapter = new MyBaseAdapter();
		}
		lv_search_result.setAdapter(baseAdapter);
	}

	public View getView(){
		searchView.setTag(this);
		return searchView;
	}
	
	class MyBaseAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mWorkList.size();
		}

		@Override
		public Object getItem(int position) {
			return mWorkList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if(convertView==null){
				holder = new ViewHolder();
				convertView = View.inflate(mContext, R.layout.item_search_result, null);
				holder.ivState = (ImageView)convertView.findViewById(R.id.iv_search_state);
				holder.tvPriority = (TextView)convertView.findViewById(R.id.tv_search_priority);
				holder.tvAddress = (TextView)convertView.findViewById(R.id.tv_search_address);
				holder.tvTime = (TextView)convertView.findViewById(R.id.tv_search_time);
				holder.tvDes = (TextView)convertView.findViewById(R.id.tv_search_des);
				holder.tvState = (TextView)convertView.findViewById(R.id.tv_search_state);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder)convertView.getTag();
			}
			
			Records records = mWorkList.get(position);
			
			holder.tvPriority.setText(records.repairCaseCode+"("+records.priorityDisplay+")");
			holder.tvAddress.setText("地址："+records.address);
			holder.tvTime.setText("时间："+records.requestTime);
			holder.tvDes.setText("描述："+records.description);
			holder.tvState.setText(records.statusDisplay);
			
			Integer resId = resourceState.get(records.statusDisplay);
			if(resId!=null){
				holder.ivState.setImageResource(resId);
			}
			
			return convertView;
		}
	}
	
	class ViewHolder{
		ImageView ivState;
		TextView tvPriority;
		TextView tvAddress;
		TextView tvTime;
		TextView tvDes;
		TextView tvState;
	}

	@Override
	public void onRefresh() {
		new OrderManager().cases(new BaseCallback<CasesBean>(){
			@Override
			public void messageSuccess(CasesBean bean) {
				baseAdapter.notifyDataSetChanged();
				refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_SUCCEED);
			}

			@Override
			public void connectFailure(MessageBean messageBean) {
				refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
			}

			@Override
			public void messageFailure(MessageBean backError) {
				refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
			}
		});
	}
}