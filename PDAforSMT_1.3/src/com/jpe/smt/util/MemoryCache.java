package com.jpe.smt.util;

import com.jpe.smt.R;
import com.jpe.smt.pojo.User;

public class MemoryCache {
	private static MemoryCache instanceCache = null;
	private User currentUser = null;
	public int[] faceID = { R.drawable.face01, R.drawable.face02,
			R.drawable.face03, R.drawable.face04, R.drawable.face05,
			R.drawable.face06, R.drawable.face07, R.drawable.face08,
			R.drawable.face09, R.drawable.face10, R.drawable.face11,
			R.drawable.face12, R.drawable.face13, R.drawable.face14,
			R.drawable.face15, R.drawable.face16, R.drawable.face17,
			R.drawable.face18, R.drawable.face19, R.drawable.face20,
			R.drawable.face21, R.drawable.face22, R.drawable.face23,
			R.drawable.face24, R.drawable.face25, R.drawable.face26,
			R.drawable.face27, R.drawable.face28, R.drawable.face29,
			R.drawable.face30 };
	private boolean logined = false;
	public boolean uploading = false;

	public static MemoryCache getInstance() {
		if (instanceCache == null)
			instanceCache = new MemoryCache();
		return instanceCache;
	}

	public User getCurrentUser() {
		return this.currentUser;
	}

	public boolean isLogin() {
		return this.logined;
	}

	public void logout() {
		this.currentUser = null;
		this.logined = false;
	}

	public void setCurrentUser(User paramUser) {
		this.currentUser = paramUser;
		this.logined = true;
	}

	public void setLoginUser(String paramString) {
		this.currentUser = new User();
		this.currentUser.phone = paramString;
		this.logined = true;
	}
}