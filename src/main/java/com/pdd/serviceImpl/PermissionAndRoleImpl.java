package com.pdd.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdd.bean.permission;
import com.pdd.bean.role;
import com.pdd.dao.PermissionAndRoleDao;
import com.pdd.service.PermissionAndRoleService;

@Service
public class PermissionAndRoleImpl implements PermissionAndRoleService{
	
	@Autowired
	private PermissionAndRoleDao pardao;
	
	@Override
	public List<role> getRole(String uname) {
		return pardao.getRole(uname);
	}

	@Override
	public List<permission> getPermission(String uname) {
		return pardao.getPermission(uname);
	}

	@Override
	public void addRole(int uid) {
		pardao.addRole(uid);
	}
	
}
