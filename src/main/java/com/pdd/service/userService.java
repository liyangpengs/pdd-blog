package com.pdd.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.pdd.bean.User;

@Service
public interface userService {
	//登录
	User login(String name);
	//注册
	void regis(User usrt);
	//获取角色
	String getRole(String uname);
	//获取权限
	Set<String> getPermission(String uname);
	//默认角色
	void addRole(int uid);
}
