package com.jpe.smt.activity;

import com.jpe.smt.R;
import com.jpe.smt.dialogs.LogoutShowDialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class SMT_ManualActivity extends Activity {
	private WebView webView;

	private void readHtmlFormAssets(String paramString) {
		if (this.webView == null)
			this.webView = ((WebView) findViewById(R.id.webview));
		WebSettings localWebSettings = this.webView.getSettings();
		localWebSettings.setUseWideViewPort(true);
		localWebSettings.setJavaScriptEnabled(true);
		this.webView.loadUrl(paramString);
	}

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(1);
		setContentView(R.layout.whcg_manual_view);
	}

	protected void onStart() {
		readHtmlFormAssets("file:///android_asset/Phone/index.html");
		super.onStart();
	}

	protected void onStop() {
		super.onStop();
	}

	// 监听返回按钮
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			LogoutShowDialog.showMessageDialog(SMT_ManualActivity.this,
					"您确定要退出吗？", "温馨提示");
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}