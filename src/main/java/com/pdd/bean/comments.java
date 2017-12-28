package com.pdd.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class comments {
	private Integer cids;
	private User replyAuthor;
	private User bereplyAuthor;
	private String content;
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	private Date publishTime;
	private Integer isdelete;
	private Integer state;
	private int cid;
	public Integer getCids() {
		return cids;
	}
	public void setCids(Integer cids) {
		this.cids = cids;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public User getReplyAuthor() {
		return replyAuthor;
	}
	public void setReplyAuthor(User replyAuthor) {
		this.replyAuthor = replyAuthor;
	}
	public User getBereplyAuthor() {
		return bereplyAuthor;
	}
	public void setBereplyAuthor(User bereplyAuthor) {
		this.bereplyAuthor = bereplyAuthor;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
