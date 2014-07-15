package com.jpe.smt.pojo;

import com.jpe.smt.util.TopicIDCreator;

/**
 * @author oywf 初始化 案件上报条目（先指定一些默认信息）
 */
public class InsertTopicPOJO {
	public String body = "";
	public String lat = "";
	public String lng = "";
	// 默认上报类型为0, 0是没有定义的
	public int mode = 0;
	public String phone = "";
	// 街道地址
	public String place = "";
	// 区域
	public String region = "";
	// 根据时间生成一个上报的id
	public String topicid = TopicIDCreator.createNewTopicID();
	public int status = 0;

	public InsertTopicPOJO() {
	}

	public InsertTopicPOJO(Topic paramTopic) {
		this.phone = paramTopic.phone;
		this.status = paramTopic.status;
		this.place = paramTopic.place;
		this.lat = paramTopic.lat;
		this.lng = paramTopic.lng;
		this.body = paramTopic.body;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}