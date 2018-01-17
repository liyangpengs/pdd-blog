package com.pdd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pdd.bean.news;
import com.pdd.bean.permission;
import com.pdd.bean.role;
import com.pdd.service.PermissionAndRoleService;
import com.pdd.vo.JsonData;

@Controller
@Scope("prototype")
public class permissionController {
	
	@Autowired
	private PermissionAndRoleService pars;
	
	@RequiresPermissions("admin:permission:select")
	@RequestMapping(value="/getAllPermission",method=RequestMethod.POST)
	@ResponseBody
	public JsonData getAllPermission(Integer pageSize,Integer pageNum){
		JsonData json=new JsonData();
		PageHelper.startPage(pageNum, pageSize);
		List<permission> plist=pars.getPermission(null);
		PageInfo<permission> pageinfo=new PageInfo<>(plist);
		json.setData(pageinfo.getList());
		json.setPageInfo(new com.pdd.vo.PageInfo(pageinfo.getPages(), pageinfo.getTotal(), pageNum, pageSize));
		return json;
	}
	
	@RequiresPermissions("admin:role:select")
	@RequestMapping(value="/getAllRole",method=RequestMethod.POST)
	@ResponseBody
	public JsonData getAllRole(Integer pageSize,Integer pageNum){
		JsonData json=new JsonData();
		PageHelper.startPage(pageNum, pageSize);
		List<role> plist=pars.getRole(null);
		PageInfo<role> pageinfo=new PageInfo<role>(plist);
		json.setData(pageinfo.getList());
		json.setPageInfo(new com.pdd.vo.PageInfo(pageinfo.getPages(), pageinfo.getTotal(), pageNum, pageSize));
		return json;
	}
	
	@RequiresPermissions("admin:role:update")
	@RequestMapping(value="/edit-Permission",method=RequestMethod.POST)
	@ResponseBody
	public JsonData Edit_permission(String pid,String pname){
		JsonData json=new JsonData();
		return json;
	}
}
