package com.jpe.smt.activity;

import java.util.ArrayList;
import com.jpe.smt.R;
import com.jpe.smt.imageCache.ImageCache;
import com.jpe.smt.imageCache.loader.ImageFetcher;
import com.jpe.smt.pojo.Attach;
import com.jpe.smt.pojo.News;
import com.jpe.smt.widgetCoverFlow.CoverFlow;
import com.jpe.smt.widgetCoverFlow.ResourceImageAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author oywf 新闻详情页面 和热门信息详情页面 主要就是内容传递的问题 要知道是哪个新闻对象进行的展示 直接传递对象过来！
 */
public class SMT_NewsDetailsActivity extends Activity {
	private TextView Titl_text, news_ptime, news_text_detail;
	// 更换图片展示 2014-04-28
	// private ImageView detail_leftimageView;

	private ImageFetcher mImageLoader;
	private Button btnReturn;
	private News cg_new;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.whcg_detail_news_view);
		initialTitle();
		cg_new = (News) getIntent().getSerializableExtra("cg_new");
		Titl_text = (TextView) findViewById(R.id.Titl_text);
		news_ptime = (TextView) findViewById(R.id.news_ptime);
		news_text_detail = (TextView) findViewById(R.id.news_text_detail);
		// detail_leftimageView = (ImageView)
		// findViewById(R.id.detail_leftimageView);
		// 文字和图片以不同的adapter进行设置
		final CoverFlow coverFlow1 = (CoverFlow) findViewById(this
				.getResources().getIdentifier("coverflow", "id",
						"com.jpe.smt"));
		// 这里设置没有倒影 直接图片展示 设置false
		setupCoverFlow(coverFlow1);

		initData();
		btnReturn = (Button) findViewById(R.id.btnReturn);

		btnReturn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SMT_NewsDetailsActivity.this.finish();
			}
		});
	}

	// 设置数据
	public void initData() {
		// 设置新闻标题
		if (cg_new.getTitle() != null && !"".equals(cg_new.getTitle())) {
			Titl_text.setText(cg_new.getTitle());
		} else {
			Titl_text.setText("暂无标题");
		}

		// 设置时间
		if (cg_new.getTime() != null && !"".equals(cg_new.getTime())) {
			news_ptime.setText("时间:" + cg_new.getTime());
		} else {
			news_ptime.setText("暂无时间");
		}
		// 设置正文
		if (cg_new.getBody() != null && !"".equals(cg_new.getBody())) {
			news_text_detail.setText(cg_new.getBody());
		} else {
			news_text_detail.setText("暂无新闻正文");
		}

	}

	// 设置界面标题栏目
	private void initialTitle() {
		((TextView) findViewById(R.id.middle_title_text)).setText("新闻详情");
	}

	// **********************以下是图片展示效果*****************************
	private void setupCoverFlow(final CoverFlow mCoverFlow) {
		BaseAdapter coverImageAdapter;
		// 设置图片路径资源
		ArrayList<Attach> attachs = cg_new.getAttachs();
		ArrayList<String> imageUrls = new ArrayList<String>();
		for (int i = 0; i < attachs.size(); i++) {
			imageUrls.add(attachs.get(i).getImageUrl());
		}
		coverImageAdapter = new ResourceImageAdapter(
				SMT_NewsDetailsActivity.this, imageUrls);
		mCoverFlow.setAdapter(coverImageAdapter);
		// 设置默认第几个图片显示
		mCoverFlow.setSelection(1, true);
	}

	// *************************展示效果结束*****************************************
	// 监听返回按钮
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			SMT_NewsDetailsActivity.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}