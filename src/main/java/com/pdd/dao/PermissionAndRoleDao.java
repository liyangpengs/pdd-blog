package com.pdd.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pdd.bean.permission;
import com.pdd.bean.role;

public interface PermissionAndRoleDao {
	//��ȡ��ɫ
	List<role> getRole(@Param("sname")String uname);
	//��ȡȨ��
	List<permission> getPermission(@Param("sname")String uname);
	//�����û�ʱĬ��Ȩ��
	void addRole(@Param("uid")int uid);
	
}
