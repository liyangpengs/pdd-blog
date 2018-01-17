package com.pdd.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pdd.bean.permission;
import com.pdd.bean.role;

public interface PermissionAndRoleDao {
	//获取角色
	List<role> getRole(@Param("sname")String uname);
	//获取权限
	List<permission> getPermission(@Param("sname")String uname);
	//新增用户时默认权限
	void addRole(@Param("uid")int uid);
	
}
