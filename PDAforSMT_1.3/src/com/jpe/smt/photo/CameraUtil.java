package com.jpe.smt.photo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jpe.smt.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

/**
 * @author oywf
 * 
 */
public class CameraUtil {
	private Activity activity;
	// 当前照片的路径
	private String mCurrentPhotoPath;

	public CameraUtil(Activity activity) {
		super();
		this.activity = activity;
	}

	/**
	 * 获取SD卡路径，创建文件夹
	 * 
	 * @return file
	 */
	private File getAlbumDir() {
		File storageDir = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			String path = Environment.getExternalStorageDirectory() + "/"
					+ getAlbumName();
			storageDir = new File(path);

			if (storageDir != null) {
				if (!storageDir.mkdirs()) {
					if (!storageDir.exists()) {
						Log.d("CameraTemp", "failed to create directory");
						return null;
					}
				}
			}

		} else {
			Log.v("storage", "External storage is not mounted READ/WRITE.");
		}

		return storageDir;
	}

	/**
	 * 存放附件路劲
	 * 
	 * @return
	 */
	private String getAlbumName() {
		return activity.getString(R.string.imgpath);
	}

	/**
	 * 拍照
	 * 
	 * @param activity
	 */
	public void cameraInit(Activity activity) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		File f = null;

		try {
			String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			File albumF = getAlbumDir();
			f = File.createTempFile(timeStamp, ".jpg", albumF);
			// 这里出现了bug mCurrentPhotoPath只是临时文件路径 要是没拍照 就没相片了！(影响应该不大 有的手机是没)
			mCurrentPhotoPath = f.getAbsolutePath();
			// 将拍照的路径保存到sharedpreference
			SharedPreferences preferences = activity.getSharedPreferences(
					"ppp", Context.MODE_PRIVATE);
			Editor editor = preferences.edit();
			editor.putString("m", mCurrentPhotoPath);
			editor.commit();
			// 文件保存的位置
			takePictureIntent
					.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		} catch (IOException e) {
			e.printStackTrace();
			f = null;
			mCurrentPhotoPath = null;
		}
		// 102相机调用请求码
		activity.startActivityForResult(takePictureIntent, 102);
	}

	/**
	 * 根据路径删除图片
	 * 
	 * @param path
	 */
	public static void deleteTempFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}
}
