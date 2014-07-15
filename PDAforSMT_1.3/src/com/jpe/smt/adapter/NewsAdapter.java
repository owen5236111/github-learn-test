package com.jpe.smt.adapter;

import java.util.List;

import com.jpe.smt.R;

import com.jpe.smt.imageCache.ImageCache;
import com.jpe.smt.imageCache.loader.ImageFetcher;
import com.jpe.smt.imageCache.loader.ImageWorker;
import com.jpe.smt.pojo.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author oywf 城管新闻的列表adpter
 */
public class NewsAdapter extends BaseAdapter {
	private List<News> beans;
	private LayoutInflater mInflater;
	private ImageWorker mImageLoader;

	public NewsAdapter(List<News> beans, Context context) {
		super();
		this.beans = beans;
		this.mInflater = LayoutInflater.from(context);
		this.mImageLoader = new ImageFetcher(context);
		// 设置缓存
		this.mImageLoader.setImageCache(ImageCache.getInstance(context));
	}

	public void setItemList(List<News> beans) {
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
			convertView = mInflater.inflate(R.layout.listview_item_news, null);
			holder.list_item_img = (ImageView) convertView
					.findViewById(R.id.list_item_img);
			holder.list_item_title = (TextView) convertView
					.findViewById(R.id.list_item_title);
			holder.list_item_desc_title = (TextView) convertView
					.findViewById(R.id.list_item_desc_title);
			holder.list_item_reply = (TextView) convertView
					.findViewById(R.id.list_item_reply);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 图片的uri string地址(这里还需要优化 如果没有图片默认一个图片值)问题 如果url是坏的怎么办？
		// 这里只设置第一幅图片即可(这里要做非空判断 否则空子针)
		if (beans.get(position).getAttachs().size() > 0) {
			mImageLoader.loadImage(beans.get(position).getAttachs().get(0)
					.getImageUrl(), holder.list_item_img,
					R.drawable.default_news);
			// holder.list_item_img.setImageURI(Uri.parse(beans.get(position)
			// .getPic()));

		} else {
			// 没有图片填充
		}
		holder.list_item_title
				.setText(beans.get(position).getTitle() == null ? "" : ((beans
						.get(position).getTitle())));

		holder.list_item_desc_title
				.setText((beans.get(position).getBody() == null ? "" : (beans
						.get(position).getBody())));
		holder.list_item_reply
				.setText(beans.get(position).getTime() == null ? "" : "时间:"
						+ beans.get(position).getTime());
		return convertView;
	}

	private final class ViewHolder {
		// 城管新闻信息缩略图
		public ImageView list_item_img;
		// title
		public TextView list_item_title;
		// desc
		public TextView list_item_desc_title;
		// time
		public TextView list_item_reply;

	}

}
