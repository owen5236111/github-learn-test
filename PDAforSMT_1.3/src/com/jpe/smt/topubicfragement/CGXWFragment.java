package com.jpe.smt.topubicfragement;

import java.util.ArrayList;
import java.util.List;

import com.jpe.smt.R;
import com.jpe.smt.activity.SMT_NewsDetailsActivity;
import com.jpe.smt.adapter.NewsAdapter;
import com.jpe.smt.pojo.Attach;
import com.jpe.smt.pojo.News;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CGXWFragment extends Fragment {
	private NewsAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.whcg_topublic_fragement_xw, null);
		ListView tv_fragment = (ListView) view
				.findViewById(R.id.tv_fragment_xw);
		List<News> beans = initData();
		adapter = new NewsAdapter(beans, getActivity());
		tv_fragment.setAdapter(adapter);
		// 为新闻列表设置点击事件 （主要是跳转到详情页面用）
		tv_fragment.setOnItemClickListener(new newsItemClick());
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

	public ArrayList<News> initData() {
		ArrayList<News> news = new ArrayList<News>();
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

		News xw1 = new News();
		xw1.setId(1 + "");
		xw1.setAttachs(attachs);
		xw1.setTitle("城管新闻标题1");
		xw1.setBody("城管新闻内容描述，新闻简介城管新闻内容描述，" + "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述"
				+ "，新闻简介城管新闻内容描述，新闻简介城管新闻内容描述" + "，新闻简介城管新闻内容描述，新闻简介城管新闻内容描述，"
				+ "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述，"
				+ "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!");
		xw1.setTime("2014-04-22 16:43:15");
		news.add(xw1);

		News xw2 = new News();
		xw2.setId(2 + "");
		xw2.setAttachs(attachs);
		xw2.setTitle("城管新闻标题2");
		xw2.setBody("城管新闻内容描述，新闻简介城管新闻内容描述，"
				+ "新闻简介城管新闻内容描述，新"
				+ "闻简介城管新闻内容描述"
				+ "，新闻简介城管新闻内容描述，新闻简介城管新闻内容描述"
				+ "，新闻简介城管新闻内容描"
				+ "述，新闻简介城管新闻内容描述，"
				+ "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述，"
				+ "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!新闻"
				+ "简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!新闻简介城"
				+ "管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!新闻简介城管新闻内"
				+ "容描述，新闻简介城管新闻内容描述!!!!!!!!!!新闻简介城管新闻内容描述，新闻"
				+ "简介城管新闻内容描述!!!!!!!!!!新闻简介城管新闻内容描述，新闻简介城管新闻内"
				+ "容描述!!!!!!!!!!新闻简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!新闻简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!新闻简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!新闻简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!新闻简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!"
				+ "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!");
		xw2.setTime("2014-04-22 16:43:15");
		news.add(xw2);

		News xw3 = new News();
		xw3.setId(3 + "");
		xw3.setAttachs(attachs);
		xw3.setTitle("城管新闻标题3");
		xw3.setBody("城管新闻内容描述，新闻简介城管新闻内容描述，" + "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述"
				+ "，新闻简介城管新闻内容描述，新闻简介城管新闻内容描述" + "，新闻简介城管新闻内容描述，新闻简介城管新闻内容描述，"
				+ "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述，"
				+ "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!");
		xw3.setTime("2014-04-22 16:43:15");
		news.add(xw3);
		News xw4 = new News();
		xw4.setId(4 + "");
		xw4.setAttachs(attachs);
		xw4.setTitle("城管新闻标题4");
		xw4.setBody("城管新闻内容描述，新闻简介城管新闻内容描述，" + "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述"
				+ "，新闻简介城管新闻内容描述，新闻简介城管新闻内容描述" + "，新闻简介城管新闻内容描述，新闻简介城管新闻内容描述，"
				+ "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述，"
				+ "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!");
		xw4.setTime("2014-04-22 22:43:18");
		news.add(xw4);

		News xw5 = new News();
		xw5.setId(5 + "");
		xw5.setAttachs(attachs);
		xw5.setTitle("城管新闻标题5");
		xw5.setBody("城管新闻内容描述，新闻简介城管新闻内容描述，" + "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述"
				+ "，新闻简介城管新闻内容描述，新闻简介城管新闻内容描述" + "，新闻简介城管新闻内容描述，新闻简介城管新闻内容描述，"
				+ "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述，"
				+ "新闻简介城管新闻内容描述，新闻简介城管新闻内容描述!!!!!!!!!!");
		xw5.setTime("2014-04-22 16:43:15");
		news.add(xw5);
		return news;
	}
}
