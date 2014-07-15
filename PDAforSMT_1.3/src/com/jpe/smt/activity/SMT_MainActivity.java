package com.jpe.smt.activity;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.jpe.smt.R;
import com.jpe.smt.DataBase.WhcgDataCacheOpt;
import com.jpe.smt.pojo.User;
import com.jpe.smt.util.MemoryCache;

import java.util.List;

public class SMT_MainActivity extends TabActivity implements
		RadioGroup.OnCheckedChangeListener {
	public static final String TAB_ITEM_1 = "tabItem1";
	public static final String TAB_ITEM_2 = "tabItem2";
	public static final String TAB_ITEM_3 = "tabItem3";
	public static final String TAB_ITEM_4 = "tabItem4";
	public static final String[] tabs = { "tabItem1", "tabItem2", "tabItem3",
			"tabItem4" };
	private String ACTION_UPDATE = "com.echoliv.wuhan.civilcityinspector.updateManual";
	private String TAG = "WhcgMainActivity";
	private RadioGroup group;
	private WhcgDataCacheOpt mWhcgDataCacheOpt;
	private RadioButton radio_button1;
	private RadioButton radio_button4;
	private TabHost tabHost;

	private void changeBg(int paramInt) {
		switch (paramInt) {
		default:
			this.radio_button1.setBackgroundResource(R.drawable.tab_btn_bg_d);
			this.tabHost.setCurrentTabByTag("tabItem1");
			return;
		case R.id.radio_button1:
			this.radio_button1.setBackgroundResource(R.drawable.tab_btn_bg_d);
			this.radio_button4.setBackgroundDrawable(null);
			this.tabHost.setCurrentTabByTag("tabItem1");
			return;
		case R.id.radio_button2:
			this.radio_button1.setBackgroundDrawable(null);
			this.radio_button4.setBackgroundDrawable(null);
			this.tabHost.setCurrentTabByTag("tabItem2");
			return;
		case R.id.radio_button3:
			this.radio_button1.setBackgroundDrawable(null);
			this.radio_button4.setBackgroundDrawable(null);
			this.tabHost.setCurrentTabByTag("tabItem3");
			return;
		case R.id.radio_button4:
			this.radio_button4.setBackgroundResource(R.drawable.tab_btn_bg_d);
			this.radio_button1.setBackgroundDrawable(null);
			this.tabHost.setCurrentTabByTag("tabItem4");
			return;
		}
	}

	/**
	 * 得到是否登陆的缓存信息
	 */
	private void getLoginCache() {
		if (this.mWhcgDataCacheOpt == null)
			this.mWhcgDataCacheOpt = new WhcgDataCacheOpt(this);
		try {
			List<User> localList = this.mWhcgDataCacheOpt.get_table_user();
			if ((localList != null) && (localList.size() > 0)) {
				User localUser = (User) localList.get(localList.size() - 1);
				if (this.mWhcgDataCacheOpt.isLogin())
					MemoryCache.getInstance().setCurrentUser(localUser);
				Log.e(this.TAG, "从缓存中成功读取了登录数据");
			}
		} catch (Exception localException) {
			Log.e(this.TAG, localException.toString());
		}
	}

	protected void dialog() {
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
		localBuilder.setMessage("确认注销吗？");
		localBuilder.setTitle("提示");
		localBuilder.setPositiveButton("确认",
				new DialogInterface.OnClickListener() {
					public void onClick(
							DialogInterface paramAnonymousDialogInterface,
							int paramAnonymousInt) {
						paramAnonymousDialogInterface.dismiss();
						SMT_MainActivity.this.finish();
						MemoryCache.getInstance().logout();
					}
				});
		localBuilder.setNegativeButton("取消",
				new DialogInterface.OnClickListener() {
					public void onClick(
							DialogInterface paramAnonymousDialogInterface,
							int paramAnonymousInt) {
						paramAnonymousDialogInterface.dismiss();
					}
				});
		localBuilder.create().show();
	}

	public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt) {
		changeBg(paramInt);
	}

	public void onCreate(Bundle paramBundle) {
		// getLoginCache();
		super.onCreate(paramBundle);
		requestWindowFeature(1);
		setContentView(R.layout.whcg_main_view);
		int i = getIntent().getIntExtra("currentTab", -1);
		this.group = ((RadioGroup) findViewById(R.id.main_radio));
		this.group.setOnCheckedChangeListener(this);
		this.tabHost = getTabHost();
		TabHost.TabSpec localTabSpec1 = this.tabHost.newTabSpec("tabItem1");
		TabHost.TabSpec localTabSpec2 = this.tabHost.newTabSpec("tabItem2");
		TabHost.TabSpec localTabSpec3 = this.tabHost.newTabSpec("tabItem3");
		TabHost.TabSpec localTabSpec4 = this.tabHost.newTabSpec("tabItem4");
		localTabSpec1.setIndicator("tabItem1").setContent(
				new Intent(this, SMT_ManualActivity.class));
		Intent localIntent1 = new Intent(this, SMT_TousuActivity.class);
		localIntent1.putExtra("type", "tousu");
		localTabSpec2.setIndicator("tabItem2").setContent(localIntent1);
		localTabSpec3.setIndicator("tabItem3").setContent(
				new Intent(this, SMT_ToPublicActivity.class));
		Intent localIntent2 = new Intent(this, SMT_DealWithActivity.class);
		localTabSpec4.setIndicator("tabItem4").setContent(localIntent2);
		this.tabHost.addTab(localTabSpec1);
		this.tabHost.addTab(localTabSpec2);
		this.tabHost.addTab(localTabSpec3);
		this.tabHost.addTab(localTabSpec4);
		this.radio_button1 = ((RadioButton) findViewById(R.id.radio_button1));
		this.radio_button4 = ((RadioButton) findViewById(R.id.radio_button4));
		if (i != -1) {
			this.tabHost.setCurrentTabByTag(tabs[i]);
			this.radio_button1.setBackgroundDrawable(null);
			changeBg(new int[] { R.id.radio_button1, R.id.radio_button2,
					R.id.radio_button3, R.id.radio_button4 }[i]);
			return;
		}
		this.tabHost.setCurrentTabByTag(tabs[0]);
		changeBg(R.id.radio_button1);
	}

	public static class CurrentTab {
		public static int DealWith = 3;
		public static int News = 0;
		public static int Public;
		public static int Tousu = 1;

		static {
			Public = 2;
		}
	}
}