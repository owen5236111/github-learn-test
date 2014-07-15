package com.jpe.smt.activity;

import java.util.List;
import java.util.Set;

import com.jpe.smt.R;
import com.jpe.smt.imageScan.ChildAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ShowImageActivity extends Activity {
	private GridView mGridView;
	private List<String> list;
	private ChildAdapter adapter;
	private Button btn_selectImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.grid_show_image);
		initialTitle();
		mGridView = (GridView) findViewById(R.id.child_grid);
		btn_selectImg = (Button) findViewById(R.id.btn_selectImg);
		list = getIntent().getStringArrayListExtra("data");
		adapter = new ChildAdapter(this, list, mGridView);
		mGridView.setAdapter(adapter);
		btn_selectImg.setOnClickListener(new OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				SharedPreferences preferences = ShowImageActivity.this
						.getSharedPreferences("imgpaths", Context.MODE_PRIVATE);
				Editor editor = preferences.edit();
				Set<String> paths = adapter.getSelectItemsPaths(adapter
						.getSelectItems());
				editor.putStringSet("paths", paths);
				editor.commit();
				ShowImageActivity.this.finish();
			}
		});

	}

	@Override
	public void onBackPressed() {
		ShowImageActivity.this.finish();
		super.onBackPressed();
	}

	// 设置界面标题栏目
	private void initialTitle() {
		((TextView) findViewById(R.id.middle_title_text)).setText("图片选择");
	}
}
