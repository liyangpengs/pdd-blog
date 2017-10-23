package com.pdd.service;

import org.springframework.stereotype.Service;
import com.pdd.bean.User;

@Service
public interface userService {
	//µÇÂ¼
	User login(String name,String pwd);
	//×¢²á
	void regis(User usrt);
}
