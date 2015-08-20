package com.gongyou.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.gongyou.R;
import com.gongyou.ui.widget.DrawingBoardWidget;
import com.gongyou.ui.widget.MessageProcessWidget;

public class TestActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		DrawingBoardWidget mp_message_process = (DrawingBoardWidget)findViewById(R.id.mp_message_process);
		mp_message_process.setMoney("500万");
		
	}
}
