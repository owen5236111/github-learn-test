package com.jpe.smt.topubicfragement;

import java.util.ArrayList;
import java.util.List;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.jpe.smt.R;
import com.jpe.smt.adapter.WenTiPaiHangAdapter;
import com.jpe.smt.pojo.WenTiLeiXin;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * @author oywf 热心市民 应该人数会比较多 这里先添加上拉刷新功能
 */
public class RXSMFragment extends Fragment {
	private WenTiPaiHangAdapter smAdapter;
	/**
	 * 刷新模式， 包括{ 1.DISABLED(0x0) 禁止通过手势和手动执行 2.PULL_FROM_START(0x1) 可执行下拉刷新
	 * 3.PULL_FROM_END(0x2) 可执行上拉刷新 3.BOTH(0x3) 上下都可执行
	 * 4.MANUAL_REFRESH_ONLY(0x4) 禁止通过手势执行，但可以手动设置 }
	 */
	/** 这里我们来判断是下拉还是上拉 */
	private Mode CurrentMode = Mode.PULL_FROM_END;
	// refashlistview
	private PullToRefreshListView mPullRefreshListView;

	private List<WenTiLeiXin> beans = initData();

	// 测试刷新用的id(可以删了)
	private int count = 16;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.whcg_topublic_fragement_sm, null);
		// ListView tv_fragment = (ListView) view
		// .findViewById(R.id.tv_fragment_sm);

		mPullRefreshListView = (PullToRefreshListView) view
				.findViewById(R.id.tv_fragment_sm);

		// 这里添加上拉刷新的操作
		// 设置刷新方式 是上拉还是下拉
		mPullRefreshListView.setMode(CurrentMode);
		// 监听列表被刷新时事件.
		mPullRefreshListView
				.setOnRefreshListener(new OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {

						int flags = DateUtils.FORMAT_SHOW_TIME
								| DateUtils.FORMAT_SHOW_DATE
								| DateUtils.FORMAT_ABBREV_ALL;

						String label = DateUtils.formatDateTime(getActivity(),
								System.currentTimeMillis(), flags);
						// 更新最后刷新时间
						refreshView.getLoadingLayoutProxy()
								.setLastUpdatedLabel(label);

						// 执行加载更多数据任务.task调用必须在UI线程中
						new GetDataTask().execute();
					}
				});
		smAdapter = new WenTiPaiHangAdapter(beans, inflater);
		mPullRefreshListView.setAdapter(smAdapter);
		return view;
	}

	// 后台执行数据异步请求 这里传入的应该是 url地址 然后服务端返回string 这里模拟就直接返回好的list<News>

	private class GetDataTask extends AsyncTask<Void, Void, List<WenTiLeiXin>> {
		// 这里去请求数据
		@Override
		protected List<WenTiLeiXin> doInBackground(Void... params) {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
			}
			return beans;
		}

		// 这里对请求返回的数据结果进行封装
		@Override
		protected void onPostExecute(List<WenTiLeiXin> result) {

			// 这里是提供给我们比较MODE的方法，返回0则表示相当
			if (CurrentMode.compareTo(Mode.PULL_FROM_START) == 0) {
				WenTiLeiXin wt = new WenTiLeiXin();
				wt.setId(count);
				wt.setName("155****23" + count);
				wt.setBili(count + "");
				count++;
				beans.add(wt);
			} else {
				WenTiLeiXin wt = new WenTiLeiXin();
				wt.setId(count);
				wt.setName("155****23" + count);
				wt.setBili(count + "");
				count++;
				beans.add(wt);
			}

			smAdapter.notifyDataSetChanged();

			// 当数据加载完成，需要调用onRefreshComplete.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	/**
	 * @return 指定一些死数据
	 */
	public ArrayList<WenTiLeiXin> initData() {
		ArrayList<WenTiLeiXin> wts = new ArrayList<WenTiLeiXin>();
		WenTiLeiXin wt1 = new WenTiLeiXin();
		wt1.setId(1);
		wt1.setName("155****2319");
		wt1.setBili("108");
		wts.add(wt1);
		WenTiLeiXin wt2 = new WenTiLeiXin();
		wt2.setId(2);
		wt2.setName("186****1120");
		wt2.setBili("102");
		wts.add(wt2);
		WenTiLeiXin wt3 = new WenTiLeiXin();
		wt3.setId(3);
		wt3.setName("133****2321");
		wt3.setBili("97");
		wts.add(wt3);
		WenTiLeiXin wt4 = new WenTiLeiXin();
		wt4.setId(4);
		wt4.setName("159****2324");
		wt4.setBili("94");
		wts.add(wt4);
		WenTiLeiXin wt5 = new WenTiLeiXin();
		wt5.setId(5);
		wt5.setName("155****9980");
		wt5.setBili("88");
		wts.add(wt5);
		WenTiLeiXin wt6 = new WenTiLeiXin();
		wt6.setId(6);
		wt6.setName("155****3234");
		wt6.setBili("80");
		wts.add(wt6);

		WenTiLeiXin wt7 = new WenTiLeiXin();
		wt7.setId(7);
		wt7.setName("180****4241");
		wt7.setBili("79");
		wts.add(wt7);
		WenTiLeiXin wt8 = new WenTiLeiXin();
		wt8.setId(8);
		wt8.setName("134****7651");
		wt8.setBili("67");
		wts.add(wt8);
		WenTiLeiXin wt9 = new WenTiLeiXin();
		wt9.setId(9);
		wt9.setName("159****8834");
		wt9.setBili("60");
		wts.add(wt9);
		WenTiLeiXin wt10 = new WenTiLeiXin();
		wt10.setId(10);
		wt10.setName("130****4211");
		wt10.setBili("56");
		wts.add(wt10);
		WenTiLeiXin wt11 = new WenTiLeiXin();
		wt11.setId(11);
		wt11.setName("133****2333");
		wt11.setBili("44");
		wts.add(wt11);
		WenTiLeiXin wt12 = new WenTiLeiXin();
		wt12.setId(12);
		wt12.setName("156****7766");
		wt12.setBili("35");
		wts.add(wt12);
		WenTiLeiXin wt13 = new WenTiLeiXin();
		wt13.setId(13);
		wt13.setName("189****6226");
		wt13.setBili("35");
		wts.add(wt13);
		WenTiLeiXin wt14 = new WenTiLeiXin();
		wt14.setId(14);
		wt14.setName("158****3421");
		wt14.setBili("32");
		wts.add(wt14);
		WenTiLeiXin wt15 = new WenTiLeiXin();
		wt15.setId(15);
		wt15.setName("180****8463");
		wt15.setBili("10");
		wts.add(wt15);
		return wts;

	}

}
