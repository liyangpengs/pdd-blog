package com.pdd.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.pdd.bean.User;

@Service
public interface userService {
	//��¼
	User login(String name);
	//ע��
	void regis(User usrt);
	//��ȡ��ɫ
	String getRole(String uname);
	//��ȡȨ��
	Set<String> getPermission(String uname);
	//Ĭ�Ͻ�ɫ
	void addRole(int uid);
}
