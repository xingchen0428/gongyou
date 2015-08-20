package com.gongyou.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gongyou.R;

public class DeviceActivity extends Activity {

	private ImageView iv_title_left;
	private TextView tv_title;
	private ListView lv_device;
	private String[] names;
	private int[] res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device);

		initView();
		initListener();
		initData();
	}

	private void initView() {
		iv_title_left = (ImageView) this.findViewById(R.id.iv_title_left);
		tv_title = (TextView) this.findViewById(R.id.tv_title);

		lv_device = (ListView) this.findViewById(R.id.lv_device);
		lv_device.setDivider(null);
	}

	private void initListener() {
		iv_title_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		lv_device.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO 点击ListView条目跳转
				Toast.makeText(DeviceActivity.this, "check---" + position,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void initData() {
		iv_title_left.setImageResource(R.drawable.rightmenu);
		iv_title_left.setVisibility(View.VISIBLE);
		tv_title.setText(this.getIntent().getStringExtra("title"));

		names = new String[] { "保险箱", "门禁系统", "空调系统", "考勤系统", "停车场", "冷机系统",
				"电梯", "能源系统", "消防系统", "供暖系统", "空气质量", "灯光系统", "门卡", "电力系统",
				"中水系统", "监控系统", "VAV", "通风系统", "访客系统", "水循环系统", "水泵系统" };
		
		res = new int[] { R.drawable.dv_bx, R.drawable.dv_mj, R.drawable.dv_kt,
				R.drawable.dv_kq, R.drawable.dv_tc, R.drawable.dv_lj,
				R.drawable.dv_dt, R.drawable.dv_ny, R.drawable.dv_xf,
				R.drawable.dv_gn, R.drawable.dv_kqzl, R.drawable.dv_dg,
				R.drawable.dv_mk, R.drawable.dv_dl, R.drawable.dv_zs,
				R.drawable.dv_jk, R.drawable.dv_bx, R.drawable.dv_tf,
				R.drawable.dv_fk, R.drawable.dv_sxh, R.drawable.dv_sb };
		
		lv_device.setAdapter(new MyBaseAdapter());
	}
	
	class MyBaseAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return names.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView = View.inflate(DeviceActivity.this, R.layout.item_device, null);
			}
			
			ImageView iv_dv = (ImageView)convertView.findViewById(R.id.iv_dv);
			TextView tv_dv = (TextView)convertView.findViewById(R.id.tv_dv);
			
			iv_dv.setImageResource(res[position]);
			tv_dv.setText(names[position]);
			
			return convertView;
		}
	}
}
