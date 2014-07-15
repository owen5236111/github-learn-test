package com.jpe.smt.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author oywf 用户上报表
 */
public class Topic implements Serializable {
	// 包含：投诉编号id、投诉人手机号、投诉类型名称、
	// 投诉内容、区域、投诉人经度、投诉人纬度、
	// 投诉人地址街道名称、关联的附件信息id、投诉时间、投诉类型
	// （1、问题上报 2、违建上报 3、市容环境 4、园林绿化）、
	// 案件处理状态 （1、未受理 2、待处理 3、处理中 4、已处理）、
	// 案件处理时间、处理人名称
	private static final long serialVersionUID = 6441478787026783664L;
	// 投诉编号id
	public int id;
	// 投诉内容
	public String body;
	// 处理人名字
	public String cname;
	// 处理时间
	public String ctime;
	public String lat;
	public String lng;
	// 投诉类型
	public String mode;
	// 投诉人手机号
	public String phone;
	// 街道地址
	public String place;
	public int status;
	// 投诉类型名称
	public String subject;
	// 投诉时间
	public String time;
	// // 关联的附件表id
	// public int attachId;
	public ArrayList<Attach> attachs;
	// 区域地址
	public String area;
	// 开始处理时间
	public String stime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	// public int getAttachId() {
	// return attachId;
	// }
	//
	// public void setAttachId(int attachId) {
	// this.attachId = attachId;
	// }

	public String getArea() {
		return area;
	}

	public ArrayList<Attach> getAttachs() {
		return attachs;
	}

	public void setAttachs(ArrayList<Attach> attachs) {
		this.attachs = attachs;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

}