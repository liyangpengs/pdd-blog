package com.pdd.service;

import org.springframework.stereotype.Service;
import com.pdd.bean.User;

@Service
public interface userService {
	//��¼
	User login(String name,String pwd);
	//ע��
	void regis(User usrt);
}
