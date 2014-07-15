package com.jpe.smt.pojo;

import java.io.Serializable;

public class Attach implements Serializable {

	private static final long serialVersionUID = 7771782521315605608L;
	public int id;
	public String imageUrl;
	public int attachId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getAttachId() {
		return attachId;
	}

	public void setAttachId(int attachId) {
		this.attachId = attachId;
	}

}
