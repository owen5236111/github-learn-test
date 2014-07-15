package com.jpe.smt.topubicfragement;

import java.util.ArrayList;
import java.util.List;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.jpe.smt.R;
import com.jpe.smt.activity.SMT_NewsDetailsActivity;
import com.jpe.smt.adapter.NewsAdapter;
import com.jpe.smt.pojo.Attach;
import com.jpe.smt.pojo.News;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author oywf
 * 正式开发的时候 热门信息和城管新闻都会被屏蔽，目前不修改
 */
public class RMXXFragment extends Fragment {
	private NewsAdapter adapter;
	// 总的数据展示
	private ArrayList<News> news = new ArrayList<News>();
	/**
	 * 刷新模式， 包括{ 1.DISABLED(0x0) 禁止通过手势和手动执行 2.PULL_FROM_START(0x1) 可执行下拉刷新
	 * 3.PULL_FROM_END(0x2) 可执行上拉刷新 3.BOTH(0x3) 上下都可执行
	 * 4.MANUAL_REFRESH_ONLY(0x4) 禁止通过手势执行，但可以手动设置 }
	 */
	/** 这里我们来判断是下拉还是上拉 */
	private Mode CurrentMode = Mode.PULL_FROM_END;
	// refashlistview
	private PullToRefreshListView mPullRefreshListView;

