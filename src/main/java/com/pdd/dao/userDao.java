package com.pdd.dao;

import java.util.Set;
import org.apache.ibatis.annotations.Param;
import com.pdd.bean.User;

public interface userDao {
	//登录
	User login(@Param("sname")String name);
	//注册
	void regis(User usrt);
	//获取角色
	String getRole(@Param("sname")String uname);
	//获取权限
	Set<String> getPermission(@Param("sname")String uname);
	//新增用户时默认权限
	void addRole(@Param("uid")int uid);
}
