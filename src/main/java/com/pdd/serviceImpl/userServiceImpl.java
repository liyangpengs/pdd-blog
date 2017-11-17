package com.pdd.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdd.bean.User;
import com.pdd.dao.userDao;
import com.pdd.service.userService;

@Service
public class userServiceImpl implements userService{
	@Autowired
	private userDao udao;
	
	public User login(String name) {
		return udao.login(name);
	}

	public void regis(User usrt) {
		udao.regis(usrt);
	}

	public String getRole(String uname) {
		return udao.getRole(uname);
	}

	public Set<String> getPermission(String uname) {
		return udao.getPermission(uname);
	}

	public void addRole(int uid) {
		udao.addRole(uid);
	}
}
