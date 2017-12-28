package com.pdd.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class news implements Serializable{
	
	private Integer nid;
	private String title;
	private User author;
	private Integer visible;
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	private Date publishtime;
	private String url;
	private String imgUrl;
	private news_type news_type;
	private int istop;
	private String keyword;
	private String descs;
	private int seecount;
	public Integer getVisible() {
		return visible;
	}
	public void setVisible(Integer visible) {
		this.visible = visible;
	}
	public int getSeecount() {
		return seecount;
	}
	public void setSeecount(int seecount) {
		this.seecount = seecount;
	}
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public int getIstop() {
		return istop;
	}
	public void setIstop(int istop) {
		this.istop = istop;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public news_type getNews_type() {
		return news_type;
	}
	public void setNews_type(news_type news_type) {
		this.news_type = news_type;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
