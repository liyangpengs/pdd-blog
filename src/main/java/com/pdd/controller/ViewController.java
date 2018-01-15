package com.pdd.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class ViewController {
	
	/**
	 * 视图权限验证
	 * @param path2
	 * @return
	 */
	@RequestMapping("/view/admin/{path2}")
	public String getAdminVIew(@PathVariable("path2")String path2){
		if(!path2.equals("login")&&!path2.equals("unauthorized")){
			//验证权限
			SecurityUtils.getSubject().checkPermission("admin:"+path2+":html");
		}
		return "admin/"+path2;
	}
	
	@RequestMapping("/view/Reception/{path2}")
	public String getReceptionVIew(@PathVariable("path2")String path2){
		return "Reception/"+path2;
	}	
	
	@RequestMapping("/admin")
	public String adminView(){
		return "admin/login";
	}
}
