package com.pdd.bean;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class comment {
	private Integer cid;
	private String content;
	private User author;
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	private Date publishTime;
	private Integer state;
	private Integer isdelete;
	private List<comments> comments;
	private Integer nid;
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public List<comments> getComments() {
		return comments;
	}
	public void setComments(List<comments> comments) {
		this.comments = comments;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
}
