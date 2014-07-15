package com.jpe.smt.topubicfragement;

import java.util.ArrayList;
import java.util.List;

import com.jpe.smt.R;
import com.jpe.smt.adapter.WenTiPaiHangAdapter;
import com.jpe.smt.pojo.WenTiLeiXin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * @author oywf 数据在这里进行加载
 */
public class WTPHFragment extends Fragment {
	private WenTiPaiHangAdapter wtAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.whcg_topublic_fragement, null);
		ListView tv_fragment = (ListView) view.findViewById(R.id.tv_fragment);
		List<WenTiLeiXin> beans = initData();
		wtAdapter = new WenTiPaiHangAdapter(beans, inflater);
		tv_fragment.setAdapter(wtAdapter);
		return view;
	}

	/**
	 * @return
	 * 指定一些死数据 
	 */
	public ArrayList<WenTiLeiXin> initData() {
		ArrayList<WenTiLeiXin> wts = new ArrayList<WenTiLeiXin>();
		WenTiLeiXin wt1 = new WenTiLeiXin();
		wt1.setId(1);
		wt1.setName("市容环境");
		wt1.setBili("58.77%");
		wts.add(wt1);
		WenTiLeiXin wt2 = new WenTiLeiXin();
		wt2.setId(2);
		wt2.setName("房屋建筑");
		wt2.setBili("24.02%");
		wts.add(wt2);
		WenTiLeiXin wt3 = new WenTiLeiXin();
		wt3.setId(3);
		wt3.setName("其他设施");
		wt3.setBili("9.54%");
		wts.add(wt3);
		WenTiLeiXin wt4 = new WenTiLeiXin();
		wt4.setId(4);
		wt4.setName("道路交通设施");
		wt4.setBili("4.43%");
		wts.add(wt4);
		WenTiLeiXin wt5 = new WenTiLeiXin();
		wt5.setId(5);
		wt5.setName("市政公用设施");
		wt5.setBili("2.90%");
		wts.add(wt5);
		WenTiLeiXin wt6 = new WenTiLeiXin();
		wt6.setId(6);
		wt6.setName("园林绿化");
		wt6.setBili("0.34%");
		wts.add(wt6);
		return wts;

	}

}
