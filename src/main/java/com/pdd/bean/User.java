package com.pdd.bean;

import java.io.Serializable;
import java.util.Date;

import redis.clients.jedis.Jedis;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sid;
	private String sname;
	private String pwd;
	private String semail;
	private String snickName;
	private String sphone;
	private Integer isadmin;
	private String registerIp;
	private Date regisdate;
	private String userHead;
	public String getUserHead() {
		return userHead;
	}
	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}
	public Date getRegisdate() {
		return regisdate;
	}
	public void setRegisdate(Date regisdate) {
		this.regisdate = regisdate;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public Integer getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSemail() {
		return semail;
	}
	public void setSemail(String semail) {
		this.semail = semail;
	}
	public String getSnickName() {
		return snickName;
	}
	public void setSnickName(String snickName) {
		this.snickName = snickName;
	}
	public String getSphone() {
		return sphone;
	}
	
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	
	@Override
	public String toString() {
		return "{sid=" + sid + ", sname=" + sname + ", pwd=" + pwd + ", semail=" + semail + ", snickName="
				+ snickName + ", sphone=" + sphone + ", registerIp=" + registerIp + ", regisdate="+regisdate.getTime()+"}";
	}
	public static void main(String[] args) {
		System.out.println(new User());
	}
}
