package com.jpe.smt.photo;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * 多媒体附件打开类
 * 
 * @author oywf
 * 
 */
public class PlayMediaAll {
	/**
	 * 支持jpg png JPEG 3gp mp4 amr mp3
	 * @param activity
	 * @param mediaPath
	 */
	@SuppressLint("DefaultLocale")
	public static void playMedia(Activity activity, String mediaPath) {
		if ((mediaPath != null) && (activity != null)) {
			if ((mediaPath.toLowerCase().endsWith(".jpg")) || (mediaPath.toLowerCase().endsWith(".png"))
					|| (mediaPath.toLowerCase().endsWith(".jpeg"))) {
				File file = new File(mediaPath);
				Intent intent = new Intent("android.intent.action.VIEW");
				intent.setDataAndType(Uri.fromFile(file), "image/*");
				activity.startActivity(intent);
			} else if ((mediaPath.toLowerCase().endsWith(".3gp"))
					|| (mediaPath.toLowerCase().endsWith(".mp4"))) {
				Intent intent = new Intent("android.intent.action.VIEW");
				Uri uri = Uri.parse(mediaPath);
				intent.setDataAndType(uri, "video/*");
				activity.startActivity(intent);
			} else if ((mediaPath.toLowerCase().endsWith(".amr"))
					|| (mediaPath.toLowerCase().endsWith(".mp3"))) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				Uri uri = Uri.parse(mediaPath);
				intent.setDataAndType(uri, "video/*");
				activity.startActivity(intent);
			} else {
				Intent intent = new Intent("android.intent.action.VIEW");
				Uri uri = Uri.parse(mediaPath);
				intent.setDataAndType(uri, "*/*");
				activity.startActivity(intent);
			}
		}
	}
}
