package com.jpe.smt.dealwithfragement;

import java.util.ArrayList;
import java.util.List;
import com.jpe.smt.R;
import com.jpe.smt.activity.SMT_DetailTopicActivity;
import com.jpe.smt.adapter.DealWithAdapter;
import com.jpe.smt.pojo.Attach;
import com.jpe.smt.pojo.Topic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class YCLFragment extends Fragment {
	private DealWithAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.whcg_dealwith_fragement_sywt,
				null);
		ListView tv_fragment = (ListView) view
				.findViewById(R.id.tv_fragment_wt);
		List<Topic> beans = initData();
		adapter = new DealWithAdapter(beans, getActivity());
		tv_fragment.setAdapter(adapter);
		// 添加点击事件
		tv_fragment.setOnItemClickListener(new TaskItemClick());
		return view;
	}

	private class TaskItemClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// Toast.makeText(getActivity(), "position:" + position, 1).show();
			ListView lview = (ListView) parent;
			// 可以传递复杂的对象 很爽
			Topic topic = (Topic) lview.getItemAtPosition(position);
			Intent intent = new Intent(getActivity(),
					SMT_DetailTopicActivity.class);
			intent.putExtra("topic", topic);
			startActivity(intent);

		}

	}

	// 初始化数据
	public ArrayList<Topic> initData() {
		ArrayList<Topic> topics = new ArrayList<Topic>();
		// 所有类型一样准备一个 突出是所有问题
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

		Topic tp4 = new Topic();
		tp4.setArea("越秀区");
		tp4.setBody("投诉内容，投诉内容投诉内容，投诉内容投诉内容投诉内容投诉内容投诉内容投诉内容投诉内容投诉内容投诉内容");
		tp4.setSubject("违建上报");
		tp4.setTime("2014-04-23 14:39:14");
		tp4.setStime("2014-04-23 14:50:45");
		tp4.setCtime("2014-04-24 10:09:22");
		tp4.setStatus(4);
		tp4.setAttachs(attachs);
		topics.add(tp4);

		return topics;
	}

}
