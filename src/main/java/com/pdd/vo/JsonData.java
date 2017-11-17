package com.pdd.vo;

import java.util.Date;

public class JsonData {
	private Integer code;
	private String massage;
	private Object data;
	private long accessTime;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public long getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(long accessTime) {
		this.accessTime = accessTime;
	}
	
	public JsonData() {
		this.code=200;
		this.accessTime=new Date().getTime();
		this.massage="Success";
		this.data=null;
	}
}
