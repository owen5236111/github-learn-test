package com.jpe.smt.adapter;

import java.util.List;

import com.jpe.smt.R;
import com.jpe.smt.imageCache.ImageCache;
import com.jpe.smt.imageCache.loader.ImageFetcher;
import com.jpe.smt.imageCache.loader.ImageWorker;
import com.jpe.smt.pojo.Topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author oywf 问题处理列表的adpter
 */
public class DealWithAdapter extends BaseAdapter {
	private List<Topic> beans;
	private LayoutInflater mInflater;
	private ImageWorker mImageLoader;

	public DealWithAdapter(List<Topic> beans, Context context) {
		super();
		this.beans = beans;
		this.mInflater = LayoutInflater.from(context);
		this.mImageLoader = new ImageFetcher(context);
		// 设置缓存
		this.mImageLoader.setImageCache(ImageCache.getInstance(context));
	}

	public void setItemList(List<Topic> beans) {
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
			convertView = mInflater.inflate(R.layout.listview_item_dealwith,
					null);
			holder.list_item_img = (ImageView) convertView
					.findViewById(R.id.list_item_img);
			holder.list_item_title = (TextView) convertView
					.findViewById(R.id.list_item_title);
			holder.list_item_desc_title = (TextView) convertView
					.findViewById(R.id.list_item_desc_title);
			holder.list_item_status = (TextView) convertView
					.findViewById(R.id.list_item_status);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 首页列表图片填充 只填充第一张
		if (beans.get(position).getAttachs().size() > 0) {
			mImageLoader.loadImage(beans.get(position).getAttachs().get(0)
					.getImageUrl(), holder.list_item_img,
					R.drawable.default_news);
			// holder.list_item_img.setImageURI(Uri.parse(beans.get(position)
			// .getPic()));
		} else {
			// 没有图片填充
		}
		// XX区—XX投诉
		holder.list_item_title
				.setText(beans.get(position).getArea() == null ? "" : ((beans
						.get(position).getArea() + "-" + beans.get(position)
						.getSubject())));

		holder.list_item_desc_title
				.setText((beans.get(position).getBody() == null ? "" : (beans
						.get(position).getBody())));
		// 案件处理状态 （1、未受理 2、待处理 3、处理中 4、已处理）、
		switch (beans.get(position).getStatus()) {
		case 1:
			holder.list_item_status.setText("未处理");
			break;
		case 2:
			holder.list_item_status.setText("待处理");
			break;
		case 3:
			holder.list_item_status.setText("处理中");
			break;
		case 4:
			holder.list_item_status.setText("已处理");
			break;
		default:
			holder.list_item_status.setText("未受理");
			break;
		}

		return convertView;
	}

	private final class ViewHolder {
		// 城管新闻信息缩略图
		public ImageView list_item_img;
		// title
		public TextView list_item_title;
		// desc
		public TextView list_item_desc_title;
		// status
		public TextView list_item_status;

	}

}
