package com.pdd.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.pdd.bean.permission;
import com.pdd.bean.role;

@Service
public interface PermissionAndRoleService {
	//获取角色
	List<role> getRole(String uname);
	//获取权限
	List<permission> getPermission(String uname);
	//新增用户时默认权限
	void addRole(int uid);
}
