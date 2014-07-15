package com.jpe.smt.photo;

import java.util.ArrayList;

import com.jpe.smt.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {
	private ArrayList<String> imgpaths;
	private LayoutInflater mInflater;

	public ItemAdapter(ArrayList<String> imgpaths, Context context) {
		super();
		this.imgpaths = imgpaths;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return imgpaths.size();
	}

	@Override
	public Object getItem(int position) {

		return imgpaths.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.image_list_item, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.tv);
		String[] strs = imgpaths.get(position).split("/");
		String name = strs[strs.length - 1];
		tv.setText(name);
		return convertView;
	}

}
