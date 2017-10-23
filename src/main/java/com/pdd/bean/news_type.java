package com.pdd.bean;

import java.io.Serializable;
import java.util.Date;

public class news_type implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tid;
	private String 	tname;
	private Integer cansee;
	private Date createtime;
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Integer getCansee() {
		return cansee;
	}
	public void setCansee(Integer cansee) {
		this.cansee = cansee;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
