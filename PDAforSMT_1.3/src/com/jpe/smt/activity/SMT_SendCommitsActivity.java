package com.jpe.smt.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.jpe.smt.R;
import com.jpe.smt.aMap.AroundActivity;
import com.jpe.smt.aMapUtil.BmapPoint;
import com.jpe.smt.adapter.SpinnerAdapter;
import com.jpe.smt.imageCache.util.PictureUtil;
import com.jpe.smt.photo.CameraUtil;
import com.jpe.smt.photo.ItemAdapter;
import com.jpe.smt.photo.PlayMediaAll;
import com.jpe.smt.pojo.Topic;
import com.jpe.smt.util.ButtonUtil;
import com.jpe.smt.widgetScrollView.Utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SMT_SendCommitsActivity extends Activity {
	// 定位请求码
	private static int LOCATION_REQUEST_CODE = 101;
	// 照相请求码
	private static int CAMERA_REQUEST_CODE = 102;

	// 相册选择请求码
	private static int CHOICE_REQUEST_CODE = 103;
	// 定义区域选择的下拉列表
	private String[] areaList = { "白云区", "天河区", "番禺区", "海珠区", "南沙区", "荔湾区",
			"萝岗区", "黄浦区", "越秀区", "花都区" };
	private Spinner area_spinner;
	private SpinnerAdapter sadpater;
	// 地图定位视图
	private TextView location_text;
	// 问题输入框
	private EditText detail_edittext;
	// ******************拍照按钮******************
	private Button btnTakePhoto;
	private ArrayList<String> imgPaths = new ArrayList<String>();
	private ListView imglist;
	private ItemAdapter adapter;
	// 记录删除的位置
	private int pos;
	// ******************以上是拍照的*********
	// 相册选择
	private Button btnSelectPhoto;
	// 提交按钮
	private Button do_submit_button;

	// 定义一个案件类
	private Topic topic = new Topic();
	// 定义街道名称的字符串
	private String place;
	// 定义坐标定位的经纬度
	private BmapPoint latlng;
	// 现在有了 类型、经纬度、时间、id、事情

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			switch (msg.what) {
			// 更改定位view 显示当前定位的街道名称
			case 1:
				location_text.setText(place);
				break;
			// 更改照相的图片路径列表
			case 2:
				if (imgPaths.size() > 0) {
					adapter = new ItemAdapter(imgPaths,
							SMT_SendCommitsActivity.this);
					imglist.setAdapter(adapter);
					// 动态设置ListView组建的高度 在ScrollView中嵌套listview的解决办法
					Utility.setListViewHeightBasedOnChildren(imglist);
				}
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.whcg_sendcommits_view_01);
		// 获取标题名称
		Intent intent = getIntent();
		Bundle bdl = intent.getExtras();
		String str = bdl.getString("title");
		initialTitle(str);
		area_spinner = (Spinner) findViewById(R.id.area_spinner);
		sadpater = new SpinnerAdapter(areaList, SMT_SendCommitsActivity.this);
		area_spinner.setAdapter(sadpater);
		area_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// 设置案件区域
				topic.setArea(areaList[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		// 地图定位
		location_text = (TextView) findViewById(R.id.location_text);
		// 当布局被点击就跳转
		LinearLayout loc_Layout = (LinearLayout) findViewById(R.id.loc_Layout);
		loc_Layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toLoaction();
			}
		});

		// 问题详情
		detail_edittext = (EditText) findViewById(R.id.detail_edittext);
		// 拍照
		btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
		btnTakePhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CameraUtil cameraUtil = new CameraUtil(
						SMT_SendCommitsActivity.this);
				cameraUtil.cameraInit(SMT_SendCommitsActivity.this);
			}
		});

		// 初始化图片展示list
		imglist = (ListView) findViewById(R.id.imageView1);

		// 点击预览
		imglist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				PlayMediaAll.playMedia(SMT_SendCommitsActivity.this,
						imgPaths.get(position));
			}
		});
		// 长按删除
		imglist.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				pos = position;
				// 先弹出一个对话框 让人确定是否删除
				Dialog makeSure = new AlertDialog.Builder(
						SMT_SendCommitsActivity.this)
						.setTitle("温馨提示")
						.setMessage("确定要删除？")
						.setPositiveButton("确定",
						// 注意监听是DialogInterface里面的
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										delListFiles(pos);
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

									}
								}).create();
				makeSure.show();
				return true;
			}
		});
		btnSelectPhoto = (Button) findViewById(R.id.btnSelectPhoto);
		// 相册选择
		btnSelectPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 也需要返回值
				Intent localIntent = new Intent(SMT_SendCommitsActivity.this,
						ImageSanActivity.class);
				startActivity(localIntent);
			}
		});

		// 投诉提交按钮
		do_submit_button = (Button) findViewById(R.id.do_submit_button);
		do_submit_button.setOnClickListener(new Submit());
	}

	/**
	 * 跳转到定位雷达的页面
	 */
	private void toLoaction() {

		Intent localIntent = new Intent(this, AroundActivity.class);
		startActivityForResult(localIntent, LOCATION_REQUEST_CODE);
	}

	// 请求定位成功后回调
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == LOCATION_REQUEST_CODE
				&& resultCode == AroundActivity.LOCATION_REQUEST_CODE) {
			Bundle bundle = data.getExtras();
			String str = bundle.getString("addressName");
			BmapPoint point = bundle.getParcelable("point");
			place = str;
			latlng = point;
			// 设置topic
			topic.setPlace(place);
			// 设置经纬度
			topic.setLat(String.valueOf(latlng.getLat()));
			topic.setLng(String.valueOf(latlng.getLng()));
			Toast.makeText(SMT_SendCommitsActivity.this,
					latlng.getLat() + "," + latlng.getLng(), Toast.LENGTH_SHORT)
					.show();
			handler.sendEmptyMessage(1);
		}
		if (requestCode == CAMERA_REQUEST_CODE) {
			// 这是修复打开相机之后不照相的问题
			if (resultCode == Activity.RESULT_OK) {
				Log.i("1111", "我被调用了" + requestCode + ", " + requestCode);
				SharedPreferences sp = getSharedPreferences("ppp", 0);
				Log.i("1111", "文件路径：" + sp.getString("m", ""));
				String path = sp.getString("m", "");
				imgPaths.add(path);
				handler.sendEmptyMessage(2);
			} else {
				// 相机点击了返回按钮 这里删初缓存的临时文件
				SharedPreferences sp = getSharedPreferences("ppp", 0);
				Log.i("1111", "文件路径：" + sp.getString("m", "" + "-----删除缓存临时文件"));
				String path = sp.getString("m", "");
				CameraUtil.deleteTempFile(path);
			}

		}
	}

	@SuppressLint("NewApi")
	@Override
	protected void onRestart() {
		super.onRestart();
		SharedPreferences sp = getSharedPreferences("imgpaths", 0);
		Set<String> paths = sp.getStringSet("paths", null);
		if (paths != null && paths.size() != 0) {
			for (String path : paths) {
				imgPaths.add(path);
			}
			sp.edit().clear().commit();
			handler.sendEmptyMessage(2);
		}

	}

	// 设置界面标题栏目
	private void initialTitle(String title) {
		// 设置上报案件类型
		topic.setMode(title);
		((TextView) findViewById(R.id.middle_title_text)).setText(title);
		Button moreinfo = ((Button) findViewById(R.id.personal_info_bt));
		moreinfo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Intent localIntent = new Intent(SMT_SendCommitsActivity.this,
						SMT_MoreActivity.class);
				SMT_SendCommitsActivity.this.startActivity(localIntent);
			}
		});

	}

	// 删除附件
	private void delListFiles(int position) {
		if (imgPaths != null) {
			imgPaths.remove(position);
		}
		adapter.notifyDataSetChanged();
	}

	// ====================================================

	// 进行提交(这里封装内容和上报时间)//提交完成之后直接finish(以后修改主要网络请求正这里)
	private class Submit implements OnClickListener {
		@Override
		public void onClick(View v) {
			// 先进行点击判断 防止快速点击2次
			boolean flag = ButtonUtil.isFastDoubleClick();
			if (detail_edittext.getText().toString() != null
					&& !"".equals(detail_edittext.getText().toString())
					&& !flag) {
				topic.setBody(detail_edittext.getText().toString());
				topic.setTime(new Date().toLocaleString());
				Toast.makeText(SMT_SendCommitsActivity.this, "已提交成功！",
						Toast.LENGTH_SHORT).show();
				SMT_SendCommitsActivity.this.finish();
			} else {
				Toast.makeText(SMT_SendCommitsActivity.this, "问题详情不能为空，请重新填写！",
						Toast.LENGTH_SHORT).show();
				return;
			}

		}

	}
}
