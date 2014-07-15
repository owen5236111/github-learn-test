package com.jpe.smt.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class News implements Serializable {

	private static final long serialVersionUID = -2897669529741011809L;
	public String body = "";
	public String hits = "";
	public String id = "";
	public String name = "";
	// 新闻图片附件
	public ArrayList<Attach> attachs;
	public String time = "";
	public String title = "";
	public String type = "";

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Attach> getAttachs() {
		return attachs;
	}

	public void setAttachs(ArrayList<Attach> attachs) {
		this.attachs = attachs;
	}

}