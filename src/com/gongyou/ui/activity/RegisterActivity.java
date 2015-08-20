package com.gongyou.ui.activity;


import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.gongyou.R;
import com.gongyou.ui.widget.TitleWidget;
import com.gongyou.utils.LogUtil;

public class RegisterActivity extends Activity {
	
	private TitleWidget title_widget;
	private EditText et_email;
	private EditText et_name;
	private EditText et_password;
	private EditText et_phone;
	private EditText et_validate;
	private RadioButton rb_male;
	private Button btn_register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initWidget();
		initTitle();
		initListener();
	}

	/**
	 * 获取输入的信息
	 */
	private Map getDate() {
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("mail", getDate(et_email));
		map.put("name",getDate(et_name));
		map.put("passWord",getDate(et_password));
		map.put("phone",getDate(et_phone));
		map.put("validate",getDate(et_validate) );
		map.put("sex",rb_male.isChecked()?"male":"female");
		return map;
	}
	
	private String getDate(EditText et){
		String data = String.valueOf(et.getText()).trim();
		return data;
	}

	private void initWidget() {
		title_widget = (TitleWidget)findViewById(R.id.title_widget);
		et_email = (EditText)findViewById(R.id.et_email);
		et_name = (EditText)findViewById(R.id.et_name);
		et_password = (EditText)findViewById(R.id.et_password);
		et_phone = (EditText)findViewById(R.id.et_phone);
		et_validate = (EditText)findViewById(R.id.et_validate);
		rb_male = (RadioButton) findViewById(R.id.tb_male);
		btn_register = (Button) findViewById(R.id.btn_register);
	}
	private void initTitle() {
		title_widget.setTitleText("注册");
		
	}
	
	
	private void initListener(){
		btn_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Map<String,String> map=getDate();
				if(map.containsValue("")){
					System.out.println(map);
					Toast.makeText(RegisterActivity.this, "数据不能为空", Toast.LENGTH_SHORT).show();
					LogUtil.e("test", "onclick");
					return;
				}
				
			}
		});
	}
}
