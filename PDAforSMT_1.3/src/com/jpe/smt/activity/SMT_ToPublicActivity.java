package com.jpe.smt.activity;

import java.util.ArrayList;

import com.jpe.smt.R;
import com.jpe.smt.dialogs.LogoutShowDialog;
import com.jpe.smt.topubicfragement.CGXWFragment;
import com.jpe.smt.topubicfragement.QYPHFragment;
import com.jpe.smt.topubicfragement.RMXXFragment;
import com.jpe.smt.topubicfragement.RXSMFragment;
import com.jpe.smt.topubicfragement.WTPHFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class SMT_ToPublicActivity extends FragmentActivity implements
		OnClickListener {

	private HorizontalScrollView mHorizontalScrollView;
	private LinearLayout mLinearLayout;
	private ViewPager pager;
	private ImageView mImageView;
	private int mScreenWidth;
	private int item_width;
	private int endPosition;
	private int beginPosition;
	private int currentFragmentIndex;
	private boolean isEnd;
	// private TextView view, view1, view2, view3, view4;
	private TextView view, view1, view2;
	// 用于改变view标题中字体颜色的数组
	private TextView[] viewList;

	private ArrayList<Fragment> fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.whcg_topublic_view);
		// 设置上方标题
		initialTitle();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv_view);
		mLinearLayout = (LinearLayout) findViewById(R.id.hsv_content);
		mImageView = (ImageView) findViewById(R.id.img1);
		item_width = (int) ((mScreenWidth / 4.0 + 0.5f));
		mImageView.getLayoutParams().width = item_width;

		pager = (ViewPager) findViewById(R.id.pager);
		// 初始化导航
		initNav();
		// 初始化viewPager
		initViewPager();
	}

	/**
	 * 在这里添加不同的列表显示内容
	 */
	private void initViewPager() {

		fragments = new ArrayList<Fragment>();
		// 设置问题排行fragement
		WTPHFragment fragment = new WTPHFragment();
		fragments.add(fragment);
		// 设置区域排行
		QYPHFragment fragment_qy = new QYPHFragment();
		fragments.add(fragment_qy);
		// 设置热心市民
		RXSMFragment framgment_sm = new RXSMFragment();
		fragments.add(framgment_sm);
		/*
		 * // 设置热门信息 RMXXFragment framgment_rm = new RMXXFragment();
		 * fragments.add(framgment_rm); // 设置城管新闻 CGXWFragment framgment_xw =
		 * new CGXWFragment(); fragments.add(framgment_xw);
		 */
		MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(
				getSupportFragmentManager(), fragments);
		pager.setAdapter(fragmentPagerAdapter);
		fragmentPagerAdapter.setFragments(fragments);
		pager.setOnPageChangeListener(new MyOnPageChangeListener());
		pager.setCurrentItem(0);
	}

	private void initNav() {

		RelativeLayout layout = new RelativeLayout(this);
		view = new TextView(this);
		view.setText("问题类型");
		view.setTextColor(Color.RED);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		layout.addView(view, params);
		mLinearLayout.addView(layout, (int) (mScreenWidth / 4 + 0.5f), 50);
		layout.setOnClickListener(this);
		layout.setTag(0);

		RelativeLayout layout1 = new RelativeLayout(this);
		view1 = new TextView(this);
		view1.setText("区域排行");
		view1.setTextColor(Color.BLACK);

		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params1.addRule(RelativeLayout.CENTER_IN_PARENT);
		layout1.addView(view1, params1);
		mLinearLayout.addView(layout1, (int) (mScreenWidth / 4 + 0.5f), 50);
		layout1.setOnClickListener(this);
		layout1.setTag(1);

		RelativeLayout layout2 = new RelativeLayout(this);
		view2 = new TextView(this);
		view2.setText("热心市民");
		view2.setTextColor(Color.BLACK);
		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params2.addRule(RelativeLayout.CENTER_IN_PARENT);
		layout2.addView(view2, params2);
		mLinearLayout.addView(layout2, (int) (mScreenWidth / 4 + 0.5f), 50);
		layout2.setOnClickListener(this);
		layout2.setTag(2);
		/*
		 * RelativeLayout layout3 = new RelativeLayout(this); view3 = new
		 * TextView(this); view3.setText("热门信息");
		 * view3.setTextColor(Color.BLACK); RelativeLayout.LayoutParams params3
		 * = new RelativeLayout.LayoutParams( LayoutParams.WRAP_CONTENT,
		 * LayoutParams.WRAP_CONTENT);
		 * params3.addRule(RelativeLayout.CENTER_IN_PARENT);
		 * layout3.addView(view3, params3); mLinearLayout.addView(layout3, (int)
		 * (mScreenWidth / 4 + 0.5f), 50); layout3.setOnClickListener(this);
		 * layout3.setTag(3);
		 * 
		 * RelativeLayout layout4 = new RelativeLayout(this); view4 = new
		 * TextView(this); view4.setText("城管新闻");
		 * view4.setTextColor(Color.BLACK); RelativeLayout.LayoutParams params4
		 * = new RelativeLayout.LayoutParams( LayoutParams.WRAP_CONTENT,
		 * LayoutParams.WRAP_CONTENT);
		 * params4.addRule(RelativeLayout.CENTER_IN_PARENT);
		 * layout4.addView(view4, params4); mLinearLayout.addView(layout4, (int)
		 * (mScreenWidth / 4 + 0.5f), 50); layout4.setOnClickListener(this);
		 * layout4.setTag(4);
		 */
		TextView[] cviewList = { view, view1, view2 };
		viewList = cviewList;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> fragments;
		private FragmentManager fm;

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			this.fm = fm;
		}

		public MyFragmentPagerAdapter(FragmentManager fm,
				ArrayList<Fragment> fragments) {
			super(fm);
			this.fm = fm;
			this.fragments = fragments;
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		public void setFragments(ArrayList<Fragment> fragments) {
			if (this.fragments != null) {
				FragmentTransaction ft = fm.beginTransaction();
				for (Fragment f : this.fragments) {
					ft.remove(f);
				}
				ft.commit();
				ft = null;
				fm.executePendingTransactions();
			}
			this.fragments = fragments;
			notifyDataSetChanged();
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			Object obj = super.instantiateItem(container, position);
			return obj;
		}

	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(final int position) {
			Animation animation = new TranslateAnimation(endPosition, position
					* item_width, 0, 0);

			beginPosition = position * item_width;

			currentFragmentIndex = position;
			if (animation != null) {
				animation.setFillAfter(true);
				animation.setDuration(0);
				mImageView.startAnimation(animation);
				mHorizontalScrollView.smoothScrollTo((currentFragmentIndex - 1)
						* item_width, 0);
				// 将所有标题先变成黑色
				view.setTextColor(Color.BLACK);
				view1.setTextColor(Color.BLACK);
				view2.setTextColor(Color.BLACK);
//				view3.setTextColor(Color.BLACK);
//				view4.setTextColor(Color.BLACK);

				// 滑动的时候将当前位置标题变成红色
				viewList[position].setTextColor(Color.RED);

			}
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			if (!isEnd) {
				if (currentFragmentIndex == position) {
					endPosition = item_width * currentFragmentIndex
							+ (int) (item_width * positionOffset);
				}
				if (currentFragmentIndex == position + 1) {
					endPosition = item_width * currentFragmentIndex
							- (int) (item_width * (1 - positionOffset));
				}

				Animation mAnimation = new TranslateAnimation(beginPosition,
						endPosition, 0, 0);
				mAnimation.setFillAfter(true);
				mAnimation.setDuration(0);
				mImageView.startAnimation(mAnimation);
				mHorizontalScrollView.invalidate();
				beginPosition = endPosition;
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_DRAGGING) {
				isEnd = false;
			} else if (state == ViewPager.SCROLL_STATE_SETTLING) {
				isEnd = true;
				beginPosition = currentFragmentIndex * item_width;
				if (pager.getCurrentItem() == currentFragmentIndex) {
					// 未跳入下一个页面
					mImageView.clearAnimation();
					Animation animation = null;
					// 恢复位置
					animation = new TranslateAnimation(endPosition,
							currentFragmentIndex * item_width, 0, 0);
					animation.setFillAfter(true);
					animation.setDuration(1);
					mImageView.startAnimation(animation);
					mHorizontalScrollView.invalidate();
					endPosition = currentFragmentIndex * item_width;
				}
			}
		}

	}

	@Override
	public void onClick(View v) {
		pager.setCurrentItem((Integer) v.getTag());
	}

	// 设置界面标题栏目
	private void initialTitle() {
		((TextView) findViewById(R.id.middle_title_text))
				.setText(getResources().getString(R.string.gongshi));
		// 以下是以前天气请求用了的（以后可以参考的设置）
		// if (!GetWeatherService.getInstance().updated)
		// GetWeatherService.getInstance().UpdateWeatherString(this);
		// String str = getSharedPreferences("weather", 0).getString("weather",
		// getResources().getString(2131230748));
		// ((TextView)findViewById(2131099674)).setText(str);
		Button moreinfo = ((Button) findViewById(R.id.personal_info_bt));
		moreinfo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Intent localIntent = new Intent(SMT_ToPublicActivity.this,
						SMT_MoreActivity.class);
				SMT_ToPublicActivity.this.startActivity(localIntent);
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			LogoutShowDialog.showMessageDialog(SMT_ToPublicActivity.this,
					"您确定要退出吗？", "温馨提示");
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}