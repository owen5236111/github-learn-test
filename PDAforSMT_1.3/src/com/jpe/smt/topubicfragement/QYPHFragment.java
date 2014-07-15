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

public class QYPHFragment extends Fragment {
	private WenTiPaiHangAdapter qyAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.whcg_topublic_fragement_qy, null);
		ListView tv_fragment = (ListView) view
				.findViewById(R.id.tv_fragment_qy);
		List<WenTiLeiXin> beans = initData();
		qyAdapter = new WenTiPaiHangAdapter(beans, inflater);
		tv_fragment.setAdapter(qyAdapter);

		return view;
	}

	/**
	 * @return 指定一些死数据 越秀(原东山、越秀合并)、海珠、天河、白云、黄埔、荔湾(原芳村、荔湾合并)、罗岗、
	 *         南沙、花都、番禺加上从化市、增城市,一共是10区2市
	 */
	public ArrayList<WenTiLeiXin> initData() {
		ArrayList<WenTiLeiXin> wts = new ArrayList<WenTiLeiXin>();
		WenTiLeiXin wt1 = new WenTiLeiXin();
		wt1.setId(1);
		wt1.setName("越秀区");
		wt1.setBili("18.23%");
		wts.add(wt1);
		WenTiLeiXin wt2 = new WenTiLeiXin();
		wt2.setId(2);
		wt2.setName("海珠区");
		wt2.setBili("16.01%");
		wts.add(wt2);
		WenTiLeiXin wt3 = new WenTiLeiXin();
		wt3.setId(3);
		wt3.setName("天河区");
		wt3.setBili("12.95%");
		wts.add(wt3);
		WenTiLeiXin wt4 = new WenTiLeiXin();
		wt4.setId(4);
		wt4.setName("白云区");
		wt4.setBili("12.45%");
		wts.add(wt4);
		WenTiLeiXin wt5 = new WenTiLeiXin();
		wt5.setId(5);
		wt5.setName("萝岗区");
		wt5.setBili("11.41%");
		wts.add(wt5);
		WenTiLeiXin wt6 = new WenTiLeiXin();
		wt6.setId(6);
		wt6.setName("荔湾区");
		wt6.setBili("9.78%");
		wts.add(wt6);

		WenTiLeiXin wt7 = new WenTiLeiXin();
		wt7.setId(7);
		wt7.setName("南沙区");
		wt7.setBili("9.60%");
		wts.add(wt7);
		WenTiLeiXin wt8 = new WenTiLeiXin();
		wt8.setId(8);
		wt8.setName("花都区");
		wt8.setBili("6.54%");
		wts.add(wt8);
		WenTiLeiXin wt9 = new WenTiLeiXin();
		wt9.setId(9);
		wt9.setName("番禺区");
		wt9.setBili("3.03%");
		wts.add(wt9);

		return wts;

	}

}
