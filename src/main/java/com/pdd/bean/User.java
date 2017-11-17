package com.pdd.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sid;
	private String sname;
	private String spwd;
	private String semail;
	private String snickName;
	private String sphone;
	private String registerIp;
	private Date regisdate;
	private String userHead;
	private role role;
	private String salt;
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public role getRole() {
		return role;
	}
	public void setRole(role role) {
		this.role = role;
	}
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
		return "{sid=" + sid + ", sname=" + sname + ", pwd=" + spwd + ", semail=" + semail + ", snickName="
				+ snickName + ", sphone=" + sphone + ", registerIp=" + registerIp + ", regisdate="+regisdate.getTime()+"}";
	}
	public String getSpwd() {
		return spwd;
	}
	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}
	public static void main(String[] args) {
		System.out.println(new User());
	}
}
