package com.pdd.vo;

import java.util.Date;

public class JsonData {
	private Integer code;
	private String massage;
	private Object data;
	private long accessTime;
	private Integer totalPage;
	private Integer pageNum;
	private Integer count;
	private PageInfo pageInfo;
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		if(code<200){
			this.massage="Error";
		}
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
