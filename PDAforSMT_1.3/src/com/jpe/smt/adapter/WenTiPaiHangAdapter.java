package com.jpe.smt.adapter;

import java.util.List;

import com.jpe.smt.R;

import com.jpe.smt.pojo.WenTiLeiXin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author oywf 问题类型的 adapter 数据直接这里填充算了
 */
public class WenTiPaiHangAdapter extends BaseAdapter {
	private List<WenTiLeiXin> beans;
	private LayoutInflater mInflater;

	public WenTiPaiHangAdapter(List<WenTiLeiXin> beans, LayoutInflater inflater) {
		super();
		this.beans = beans;
		this.mInflater = inflater;
	}

	public void setItemList(List<WenTiLeiXin> beans) {
		this.beans = beans;
	}

	@Override
	public int getCount() {
		return beans.size();
	}

	@Override
	public Object getItem(int position) {
		return beans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.whcg_topublic_item_wt,
					null);
			holder.sort = ((TextView) convertView.findViewById(R.id.tv_sort));
			holder.title = ((TextView) convertView.findViewById(R.id.tv_title));
			holder.bili = ((TextView) convertView.findViewById(R.id.tv_bili));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.sort.setText(String.valueOf((beans.get(position).getId()))+".");

		holder.title.setText((beans.get(position).getName() == null ? ""
				: (beans.get(position).getName())));
		holder.bili.setText(beans.get(position).getBili() == null ? "" : beans
				.get(position).getBili());
		return convertView;
	}

	private final class ViewHolder {
		public TextView sort;
		public TextView title;
		public TextView bili;

	}

}
