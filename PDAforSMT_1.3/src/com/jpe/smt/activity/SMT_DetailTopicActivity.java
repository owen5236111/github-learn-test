package com.jpe.smt.activity;

import java.util.ArrayList;

import com.jpe.smt.R;
import com.jpe.smt.imageCache.ImageCache;
import com.jpe.smt.imageCache.loader.ImageFetcher;
import com.jpe.smt.pojo.Attach;
import com.jpe.smt.pojo.Topic;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SMT_DetailTopicActivity extends Activity {
	// (头部分)投诉标题,time,类型，区域
	private TextView topicdetail_title_text, topicdetail_uploadtime_text,
			topicdetail_type_text, topicdetail_region_text;
	// 中间图片资源
	private ImageView detail_leftimageView, detail_rightimageView2;
	// 投诉正文部分
	private TextView topic_detail_webview;
	// 任务情况 主要是时间
	// 已经提交时间、 开始处理时间、 处理完成时间
	private TextView topicdetail_subtime_text, topicdetail_checktime_text,
			topicdetail_donetime_text;
	// 状态的布局 用于判断显示处理状态
	// 显示：setVisibility(View.VISIBLE);隐藏：setVisibility(View.GONE);
	private LinearLayout track_waitingcheck_line, track_checked_line,
			track_done_line;
	// 返回按钮 用于返回到之前列表信息
	private Button btnReturn;
	private Topic topic;
	private ArrayList<Attach> attachs;

	// *********************图片跑马灯动画****************
	public boolean juage = true;
	public Animation animation1;
	public Animation animation2;
	private ImageFetcher mImageLoader;
	public int count = 0;
	public Handler handler = new Handler();
	public Runnable runnable = new Runnable() {

		@Override
		public void run() {
			AnimationSet animationSet1 = new AnimationSet(true);
			AnimationSet animationSet2 = new AnimationSet(true);
			detail_rightimageView2.setVisibility(View.VISIBLE);
			TranslateAnimation ta = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
					-2f, Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_SELF, 0f);
			ta.setDuration(1000);
			animationSet1.addAnimation(ta);
			animationSet1.setFillAfter(true);
			ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
					Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
					0f, Animation.RELATIVE_TO_SELF, 0f);
			ta.setDuration(1000);
			animationSet2.addAnimation(ta);
			animationSet2.setFillAfter(true);
			// iamgeView 出去 imageView2 进来
			detail_leftimageView.startAnimation(animationSet1);
			detail_rightimageView2.startAnimation(animationSet2);

			// 设置图片信息

			mImageLoader.loadImage(attachs.get(count % attachs.size())
					.getImageUrl(), detail_leftimageView,
					R.drawable.default_news);
			count++;
			mImageLoader.loadImage(attachs.get(count % attachs.size())
					.getImageUrl(), detail_rightimageView2,
					R.drawable.default_news);
			if (juage)
				handler.postDelayed(runnable, 6000);
		}

	};

	public void onPause() {
		juage = false;
		super.onPause();
	}

	// **********************跑马灯动画结束**********************

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.whcg_detail_topic_view);
		initialTitle();
		topic = (Topic) getIntent().getSerializableExtra("topic");
		// 先得到图片
		attachs = topic.getAttachs();

		mImageLoader = new ImageFetcher(SMT_DetailTopicActivity.this);
		// 设置缓存
		mImageLoader.setImageCache(ImageCache
				.getInstance(SMT_DetailTopicActivity.this));
		// 头部分
		topicdetail_title_text = (TextView) findViewById(R.id.topicdetail_title_text);
		topicdetail_uploadtime_text = (TextView) findViewById(R.id.topicdetail_uploadtime_text);
		topicdetail_type_text = (TextView) findViewById(R.id.topicdetail_type_text);
		topicdetail_region_text = (TextView) findViewById(R.id.topicdetail_region_text);
		initHead();
		// 投诉正文
		topic_detail_webview = (TextView) findViewById(R.id.topic_detail_webview);
		initBody();
		// 问题处理跟踪的时间设置(根据任务状态进行判断后设置)
		topicdetail_subtime_text = (TextView) findViewById(R.id.topicdetail_subtime_text);
		topicdetail_checktime_text = (TextView) findViewById(R.id.topicdetail_checktime_text);
		topicdetail_donetime_text = (TextView) findViewById(R.id.topicdetail_donetime_text);
		// linearlayout 控制显示
		track_waitingcheck_line = (LinearLayout) findViewById(R.id.track_waitingcheck_line);
		track_checked_line = (LinearLayout) findViewById(R.id.track_checked_line);
		track_done_line = (LinearLayout) findViewById(R.id.track_done_line);
		initGenZhong();
		// 返回按钮
		btnReturn = (Button) findViewById(R.id.btnReturn);
		btnReturn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SMT_DetailTopicActivity.this.finish();
			}
		});
		// 得到图片
		detail_leftimageView = (ImageView) findViewById(R.id.detail_leftimageView);
		detail_rightimageView2 = (ImageView) findViewById(R.id.detail_rightimageView2);
		if (null != attachs.get(0)) {
			// 开启跑马灯效果
			mImageLoader.loadImage(attachs.get(0).getImageUrl(),
					detail_leftimageView, R.drawable.default_news);
		}
		detail_rightimageView2.setVisibility(View.GONE);

		handler.postDelayed(runnable, 1000);

	}

	// 初始化头部信息
	public void initHead() {
		if (topic.getSubject() != null && !"".equals(topic.getSubject())) {
			topicdetail_title_text.setText(topic.getSubject() + "投诉");
		} else {
			topicdetail_title_text.setText("");
		}

		if (topic.time != null && !"".equals(topic.getTime())) {
			topicdetail_uploadtime_text.setText(topic.getTime());
		} else {
			topicdetail_uploadtime_text.setText("");
		}
		if (topic.getSubject() != null && !"".equals(topic.getSubject())) {
			topicdetail_type_text.setText(topic.getSubject());
		} else {
			topicdetail_type_text.setText("");
		}
		if (topic.getArea() != null && !"".equals(topic.getArea())) {
			topicdetail_region_text.setText(topic.getArea());
		} else {
			topicdetail_region_text.setText("");
		}
	}

	// 初始化投诉正文
	public void initBody() {
		if (topic.getBody() != null && !"".equals(topic.getBody())) {
			topic_detail_webview.setText(topic.getBody());
		} else {
			topic_detail_webview.setText("");
		}
	}

	// 初始化跟踪内容
	public void initGenZhong() {
		// 状态肯定有值 不须要判断// 案件处理状态 （1、未受理 2、待处理 3、处理中 4、已处理）
		switch (topic.getStatus()) {
		case 1:
			// 只有提交时间
			track_waitingcheck_line.setVisibility(View.VISIBLE);
			track_checked_line.setVisibility(View.GONE);
			track_done_line.setVisibility(View.GONE);
			topicdetail_subtime_text.setText("时间追踪：" + topic.getTime());
			break;
		case 2:
			// 只有提交时间
			track_waitingcheck_line.setVisibility(View.VISIBLE);
			track_checked_line.setVisibility(View.GONE);
			track_done_line.setVisibility(View.GONE);
			topicdetail_subtime_text.setText("时间追踪：" + topic.getTime());
			break;
		case 3:
			// 有开始处理时间
			track_waitingcheck_line.setVisibility(View.VISIBLE);
			track_checked_line.setVisibility(View.VISIBLE);
			track_done_line.setVisibility(View.GONE);
			topicdetail_subtime_text.setText("时间追踪：" + topic.getTime());
			topicdetail_checktime_text.setText("时间追踪：" + topic.getStime());
			break;
		case 4:
			track_waitingcheck_line.setVisibility(View.VISIBLE);
			track_checked_line.setVisibility(View.VISIBLE);
			track_done_line.setVisibility(View.VISIBLE);
			topicdetail_subtime_text.setText("时间追踪：" + topic.getTime());
			topicdetail_checktime_text.setText("时间追踪：" + topic.getStime());
			topicdetail_donetime_text.setText("时间追踪：" + topic.getCtime());
			break;
		}
	}

	// 设置界面标题栏目
	private void initialTitle() {
		((TextView) findViewById(R.id.middle_title_text)).setText("处理详情");
	}

	// 监听返回按钮
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			SMT_DetailTopicActivity.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}