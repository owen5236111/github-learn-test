package com.jpe.smt.activity;

import com.jpe.smt.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SMT_MoreActivity extends Activity {

	private View gerenzhongxin, help, update, about;
	private static int LOGIN = 1;

	// 判断点击事件进行请求分发
	private View.OnClickListener clickonLines = new View.OnClickListener() {

		public void onClick(View paramAnonymousView) {
			switch (paramAnonymousView.getId()) {
			case R.id.personalcenter_line:
				personalCenter();
				break;
			case R.id.update_line:
				update();
				break;
			case R.id.about_line:
				about();
				break;
			case R.id.setting_line:
				showHelp();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.whcg_more_view);
		initialTitle();
		gerenzhongxin = findViewById(R.id.personalcenter_line);
		help = findViewById(R.id.setting_line);
		update = findViewById(R.id.update_line);
		about = findViewById(R.id.about_line);

		// 添加监听事件
		gerenzhongxin.setOnClickListener(this.clickonLines);
		help.setOnClickListener(this.clickonLines);
		update.setOnClickListener(this.clickonLines);
		about.setOnClickListener(this.clickonLines);

	}

	// 设置界面标题栏目
	private void initialTitle() {
		((TextView) findViewById(R.id.middle_title_text))
				.setText(getResources().getString(R.string.main_title_more));
	}

	/**
	 * 跳转到个人中心 /更新 /关于
	 */
	public void personalCenter() {
		// 判断是否登陆过 这里先登陆实验下
		// if (MemoryCache.getInstance().isLogin())
		// System.out.println("个人中心被调用了！");
		if (true) {
			startActivity(new Intent(SMT_MoreActivity.this,
					SMT_PersonalInfoActivity.class));
			SMT_MoreActivity.this.finish();
		}
		// } else {
		// startActivityForResult(
		// new Intent(this, WhcgUserloginActivity.class), LOGIN);
		// }
	}

	public void update() {
		Toast.makeText(SMT_MoreActivity.this, "没有更新！", 1).show();
	}

	public void about() {
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
		localBuilder.setTitle(R.string.about);
		localBuilder
				.setView(((LayoutInflater) getSystemService("layout_inflater"))
						.inflate(R.layout.about_dialog_view, null));
		localBuilder.setPositiveButton("确认",
				new DialogInterface.OnClickListener() {
					public void onClick(
							DialogInterface paramAnonymousDialogInterface,
							int paramAnonymousInt) {
						paramAnonymousDialogInterface.dismiss();
					}
				});
		localBuilder.create().show();
	}

	public void showHelp() {
		Intent localIntent = new Intent(SMT_MoreActivity.this,
				SMT_GuidanceActicity.class);
		// 给数据库 告诉已经看过帮助
		localIntent.putExtra("firstload", 1);
		SMT_MoreActivity.this.startActivity(localIntent);
	}

}
