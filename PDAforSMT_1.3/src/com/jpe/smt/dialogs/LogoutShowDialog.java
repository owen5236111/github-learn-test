package com.jpe.smt.dialogs;

import com.jpe.smt.R;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author oywf 仿iphone样式退出的时候调用弹出窗 选择是否退出
 * 
 */
public class LogoutShowDialog {
	public static void showMessageDialog(Context context, String msg,
			String title) {
		final Dialog lDialog = new Dialog(context,
				android.R.style.Theme_Translucent_NoTitleBar);
		lDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		lDialog.setContentView(R.layout.logout_dialog);
		((TextView) lDialog.findViewById(R.id.dialog_title)).setText(title);
		((TextView) lDialog.findViewById(R.id.dialog_message)).setText(msg);
		((Button) lDialog.findViewById(R.id.ok)).setText("确定");
		((Button) lDialog.findViewById(R.id.cancel))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						lDialog.dismiss();
					}
				});
		((Button) lDialog.findViewById(R.id.ok))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						lDialog.dismiss();
						System.exit(0);
					}
				});
		lDialog.show();

	}
}
