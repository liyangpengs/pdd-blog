package com.pdd.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.pdd.bean.permission;
import com.pdd.bean.role;

@Service
public interface PermissionAndRoleService {
	//��ȡ��ɫ
	List<role> getRole(String uname);
	//��ȡȨ��
	List<permission> getPermission(String uname);
	//�����û�ʱĬ��Ȩ��
	void addRole(int uid);
}
