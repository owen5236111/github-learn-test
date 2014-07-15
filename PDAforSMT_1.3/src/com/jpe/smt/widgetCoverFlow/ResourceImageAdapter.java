package com.jpe.smt.widgetCoverFlow;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpe.smt.R;
import com.jpe.smt.imageCache.ImageCache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class ResourceImageAdapter extends AbstractCoverFlowImageAdapter {
	// 图片路径
	private ArrayList<String> images;

	/** The bitmap map. */
	private final Map<Integer, WeakReference<Bitmap>> bitmapMap = new HashMap<Integer, WeakReference<Bitmap>>();

	private final Context context;

	// 测试用
	/** The Constant IMAGE_RESOURCE_IDS. */
	private static final List<Integer> IMAGE_RESOURCE_IDS = new ArrayList<Integer>(
			20);

	/** The Constant DEFAULT_RESOURCE_LIST. */
	private static final int[] DEFAULT_RESOURCE_LIST = { R.drawable.image01,
			R.drawable.image02, R.drawable.image03, R.drawable.image04,
			R.drawable.image05 };

	public ResourceImageAdapter(final Context context,
			ArrayList<String> imageUrls) {
		super();
		this.context = context;
		this.images = imageUrls;
		// 测试
		setResources(DEFAULT_RESOURCE_LIST);
	}

	@Override
	public synchronized int getCount() {
		return images.size();
	}

	// 测试用
	public final synchronized void setResources(final int[] resourceIds) {
		IMAGE_RESOURCE_IDS.clear();
		for (final int resourceId : resourceIds) {
			IMAGE_RESOURCE_IDS.add(resourceId);
		}
		// 动态刷新activity
		notifyDataSetChanged();
	}

	@Override
	protected Bitmap createBitmap(final int position) {
		final Bitmap bitmap = ((BitmapDrawable) context.getResources()
				.getDrawable(IMAGE_RESOURCE_IDS.get(position))).getBitmap();
		bitmapMap.put(position, new WeakReference<Bitmap>(bitmap));
		return bitmap;
		// 这里的images应该是本地缓存文件的路径了 不是网络路径
		// if (getCount() > 0) {
		// Bitmap bitmap = ImageCache.getInstance(context)
		// .getBitmapFromDiskCache(images.get(position));
		// bitmapMap.put(position, new WeakReference<Bitmap>(bitmap));
		// return bitmap;
		// } else {
		// return null;
		// }
	}

}