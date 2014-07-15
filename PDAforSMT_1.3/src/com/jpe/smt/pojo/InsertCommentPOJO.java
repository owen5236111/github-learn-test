package com.jpe.smt.pojo;

public class InsertCommentPOJO {
	public String body = "";
	public String level = "";
	public String score1 = "";
	public String score2 = "";
	public String score3 = "";
	public String time = "";
	public String topicid = "";
	public String userid = "";

	public InsertCommentPOJO() {
	}

	public InsertCommentPOJO(Comment paramComment) {
		this.topicid = paramComment.topicid;
		this.body = paramComment.body;
		this.time = paramComment.time;
		this.level = paramComment.level;
		this.score1 = paramComment.score1;
		this.score2 = paramComment.score2;
		this.score3 = paramComment.score3;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getScore1() {
		return score1;
	}

	public void setScore1(String score1) {
		this.score1 = score1;
	}

	public String getScore2() {
		return score2;
	}

	public void setScore2(String score2) {
		this.score2 = score2;
	}

	public String getScore3() {
		return score3;
	}

	public void setScore3(String score3) {
		this.score3 = score3;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}