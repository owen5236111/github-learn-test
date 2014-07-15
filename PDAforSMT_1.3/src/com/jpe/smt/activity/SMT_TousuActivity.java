package com.jpe.smt.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jpe.smt.R;

import com.jpe.smt.CircleWidget.CircleImageView;
import com.jpe.smt.CircleWidget.CircleLayout;
import com.jpe.smt.CircleWidget.CircleLayout.OnItemClickListener;
import com.jpe.smt.CircleWidget.CircleLayout.OnItemSelectedListener;
import com.jpe.smt.aMap.LocationActivity;
import com.jpe.smt.dialogs.LogoutShowDialog;

public class SMT_TousuActivity extends Activity implements
		OnItemSelectedListener, OnItemClickListener {
	private TextView selectedTextView;

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(1);
		setContentView(R.layout.whcg_tousu_view);
		CircleLayout circleMenu = (CircleLayout) findViewById(R.id.carousel);
		circleMenu.setOnItemSelectedListener(this);
		circleMenu.setOnItemClickListener(this);

		selectedTextView = (TextView) findViewById(R.id.main_selected_textView);
		selectedTextView.setText(((CircleImageView) circleMenu
				.getSelectedItem()).getName());
	}

	// 监听返回按钮
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			LogoutShowDialog.showMessageDialog(SMT_TousuActivity.this,
					"您确定要退出吗？", "温馨提示");
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onItemClick(View view, int position, long id, String name) {
		// 其他设备1市政公用设施2房屋建筑3 雷达 position4 道路交通5园林绿化0
		switch (position) {
		case 1:
			toSubmitAct("其他设备投诉");
			break;
		case 2:
			toSubmitAct("市政公用设施投诉");
			break;
		case 3:
			toSubmitAct("房屋建筑投诉");
			break;
		case 4:
			SMT_TousuActivity.this.toLoaction();
			break;
		case 5:
			toSubmitAct("道路交通投诉");
			break;
		case 0:
			toSubmitAct("园林绿化投诉");
			break;

		}

	}

	/**
	 * 跳转到上报页面
	 */
	private void toSubmitAct(String title) {
		// 将需要跳转的页面的标题名称在这里传递
		Intent localIntent = new Intent(this, SMT_SendCommitsActivity.class);
		localIntent.putExtra("title", title);
		startActivity(localIntent);
	}

	/**
	 * 跳转到定位雷达的页面
	 */
	private void toLoaction() {
		Intent localIntent = new Intent(this, LocationActivity.class);
		startActivity(localIntent);
	}

	// 点选的时候在下方显示栏目名称
	@Override
	public void onItemSelected(View view, int position, long id, String name) {
		selectedTextView.setText(name);
	}

}