package com.gongyou.ui.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gongyou.R;
import com.gongyou.base.BaseNet.BaseCallback;
import com.gongyou.domain.CasesBean;
import com.gongyou.domain.LoginBean;
import com.gongyou.domain.MessageBean;
import com.gongyou.net.OrderManager;
import com.gongyou.ui.view.FunctionView;
import com.gongyou.ui.view.SearchView;
import com.gongyou.ui.view.SlidingLeftView;
import com.gongyou.ui.view.UserView;
import com.gongyou.ui.widget.NoTouchViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class HomeActivity extends Activity {

	private NoTouchViewPager vp_home;
	private ImageView iv_home_function;
	private ImageView iv_home_search;
	private ImageView iv_home_user;
	private ImageView iv_title_left;
	private TextView tv_title_right;
	private TextView tv_title;
	private ArrayList<View> viewList = new ArrayList<View>();
	private View view_function;
	private View view_search;
	private View view_user;
	private View slidingLeftView;
	private SlidingMenu mMenu;
	private String type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		initView();
		initListener();
		initData();
	}

	//测试git提交  test git push too----test git push branch
	private void initView() {
		this.findViewById(R.id.view_shadow).setVisibility(View.VISIBLE);
		vp_home = (NoTouchViewPager) this.findViewById(R.id.vp_home);

		iv_home_function = (ImageView) this.findViewById(R.id.iv_home_function);
		iv_home_search = (ImageView) this.findViewById(R.id.iv_home_search);
		iv_home_user = (ImageView) this.findViewById(R.id.iv_home_user);

		iv_title_left = (ImageView) this.findViewById(R.id.iv_title_left);
		tv_title_right = (TextView) this.findViewById(R.id.tv_title_right);
		tv_title = (TextView) this.findViewById(R.id.tv_title);
		
		view_function = View.inflate(this, R.layout.view_function, null);
		view_search = View.inflate(this, R.layout.view_search, null);
		view_search = View.inflate(this, R.layout.view_user, null);
		
		type = ((LoginBean)(this.getIntent().getSerializableExtra("LoginBean"))).type;
		if("1".equals(type) || "2".equals(type) || "3".equals(type) ||"4".equals(type)){
			initSlidingMenu();
			iv_title_left.setVisibility(View.VISIBLE);
		}
	}

	private void initSlidingMenu() {
		slidingLeftView = new SlidingLeftView(this).getView();
		mMenu = new SlidingMenu(this);
		mMenu.setMode(SlidingMenu.LEFT);
		mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mMenu.setShadowWidthRes(R.dimen.shadow_width);
		mMenu.setShadowDrawable(R.drawable.shadow);
		
		mMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mMenu.setFadeDegree(0.35f);
		mMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		
		mMenu.setMenu(slidingLeftView);
	}

	private void initListener() {
		iv_home_function.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				vp_home.setCurrentItem(0,false);
				HomeActivity.this.findViewById(R.id.view_shadow).setVisibility(View.VISIBLE);
				if("1".equals(type) || "2".equals(type) || "3".equals(type) ||"4".equals(type)){
					iv_title_left.setVisibility(View.VISIBLE);
				}
				tv_title_right.setVisibility(View.GONE);
				tv_title.setText("工友互联");
				setBackgroundSelecter(true,false,false);
			}
		});

		iv_home_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				vp_home.setCurrentItem(1,false);
				HomeActivity.this.findViewById(R.id.view_shadow).setVisibility(View.GONE);
				iv_title_left.setVisibility(View.GONE);
				tv_title_right.setVisibility(View.GONE);
				tv_title.setText("搜索");
				setBackgroundSelecter(false,true,false);
				
				new OrderManager().cases(new BaseCallback<CasesBean>(){
					@Override
					public void messageSuccess(CasesBean bean) {
						System.out.println("my records-----------"+bean.records);
						
						((SearchView)(viewList.get(1).getTag())).setData(bean);
					}

					@Override
					public void connectFailure(MessageBean messageBean) {
						Toast.makeText(HomeActivity.this, "请检查您的网络连接", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void messageFailure(MessageBean backError) {
						
					}
				});
			}
		});

		iv_home_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				vp_home.setCurrentItem(2,false);
				HomeActivity.this.findViewById(R.id.view_shadow).setVisibility(View.GONE);
				iv_title_left.setVisibility(View.GONE);
				tv_title_right.setVisibility(View.GONE);
				tv_title.setText("我的信息");
				setBackgroundSelecter(false,false,true);
			}
		});
		
		iv_title_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mMenu.toggle();
			}
		});
	}
	
	private void initData() {
		viewList.add(new FunctionView(this).getView());
		viewList.add(new SearchView(this).getView());
		viewList.add(new UserView(this).getView());
		
		vp_home.setAdapter(new MyPagerAdapter());
	}
	
	class MyPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return viewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			View view = viewList.get(position);
			ViewGroup parent = (ViewGroup)view.getParent();
			if(parent!=null){
				parent.removeAllViews();
			}
			
			container.addView(view);
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(viewList.get(position));
		}
	}
	
	public void setBackgroundSelecter(boolean isFunction, boolean isSearch, boolean isUsered){
		if(isFunction){
			iv_home_function.setImageResource(R.drawable.iv_home_function_selecter);
		}else{
			iv_home_function.setImageResource(R.drawable.iv_home_function);
		}
		
		if(isSearch){
			iv_home_search.setImageResource(R.drawable.iv_home_search_selecter);
		}else{
			iv_home_search.setImageResource(R.drawable.iv_home_search);
		}
		
		if(isUsered){
			iv_home_user.setImageResource(R.drawable.iv_home_user_selecter);
		}else{
			iv_home_user.setImageResource(R.drawable.iv_home_user);
		}
	}
}
