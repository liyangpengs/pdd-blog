package com.pdd.dao;

import org.apache.ibatis.annotations.Param;

import com.pdd.bean.User;

public interface userDao {
	//��¼
	User login(@Param("sname")String name,@Param("pwd")String pwd);
	//ע��
	void regis(User usrt);
}
