package com.jpe.smt.adapter;

import com.jpe.smt.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author oywf
 * 
 */
public class SpinnerAdapter extends BaseAdapter {
	private String[] areas;
	private LayoutInflater mInflater;

	public SpinnerAdapter(String[] areas, Context context) {
		super();
		this.areas = areas;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return areas.length;
	}

	@Override
	public Object getItem(int position) {

		return areas[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.spinner_item, null);
			holder.tv_spinner = ((TextView) convertView
					.findViewById(R.id.tv_spinner));

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_spinner.setText(areas[position]);

		return convertView;
	}

	private final class ViewHolder {
		public TextView tv_spinner;

	}

}
