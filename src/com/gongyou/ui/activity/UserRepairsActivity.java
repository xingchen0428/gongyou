package com.gongyou.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.gongyou.R;
import com.gongyou.ui.view.DateTimePickerView;
import com.gongyou.ui.view.DateTimePickerView.OnDateTimeSetListener;
import com.gongyou.ui.widget.PhotoView;
import com.gongyou.utils.StringUtil;

@SuppressWarnings("deprecation")
public class UserRepairsActivity extends Activity {

	private ImageView iv_title_left;
	private TextView tv_title;
	private EditText et_repairs_des;
	private LinearLayout ll_repairs_settime;
	private TextView tv_repairs_time;
	private ImageView iv_repairs_tp;
	private ImageView iv_repairs_ap;
	private LinearLayout ll_repairs_photos;
	private SlidingDrawer mDrawer;
	private TextView tv_chouti_finish;
	private DateTimePickerView pickerView;
	private LinearLayout content;
	private Uri photoUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repairs);
		
		initView();
		initListener();
		initData();
	}

	private void initView() {
		iv_title_left = (ImageView)this.findViewById(R.id.iv_title_left);
		tv_title = (TextView)this.findViewById(R.id.tv_title);
		
		et_repairs_des = (EditText)this.findViewById(R.id.et_repairs_des);
		ll_repairs_settime = (LinearLayout)this.findViewById(R.id.ll_repairs_settime);
		tv_repairs_time = (TextView)this.findViewById(R.id.tv_repairs_time);
		
		iv_repairs_tp = (ImageView)this.findViewById(R.id.iv_repairs_tp);
		iv_repairs_ap = (ImageView)this.findViewById(R.id.iv_repairs_ap);
		
		ll_repairs_photos = (LinearLayout)this.findViewById(R.id.ll_repairs_photos);
		
		mDrawer = (SlidingDrawer) findViewById(R.id.slidingdrawer);
		tv_chouti_finish = (TextView)this.findViewById(R.id.tv_chouti_finish);

		pickerView = new DateTimePickerView(this, System.currentTimeMillis());
		content = (LinearLayout)this.findViewById(R.id.content);
		content.addView(pickerView.getView());
	}

	private void initListener() {
		iv_title_left.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				iv_title_left.setVisibility(View.GONE);
				finish();
			}
		});
		
		ll_repairs_settime.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				mDrawer.animateOpen();
			}
		});
		
		iv_repairs_tp.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//TODO 跳转拍照
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//调用android自带的照相机
//				photoUrl = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				startActivityForResult(intent, 1); 
			}
		});
		
		iv_repairs_ap.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//TODO 跳转图片库
				Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//调用android的图库
				startActivityForResult(i, 2);
			}
		});
		
		tv_chouti_finish.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				pickerView.onClick();
				mDrawer.animateClose();
			}
		});
		
		pickerView.setOnDateTimeSetListener(new OnDateTimeSetListener(){

			@Override
			public void OnDateTimeSet(View dialog, long date) {
				Toast.makeText(UserRepairsActivity.this,
						"您输入的日期是：" + StringUtil.getStringDate(date), Toast.LENGTH_LONG)
						.show();
				tv_repairs_time.setText(StringUtil.getStringDate(date));
			}
		});
	}

	private void initData() {
		iv_title_left.setVisibility(View.VISIBLE);
		iv_title_left.setImageResource(R.drawable.rightmenu);
		tv_title.setText("日常报修");
		
		tv_repairs_time.setText(StringUtil.getStringDate(System.currentTimeMillis()));
	}
	
	public void commitOrder(View v){
		//TODO 将报修的内容提交到服务器
		
		finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Bitmap bitmap = null;
		switch (requestCode) {
		case 1:
			Toast.makeText(UserRepairsActivity.this, "相机打开了。。。", Toast.LENGTH_SHORT).show();
			switch (resultCode) {
				case Activity.RESULT_OK:
					Bundle bundle = data.getExtras();
					bitmap = (Bitmap) bundle.get("data");
					break;
				}
			break;
		case 2:
			Toast.makeText(UserRepairsActivity.this, "图库打开了。。。", Toast.LENGTH_SHORT).show();
			switch (resultCode) {
			case Activity.RESULT_OK:
				Uri uri = data.getData();
				Cursor cursor = this.getContentResolver().query(uri, null,
				null, null, null); 
				cursor.moveToFirst();
				String imgPath = cursor.getString(1); // 图片文件路径 
				
				Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = false;
				options.inSampleSize = 2;
				bitmap = BitmapFactory.decodeFile(imgPath, options); 
				break;
			}
			break;
		}
		
		if(bitmap!=null){
			ll_repairs_photos.addView(new PhotoView(this).setResource(bitmap).getView());
		}
	}
}
