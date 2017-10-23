package com.pdd.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdd.bean.User;
import com.pdd.dao.userDao;
import com.pdd.service.userService;

@Service
public class userServiceImpl implements userService{
	@Autowired
	private userDao udao;
	
	public User login(String name, String pwd) {
		return udao.login(name, pwd);
	}

	public void regis(User usrt) {
		udao.regis(usrt);
	}
}
