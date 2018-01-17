package com.pdd.bean;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rlid;
	private String rname;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	public Integer getRlid() {
		return rlid;
	}
	public void setRlid(Integer rlid) {
		this.rlid = rlid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
