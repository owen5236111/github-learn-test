package com.jpe.smt.util;

/**
 * button 工具类 全部放在这里
 * 
 */
public class ButtonUtil {
  
	private static long lastClickTime;

	/**
	 * 防止双击(判断是否双击了 是就返回true 不是就是false)
	 */
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 500) {
			return true;
		}
		lastClickTime = time;
		return false;
	}
}
