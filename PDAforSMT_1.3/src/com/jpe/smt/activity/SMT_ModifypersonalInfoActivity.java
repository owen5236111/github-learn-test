package com.jpe.smt.activity;


import com.jpe.smt.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class SMT_ModifypersonalInfoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.whcg_modifypersonal_info_view);
		initialTitle();
	}

	// 设置界面标题栏目
	private void initialTitle() {
		((TextView) findViewById(R.id.middle_title_text))
				.setText(getResources().getString(R.string.selfinfo_edit));
		// if (!GetWeatherService.getInstance().updated)
		// GetWeatherService.getInstance().UpdateWeatherString(this);
		// String str = getSharedPreferences("weather", 0).getString("weather",
		// getResources().getString(2131230748));
		// ((TextView)findViewById(2131099674)).setText(str);
		Button moreinfo = ((Button) findViewById(R.id.personal_info_bt));
		moreinfo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Intent localIntent = new Intent(
						SMT_ModifypersonalInfoActivity.this,
						SMT_MoreActivity.class);
				SMT_ModifypersonalInfoActivity.this
						.startActivity(localIntent);
				SMT_ModifypersonalInfoActivity.this.finish();
			}
		});
	}
}