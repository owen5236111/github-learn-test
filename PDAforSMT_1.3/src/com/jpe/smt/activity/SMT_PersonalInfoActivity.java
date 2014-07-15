package com.jpe.smt.activity;

import com.jpe.smt.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SMT_PersonalInfoActivity extends Activity {
	// 编辑个人信息按钮
	private Button selfinfo_edit_bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.whcg_personalinfo_view);
		initialTitle();
		selfinfo_edit_bt = (Button) findViewById(R.id.selfinfo_edit_bt);
		selfinfo_edit_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 这里要先判断 人是否登陆过，登陆过才让编辑个人信息，如果没登陆。则跳转到登陆界面
				startActivity(new Intent(SMT_PersonalInfoActivity.this,
						SMT_ModifypersonalInfoActivity.class));
			}
		});

	}

	// 设置界面标题栏目
	private void initialTitle() {
		((TextView) findViewById(R.id.middle_title_text))
				.setText(getResources().getString(R.string.personalcenter));
		Button moreinfo = ((Button) findViewById(R.id.personal_info_bt));
		moreinfo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Intent localIntent = new Intent(SMT_PersonalInfoActivity.this,
						SMT_MoreActivity.class);
				SMT_PersonalInfoActivity.this.startActivity(localIntent);
				SMT_PersonalInfoActivity.this.finish();
			}
		});
	}
}