package com.pdd.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.pdd.bean.User;

@Service
public interface userService {
	//µÇÂ¼
	User login(String name);
	//×¢²á
	void regis(User usrt);
}