	private List<News> beans = initData();
	// 测试刷新用的id(可以删了)
	private int count = 11;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.whcg_topublic_fragement_rm, null);
		// 以下要变成自定义的下拉刷新列表
		// ListView tv_fragment = (ListView) view
		// .findViewById(R.id.tv_fragment_rm);
		mPullRefreshListView = (PullToRefreshListView) view
				.findViewById(R.id.tv_fragment_rm);

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
		adapter = new NewsAdapter(beans, getActivity());
		mPullRefreshListView.setAdapter(adapter);

		// 为新闻列表设置点击事件 （主要是跳转到详情页面用）
		mPullRefreshListView.setOnItemClickListener(new newsItemClick());
		return view;
	}

	private class newsItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// Toast.makeText(getActivity(), "position:" + position, 1).show();
			ListView lview = (ListView) parent;
			News cg_new = (News) lview.getItemAtPosition(position);
			Intent intent = new Intent(getActivity(),
					SMT_NewsDetailsActivity.class);
			intent.putExtra("cg_new", cg_new);
			startActivity(intent);

		}

	}

	// 后台执行数据异步请求 这里传入的应该是 url地址 然后服务端返回string 这里模拟就直接返回好的list<News>

	private class GetDataTask extends AsyncTask<Void, Void, List<News>> {
		// 这里去请求数据
		@Override
		protected List<News> doInBackground(Void... params) {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
			}
			return beans;
		}

		// 这里对请求返回的数据结果进行封装
		@Override
		protected void onPostExecute(List<News> result) {
			ArrayList<Attach> attachs = new ArrayList<Attach>();
			Attach at1 = new Attach();
			at1.setAttachId(1);
			at1.setId(1);
			at1.setImageUrl("http://e.hiphotos.bdimg.com/wisegame/pic/item/3166d0160924ab18b3bc837b37fae6cd7a890bd7.jpg");
			attachs.add(at1);
			Attach at2 = new Attach();
			at2.setAttachId(1);
			at2.setId(2);
			at2.setImageUrl("http://h.hiphotos.bdimg.com/wisegame/pic/item/5adbb6fd5266d016901d4aa5962bd40735fa3533.jpg");
			attachs.add(at2);
			Attach at3 = new Attach();
			at3.setAttachId(1);
			at3.setId(3);
			at3.setImageUrl("http://g.hiphotos.bdimg.com/wisegame/pic/item/a81349540923dd54b0d5ca37d309b3de9d8248cf.jpg");
			attachs.add(at3);
			// 这里是提供给我们比较MODE的方法，返回0则表示相当
			if (CurrentMode.compareTo(Mode.PULL_FROM_START) == 0) {

				News xw = new News();
				xw.setId(count + "");
				xw.setAttachs(attachs);
				xw.setTitle("下拉刷新" + count);
				xw.setBody("热门信息内容热门信息内容热门信息" + "内容热门信息内容热门信息内容热门信息内"
						+ "容热门信息内容热门信息内容热门信息内容热门" + "信息内容热门信息内容热门信息内容热门信息内容热"
						+ "门信息内容热门信息内容热门信息内容热门信息内容热"
						+ "门信息内容热门信息内容热门信息内容热门信息内容热" + "门信息内容热门信息内容热门"
						+ "信息内容热门信息内容热门信息内容热门信息内容" + "热门信息内容热门信息内容热门信息内"
						+ "容热门信息内容热门信息内容热门信息内容热门" + "信息内容热门信息内容热门信息内容热门信息内"
						+ "容热门信息内容热门信息内容热门信息内容热门信息"
						+ "内容热门信息内容热门信息内容热门信息内容热门信息内"
						+ "容热门信息内容热门信息内容热门信息内容热门信息" + "内容热门信"
						+ "息内容热门信息内容热门信息内容热门信息内容热门" + "信息内容热门信息内容");
				xw.setTime("2014-04-24 11:45:" + count);
				count++;
				beans.add(xw);
			} else {
				News xw = new News();
				xw.setId(count + "");
				xw.setAttachs(attachs);
				xw.setTitle("上拉刷新" + count);
				xw.setBody("热门信息内容热门信息内容热门信息" + "内容热门信息内容热门信息内容热门信息内"
						+ "容热门信息内容热门信息内容热门信息内容热门" + "信息内容热门信息内容热门信息内容热门信息内容热"
						+ "门信息内容热门信息内容热门信息内容热门信息内容热"
						+ "门信息内容热门信息内容热门信息内容热门信息内容热" + "门信息内容热门信息内容热门"
						+ "信息内容热门信息内容热门信息内容热门信息内容" + "热门信息内容热门信息内容热门信息内"
						+ "容热门信息内容热门信息内容热门信息内容热门" + "信息内容热门信息内容热门信息内容热门信息内"
						+ "容热门信息内容热门信息内容热门信息内容热门信息"
						+ "内容热门信息内容热门信息内容热门信息内容热门信息内"
						+ "容热门信息内容热门信息内容热门信息内容热门信息" + "内容热门信"
						+ "息内容热门信息内容热门信息内容热门信息内容热门" + "信息内容热门信息内容");
				xw.setTime("2014-04-24 11:45:" + count);
				count++;
				beans.add(xw);
			}

			adapter.notifyDataSetChanged();

			// 当数据加载完成，需要调用onRefreshComplete.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	// 模拟第一次请求的数据
	public ArrayList<News> initData() {
		// 测试用 附件就都用同一个算了
		ArrayList<Attach> attachs = new ArrayList<Attach>();
		Attach at1 = new Attach();
		at1.setAttachId(1);
		at1.setId(1);
		at1.setImageUrl("http://e.hiphotos.bdimg.com/wisegame/pic/item/3166d0160924ab18b3bc837b37fae6cd7a890bd7.jpg");
		attachs.add(at1);
		Attach at2 = new Attach();
		at2.setAttachId(1);
		at2.setId(2);
		at2.setImageUrl("http://h.hiphotos.bdimg.com/wisegame/pic/item/5adbb6fd5266d016901d4aa5962bd40735fa3533.jpg");
		attachs.add(at2);
		Attach at3 = new Attach();
		at3.setAttachId(1);
		at3.setId(3);
		at3.setImageUrl("http://g.hiphotos.bdimg.com/wisegame/pic/item/a81349540923dd54b0d5ca37d309b3de9d8248cf.jpg");
		attachs.add(at3);

		for (int i = 1; i < 11; i++) {
			News xw1 = new News();
			xw1.setId(i + "");
			xw1.setAttachs(attachs);
			xw1.setTitle("热门信息标题" + i);
			xw1.setBody("热门信息内容热门信息内容热门信息" + "内容热门信息内容热门信息内容热门信息内"
					+ "容热门信息内容热门信息内容热门信息内容热门" + "信息内容热门信息内容热门信息内容热门信息内容热"
					+ "门信息内容热门信息内容热门信息内容热门信息内容热" + "门信息内容热门信息内容热门信息内容热门信息内容热"
					+ "门信息内容热门信息内容热门" + "信息内容热门信息内容热门信息内容热门信息内容"
					+ "热门信息内容热门信息内容热门信息内" + "容热门信息内容热门信息内容热门信息内容热门"
					+ "信息内容热门信息内容热门信息内容热门信息内" + "容热门信息内容热门信息内容热门信息内容热门信息"
					+ "内容热门信息内容热门信息内容热门信息内容热门信息内" + "容热门信息内容热门信息内容热门信息内容热门信息"
					+ "内容热门信" + "息内容热门信息内容热门信息内容热门信息内容热门" + "信息内容热门信息内容");
			xw1.setTime("2014-04-24 11:45:2" + i);
			news.add(xw1);
		}

		return news;
	}
}
